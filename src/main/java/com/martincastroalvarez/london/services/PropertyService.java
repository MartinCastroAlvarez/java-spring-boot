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
public class PropertyService {
    // --------------------------------------------------------------------
    // Property business logic.
    // --------------------------------------------------------------------

    @Autowired
    private PropertyRepository repository;

    @Autowired
    private JobRepository jobs;

    @Autowired
    private PropertyTasks tasks;

    @Autowired
    private PersonService persons;

    private Logger logger = LoggerFactory.getLogger(PropertyService.class);

    public List<Property> list(
        Optional<String> name,
        Optional<String> sort_key,
        Optional<Integer> page_limit,
        Optional<Integer> page_offset
    ) {
        // --------------------------------------------------------------------
        // Listing all Properties.
        // Supports pagination and sorting by different attributes.
        // --------------------------------------------------------------------
        logger.info("Service | Property | Search" + name + " " + sort_key + " " + page_offset);
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        if (sort_key.isPresent()) {
            sort = Sort.by(Sort.Direction.ASC, sort_key.get());
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
        List<Property> properties;
        if (name.isPresent()) {
            properties = repository.findByName(name.get(), page).getContent();
        } else {
            properties = repository.findAll(page).getContent();
        }
        return properties;
    }

    public Property get(long id) throws PropertyNotFoundError {
        // --------------------------------------------------------------------
        // Getting a Property by id.
        // --------------------------------------------------------------------
        logger.info("Service | Property | Get by ID: " + id);
        Property property = repository.findById(id).orElseThrow(() -> new PropertyNotFoundError());
        return property;
    }

    public Property create(String name, Long ownerId) throws PersonNotFoundError {
        // --------------------------------------------------------------------
        // Creating a Property.
        // --------------------------------------------------------------------
        logger.info("Service | Property | Create: " + name + " " + ownerId);
        Property property = new Property();
        property.setName(name);
        property.setOwner(persons.get(ownerId));
        repository.save(property);
        return property;
    }

    public Property update(long id, Optional<String> name, Optional<Long> ownerId)
        throws PropertyNotFoundError, PersonNotFoundError {
        // --------------------------------------------------------------------
        // Updating a Property by id.
        // --------------------------------------------------------------------
        logger.info("Service | Property | Update: " + name + " " + ownerId);
        Property property = this.get(id);
        if (name.isPresent()) {
            property.setName(name.get());
        }
        if (ownerId.isPresent()) {
            property.setOwner(persons.get(ownerId.get()));
        }
        repository.save(property);
        return property;
    }

    public Job delete(long id) {
        // --------------------------------------------------------------------
        // Deleting a Property by id.
        // --------------------------------------------------------------------
        logger.info("Service | Property | Delete: " + id);
        Job job = new Job();
        jobs.save(job);
        try {
            tasks.delete(job, id);
        } catch (InterruptedException error) {}
        return job;
    }

}
