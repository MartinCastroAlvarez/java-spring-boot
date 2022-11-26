package com.martincastroalvarez.london;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class UserNotFoundError extends Exception {
    // --------------------------------------------------------------------
    // Custom exception raised when a User is not found.
    // --------------------------------------------------------------------
}
