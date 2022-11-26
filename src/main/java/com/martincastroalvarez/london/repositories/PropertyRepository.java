package com.martincastroalvarez.london;

import java.util.List;

import org.springframework.stereotype.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

@Repository
public interface PropertyRepository extends CrudRepository<Property, Long> {
    // --------------------------------------------------------------------
    // Property model repository.
    //
    // By extending from the Spring CrudRepository, we will have some
    // methods for our data repository implemented, including findAll().
    // --------------------------------------------------------------------

    List<Property> findAll();

    Page<Property> findAll(Pageable pageable);

    @Query("FROM Property p WHERE p.name LIKE CONCAT('%', :name, '%')")
    Page<Property> findByName(String name, Pageable pageable);
}
