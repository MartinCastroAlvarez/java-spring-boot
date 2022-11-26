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
public class PersonService {
    // --------------------------------------------------------------------
    // Person business logic.
    // --------------------------------------------------------------------

    @Autowired
    private PersonRepository repository;

    @Autowired
    private JobRepository jobs;

    @Autowired
    private PersonTasks tasks;

    private Logger logger = LoggerFactory.getLogger(PersonService.class);

    public List<Person> list(
        Optional<String> name,
        Optional<String> sort_key,
        Optional<Integer> page_limit,
        Optional<Integer> page_offset
    ) {
        // --------------------------------------------------------------------
        // Listing all Persons.
        // Supports pagination and sorting by different attributes.
        // --------------------------------------------------------------------
        logger.info("Service | Person | Search" + name + " " + sort_key + " " + page_offset);
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
        List<Person> persons;
        if (name.isPresent()) {
            persons = repository.findByName(name.get(), page).getContent();
        } else {
            persons = repository.findAll(page).getContent();
        }
        return persons;
    }

    public Person get(long id) throws PersonNotFoundError {
        // --------------------------------------------------------------------
        // Getting a Person by id.
        // --------------------------------------------------------------------
        logger.info("Service | Person | Get by ID: " + id);
        Person person = repository.findById(id).orElseThrow(() -> new PersonNotFoundError());
        return person;
    }

    public Person create(String name) {
        // --------------------------------------------------------------------
        // Creating a Person.
        // --------------------------------------------------------------------
        logger.info("Service | Person | Create: " + name);
        Person person = new Person();
        person.setName(name);
        repository.save(person);
        return person;
    }

    public Person update(long id, Optional<String> name) throws PersonNotFoundError {
        // --------------------------------------------------------------------
        // Updating a Person by id.
        // --------------------------------------------------------------------
        logger.info("Service | Person | Update: " + name);
        Person person = this.get(id);
        if (name.isPresent()) {
            person.setName(name.get());
        }
        repository.save(person);
        return person;
    }

    public Job delete(long id) {
        // --------------------------------------------------------------------
        // Deleting a Person by id.
        // --------------------------------------------------------------------
        logger.info("Service | Person | Delete: " + id);
        Job job = new Job();
        jobs.save(job);
        try {
            tasks.delete(job, id);
        } catch (InterruptedException error) {}
        return job;
    }

}
