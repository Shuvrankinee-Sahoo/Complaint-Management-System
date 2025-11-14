package com.studcomp.stuBackend.Controller;

import com.studcomp.stuBackend.Dtos.LoginDto;
import com.studcomp.stuBackend.Entitys.Users;
import com.studcomp.stuBackend.Repo.UserRepo;
import com.studcomp.stuBackend.Servises.JwtServiceFilter.JwtToken;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class login {
    @Autowired private AuthenticationManager authenticationManager;
    @Autowired private UserRepo repo;
    @Autowired private JwtToken jwtToken;
    @PostMapping("/login")
    public ResponseEntity<String> LogingCtl(@RequestBody LoginDto loginDto, HttpServletResponse response) {

        System.out.println(loginDto.getPassword()+loginDto.getMail());
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.getMail(), loginDto.getPassword())
            );

            // Only reached if authentication succeeds
            System.out.println("auth isAuthenticated(): " + auth.isAuthenticated());

            Users uu = repo.findByEmail(loginDto.getMail())
                    .orElseThrow(() -> new UsernameNotFoundException("None"));
            System.out.println("Loaded user: " + uu);

            // Generate token and set cookies...
            String token = jwtToken.GenerateToken(uu);
            System.out.println("JWT Token: " + token);

            Cookie cc = new Cookie("token", token);
            cc.setSecure(false); // local dev
            cc.setHttpOnly(true);
            cc.setPath("/");
            cc.setAttribute("SameSite", "Lax");
            cc.setMaxAge(5 * 24 * 60 * 60);
            response.addCookie(cc);
            Cookie roleCookie = new Cookie("role", uu.getRole());
            roleCookie.setSecure(false);
            roleCookie.setHttpOnly(false);
            roleCookie.setPath("/");
            roleCookie.setAttribute("SameSite", "Lax");
            roleCookie.setMaxAge(5 * 24 * 60 * 60);
            response.addCookie(roleCookie);

            return ResponseEntity.ok(token);

        } catch (Exception e) {
            System.out.println("Authentication failed: " + e.getClass().getSimpleName() + " - " + e.getMessage());
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }

}
