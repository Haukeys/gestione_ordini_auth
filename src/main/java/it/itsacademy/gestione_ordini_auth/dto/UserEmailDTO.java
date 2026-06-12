package it.itsacademy.gestione_ordini_auth.dto;

//NOUVELLE CLASS POUR LIER USER AVEC LE MESSAGE QUI DOIT LUI ARRIVER
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEmailDTO {

    @NotNull(message = "La password non puo essere null")
    private UUID idUtente;

    @NotBlank(message = "La password non puo essere vuota")
    private String username;

    @NotBlank(message = "La password non puo essere vuota")
    private String email;

}
