package com.capstone.galapagosUber.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.UUID;

@Component
public class JwtUtil {

    private final SecretKey jwtSecret;
    private final long jwtExpirationMs;

    public JwtUtil(@Value("MIIBVQIBADANBgkqhkiG9w0BAQEFAASCAT8wggE7AgEAAkEA4ty/6NviFXYPz8/wBjQaQJmrNEoK2Y5O+LcTLab+s17Mb4vPbX9IxezwaPtzsvJGd3ZMgCfNmL8uJ6n4Ujn4BwIDAQABAkBhcn9A0AqaO19pXaF1mLXaH+gJivCEKPvFHCaxynTHqNaMochLZr00IqimkdaFVqZm3rL6l+D1NleYnk7BUXlpAiEA/YbUhNSli6udz9+cpCCtWHUZiL6HEJUqvQSUwJYbS20CIQDlE1ObDGMdCy2gnvRilJPZJK53AtBLQjTLgK1j5dcUwwIhANpR5WiNhhGEs91yfn2H9j6aeGadS8on6injbAV8PfzxAiEA2Y7rXlv7XcAaC2wrqdx2NkpyL7FKZc9xGL0S78d/58UCIBpNSgiyB8q9cXO7MdEG/+fsEaaPFJv4RIjLIuliaj90") String jwtSecretStr,
                   @Value("86400000") long jwtExpirationMs) {
        byte[] decodedKey = io.jsonwebtoken.io.Decoders.BASE64.decode(jwtSecretStr);
        this.jwtSecret = Keys.hmacShaKeyFor(decodedKey);
        this.jwtExpirationMs = jwtExpirationMs;
    }

    /**
     * Generates a JWT token containing the user's ID and role.
     //     *
     //     * @param userId The UUID of the user.
     //     * @param role   The role of the user (e.g., "rider" or "driver").
     //     * @return The generated JWT token.
     */

    public String generateJwtToken(UUID userId, String role) {
        return Jwts.builder()
                .setSubject(userId.toString())
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(jwtSecret, SignatureAlgorithm.HS512)
                .compact();
    }

    /**
     * Validates the JWT token.
     *
     * @param token The JWT token to validate.
     * @return True if valid, else false.
     */
    public boolean validateJwtToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(jwtSecret).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            // Log the exception as needed
        }
        return false;
    }

    /**
     * Extracts the user ID from the JWT token.
     *
     * @param token The JWT token.
     * @return The user ID as UUID.
     */
    public UUID getUserIdFromJwtToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(jwtSecret)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return UUID.fromString(claims.getSubject());
    }

    /**
     * Extracts the role from the JWT token.
     *
     * @param token The JWT token.
     * @return The role as String.
     */
    public String getRoleFromJwtToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(jwtSecret)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.get("role", String.class);
    }
}
