package com.juangutierrez.api;

import com.juangutierrez.document.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PersonaApiTest {

    @Autowired
    private WebTestClient webTestClient;

    private Person data;

    @Test
    void findAll(){
        this.webTestClient
            .get()
            .uri("/person")
            .accept(MediaType.APPLICATION_STREAM_JSON)
            .exchange()
            .expectStatus().isOk()
            .expectBodyList(Person.class)
            .hasSize(1);
    }

    @Test
    void register(){
        data = new Person();
        data.setDocument("87654321");
        data.setNames("Carlos");
        data.setLastnames("Quispe Martines");
        data.setStatus(1);
        this.webTestClient
            .post()
            .uri("/person")
            .accept(MediaType.APPLICATION_STREAM_JSON)
            .body(Mono.just(data),Person.class)
            .exchange()
            .expectStatus().isOk()
            .expectBody(Person.class);
    }

    @Test
    void update(){
        data = new Person();
        data.setId("628282da1af4ea02ff5316a9");
        data.setDocument("87654321");
        data.setNames("Carlos");
        data.setLastnames("Quispe Martines");
        data.setStatus(1);
        this.webTestClient
            .put()
            .uri("/person")
            .accept(MediaType.APPLICATION_STREAM_JSON)
            .body(Mono.just(data),Person.class)
            .exchange()
            .expectStatus().isOk()
            .expectBody(Person.class);
    }

    @Test
    void findId(){
        this.webTestClient
            .get()
            .uri("/person/628282da1af4ea02ff5316a9")
            .accept(MediaType.APPLICATION_STREAM_JSON)
            .exchange()
            .expectStatus().isOk()
            .expectBody(Person.class);
    }

    @Test
    void findId_error(){
        this.webTestClient
            .get()
            .uri("/person/628282da1af4ea02ff5316a7")
            .accept(MediaType.APPLICATION_STREAM_JSON)
            .exchange()
            .expectStatus().is5xxServerError();
    }

    @Test
    void delete(){
        this.webTestClient
            .get()
            .uri("/person/628282da1af4ea02ff5316a9")
            .accept(MediaType.APPLICATION_STREAM_JSON)
            .exchange()
            .expectStatus().isOk();
    }

    @Test
    void delete_error(){
        this.webTestClient
            .delete()
            .uri("/person/628282da1af4ea02ff5316a7")
            .accept(MediaType.APPLICATION_STREAM_JSON)
            .exchange()
            .expectStatus().is5xxServerError();
    }
}
