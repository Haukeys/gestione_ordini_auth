package it.itsacademy.gestione_ordini_auth.controller;

import it.itsacademy.gestione_ordini_auth.dto.*;
import it.itsacademy.gestione_ordini_auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/auth") // Combiné au context-path du .env (/api), l'URL sera /api/auth/...
@AllArgsConstructor // Utilise la même approche que ton filtre : injection globale par constructeur automatique
public class AuthController {

    private final AuthService authService;

    /**
     * Endpoint d'inscription (Sign Up)
     * Accessible publiquement via la configuration de sécurité.
     */
    @PostMapping("/register")
    public ResponseEntity<AuthUserDTO> register(@Valid @RequestBody SignUpDTO signupDTO) {
        AuthUserDTO createdUser = authService.register(signupDTO);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    /**
     * Endpoint de connexion (Login)
     * Génère et retourne le profil de l'utilisateur contenant son token JWT.
     */
    @PostMapping("/login")
    public ResponseEntity<AuthUserDTO> login(@Valid @RequestBody LoginDTO loginDTO) {
        AuthUserDTO authenticatedUser = authService.login(loginDTO);
        return ResponseEntity.ok(authenticatedUser);
    }

    /**
     * Endpoint de mise à jour (authuser_update)
     * Modifie les données de l'utilisateur à partir de son identifiant UUID.
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<AuthUserDTO> updateProfile(
            @PathVariable("id") UUID idUtente,
            @Valid @RequestBody AuthUserUpdateDTO authUserUpdateDTO) {

        AuthUserDTO updatedUser = authService.updateProfile(idUtente, authUserUpdateDTO);
        return ResponseEntity.ok(updatedUser);
    }
    @GetMapping(path = "/health")
    public void health() {}
}
