package it.itsacademy.gestione_ordini_auth.repository;

import it.itsacademy.gestione_ordini_auth.entity.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AuthUserRepository extends JpaRepository<AuthUser, UUID> {
    // Spring Security l'utilise pour charger l'utilisateur par son nom de compte
    Optional<AuthUser> findByUsername(String username);

    // Utile pour la phase d'inscription (Sign Up) pour vérifier les doublons
    boolean existsByUsername(String username);
}
