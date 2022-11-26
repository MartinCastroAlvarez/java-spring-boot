package com.martincastroalvarez.london;

import java.util.Optional;
import java.util.List;

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

@RestController
@RequestMapping("/api/v1/persons")
public class PersonController {
    // --------------------------------------------------------------------
    // Person API Resource.
    //
    // This controllers handles requests to the `/persons` and
    // `persons/:id` endpoints.
    // --------------------------------------------------------------------

    private Logger logger = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    private PersonService service;

	@GetMapping("/{id}")
	public Person details(@PathVariable long id) throws PersonNotFoundError {
        // --------------------------------------------------------------------
        // GET method handler.
        // 
        // Returns the details of a Person by Person id.
        // --------------------------------------------------------------------
        logger.info("Controller | Person | Details: " + id);
        return service.get(id);
	}

	@GetMapping("/")
    public List<Person> search(
        @RequestParam Optional<String> name,
        @RequestParam Optional<String> sort_key,
        @RequestParam Optional<Integer> limit,
        @RequestParam Optional<Integer> offset
    ) throws PersonNotFoundError {
        // --------------------------------------------------------------------
        // GET method handler.
        //
        // Returns a list of Persons.
        // --------------------------------------------------------------------
        logger.info("Controller | Person | List: " + name);
        return service.list(name, sort_key, limit, offset);
    }

	@PostMapping("/")
    public Person create(@Valid @RequestBody PersonCreateRequest request) {
        // --------------------------------------------------------------------
        // POST method handler.
        //
        // Creates a new Person.
        // --------------------------------------------------------------------
        logger.info("Controller | Person | Create: " + request);
        return service.create(request.getName());
    }

	@PutMapping("/{id}")
    public Person update(@PathVariable long id, @Valid @RequestBody PersonUpdateRequest request)
        throws PersonNotFoundError {
        // --------------------------------------------------------------------
        // PUT method handler.
        //
        // Updates an existing Person.
        // --------------------------------------------------------------------
        logger.info("Controller | Person | Update: " + id + " " + request);
        return service.update(id, request.getName());
    }

	@DeleteMapping("/{id}")
	public Job delete(@PathVariable long id) throws PersonNotFoundError {
        // --------------------------------------------------------------------
        // DELETE method handler.
        //
        // Deletes an existing Person.
        // --------------------------------------------------------------------
        logger.info("Controller | Person | Delete: " + id);
        return service.delete(id);
    }

}
