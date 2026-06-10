package it.itsacademy.gestione_ordini_auth.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {

    @NotBlank(message = "Lo username non puo essere vuoto")
    private String username;

    @NotBlank(message = "La password non puo essere vuota")
    private String password;
}