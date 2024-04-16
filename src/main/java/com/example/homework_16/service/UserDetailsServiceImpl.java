package com.example.homework_16.service;

import com.example.homework_16.model.entity.SecurityUser;
import com.example.homework_16.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return SecurityUser.fromEntity(userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователя с указанным именем не существует")));
    }
}
