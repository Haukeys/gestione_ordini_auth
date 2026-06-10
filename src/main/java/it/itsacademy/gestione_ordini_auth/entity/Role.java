package it.itsacademy.gestione_ordini_auth.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_Role")
    private UUID idRole;


    @Enumerated(EnumType.STRING) // Force le stockage du nom (USER) et non de l'index (1)
    @Column(nullable = false)
    private Roles roles;
}
