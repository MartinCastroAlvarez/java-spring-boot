package com.martincastroalvarez.london;
 
import java.util.Optional;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
 
@ExtendWith(SpringExtension.class)
@WebMvcTest(PersonController.class)
public class PersonApiTests {
 
    @MockBean
    PersonService personService;
 
    @Autowired
    MockMvc mockMvc;
 
    @Test
    public void testfindAll() throws Exception {
        Person person = new Person();
        person.setName("C++");
        List<Person> persons = Arrays.asList(person);
        Mockito.when(personService.list(
            Optional.of(""), Optional.of(""), Optional.of(0), Optional.of(0)
        )).thenReturn(persons);
        mockMvc.perform(get("/api/v1/persons"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", Matchers.hasSize(1)))
            .andExpect(jsonPath("$[0].name", Matchers.is("C++")));
    }
}
