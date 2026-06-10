package it.itsacademy.gestione_ordini_auth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpDTO {

    @NotBlank(message = "Lo username non puo essere vuoto")
    private String username;

    @NotBlank(message = "La password non puo essere vuota")
    private String password;

    @NotBlank(message = "Il name non puo essere vuoto")
    private String name;

    @NotBlank(message = "Il surname non puo essere vuoto")
    private String surname;
}
