package com.martincastroalvarez.london;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PersonRepositoryTests {

  @Autowired
  PersonRepository personRepository;

  @Test
  public void testCreateReadDelete() {
    Person person = new Person();
    person.setName("C++");

    personRepository.save(person);

    Sort sort = Sort.by(Sort.Direction.ASC, "id");
    Pageable page = PageRequest.of(0, 10, sort);

    Iterable<Person> persons = personRepository.findAll();
    Assertions.assertThat(persons).extracting(Person::getName).containsOnly("C++");

    personRepository.deleteAll();
    Assertions.assertThat(personRepository.findAll()).isEmpty();
  }
}
