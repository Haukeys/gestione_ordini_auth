package it.itsacademy.gestione_ordini_auth.repository;


import it.itsacademy.gestione_ordini_auth.entity.Role;
import it.itsacademy.gestione_ordini_auth.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {

    // Permet de trouver l'entité Role à partir de la valeur de l'énumération Roles (ex: Roles.USER)
    Optional<Role> findByRoles(Roles roles);
}
