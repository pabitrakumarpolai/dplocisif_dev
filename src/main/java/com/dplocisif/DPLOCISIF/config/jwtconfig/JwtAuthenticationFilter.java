package com.dplocisif.DPLOCISIF.config.jwtconfig;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Function;

@Service
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private String SECRET_KEY; // Change this to your secret key

    public JwtAuthenticationFilter(@Value("${jwt.secretKey}") String SECRET_KEY) {
        this.SECRET_KEY = SECRET_KEY;
    }

    private final List<String> unsecuredUrls = Arrays.asList("/refresh-token");
    private String encodedKey;// URLs to leave unsecured

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestURI = request.getRequestURI();

        if (requestURI.contains("/login")) {
            filterChain.doFilter(request, response);
            return;
        }

        if (isUnsecuredUrl(requestURI)) {
            filterChain.doFilter(request, response);
            return;
        }
        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return;
        }

        String token = header.substring(7);
        if (isTokenValid(token)) {
            try {
                encodedKey = Base64.getEncoder().encodeToString(SECRET_KEY.getBytes());
                Claims claims = Jwts.parser().setSigningKey(encodedKey).build().parseSignedClaims(token).getBody();
                request.setAttribute("claims", claims);
            } catch (SignatureException e) {
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                return;
            }
        } else {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().write("Session Expired");
        }

        filterChain.doFilter(request, response);
    }

    private boolean isUnsecuredUrl(String requestURI) {
        return unsecuredUrls.stream().anyMatch(requestURI::endsWith);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY.getBytes(Charset.forName("UTF-8"))).build()
                .parseClaimsJws(token).getBody();
    }

    public boolean isTokenValid(String token) {
        return isTokenExpired(token);
    }
    private boolean isTokenExpired(String token) {
        return !extractExpiration(token).before(new Date());
    }
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}
