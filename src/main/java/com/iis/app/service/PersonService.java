package com.iis.app.service;


import java.util.List;
import java.util.Optional;
import java.util.UUID;


import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iis.app.dto.DtoPerson;
import com.iis.app.entity.TPerson;
import com.iis.app.repository.PersonRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    public boolean insert(DtoPerson dtoPerson){
        dtoPerson.setIdPerson(UUID.randomUUID().toString());
		dtoPerson.setCreatedAt(new Date());
		dtoPerson.setUpdatedAt(new Date());
        
        TPerson tPerson=new TPerson();

        tPerson.setIdPerson(dtoPerson.getIdPerson());
		tPerson.setFirstName(dtoPerson.getFirstName());
		tPerson.setSurName(dtoPerson.getSurName());
		tPerson.setDni(dtoPerson.getDni());
		tPerson.setGender(dtoPerson.getGender());
		tPerson.setBirthDate(dtoPerson.getBirthDate());
		tPerson.setCreatedAt(dtoPerson.getCreatedAt());
		tPerson.setUpdatedAt(dtoPerson.getUpdatedAt());

		personRepository.save(tPerson);
        return true;
    }
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

    //ELIMINAR
    public boolean delete(String idPerson) {
        Optional<TPerson> optionalTPerson = personRepository.findById(idPerson);

        if (!optionalTPerson.isPresent()) {
            throw new EntityNotFoundException("Persona con ID " + idPerson + " no encontrada");
        }

        personRepository.delete(optionalTPerson.get());
        return true;
    }

    public boolean update(DtoPerson dtoPerson){
        dtoPerson.setUpdatedAt(new Date());
        try{
            Optional<TPerson>optionalPerson=personRepository.findById(dtoPerson.getIdPerson());
            if(optionalPerson.isPresent()){
                TPerson personToUpdate=optionalPerson.get();
                personToUpdate.setFirstName(dtoPerson.getFirstName());
                personToUpdate.setSurName(dtoPerson.getSurName());
                personToUpdate.setDni(dtoPerson.getDni());
                personToUpdate.setGender(dtoPerson.getGender());
                personToUpdate.setBirthDate(dtoPerson.getBirthDate());
                personToUpdate.setUpdatedAt(dtoPerson.getUpdatedAt());

                personRepository.save(personToUpdate);
                return true;
            }else{
                return false;
            }
        } catch(Exception e){
            throw new RuntimeException("Error al actualizar la persona ",e);
        }
    }    

}
