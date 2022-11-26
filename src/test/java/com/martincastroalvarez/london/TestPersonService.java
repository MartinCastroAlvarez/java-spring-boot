package com.martincastroalvarez.london;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.test.context.junit4.SpringRunner;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class TestPersonService {

    @Autowired
    private PersonRepository repository;

    @Autowired
    private PersonService service;

    @BeforeEach
    void setUp() {
        Person person1 = new Person();
        person1.setName("Java");
        repository.save(person1);
        Person person2 = new Person();
        person2.setName("Python");
        repository.save(person2);
        Person person3 = new Person();
        person3.setName("Javascript");
        repository.save(person3);
        Person person4 = new Person();
        person4.setName("Perl");
        repository.save(person4);
    }

    @Test
    void testCreate() throws Exception {
        Person person = service.create("C++");
        assertThat(person.getId()).isNotNull();
        assertThat(person.getName()).isEqualTo("C++1");
        throw new Exception("Exception message");
    }

}
