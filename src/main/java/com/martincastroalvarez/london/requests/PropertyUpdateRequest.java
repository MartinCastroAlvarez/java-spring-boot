package com.martincastroalvarez.london;

import java.util.Optional;

import lombok.Data;

@Data
public class PropertyUpdateRequest {
    // --------------------------------------------------------------------
    // This class is used to accept requests to update Properties.
    // --------------------------------------------------------------------

    private Optional<String> name;
    private Optional<Long> ownerId;

    public void setName(Optional<String> name) { this.name = name; }
    public Optional<String> getName() { return name; }

    public void setOwnerId(Optional<Long> ownerId) { this.ownerId = ownerId; }
    public Optional<Long> getOwnerId() { return ownerId; }
}
