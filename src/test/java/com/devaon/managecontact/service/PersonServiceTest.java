package com.devaon.managecontact.service;

import com.devaon.managecontact.controller.dto.PersonDto;
import com.devaon.managecontact.domain.Person;
import com.devaon.managecontact.exception.PersonNotFoundException;
import com.devaon.managecontact.repository.PersonRepository;
import com.devaon.managecontact.service.argumentmatcher.IsNameWillBeUpdated;
import com.devaon.managecontact.service.argumentmatcher.IsPersonWillBeDeleted;
import com.devaon.managecontact.service.argumentmatcher.IsPersonWillBeInserted;
import com.devaon.managecontact.service.argumentmatcher.IsPersonWillBeUpdated;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {
    @InjectMocks
    private PersonService personService;

    @Mock
    private PersonRepository personRepository;

    @Test
    void getAll(){
        when(personRepository.findAll(any(Pageable.class)))
                .thenReturn(new PageImpl<>(Lists.newArrayList(new Person("martin"), new Person("dennis"), new Person("tony"))));

        Page<Person> result = personService.getAll(PageRequest.of(0, 3));

        assertThat(result.getNumberOfElements()).isEqualTo(3);
        assertThat(result.getContent().get(0).getName()).isEqualTo("martin");
        assertThat(result.getContent().get(1).getName()).isEqualTo("dennis");
        assertThat(result.getContent().get(2).getName()).isEqualTo("tony");
    }

    @Test
    void getPeopleByName() {
        when(personRepository.findByName("aonee"))
                .thenReturn(Lists.newArrayList(new Person("aonee")));

        List<Person> result = personService.getPeopleByName("aonee");

        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getName()).isEqualTo("aonee");
    }

    @Test
    void getPerson(){
        when(personRepository.findById(1L))
                .thenReturn(Optional.of(new Person("martin")));

        Person person = personService.getPerson(1L);

        assertThat(person.getName()).isEqualTo("martin");
    }

    @Test
    void postPerson(){
        personService.postPerson(mockPersonDto());

        verify(personRepository, times(1)).save(argThat(new IsPersonWillBeInserted()));
    }

    @Test
    void modify() {
        when(personRepository.findById(1L))
                .thenReturn(Optional.of(new Person("aonee")));

        personService.modify(1L, mockPersonDto());

        verify(personRepository, times(1)).save(argThat(new IsPersonWillBeUpdated()));
    }


    @Test
    void modifyByNameIfPersonNotFound() {
        when(personRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(PersonNotFoundException.class, () -> personService.modify(1L, "daniel"));
    }

    @Test
    void modifyByName(){
        when(personRepository.findById(1L))
                .thenReturn(Optional.of(new Person("martin")));

        personService.modify(1L, "daniel");

        verify(personRepository, times(1)).save(argThat(new IsNameWillBeUpdated()));
    }

    @Test
    void delete(){
        when(personRepository.findById(1L))
                .thenReturn(Optional.of(new Person("martin")));

        personService.delete(1L);

        verify(personRepository, times(1)).save(argThat(new IsPersonWillBeDeleted()));
    }

    private PersonDto mockPersonDto() {
        return PersonDto.of("aonee", "programming", "seoul", LocalDate.now(), "programmer", "010-1111-2222");
    }
}