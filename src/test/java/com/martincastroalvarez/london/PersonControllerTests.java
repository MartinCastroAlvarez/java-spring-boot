package com.martincastroalvarez.london;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
 
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PersonControllerTests {
 
  @Autowired
  PersonController personController;
 
  @Test
  public void contextLoads() {
    Assertions.assertThat(personController).isNot(null);
  }
}
