package com.juangutierrez.api;

import com.juangutierrez.document.Person;
import com.juangutierrez.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/person")
public class PersonApi {

    @Autowired
    PersonService personService;

    @GetMapping(produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<Person> findAll(){
        return personService.findAll();
    }

    @PostMapping(produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Mono<Person> save(@RequestBody Person p){
        return personService.save(p);
    }

    @PutMapping(produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Mono<Person> update(@RequestBody Person p){
        return personService.update(p);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Mono<Person> findById(@PathVariable("id") String id){
        return personService.getId(id);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Mono<Void> delete(@PathVariable("id") String id){
        return personService.delete(id);
    }
}
