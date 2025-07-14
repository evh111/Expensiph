package com.elijahhelmandollar.expensiph.dto;

public class UserRegistrationDto {

    private String username;
    private String password;
    private String confirmPassword;

    // Accessors and mutators.
    public String getUsername() {

        return this.username;

    }

    public void setUsername(String username) {

        this.username = username;

    }

    public String getPassword() {

        return this.password;

    }

    public void setPassword(String password) {

        this.password = password;

    }

    public String getConfirmPassword() {

        return this.confirmPassword;

    }

    public void setConfirmPassword(String confirmPassword) {

        this.confirmPassword = confirmPassword;

    }

}
