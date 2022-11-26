package com.martincastroalvarez.london;

import java.util.Optional;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/v1/properties")
public class PropertyController {
    // --------------------------------------------------------------------
    // Property API Resource.
    //
    // This controllers handles requests to the `/properties` and
    // `properties/:id` endpoints.
    // --------------------------------------------------------------------

    private Logger logger = LoggerFactory.getLogger(PropertyController.class);

    @Autowired
    private PropertyService service;

	@GetMapping("/{id}")
	public Property details(@PathVariable long id) throws PropertyNotFoundError {
        // --------------------------------------------------------------------
        // GET method handler.
        // 
        // Returns the details of a Property by Property id.
        // --------------------------------------------------------------------
        logger.info("Controller | Property | Details: " + id);
        return service.get(id);
	}

	@GetMapping("/")
    public List<Property> search(
        @RequestParam Optional<String> name,
        @RequestParam Optional<String> sort_key,
        @RequestParam Optional<Integer> limit,
        @RequestParam Optional<Integer> offset
    ) throws PropertyNotFoundError {
        // --------------------------------------------------------------------
        // GET method handler.
        //
        // Returns a list of Properties.
        // --------------------------------------------------------------------
        logger.info("Controller | Property | List: " + name);
        return service.list(name, sort_key, limit, offset);
    }

	@PostMapping("/")
    public Property create(@Valid @RequestBody PropertyCreateRequest request)
        throws PersonNotFoundError {
        // --------------------------------------------------------------------
        // POST method handler.
        //
        // Creates a new Property.
        // --------------------------------------------------------------------
        logger.info("Controller | Property | Create: " + request);
        return service.create(request.getName(), request.getOwnerId());
    }

	@PutMapping("/{id}")
    public Property update(@PathVariable long id, @Valid @RequestBody PropertyUpdateRequest request)
        throws PropertyNotFoundError, PersonNotFoundError {
        // --------------------------------------------------------------------
        // PUT method handler.
        //
        // Updates an existing Property.
        // --------------------------------------------------------------------
        logger.info("Controller | Property | Update: " + id + " " + request);
        return service.update(id, request.getName(), request.getOwnerId());
    }

	@DeleteMapping("/{id}")
	public Job delete(@PathVariable long id) throws PropertyNotFoundError {
        // --------------------------------------------------------------------
        // DELETE method handler.
        //
        // Deletes an existing Property.
        // --------------------------------------------------------------------
        logger.info("Controller | Property | Delete: " + id);
        return service.delete(id);
    }

}
