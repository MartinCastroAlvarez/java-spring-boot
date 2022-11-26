package com.martincastroalvarez.london;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTests {

    @InjectMocks
    PersonService service;

    @Mock
    PersonRepository dao;

    @Test
    public void testFindAllPersons() {

        List<Person> list = new ArrayList<Person>();
        Person p1 = new Person();
        Person p2 = new Person();
        Person p3 = new Person();

        p1.setName("C++");
        p1.setName("Python");
        p1.setName("Perl");

        list.add(p1);
        list.add(p2);
        list.add(p3);

        List<Person> perList = service.list(
            Optional.of(""), Optional.of(""), Optional.of(0), Optional.of(0)
        );

        assertEquals(3, perList.size());
        verify(dao, times(1)).findAll();
    }

    @Test
    public void testCreateOrSavePerson()
    {
        Person person = service.create("C++");
        assertEquals(person.getName(), "C++");
    }
}
