package com.studcomp.stuBackend.Controller;

import com.studcomp.stuBackend.Entitys.Users;
import com.studcomp.stuBackend.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin
@RestController
@RequestMapping("/user")
public class signup {
    @Autowired
    UserRepo repo;

    @PostMapping("/signup")
    public ResponseEntity<String> signupController(@RequestBody Users user) {
        try {
            if (repo.findByEmail(user.getEmail()).isPresent()) {
                return ResponseEntity.badRequest().body("Email already registered");
            }
            repo.save(user);
            return ResponseEntity.ok("User registered successfully!");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

}
