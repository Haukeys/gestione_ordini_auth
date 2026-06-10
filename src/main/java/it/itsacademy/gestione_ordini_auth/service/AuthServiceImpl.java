package it.itsacademy.gestione_ordini_auth.service;

import it.itsacademy.gestione_ordini_auth.dto.AuthUserDTO;
import it.itsacademy.gestione_ordini_auth.dto.LoginDTO;
import it.itsacademy.gestione_ordini_auth.dto.SignUpDTO;
import it.itsacademy.gestione_ordini_auth.dto.AuthUserUpdateDTO;
import it.itsacademy.gestione_ordini_auth.entity.AuthUser;
import it.itsacademy.gestione_ordini_auth.entity.Role;
import it.itsacademy.gestione_ordini_auth.entity.Roles;
import it.itsacademy.gestione_ordini_auth.mapper.AuthUserMapper;
import it.itsacademy.gestione_ordini_auth.repository.AuthUserRepository;
import it.itsacademy.gestione_ordini_auth.repository.RoleRepository;
import it.itsacademy.gestione_ordini_auth.utility.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthUserRepository authUserRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthUserMapper authUserMapper;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
//NE PAS OUBLIER COMMENT SONT STRUCTURER CHAQUE ELEMENT A INSERER CAR ÇA PEUT DONNER DES ERREUR DU STYLE 401-403
    @Override
    @Transactional
    public AuthUserDTO register(SignUpDTO signupDTO) {
        // 1. Vérifier si l'utilisateur existe déjà
        if (authUserRepository.existsByUsername(signupDTO.getUsername())) {
            throw new IllegalArgumentException("Username già in uso!");
        }

        // 2. Convertir le SignupDTO en entité AuthUser via ton nouveau Mapper
        AuthUser authUser = authUserMapper.toAuthUserSignUp(signupDTO);

        // 3. Encoder le mot de passe en BCrypt de manière sécurisée
        authUser.setPassword(passwordEncoder.encode(signupDTO.getPassword()));
        authUser.setIsAttivo(true); // Compte actif par défaut

        // 4. Assigner le rôle par défaut (USER) de type entité Role
        Role defaultRole = roleRepository.findByRoles(Roles.USER)
                .orElseThrow(() -> new RuntimeException("Errore: Ruolo USER non trovato nel database!"));
        authUser.setRoles(Set.of(defaultRole));

        // 5. Sauvegarder dans MySQL
        AuthUser savedUser = authUserRepository.save(authUser);

        // 6. Retourner le DTO de réponse propre
        return authUserMapper.toAuthUserDTO(savedUser);
    }

    @Override
    @Transactional(readOnly = true)
    public AuthUserDTO login(LoginDTO loginDTO) {
        // Authentifier l'utilisateur via Spring Security
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword())
        );

        // Récupérer l'utilisateur depuis la base de données pour avoir accès à son ID (UUID)
        AuthUser user = authUserRepository.findByUsername(loginDTO.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Utente non trovato dopo l'autenticazione"));

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        // Préparer les claims spécifiques requis par ta Gateway (id et rôles)
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("userId", user.getIdUtente().toString()); // Ajout de l'UUID sous forme de chaîne

        String rolesString = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(",")); // Exemple : "ROLE_USER"
        extraClaims.put("roles", rolesString);

        // Générer le jeton JWT crypté avec la clé secrète
        String token = jwtService.generateToken(extraClaims, userDetails);

        // Convertir l'entité en AuthUserDTO et y injecter le token généré
        AuthUserDTO authUserDTO = authUserMapper.toAuthUserDTO(user);
        authUserDTO.setToken(token);

        return authUserDTO;
    }

    @Override
    @Transactional
    public AuthUserDTO updateProfile(UUID idUtente, AuthUserUpdateDTO authUserUpdateDTO) {
        // Récupérer l'utilisateur existant
        AuthUser authUser = authUserRepository.findById(idUtente)
                .orElseThrow(() -> new UsernameNotFoundException("Utente non trovato con ID: " + idUtente));

        // Utiliser la méthode de mise à jour partielle du mapper MapStruct
        authUserMapper.updateAuthUserFromDTO(authUserUpdateDTO, authUser);

        // Sauvegarder les modifications en base de données
        AuthUser updatedUser = authUserRepository.save(authUser);

        // Retourner l'AuthUserDTO modifié
        return authUserMapper.toAuthUserDTO(updatedUser);
    }
}