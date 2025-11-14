package com.studcomp.stuBackend.Repo;

import com.studcomp.stuBackend.Entitys.Complaints;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ComplaintsRepo extends JpaRepository<Complaints, Integer> {
    List<Complaints> findByMail(String email);
    long countByMail(String email);
    long countByMailAndStatus(String email, String status);
    long countByStatus(String pending);
}
