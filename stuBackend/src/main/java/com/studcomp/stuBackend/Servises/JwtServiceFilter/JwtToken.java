package com.studcomp.stuBackend.Servises.JwtServiceFilter;

import com.studcomp.stuBackend.Entitys.Users;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
@Service
public class JwtToken {
    private final SecretKey key= Jwts.SIG.HS256.key().build();
    public String GenerateToken(Users user){
        System.out.println("hii");
        return Jwts.builder()
                .claim("role",user.getRole())
                .subject(user.getEmail())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+1000*60*60))
                .signWith(key).compact();
    }
    public String getUser(String Token){
        System.out.println("gteUser"+Token);

        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(Token)
                .getPayload()
                .getSubject();
    }
    public  boolean ValidateToken(String Token, UserDetails userDetails){
        String Username=getUser(Token);
        return userDetails.getUsername().equals(Username)&&!IsTokenValid(Token);

    }

    private boolean IsTokenValid(String token) {
        return  Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getExpiration().before(new Date());
    }

}
