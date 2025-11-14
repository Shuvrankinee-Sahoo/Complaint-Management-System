package com.studcomp.stuBackend.Servises.JwtServiceFilter;

import com.studcomp.stuBackend.Servises.UserDetailsServiceS;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
@Service
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    JwtToken jwtToken;
    @Autowired
    UserDetailsServiceS userDetailsServiceS;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token =null;
        Cookie[] cc= request.getCookies();
        if(cc!=null){
            token= Arrays.stream(cc)
                    .filter(c->c.getName().equals("token"))
                    .map(Cookie::getValue)
                    .findFirst()
                    .orElse(null);
        }
        System.out.println("SujitMyuser1 "+token);

        try {
            if (token != null) {
                String Email = jwtToken.getUser(token);
                System.out.println("gma"+token+Email);


                if (Email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UserDetails userDetails = userDetailsServiceS.loadUserByUsername(Email);
                    if (jwtToken.ValidateToken(token, userDetails)) {
                        UsernamePasswordAuthenticationToken token1 = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                        SecurityContextHolder.getContext().setAuthentication(token1);
                    }
                }
            }
        }
        catch (Exception e){
            System.out.println("fd");
        }
        filterChain.doFilter(request,response);


    }
}
