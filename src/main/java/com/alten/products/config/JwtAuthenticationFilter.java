package com.alten.products.config;

import com.alten.products.domain.User;
import com.alten.products.service.JwtService;
import com.alten.products.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.util.Collections;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException, java.io.IOException {

        String requestURI = request.getRequestURI();
        System.out.println("Filtrage de la requête: " + requestURI);

        if (requestURI.equals("/auth/token") || requestURI.equals("/auth/account")) {
            System.out.println("Bypass du filtre JWT pour: " + requestURI);
            filterChain.doFilter(request, response);
            return;
        }

        String token = request.getHeader("Authorization");

        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7); // Enlève "Bearer " du début du token
            try {
                // Extraction et validation du token JWT ici
                // (tu peux appeler ton `JwtUtil.extractClaims` ici pour valider le token)
                Claims claims = JwtService.extractClaims(token);

                if (claims != null) {
                    // Si le token est valide, on le place dans le contexte de sécurité
                    SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
                            claims.getSubject(), null, Collections.emptyList()));
                }
            } catch (Exception e) {
                System.out.println("Token invalid or expired");
            }
        }

        // Passe à la requête suivante
        filterChain.doFilter(request, response);
    }
}

