package com.hotel.controller;


import com.hotel.entity.user.Person;
import com.hotel.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@Secured("ADMIN")
@RequestMapping("/api/users")
public class PersonRestController {
    PersonService personService;

    public PersonRestController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/{personId}")
    public ResponseEntity<Person> getUsers(@PathVariable Long personId) {
        return new ResponseEntity<>(personService.getPersonById(personId), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Person>> getUsers() {
        return new ResponseEntity<>(personService.findAllPeople(), HttpStatus.OK);
    }

    @PostMapping("/create-user")
    public ResponseEntity<Person> createUser(@RequestBody Person person) {
        return new ResponseEntity<>(personService.savePerson(person), HttpStatus.CREATED);
    }

    @PutMapping("/update-user/{id}")
    public ResponseEntity<Person> updateUser(@RequestBody Person updatedPerson, @PathVariable Long id) {
        return new ResponseEntity<>(personService.updatePerson(id, updatedPerson), HttpStatus.OK);
    }

    @DeleteMapping("/delete-user/{personId}")
    public ResponseEntity<String> deleteUser(@PathVariable String personId) {
        return new ResponseEntity<>(personService.deletePerson(Long.parseLong(personId)), HttpStatus.OK);
    }
}