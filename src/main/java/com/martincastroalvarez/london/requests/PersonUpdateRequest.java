package com.martincastroalvarez.london;

import java.util.Optional;

import lombok.Data;

@Data
public class PersonUpdateRequest {
    // --------------------------------------------------------------------
    // This class is used to accept requests to update Persons.
    // --------------------------------------------------------------------

    private Optional<String> name;

    public void setName(Optional<String> name) { this.name = name; }
    public Optional<String> getName() { return name; }
}
