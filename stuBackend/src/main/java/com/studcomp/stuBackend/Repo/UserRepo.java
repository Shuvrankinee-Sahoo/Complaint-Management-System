package com.studcomp.stuBackend.Repo;

import com.studcomp.stuBackend.Entitys.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<Users, Integer> {
    Optional<Users> findByEmail(String email);

    Users findByRegiNo(String regNo);

}
