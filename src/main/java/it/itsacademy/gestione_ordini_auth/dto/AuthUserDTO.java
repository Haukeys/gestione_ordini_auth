package it.itsacademy.gestione_ordini_auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthUserDTO {

    private UUID idUtente;
    private String username;
    private String name;
    private String surname;
    private Boolean isAttivo;
    private Set<RoleDTO> roles; // Représente la liste des entités Role associées
    private String token;       // Contient le token JWT pour la Gateway si c'est une réponse de Login
}