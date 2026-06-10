package it.itsacademy.gestione_ordini_auth.security;



import it.itsacademy.gestione_ordini_auth.entity.AuthUser;
import it.itsacademy.gestione_ordini_auth.repository.AuthUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final AuthUserRepository authUserRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthUser user = authUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Utente non trovato con username: " + username));

        return new User(
                user.getUsername(),
                user.getPassword(),
                user.getIsAttivo(), // Si false -> lève une DisabledException
                true,
                true,
                true,
                getAuthorities(user)
        );
    }

    private static Collection<? extends GrantedAuthority> getAuthorities(AuthUser user) {
        if (user.getRoles() == null) {
            return Collections.emptyList();
        }

        String[] authorities = user.getRoles().stream()
                .map(item -> {
                    String roleName = item.getRoles().name();
                    return roleName.startsWith("ROLE_") ? roleName : "ROLE_" + roleName;
                })
                .toArray(String[]::new);

        return AuthorityUtils.createAuthorityList(authorities);
    }
}