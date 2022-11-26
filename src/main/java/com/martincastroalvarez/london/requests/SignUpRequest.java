package com.martincastroalvarez.london;

import javax.validation.constraints.Size;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class SignUpRequest {
    // --------------------------------------------------------------------
    // This class is used to accept requests to create Users.
    // --------------------------------------------------------------------

    @NotEmpty(message="Name must not be empty.")
    @NotBlank(message="Name is required.")
    @Size(min=8, max=50, message="Invalid name length.")
    private String name;

    @NotEmpty(message="Password must not be empty.")
    @NotBlank(message="Password is required.")
    @Size(min=8, max=50, message="Invalid password length.")
    private String password;

    public void setName(String name) { this.name = name; }
    public String getName() { return name; }

    public void setPassword(String password) { this.password = password; }
    public String getPassword() { return password; }
}
