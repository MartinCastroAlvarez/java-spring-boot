package com.martincastroalvarez.london;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.spec.InvalidKeySpecException;

@RestController
@RequestMapping("/api/v1/auth")
public class UserController {
    // --------------------------------------------------------------------
    // User API Resource.
    //
    // This controllers handles requests to the `/api/login`,
    // `/api/logout`, `/api/signup` and `api/session` endpoints.
    // --------------------------------------------------------------------

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService service;

    /*
	@GetMapping("/session")
	public User details() throws UserNotFoundError {
        // --------------------------------------------------------------------
        // GET method handler.
        // 
        // Returns the details of the logged in user.
        // --------------------------------------------------------------------
        logger.info("Controller | User | Details: " + id);
        return service.get(id);
	}
    */

    /*
	@PutMapping("/{id}")
    public User update(@PathVariable long id, @Valid @RequestBody UserUpdateRequest request)
        throw InvalidKeySpecException {
        throws UserNotFoundError {
        // --------------------------------------------------------------------
        // PUT method handler.
        //
        // Updates an existing User.
        // --------------------------------------------------------------------
        logger.info("Controller | User | Update: " + id + " " + request);
        return service.update(id, request.getName());
    }
    */

	@PostMapping("/signup")
    public User signup(@Valid @RequestBody SignUpRequest request)
        throws InvalidKeySpecException {
        // --------------------------------------------------------------------
        // POST method handler.
        //
        // Creates a new User.
        // --------------------------------------------------------------------
        logger.info("Controller | User | Create: " + request);
        return service.create(request.getName(), request.getPassword());
    }

}
