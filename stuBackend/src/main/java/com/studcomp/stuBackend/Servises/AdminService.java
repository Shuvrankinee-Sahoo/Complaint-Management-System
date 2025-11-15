package com.studcomp.stuBackend.Servises;

import com.studcomp.stuBackend.Dtos.ProfileDto;
import com.studcomp.stuBackend.Dtos.AdminUpdateComplaintDto;
import com.studcomp.stuBackend.Dtos.StatsDto;
import com.studcomp.stuBackend.Entitys.Complaints;
import com.studcomp.stuBackend.Entitys.Users;
import com.studcomp.stuBackend.Repo.ComplaintsRepo;
import com.studcomp.stuBackend.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ComplaintsRepo complaintsRepo;

    public ProfileDto getProfile(String email) {
        Users admin = userRepo.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("no User"));
        if (admin == null) return null;

        ProfileDto dto = new ProfileDto();
        dto.setFullName(admin.getUsername());
        dto.setEmail(admin.getEmail());
        return dto;
    }

    public String updateProfile(String email, ProfileDto dto) {
        Users admin = userRepo.findByEmail(email).orElseThrow(()->new RuntimeException("no user"));

        admin.setUsername(dto.getFullName());
        admin.setEmail(dto.getEmail());

        userRepo.save(admin);
        return "Profile updated successfully";
    }

    public List<Complaints> getAllComplaints(String email) {
        Users admin = userRepo.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("no user"));
        if (admin == null || !admin.getRole().equals("admin")) {
            return null;
        }

        return complaintsRepo.findAllByOrderByDateDesc();
    }

    public String updateComplaint(AdminUpdateComplaintDto dto, String adminEmail) {
        Users admin = userRepo.findByEmail(adminEmail).orElseThrow(()->new UsernameNotFoundException("no user"));

        if (admin == null || !admin.getRole().equals("admin"))
            return "Unauthorized";

        Complaints c = complaintsRepo.findById(dto.getId()).orElse(null);
        if (c == null) return "Complaint Not Found";

        c.setStatus(dto.getStatus());
        c.setResponse(dto.getResponse());

        complaintsRepo.save(c);
        return "Complaint updated successfully";
    }

    public StatsDto getStats(String adminEmail) {
        StatsDto dto = new StatsDto();

        dto.setTotal(complaintsRepo.count());
        dto.setPending(complaintsRepo.countByStatus("Pending"));
        dto.setIn_progress(complaintsRepo.countByStatus("In Progress"));
        dto.setResolved(complaintsRepo.countByStatus("Resolved"));
        dto.setRejected(complaintsRepo.countByStatus("Rejected"));

        return dto;
    }

    public List<ProfileDto> getAllUsers() {
       List< Users> uu=userRepo.findAll();
       List<ProfileDto> pp=new ArrayList<>();
       for(Users uu1:uu){
           ProfileDto pp1=new ProfileDto();
           pp1.setEmail(uu1.getEmail());
           pp1.setFullName(uu1.getUsername());
           pp1.setRegNo(uu1.getRegiNo());
           pp.add(pp1);

       }
        return pp;
    }

    public void deleteUser(String regNo) {
        Users u = userRepo.findByRegiNo(regNo);
        if (u != null) userRepo.delete(u);
    }
}
