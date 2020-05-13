package com.votesystem.graduation.configuration;


import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthAccess {
}
