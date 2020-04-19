package com.devaon.managecontact.repository;

import com.devaon.managecontact.domain.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonRepositoryTest {
    @Autowired
    private PersonRepository personRepository;

    @Test
    public void crud(){
        Person person = Person.builder()
                .name("최예원")
                .age(24)
                .bloodType("B")
                .build();

        personRepository.save(person);

        List<Person> people = personRepository.findAll();

        assertThat(people.size()).isEqualTo(1);
        assertThat(people.get(0).getName()).isEqualTo("최예원");
        assertThat(people.get(0).getAge()).isEqualTo(24);
        assertThat(people.get(0).getBloodType()).isEqualTo("B");
    }

    @Test
    public void hashCodeEquals(){
        Person person1 = Person.builder()
                .name("최예원")
                .age(24)
                .bloodType("B")
                .build();
        Person person2 = Person.builder()
                .name("최예원")
                .age(24)
                .bloodType("B")
                .build();

        System.out.println(person1.equals(person2));
        System.out.println(person1.hashCode());
        System.out.println(person2.hashCode());

        Map<Person, Integer> map = new HashMap<>();
        map.put(person1, person1.getAge());

        System.out.println(map);
        System.out.println(map.get(person2));
    }

}