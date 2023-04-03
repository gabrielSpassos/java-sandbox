package com.gabrielspassos.poc.utils;

import com.gabrielspassos.poc.utils.dto.PersonDTO;
import com.google.common.base.Function;
import com.google.common.collect.Ordering;

import java.util.List;

public class OrderingUtils {

    public List<PersonDTO> orderByName(List<PersonDTO> people) {
        Ordering<PersonDTO> ordering = Ordering.natural().onResultOf(new Function<PersonDTO, String>() {
            public String apply(PersonDTO person) {
                return person.getName();
            }
        });

        people.sort(ordering);
        return people;
    }
}
