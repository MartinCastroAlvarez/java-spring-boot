package com.martincastroalvarez.london;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;
import org.springframework.scheduling.annotation.Async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonTasks {
    // --------------------------------------------------------------------
    // Person async tasks
    // --------------------------------------------------------------------

    @Autowired
    private PersonRepository persons;

    @Autowired
    private JobRepository jobs;

    private Logger logger = LoggerFactory.getLogger(PersonTasks.class);

    @Async
    public void delete(Job job, long id) throws InterruptedException {
        // --------------------------------------------------------------------
        // Deleting a Person by id.
        // --------------------------------------------------------------------
        logger.info("Task | Person | Delete: " + id);
        Thread.sleep(3000);
        job.start();
        try {
            Person person = persons.findById(id).orElseThrow(() -> new PersonNotFoundError());
            persons.delete(person);
            job.end();
        } catch (PersonNotFoundError error) {
            job.fail("Person now found!");
        } finally {
            jobs.save(job);
        }
    }
}
