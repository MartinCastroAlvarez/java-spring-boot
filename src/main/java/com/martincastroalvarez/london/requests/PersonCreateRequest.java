package com.martincastroalvarez.london;

import javax.validation.constraints.Size;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class PersonCreateRequest {
    // --------------------------------------------------------------------
    // This class is used to accept requests to create Persons.
    // --------------------------------------------------------------------

    @NotEmpty(message="Name must not be empty.")
    @NotBlank(message="Name is required.")
    @Size(min=8, max=50, message="Invalid name length.")
    private String name;

    public void setName(String name) { this.name = name; }
    public String getName() { return name; }
}
