package com.irojas.demojwt.Jwt;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;

import org.springframework.util.StringUtils;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    
   
    public JwtAuthenticationFilter(JwtService jwtService, UserDetailsService userDetailsService) {
		super();
		this.jwtService = jwtService;
		this.userDetailsService = userDetailsService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
	        throws ServletException, IOException {

	    try {
	        final String token = getTokenFromRequest(request);
	        final String username;

	        // Si no hay token, continúa la cadena de filtros
	        if (token == null) {
	            filterChain.doFilter(request, response);
	            return;
	        }

	        // Obtiene el nombre de usuario o email desde el token
	        username = jwtService.getEmaileFromToken(token);

	        // Si el usuario no está autenticado en el contexto y el token es válido
	        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
	            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

	            // Verifica si el token es válido
	            if (jwtService.isTokenValid(token, userDetails)) {
	                // Autentica al usuario en el contexto de seguridad de Spring
	                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
	                    userDetails, null, userDetails.getAuthorities());

	                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
	                SecurityContextHolder.getContext().setAuthentication(authToken);
	            }
	        }

	        // Continúa la cadena de filtros si todo es correcto
	        filterChain.doFilter(request, response);

	    } catch (ExpiredJwtException ex) {
	        // Si el token ha expirado, devuelve un 401 Unauthorized
	        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	        response.getWriter().write("Token expired");
	    }
	}

    private String getTokenFromRequest(HttpServletRequest request) {
        final String authHeader=request.getHeader(HttpHeaders.AUTHORIZATION);

        if(StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer "))
        {
            return authHeader.substring(7);
        }
        return null;
    }



    
}
