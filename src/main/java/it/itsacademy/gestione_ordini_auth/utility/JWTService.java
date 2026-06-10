package it.itsacademy.gestione_ordini_auth.utility;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;
import java.util.function.Function;

public interface JWTService {

    public String extractUsername(String token);

    public String generateToken(UserDetails userDetails);

    // Version surchargée cruciale pour ajouter l'ID de l'utilisateur et ses rôles
    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails);

    public boolean isTokenValid(String token, UserDetails userDetails);

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver);
}