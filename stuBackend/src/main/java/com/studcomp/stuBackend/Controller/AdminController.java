package com.studcomp.stuBackend.Controller;

import com.studcomp.stuBackend.Dtos.ProfileDto;
import com.studcomp.stuBackend.Dtos.AdminUpdateComplaintDto;
import com.studcomp.stuBackend.Entitys.Complaints;
import com.studcomp.stuBackend.Servises.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    private String getEmail(Authentication auth) {
        if (auth == null || !auth.isAuthenticated()) return null;
        return auth.getName();
    }
    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(adminService.getAllUsers());
    }

    @DeleteMapping("/users/{regNo}")
    public ResponseEntity<?> deleteUser(@PathVariable String regNo) {
        adminService.deleteUser(regNo);
        return ResponseEntity.ok("User deleted");
    }


    @GetMapping("/complaints")
    public ResponseEntity<List<Complaints>> getAllComplaints(Authentication auth) {
        String email = getEmail(auth);
        return ResponseEntity.ok(adminService.getAllComplaints(email));
    }

    @PutMapping("/complaints/update")
    public ResponseEntity<?> updateComplaint(@RequestBody AdminUpdateComplaintDto dto, Authentication auth) {
        String email = getEmail(auth);
        return ResponseEntity.ok(adminService.updateComplaint(dto, email));
    }

    @GetMapping("/stats")
    public ResponseEntity<?> getStats(Authentication auth) {
        String email = getEmail(auth);
        return ResponseEntity.ok(adminService.getStats(email));
    }
}
