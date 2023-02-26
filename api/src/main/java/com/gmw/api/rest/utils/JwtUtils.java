package com.gmw.api.rest.utils;

import com.gmw.reader.JsonConfigReader;
import com.gmw.user.tos.ExistingUserTO;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
    private static final String USER_ID = "userId";
    private static final String ROLE = "role";


    public static String generateJwtToken(ExistingUserTO existingUserTO, String role) {
        Long expiration = JsonConfigReader.readSecurityConfig().jwt().tokenExpiration();
        String secret = JsonConfigReader.readSecurityConfig().jwt().secret();
        return Jwts.builder()
                .setSubject(existingUserTO.getEmail())
                .claim(USER_ID, existingUserTO.getId())
                .claim(ROLE, role)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + expiration))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public static Long extractUserId(String token) {
        String secret = JsonConfigReader.readSecurityConfig().jwt().secret();
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().get(USER_ID, Long.class);
    }

    public static String extractUserRole(String token) {
        String secret = JsonConfigReader.readSecurityConfig().jwt().secret();
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().get(ROLE, String.class);
    }

    public static boolean isValid(String authToken) {
        try {
            String secret = JsonConfigReader.readSecurityConfig().jwt().secret();
            Jwts.parser().setSigningKey(secret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }
}
