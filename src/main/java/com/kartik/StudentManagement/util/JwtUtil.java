package com.kartik.StudentManagement.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private static final String SECRET_KEYS_STRING = "jff3949fjjf30f39jrrf493944fj309u493fa";
    private final Key SECRET_KEY = Keys.hmacShaKeyFor(SECRET_KEYS_STRING.getBytes());

      public String generateToken(String username){
         return Jwts.builder()
                  .setSubject(username)
                  .setIssuedAt(new Date())
                  .setExpiration(new Date(System.currentTimeMillis()+1000*300))
                  .signWith(SECRET_KEY).
                  compact();
      }

      public boolean validateToken(String token){
          try {
              Jwts.parserBuilder().
                      setSigningKey(SECRET_KEY).build().
                      parseClaimsJws(token);
              return true;
          }
          catch (Exception e){
              System.out.println(e.getMessage());
              return false;
          }
      }

}
