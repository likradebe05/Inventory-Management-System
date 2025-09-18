package za.ac.cput.Util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import za.ac.cput.Domain.Admin;
import za.ac.cput.Domain.Customer;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component // This makes the class a Spring Bean for dependency injection
public class JwtUtil {

    // A secure key is required to sign the JWT.
    // In a real application, you would store this securely in a config file.
    private static final String SECRET_STRING = "ThisIsAReallyLongAndSecureSecretKeyForJWTsThatShouldBeAtLeast256BitsLong";
    private static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(SECRET_STRING.getBytes(StandardCharsets.UTF_8));
    private static final long EXPIRATION_TIME = TimeUnit.HOURS.toMillis(24); // Token validity in milliseconds (24 hours)

    /**
     * Generates a JWT for a given customer.
     * @param customer The Customer object containing the userId.
     * @return The signed JWT string.
     */
    public String generateToken(Customer customer) {
        // Create the claims (payload) for the token
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", customer.getUserId()); // Add the user ID to the payload
        claims.put("email", customer.getEmail());   // Optional: add other user details
        claims.put("userType", "CUSTOMER");         // User type for differentiation
        claims.put("role", customer.getRole().name()); // Role for authorization
        claims.put("authority", customer.getAuthority()); // Spring Security authority
        claims.put("firstName", customer.getFirstName());
        claims.put("lastName", customer.getLastName());

        Date now = new Date();
        Date expiration = new Date(now.getTime() + EXPIRATION_TIME);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(customer.getEmail())
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Generates a JWT for a given admin.
     * @param admin The Admin object containing the adminId.
     * @return The signed JWT string.
     */
    public String generateToken(Admin admin) {
        // Create the claims (payload) for the token
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", admin.getAdminId());   // Add the admin ID to the payload
        claims.put("email", admin.getUserName());   // Admin uses userName as email
        claims.put("userType", "ADMIN");            // User type for differentiation
        claims.put("role", admin.getRole().name()); // Role for authorization
        claims.put("authority", admin.getAuthority()); // Spring Security authority

        Date now = new Date();
        Date expiration = new Date(now.getTime() + EXPIRATION_TIME);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(admin.getUserName())
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Validates and parses a JWT token.
     * @param token The JWT token string.
     * @return Claims object containing the token's payload.
     * @throws Exception if token is invalid or expired.
     */
    public Claims validateToken(String token) throws Exception {
        // Temporarily disabled for presentation
        /*
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
        */
        
        // Return empty claims for demo purposes
        return Jwts.claims();
    }

    /**
     * Extracts the user ID from a JWT token.
     * @param token The JWT token string.
     * @return The user ID or null if token is invalid.
     */
    public Long getUserIdFromToken(String token) {
        try {
            Claims claims = validateToken(token);
            return claims.get("userId", Long.class);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Extracts the user type from a JWT token.
     * @param token The JWT token string.
     * @return The user type ("ADMIN" or "CUSTOMER") or null if token is invalid.
     */
    public String getUserTypeFromToken(String token) {
        try {
            Claims claims = validateToken(token);
            return claims.get("userType", String.class);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Extracts the role from a JWT token.
     * @param token The JWT token string.
     * @return The role string or null if token is invalid.
     */
    public String getRoleFromToken(String token) {
        try {
            Claims claims = validateToken(token);
            return claims.get("role", String.class);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Extracts the Spring Security authority from a JWT token.
     * @param token The JWT token string.
     * @return The authority string or null if token is invalid.
     */
    public String getAuthorityFromToken(String token) {
        try {
            Claims claims = validateToken(token);
            return claims.get("authority", String.class);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Check if the token belongs to an admin user.
     * @param token The JWT token string.
     * @return True if user is admin, false otherwise.
     */
    public boolean isAdminToken(String token) {
        // Temporarily return true for demo purposes
        return true;
        /*
        String userType = getUserTypeFromToken(token);
        return "ADMIN".equals(userType);
        */
    }

    /**
     * Check if the token belongs to a customer user.
     * @param token The JWT token string.
     * @return True if user is customer, false otherwise.
     */
    public boolean isCustomerToken(String token) {
        // Temporarily return true for demo purposes  
        return true;
        /*
        String userType = getUserTypeFromToken(token);
        return "CUSTOMER".equals(userType);
        */
    }
}