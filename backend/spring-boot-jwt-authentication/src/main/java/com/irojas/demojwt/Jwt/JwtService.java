package com.irojas.demojwt.Jwt;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.irojas.demojwt.Auth.Model.User;
import com.irojas.demojwt.Auth.Service.UserService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import com.irojas.demojwt.Auth.Model.Role;
@Service
public class JwtService {

    private static final String SECRET_KEY="586E3272357538782F413F4428472B4B6250655368566B597033733676397924";
    private UserService userService;
    
    
    public JwtService(UserService userService) {
		super();
		this.userService = userService;
	}

	public String getToken(UserDetails user) {
        return getToken(new HashMap<>(), user);
    }

    private String getToken(Map<String,Object> extraClaims, UserDetails user) {
    	
    	if (user instanceof User) {
            User customUser = (User) user;
            //System.out.print(customUser.getRole().name());
         
            // Extraer todos los roles del usuario y ponerlos en los extraClaims
            List<Role> roles = customUser.getRoles();
            
            // Convertimos los roles a una lista de cadenas y los añadimos a los extraClaims
            List<String> rolesList = roles.stream()
                                          .map(Role::name)
                                          .collect(Collectors.toList());
            
            extraClaims.put("roles", rolesList); // Añadimos todos los roles
        }
    	
    	
        return Jwts
            .builder()
            .setClaims(extraClaims)
            .setSubject(user.getUsername())
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis()+10000*60*24))
            .signWith(getKey(), SignatureAlgorithm.HS256)
            .compact();
    }

    private Key getKey() {
       byte[] keyBytes=Decoders.BASE64.decode(SECRET_KEY);
       return Keys.hmacShaKeyFor(keyBytes);
    }

    public String getEmaileFromToken(String token) {
        return getClaim(token, Claims::getSubject);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username=getEmaileFromToken(token);
        return (username.equals(userDetails.getUsername())&& !isTokenExpired(token));
    }


	public boolean isTokenValid(String token) {
	    String username = getEmaileFromToken(token);
	    UserDetails userDetails = userService.loadUserByUsername(username); // Obtener UserDetails
	    return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
    
    private Claims getAllClaims(String token)
    {
        return Jwts
            .parserBuilder()
            .setSigningKey(getKey())
            .build()
            .parseClaimsJws(token)
            .getBody();
    }

    public <T> T getClaim(String token, Function<Claims,T> claimsResolver)
    {
        final Claims claims=getAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Date getExpiration(String token)
    {
        return getClaim(token, Claims::getExpiration);
    }

    private boolean isTokenExpired(String token)
    {
        return getExpiration(token).before(new Date());
    }
    
    
    
}
