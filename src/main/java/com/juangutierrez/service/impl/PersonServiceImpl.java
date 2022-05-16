package com.juangutierrez.service.impl;

import com.juangutierrez.document.Person;
import com.juangutierrez.repository.PersonRepository;
import com.juangutierrez.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository repository;

    @Override
    public Mono<Person> save(Person person) {
        return repository.save(person);
    }

    @Override
    public Mono<Person> update(Person person) {
        return repository.findById(person.getId())
            .switchIfEmpty(Mono.error(RuntimeException::new))
            .flatMap(v -> repository.save(person));
    }

    @Override
    public Flux<Person> findAll() {
        return repository.findAll()
            .filter(person -> person.getStatus() == 1);
    }

    @Override
    public Mono<Person> getId(String id) {
        return repository.findById(id)
            .switchIfEmpty(Mono.error(RuntimeException::new));
    }

    @Override
    public Mono<Void> delete(String id) {
        return repository.findById(id)
            .switchIfEmpty(Mono.error(RuntimeException::new))
            .flatMap(x -> repository.deleteById(id));
    }
}
