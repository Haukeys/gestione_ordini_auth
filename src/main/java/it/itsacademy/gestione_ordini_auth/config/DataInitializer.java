package it.itsacademy.gestione_ordini_auth.config;
import it.itsacademy.gestione_ordini_auth.entity.Role;
import it.itsacademy.gestione_ordini_auth.entity.Roles;
import it.itsacademy.gestione_ordini_auth.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
/**
 * Initialise la base de données avec les données de référence nécessaires.
 * Insère les rôles par défaut (USER) si la table Role est vide.
 * Sans cela, chaque appel à /register lève une RuntimeException (500)
 * car AuthServiceImpl.register() ne trouve pas le rôle USER.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final RoleRepository roleRepository;
    @Override
    public void run(String... args) {
        // Insère le rôle USER uniquement s'il n'existe pas encore
        if (roleRepository.findByRoles(Roles.USER).isEmpty()) {
            Role userRole = new Role();
            userRole.setRoles(Roles.USER);
            roleRepository.save(userRole);
            log.info("Rôle USER INSERITO NEL DB.");
        } else {
            log.info("Rôle USER GIA PRESENTE NEL DB.");
        }
    }
}

