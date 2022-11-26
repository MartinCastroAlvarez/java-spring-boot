package com.martincastroalvarez.london;

import org.springframework.stereotype.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    // --------------------------------------------------------------------
    // User model repository.
    //
    // By extending from the Spring CrudRepository, we will have some
    // methods for our data repository implemented, including findAll().
    // --------------------------------------------------------------------
    
    Page<User> findAll(Pageable pageable);

    @Query("FROM User u WHERE u.name LIKE CONCAT('%', :name, '%')")
    Page<User> findByName(String name, Pageable pageable);
}
