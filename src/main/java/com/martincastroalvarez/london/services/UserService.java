package com.martincastroalvarez.london;

import java.util.Optional;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.spec.InvalidKeySpecException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@Service
public class UserService {
    // --------------------------------------------------------------------
    // User business logic.
    // --------------------------------------------------------------------

    @Autowired
    private UserRepository repository;

    private Logger logger = LoggerFactory.getLogger(UserService.class);

    public List<User> list(
        Optional<String> name,
        Optional<String> sort_key,
        Optional<Integer> page_limit,
        Optional<Integer> page_offset
    ) {
        // --------------------------------------------------------------------
        // Listing all Users.
        // Supports pagination and sorting by different attributes.
        // --------------------------------------------------------------------
        logger.info("Service | User | Search" + name + " " + sort_key + " " + page_offset);
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
        List<User> users;
        if (name.isPresent()) {
            users = repository.findByName(name.get(), page).getContent();
        } else {
            users = repository.findAll(page).getContent();
        }
        return users;
    }

    public User get(long id) throws UserNotFoundError {
        // --------------------------------------------------------------------
        // Getting a USer by id.
        // --------------------------------------------------------------------
        logger.info("Service | User | Get by ID: " + id);
        User user = repository.findById(id).orElseThrow(() -> new UserNotFoundError());
        return user;
    }

    public User create(String name, String password)
        throws java.security.spec.InvalidKeySpecException {
        // --------------------------------------------------------------------
        // Creating a User.
        // --------------------------------------------------------------------
        logger.info("Service | User | Create: " + name);
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        repository.save(user);
        return user;
    }

    public User update(long id, Optional<String> name, Optional<String> password)
        throws java.security.spec.InvalidKeySpecException, UserNotFoundError {
        // --------------------------------------------------------------------
        // Updating a User by id.
        // --------------------------------------------------------------------
        logger.info("Service | User | Update: " + name);
        User user = this.get(id);
        if (name.isPresent()) {
            user.setName(name.get());
        }
        if (password.isPresent()) {
            user.setPassword(password.get());
        }
        repository.save(user);
        return user;
    }

    public void delete(long id) throws UserNotFoundError {
        // --------------------------------------------------------------------
        // Deleting a User by id.
        // --------------------------------------------------------------------
        logger.info("Service | User | Delete: " + id);
        User user = this.get(id);
        repository.delete(user);
    }
}
