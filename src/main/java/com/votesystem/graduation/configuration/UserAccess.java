package com.votesystem.graduation.configuration;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.*;

@PreAuthorize("hasRole('ROLE_USER')")
@Retention(RetentionPolicy.RUNTIME)
public @interface UserAccess {
}
