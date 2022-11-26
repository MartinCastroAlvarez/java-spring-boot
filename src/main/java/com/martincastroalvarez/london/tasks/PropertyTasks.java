package com.martincastroalvarez.london;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;
import org.springframework.scheduling.annotation.Async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PropertyTasks {
    // --------------------------------------------------------------------
    // Property async tasks
    // --------------------------------------------------------------------

    @Autowired
    private PropertyRepository properties;

    @Autowired
    private JobRepository jobs;

    private Logger logger = LoggerFactory.getLogger(PropertyTasks.class);

    @Async
    public void delete(Job job, long id) throws InterruptedException {
        // --------------------------------------------------------------------
        // Deleting a Property by id.
        // --------------------------------------------------------------------
        logger.info("Task | Property | Delete: " + id);
        job.start();
        try {
            Property property = properties.findById(id).orElseThrow(() -> new PropertyNotFoundError());
            properties.delete(property);
            job.end();
        } catch (PropertyNotFoundError error) {
            job.fail("Property now found!");
        } finally {
            jobs.save(job);
        }
    }
}
