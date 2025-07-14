package com.elijahhelmandollar.expensiph.controller;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import com.elijahhelmandollar.expensiph.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.elijahhelmandollar.expensiph.dao.UserRepository;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.elijahhelmandollar.expensiph.dto.UserRegistrationDto;
import org.springframework.security.crypto.password.PasswordEncoder;

@Controller
public class UserController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;

    }

    // Serve the user registration form.
    @GetMapping("/register")
    public String registerUserForm(Model model) {

        model.addAttribute("user", new User());

        return "registration";

    }

    // Process the user registration form.
    @PostMapping("/register")
    public String registerUser(@ModelAttribute UserRegistrationDto userRegistrationDto, Model model) {

        // Enforce username length maximum.
        if (userRegistrationDto.getUsername().length() > 25) {

            model.addAttribute("error", "The username must be between 1 and 25 characters.");
            return "registration";

        }

        // Enforce password length minimum and maximum.
        if (userRegistrationDto.getPassword().length() < 8 ||  userRegistrationDto.getPassword().length() > 64) {

            model.addAttribute("error", "The password must be between 8 and 64 characters.");
            return "registration";

        }

        // Verify that both password fields match.
        if (!userRegistrationDto.getPassword().equals(userRegistrationDto.getConfirmPassword())) {

            model.addAttribute("error", "Please ensure that both password fields match.");
            return "registration";

        }

        // Verify that the user does not already exist.
        if (userRepository.findByUsername(userRegistrationDto.getUsername()) != null) {

            model.addAttribute("error", "The username you've provided is already in use.");
            return "registration";

        }

        // Create the "User" and assign its fields.
        User user = new User();
        user.setUsername(userRegistrationDto.getUsername().trim());
        user.setPassword(passwordEncoder.encode(userRegistrationDto.getPassword().trim()));

        // Save the user.
        userRepository.save(user);

        return "redirect:/login";

    }

    // Serve the user login form.
    @GetMapping("/login")
    public String userLoginForm() {

        // All login functionality is handled by default within the "SecurityConfig" class.
        return "login";

    }

}