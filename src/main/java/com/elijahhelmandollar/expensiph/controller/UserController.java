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
    public String registerUser(@ModelAttribute UserRegistrationDto userDto, Model model) {

        if (!userDto.getPassword().equals(userDto.getConfirmPassword())) {

            model.addAttribute("error", "Please ensure that both password fields match.");
            return "registration";

        }

        // Verify that the user does not already exist.
        if (userRepository.findByUsername(userDto.getUsername()) != null) {

            // TODO: Does this display on screen manually or automatically?
            model.addAttribute("error", "The username provide is already taken.");
            return "registration";

        }

        // Create the "User" and assign its fields.
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

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