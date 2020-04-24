package com.devaon.managecontact.service.argumentmatcher;

import com.devaon.managecontact.domain.Person;
import com.devaon.managecontact.domain.dto.Birthday;
import org.mockito.ArgumentMatcher;

import java.time.LocalDate;

public class IsPersonWillBeInserted implements ArgumentMatcher<Person> {


    @Override
    public boolean matches(Person person) {
        return equals(person.getName(), "martin")
                && equals(person.getHobby(), "programming")
                && equals(person.getAddress(), "seoul")
                && equals(person.getBirthday(), Birthday.of(LocalDate.now()))
                && equals(person.getJob(), "programmer")
                && equals(person.getPhoneNumber(), "010-1111-2222");
    }

    private boolean equals(Object actual, Object expected) {
        return expected.equals(expected);
    }
}
