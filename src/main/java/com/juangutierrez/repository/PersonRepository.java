package com.juangutierrez.repository;

import com.juangutierrez.document.Person;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.data.repository.reactive.RxJava3CrudRepository;

public interface PersonRepository extends ReactiveCrudRepository<Person, String> {
}
