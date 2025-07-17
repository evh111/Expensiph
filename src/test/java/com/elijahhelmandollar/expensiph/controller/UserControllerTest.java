package com.elijahhelmandollar.expensiph.controller;

import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.*;
import static org.junit.jupiter.api.Assertions.*;
import com.elijahhelmandollar.expensiph.entity.User;
import com.elijahhelmandollar.expensiph.dao.UserRepository;
import com.elijahhelmandollar.expensiph.dto.UserRegistrationDto;
import org.springframework.security.crypto.password.PasswordEncoder;

class UserControllerTest {

    @Mock
    private Model model;

    @InjectMocks
    private UserController userController;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);

    }

    // Test user registration if the input is valid.
    @Test
    void testRegisterUser_SuccessfulRegistration() {

        UserRegistrationDto dto = new UserRegistrationDto();

        dto.setUsername("newuser");
        dto.setPassword("Password1");
        dto.setConfirmPassword("Password1");

        when(userRepository.findByUsername("newUser")).thenReturn(null);
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");

        String viewName = userController.registerUser(dto, model);

        verify(userRepository).save(any(User.class));

        assertEquals("redirect:/login", viewName);

    }

    // Test user registration if the username is already in use.
    @Test
    void testRegisterUser_UsernameAlreadyExists() {

        UserRegistrationDto dto = new UserRegistrationDto();

        dto.setUsername("testusername");
        dto.setPassword("password");
        dto.setConfirmPassword("password");

        when(userRepository.findByUsername("testusername")).thenReturn(new User());

        String viewName = userController.registerUser(dto, model);

        verify(model).addAttribute(eq("error"), eq("The username you've provided is already in use."));

        assertEquals("registration", viewName);

    }

}