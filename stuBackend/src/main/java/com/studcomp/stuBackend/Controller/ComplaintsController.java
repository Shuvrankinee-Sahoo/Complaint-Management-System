package com.studcomp.stuBackend.Controller;

import com.studcomp.stuBackend.Dtos.NewComplaintDto;
import com.studcomp.stuBackend.Dtos.ProfileDto;
import com.studcomp.stuBackend.Servises.StudentService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class ComplaintsController {

    @Autowired
    private StudentService studentService;

    private String getEmail(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }
        return authentication.getName();
    }

    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(Authentication auth) {
        String email = getEmail(auth);
        if (email == null) return ResponseEntity.status(401).body("Unauthorized");
        return ResponseEntity.ok(studentService.getProfile(email));
    }

    @PutMapping("/profile")
    public ResponseEntity<?> updateProfile(@RequestBody ProfileDto dto, Authentication auth) {
        String email = getEmail(auth);
        if (email == null) return ResponseEntity.status(401).body("Unauthorized");
        return ResponseEntity.ok(studentService.updateProfile(email, dto));
    }

    @GetMapping("/stats")
    public ResponseEntity<?> getStats(Authentication auth) {
        String email = getEmail(auth);
        if (email == null) return ResponseEntity.status(401).body("Unauthorized");
        return ResponseEntity.ok(studentService.getStats(email));
    }

    @GetMapping("/complaints")
    public ResponseEntity<?> getComplaints(Authentication auth) {
        String email = getEmail(auth);
        if (email == null) return ResponseEntity.status(401).body("Unauthorized");
        return ResponseEntity.ok(studentService.getComplaints(email));
    }
    @PutMapping("/changePassword")
    public ResponseEntity<?> changePassword(@RequestBody Map<String, String> body, Authentication auth) {

        String email = getEmail(auth);
        if (email == null) return ResponseEntity.status(401).body("Unauthorized");

        String oldPassword = body.get("currentPassword");
        String newPassword = body.get("newPassword");

        String result = studentService.changePassword(email, oldPassword, newPassword);

        if (!result.equals("Password updated"))
            return ResponseEntity.badRequest().body(result);

        return ResponseEntity.ok(result);
    }


    @PostMapping("/complaints")
    public ResponseEntity<?> addComplaint(@RequestBody NewComplaintDto dto, Authentication auth) {
        String email = getEmail(auth);
        if (email == null) return ResponseEntity.status(401).body("Unauthorized");
        return ResponseEntity.ok(studentService.addComplaint(email, dto));
    }
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse response) {

        Cookie tokenCookie = new Cookie("token", "");
        tokenCookie.setPath("/");
        tokenCookie.setHttpOnly(true);
        tokenCookie.setSecure(false);
        tokenCookie.setAttribute("SameSite", "Lax");
        tokenCookie.setMaxAge(0);

        // DELETE ROLE COOKIE
        Cookie roleCookie = new Cookie("role", "");
        roleCookie.setPath("/");
        roleCookie.setHttpOnly(false);
        roleCookie.setSecure(false);
        roleCookie.setAttribute("SameSite", "Lax");
        roleCookie.setMaxAge(0);

        response.addCookie(tokenCookie);
        response.addCookie(roleCookie);

        return ResponseEntity.ok("Logged out successfully");
    }

}
