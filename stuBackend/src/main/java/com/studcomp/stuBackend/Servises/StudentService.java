package com.studcomp.stuBackend.Servises;

import com.studcomp.stuBackend.Dtos.NewComplaintDto;
import com.studcomp.stuBackend.Dtos.ProfileDto;
import com.studcomp.stuBackend.Dtos.StatsDto;
import com.studcomp.stuBackend.Entitys.Complaints;
import com.studcomp.stuBackend.Entitys.Users;
import com.studcomp.stuBackend.Repo.ComplaintsRepo;
import com.studcomp.stuBackend.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class StudentService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ComplaintsRepo complaintRepo;

    public ProfileDto getProfile(String email) {
        Users user = userRepo.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        return new ProfileDto(user.getUsername(), user.getEmail(), user.getRegiNo());
    }

    public ProfileDto updateProfile(String email, ProfileDto dto) {
        Users user = userRepo.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        user.setUsername(dto.getFullName());
        user.setEmail(dto.getEmail());
        user.setRegiNo(dto.getRegNo());
        userRepo.save(user);
        return new ProfileDto(user.getUsername(), user.getEmail(), user.getRegiNo());
    }

    public StatsDto getStats(String email) {
        long total = complaintRepo.countByMail(email);
        long pending = complaintRepo.countByMailAndStatus(email, "Pending");
        long inProgress = complaintRepo.countByMailAndStatus(email, "In Progress");
        long resolved = complaintRepo.countByMailAndStatus(email, "Resolved");
        long rejected = complaintRepo.countByMailAndStatus(email, "Rejected");

        return new StatsDto(total, pending, inProgress, resolved, rejected);
    }

    public List<Complaints> getComplaints(String email) {
        return complaintRepo.findByMail(email);
    }

    public Complaints addComplaint(String email, NewComplaintDto dto) {
        Complaints c = new Complaints();
        c.setMail(email);
        c.setTitle(dto.getTitle());
        c.setSubject(dto.getSubject());
        c.setStatus("Pending");
        c.setDescription(dto.getDescription());
        c.setDate(LocalDate.now().toString());
        c.setResponse("NA");
        return complaintRepo.save(c);
    }

    public String changePassword(String email, String oldPassword, String newPassword) {

        // Fetch user
        Users user = userRepo.findByEmail(email).orElseThrow();

        // Check old password match
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            return "Current password is incorrect";
        }

        // Prevent same password reuse
        if (passwordEncoder.matches(newPassword, user.getPassword())) {
            return "New password cannot be same as old password";
        }

        // Update password
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepo.save(user);

        return "Password updated";
    }

}
