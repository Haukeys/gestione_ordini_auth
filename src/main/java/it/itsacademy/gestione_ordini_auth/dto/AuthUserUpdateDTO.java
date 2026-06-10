package it.itsacademy.gestione_ordini_auth.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthUserUpdateDTO {

    @NotBlank(message = "Il nome non può essere vuoto")
    private String name;

    @NotBlank(message = "Il cognome non può essere vuoto")
    private String surname;

    // On peut ajouter isAttivo si seul l'admin a le droit d'envoyer ce DTO
    private Boolean isAttivo;
}