package com.devaon.managecontact.service.argumentmatcher;

import com.devaon.managecontact.domain.Person;
import org.mockito.ArgumentMatcher;

public class IsNameWillBeUpdated implements ArgumentMatcher<Person> {
    @Override
    public boolean matches(Person person) {
        return person.getName().equals("daniel");
    }
}
