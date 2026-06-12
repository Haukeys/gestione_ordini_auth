package it.itsacademy.gestione_ordini_auth.service;

import it.itsacademy.gestione_ordini_auth.dto.*;

import java.util.UUID;

public interface AuthService {

    // Inscription : prend un SignupDTO et retourne l'AuthUserDTO créé
    public AuthUserDTO register(SignUpDTO signupDTO);

    // Connexion : prend un LoginDTO et retourne l'AuthUserDTO contenant le token JWT
    public AuthUserDTO login(LoginDTO loginDTO);

    // Mise à jour : prend l'UUID de l'utilisateur et le DTO authuser_update
    public AuthUserDTO updateProfile(UUID idUtente, AuthUserUpdateDTO authUserUpdateDTO);

    // email: prend l'UUID de l'utilisateur et le retourne en un user qui pourra recevoir des emails
    public UserEmailDTO getUserByEmail(UUID idUtente);

}