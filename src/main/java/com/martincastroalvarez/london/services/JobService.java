package com.martincastroalvarez.london;

import java.util.Optional;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@Service
public class JobService {
    // --------------------------------------------------------------------
    // Job business logic.
    // --------------------------------------------------------------------

    @Autowired
    private JobRepository repository;

    private Logger logger = LoggerFactory.getLogger(JobService.class);

    public List<Job> list(
        Optional<String> sort_key,
        Optional<Integer> page_limit,
        Optional<Integer> page_offset
    ) {
        // --------------------------------------------------------------------
        // Listing all Jobs.
        // Supports pagination and sorting by different attributes.
        // --------------------------------------------------------------------
        logger.info("Service | Job | Search" + sort_key + " " + page_offset);
        Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
        if (sort_key.isPresent()) {
            sort = Sort.by(Sort.Direction.DESC, sort_key.get());
        }
        Integer limit = 10;
        if (page_limit.isPresent()) {
            limit = page_limit.get();
        }
        Integer offset = 0;
        if (page_offset.isPresent()) {
            offset = page_offset.get();
        }
        Pageable page = PageRequest.of(offset, limit, sort);
        List<Job> jobs;
        jobs = repository.findAll(page).getContent();
        return jobs;
    }

    public Job get(long id) throws JobNotFoundError {
        // --------------------------------------------------------------------
        // Getting a Job by id.
        // --------------------------------------------------------------------
        logger.info("Service | Job | Get by ID: " + id);
        Job job = repository.findById(id).orElseThrow(() -> new JobNotFoundError());
        return job;
    }

}
