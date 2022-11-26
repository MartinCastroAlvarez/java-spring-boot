package com.martincastroalvarez.london;

import org.springframework.stereotype.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {
    // --------------------------------------------------------------------
    // Person model repository.
    //
    // By extending from the Spring CrudRepository, we will have some
    // methods for our data repository implemented, including findAll().
    // --------------------------------------------------------------------
    
    Page<Person> findAll(Pageable pageable);

    @Query("FROM Person p WHERE p.name LIKE CONCAT('%', :name, '%')")
    Page<Person> findByName(String name, Pageable pageable);
}
