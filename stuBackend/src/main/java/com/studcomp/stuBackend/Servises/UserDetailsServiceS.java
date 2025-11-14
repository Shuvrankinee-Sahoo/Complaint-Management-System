package com.studcomp.stuBackend.Servises;

import com.studcomp.stuBackend.Entitys.Users;
import com.studcomp.stuBackend.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceS implements UserDetailsService {
    @Autowired
    UserRepo repo;

    @Override
    public UserDetails loadUserByUsername(String emali) throws UsernameNotFoundException {
        System.out.println("LoadByuser");

        Users uu=repo.findByEmail(emali).orElseThrow(()->new UsernameNotFoundException("No User Not found"));
        return new myuser(uu);
    }

}
