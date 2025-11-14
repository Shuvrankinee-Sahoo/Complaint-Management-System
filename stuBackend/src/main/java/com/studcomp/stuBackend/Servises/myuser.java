package com.studcomp.stuBackend.Servises;

import com.studcomp.stuBackend.Entitys.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
public class myuser implements UserDetails {

    Users uu;

    public myuser(Users uu) {
        this.uu=uu;

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return uu.getPassword() ;
    }

    @Override
    public String getUsername() {
        System.out.println("SujitMyusername");

        return uu.getEmail();
    }
    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }

}
