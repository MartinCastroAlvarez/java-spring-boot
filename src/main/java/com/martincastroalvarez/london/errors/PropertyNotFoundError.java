package com.martincastroalvarez.london;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class PropertyNotFoundError extends Exception {
    // --------------------------------------------------------------------
    // Custom exception raised when a Property is not found.
    // --------------------------------------------------------------------
}
