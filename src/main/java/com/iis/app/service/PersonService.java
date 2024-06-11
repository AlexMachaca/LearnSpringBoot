package com.iis.app.service;


import java.util.List;
import java.util.ArrayList;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iis.app.dto.DtoPerson;
import com.iis.app.entity.TPerson;
import com.iis.app.repository.PersonRepository;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    public List<DtoPerson> getAll() {
        List<TPerson> listTPerson = personRepository.findAll();

        List<DtoPerson> listDtoPerson = new ArrayList<>();

        for (TPerson tPerson : listTPerson) {
            DtoPerson dtoPerson = new DtoPerson();

            dtoPerson.setIdPerson(tPerson.getIdPerson());
            dtoPerson.setFirstName(tPerson.getFirstName());
            dtoPerson.setSurName(tPerson.getSurName());
            dtoPerson.setDni(tPerson.getDni());
            dtoPerson.setGender(tPerson.getGender());
            dtoPerson.setBirthDate(tPerson.getBirthDate());
            dtoPerson.setCreatedAt(tPerson.getCreatedAt());
            dtoPerson.setUpdatedAt(tPerson.getUpdatedAt());

            listDtoPerson.add(dtoPerson);
        }


        return listDtoPerson;
    }
}
