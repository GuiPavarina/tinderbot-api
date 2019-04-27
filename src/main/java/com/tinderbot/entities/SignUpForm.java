package com.tinderbot.entities;

import javax.validation.constraints.*;

public class SignUpForm {

    @Size(min = 3, max = 50)
    private String username;

    @Size(max = 60)
    private String email;
       
    @Size(min = 6, max = 40)
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}