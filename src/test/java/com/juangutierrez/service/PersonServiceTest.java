package com.juangutierrez.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.juangutierrez.document.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import reactor.test.StepVerifier;

@SpringBootTest
public class PersonServiceTest {

    @Autowired
    PersonService personService;

    Person data;

    @Test
    public void when_findAllPersons_ok(){
        data = new Person();
        data.setId("628282da1af4ea02ff5316a9");
        data.setDocument("87654321");
        data.setNames("Carlos");
        data.setLastnames("Quispe Martines");
        data.setStatus(1);
        StepVerifier.create(personService.findAll())
            .expectNext(data)
            .verifyComplete();
    }

    @Test
    public void when_savePerson_ok(){
        data = new Person();
        data.setDocument("87654321");
        data.setNames("Carlos");
        data.setLastnames("Quispe Martines");
        data.setStatus(1);

        StepVerifier.create(personService.save(data))
            .expectNext(data)
            .expectComplete()
            .verify();

    }

    @Test
    public void when_updatePerson_ok(){
        data = new Person();
        data.setId("62828285278d946ee09f5dac");
        data.setDocument("87654321");
        data.setNames("Carlos");
        data.setLastnames("Quispe Martines");
        data.setStatus(1);

        StepVerifier.create(personService.update(data))
            .expectNext(data)
            .expectComplete()
            .verify();

    }

    @Test
    public void when_getId_ok(){
        data = new Person();
        data.setId("62828285278d946ee09f5dac");
        data.setDocument("87654321");
        data.setNames("Carlos");
        data.setLastnames("Quispe Martines");
        data.setStatus(1);

        StepVerifier.create(personService.getId("62828285278d946ee09f5dac"))
            .expectNext(data)
            .expectComplete()
            .verify();
    }

    @Test
    public void when_getId_error(){
        StepVerifier.create(personService.getId("627d502b827ec662ccc52387"))
            .expectError(NotFoundException.class)
            .verify();
    }


    @Test
    public void when_delete_ok(){
        StepVerifier.create(personService.delete("6282c0e7d5270e4157958455"))
            .verifyComplete();
    }

    @Test
    public void when_delete_error(){
        StepVerifier.create(personService.delete("627d502b827ec662ccc52387"))
            .expectError(NotFoundException.class)
            .verify();
    }


}

