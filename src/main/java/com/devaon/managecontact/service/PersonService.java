package com.devaon.managecontact.service;

import com.devaon.managecontact.controller.dto.PersonDto;
import com.devaon.managecontact.domain.Person;
import com.devaon.managecontact.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;


    @Transactional(readOnly = true)
    public Person getPerson(Long id) {
        return personRepository.findById(id).orElse(null);
    }


    @Transactional
    public void put(PersonDto personDto) {
        Person person = new Person();
        person.set(personDto);
        person.setName(personDto.getName());

        personRepository.save(person);
    }
}