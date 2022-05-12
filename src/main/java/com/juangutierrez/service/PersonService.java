package com.juangutierrez.service;

import com.juangutierrez.document.Person;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PersonService {

    Mono<Person> save(Person person);

    Mono<Person> update(Person person);

    Flux<Person> findAll();

    Mono<Person> getId(String id);

    Mono<Void> delete(String id);
}
