package com.learnspringboot.configs;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.learnspringboot.services.JwtService;

import io.micrometer.common.lang.NonNull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.val;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    // We want our own implementation because we want to fetch our user from our
    // database
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        // Pass the JWT authentication token
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        // To be consistent and avoid confusions
        final String userEmail;

        // Build token should be always or should start always with the keyword
        // beater
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        // Extract the token from this header from authentication header
        // beginIndex : 7 from Bearer.
        jwt = authHeader.substring(7);
        userEmail = jwtService.extractUsername(jwt);// todo extract the userEmail from JWT token;

        // If validated then user can go to next step that is Update the
        // SecurityContextHolder
        val context = SecurityContextHolder.createEmptyContext();

        SecurityContextHolder.setContext(context);
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // We must check if we have the user in database
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
            if (jwtService.isTokenValid(jwt, userDetails)) {

                // If validate then send request to our DispatchServlet
                // If token is valid then create an object of type username password
                // authentication token (is needed by the spring and by security context holder)
                // in order to update our security context
                // we dont have credentials so we passing these credentials as a null value
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities());
                // Add some more details
                authenticationToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request));

                // Final Step is to update Security ContextHolder
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        // Don't forget to add the filterchain
        filterChain.doFilter(request, response);
    }

}
