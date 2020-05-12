package com.votesystem.graduation.configuration;

import com.votesystem.graduation.model.Role;
import com.votesystem.graduation.model.User;

import java.util.Set;

public class AuthUser extends org.springframework.security.core.userdetails.User {

    private int id;
    private Set<Role> roles;

    public AuthUser(User user) {
        super(user.getEmail(),
                user.getPassword(),
                true,
                true,
                true,
                true,
                user.getRoles());

        this.id = user.getId();
        this.roles = user.getRoles();
    }

    public int getId() {
        return id;
    }

    public Set<Role> getRoles() {
        return roles;
    }
}
