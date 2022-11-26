package com.martincastroalvarez.london;

import java.util.List;
import java.util.Date;

import org.springframework.stereotype.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

@Repository
public interface JobRepository extends CrudRepository<Job, Long> {
    // --------------------------------------------------------------------
    // Job model repository.
    //
    // By extending from the Spring CrudRepository, we will have some
    // methods for our data repository implemented, including findAll().
    // --------------------------------------------------------------------
    
    List<Job> findAll();

    Page<Job> findAll(Pageable pageable);
}
