package it.itsacademy.gestione_ordini_auth.dto;

import it.itsacademy.gestione_ordini_auth.entity.Roles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDTO {

    private UUID idRole;
    private Roles roles;
}