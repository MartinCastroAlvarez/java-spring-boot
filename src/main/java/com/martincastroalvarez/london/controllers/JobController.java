package com.martincastroalvarez.london;

import java.util.Optional;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/v1/jobs")
public class JobController {
    // --------------------------------------------------------------------
    // Job API Resource.
    //
    // This controllers handles requests to the `/jobs` and
    // `jobs/:id` endpoints.
    // --------------------------------------------------------------------

    private Logger logger = LoggerFactory.getLogger(JobController.class);

    @Autowired
    private JobService service;

	@GetMapping("/{id}")
	public Job details(@PathVariable long id) throws JobNotFoundError {
        // --------------------------------------------------------------------
        // GET method handler.
        // 
        // Returns the details of a Job by Job id.
        // --------------------------------------------------------------------
        logger.info("Controller | Job | Details: " + id);
        return service.get(id);
	}

	@GetMapping("/")
    public List<Job> search(
        @RequestParam Optional<String> sort_key,
        @RequestParam Optional<Integer> limit,
        @RequestParam Optional<Integer> offset
    ) throws JobNotFoundError {
        // --------------------------------------------------------------------
        // GET method handler.
        //
        // Returns a list of Jobs.
        // --------------------------------------------------------------------
        logger.info("Controller | Job | List");
        return service.list(sort_key, limit, offset);
    }

}
