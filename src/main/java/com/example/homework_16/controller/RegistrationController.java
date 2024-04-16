package com.example.homework_16.controller;

import com.example.homework_16.model.entity.Role;
import com.example.homework_16.model.entity.Status;
import com.example.homework_16.model.entity.User;
import com.example.homework_16.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@AllArgsConstructor
@RequestMapping("/user")
public class RegistrationController {
    private UserService userService;

    @PostMapping("/registration")
    public String save(@RequestParam String username,
                       @RequestParam String password,
                       @RequestParam String passwordRepeat,
                       RedirectAttributes ra) {
        if (userService.getByName(username) != null) {
            ra.addFlashAttribute("error", "login");
            ra.addFlashAttribute("name", username);
            return "redirect:/registration";
        }
        if (!password.equals(passwordRepeat)) {
            ra.addFlashAttribute("error", "password");
            ra.addFlashAttribute("name", username);
            return "redirect:/registration";
        }
        userService.save(User.builder()
                .username(username)
                .password(new BCryptPasswordEncoder(12).encode(password))
                .role(Role.USER)
                .status(Status.ACTIVE)
                .build());
        return "redirect:/login";
    }
}
