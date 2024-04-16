package com.example.homework_16.model.middleware;

import com.example.homework_16.model.entity.Role;
import com.example.homework_16.model.entity.User;
import com.example.homework_16.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

@Component
@AllArgsConstructor
public class HeaderAttributeFilter implements Filter {
    private UserRepository userRepository;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        setAttrs(servletRequest);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private void setAttrs(ServletRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            request.setAttribute("auth", false);
        } else {
            if (auth.getPrincipal().equals("anonymousUser")) {
                request.setAttribute("auth", false);
            } else {
                request.setAttribute("auth", true);
                request.setAttribute("username", auth.getName());
                User user = userRepository.findByUsername(auth.getName()).get();
                request.setAttribute("canWrite", user.getRole().equals(Role.ADMIN));
            }
        }
    }
}
