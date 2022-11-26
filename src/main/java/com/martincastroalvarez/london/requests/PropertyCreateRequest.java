package com.martincastroalvarez.london;

import javax.validation.constraints.Size;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class PropertyCreateRequest {
    // --------------------------------------------------------------------
    // This class is used to accept requests to create Properties.
    // --------------------------------------------------------------------

    @NotEmpty(message="Name must not be empty.")
    @NotBlank(message="Name is required.")
    @Size(min=8, max=50, message="Invalid name length.")
    private String name;

    private Long ownerId;

    public void setName(String name) { this.name = name; }
    public String getName() { return name; }

    public void setOwnerId(Long ownerId) { this.ownerId = ownerId; }
    public Long getOwnerId() { return ownerId; }

}
