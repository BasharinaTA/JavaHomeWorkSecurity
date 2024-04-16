package com.example.homework_16.model.entity;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum Role {
    ADMIN(Set.of(Authority.READ, Authority.WRITE)),
    USER(Set.of(Authority.READ));

    private final Set<Authority> authorities;

    public Set<SimpleGrantedAuthority> getAuthorities() {
        return authorities.stream()
                .map(auth -> new SimpleGrantedAuthority(auth.name()))
                .collect(Collectors.toSet());
    }
    Role(Set<Authority> authorities) {
        this.authorities = authorities;
    }
}
