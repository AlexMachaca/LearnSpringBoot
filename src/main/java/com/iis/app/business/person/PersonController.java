package com.iis.app.business.person;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iis.app.business.person.Response.SoGetAll;
import com.iis.app.business.person.request.SoInsert;
import com.iis.app.business.person.request.SoUpdate;
import com.iis.app.dto.DtoPerson;
import com.iis.app.service.PersonService;

@RestController
@RequestMapping("person")
public class PersonController {
    @Autowired
    private PersonService personService;

    @PostMapping(path = "insert", consumes = { "multipart/form-data" })
    public ResponseEntity<Boolean> actionInsert(@ModelAttribute SoInsert soInsert) {
        try {
            DtoPerson dtoPerson = new DtoPerson();

            dtoPerson.setFirstName(soInsert.getFirstName());
            dtoPerson.setSurName(soInsert.getSurName());
            dtoPerson.setDni(soInsert.getDni());
            dtoPerson.setGender(soInsert.isGender());
            dtoPerson.setBirthDate(new SimpleDateFormat("yyyy-MM-dd").parse(soInsert.getBirthDate()));

            personService.insert(dtoPerson);
        } catch (Exception e) {
        }
        return new ResponseEntity<>(true, HttpStatus.CREATED);

    }

    @GetMapping("getall")
    public ResponseEntity<List<SoGetAll>> actionGetAll() {
        List<DtoPerson> listDtoPerson = personService.getAll();

        List<SoGetAll> listSoPersonGet = new ArrayList<>();

        for (DtoPerson dtoPerson : listDtoPerson) {
            listSoPersonGet.add(new SoGetAll(
                    dtoPerson.getIdPerson(),
                    dtoPerson.getFirstName(),
                    dtoPerson.getSurName(),
                    dtoPerson.getDni(),
                    dtoPerson.getGender(),
                    dtoPerson.getBirthDate()));
        }

        return new ResponseEntity<>(listSoPersonGet, HttpStatus.OK);
    }

    // ELIMINAR
    @DeleteMapping(path = "{id}")
    public ResponseEntity<Boolean> actionDelete(@PathVariable String id) {
        try {
            boolean isDeleted = personService.delete(id);
            if (isDeleted) {
                return new ResponseEntity<>(true, HttpStatus.NO_CONTENT); // Retorna 204 No Content si la eliminación
                                                                          // fue exitosa
            } else {
                return new ResponseEntity<>(false, HttpStatus.NOT_FOUND); // Retorna 404 Not Found si el ID no existe
            }
        } catch (Exception e) {
            // Maneja excepciones adecuadamente, por ejemplo, retornando un estado de error
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR); // Retorna 500 Internal Server Error
                                                                                  // en caso de excepción
        }
    }

    /*@PutMapping(path = "update/{id}")
    public ResponseEntity<Boolean> actionUpdate(@PathVariable String id, @RequestBody SoInsert soUpdate) {
        try {
            DtoPerson dtoPerson = new DtoPerson();
            dtoPerson.setIdPerson(id);

            dtoPerson.setFirstName(soUpdate.getFirstName());
            dtoPerson.setSurName(soUpdate.getSurName());
            dtoPerson.setDni(soUpdate.getDni());
            dtoPerson.setGender(soUpdate.isGender());
            dtoPerson.setBirthDate(new SimpleDateFormat("yyyy-MM-dd").parse(soUpdate.getBirthDate()));

            boolean isUpdate = personService.update(dtoPerson);
            if (isUpdate) {
                return new ResponseEntity<>(true, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/

    @PostMapping(path = "update", consumes = {"multipart/form-data"})
    public ResponseEntity<Boolean>actionUpdate(@ModelAttribute SoUpdate soUpdate){
        try {
            DtoPerson dtoPerson = new DtoPerson();

            dtoPerson.setIdPerson(soUpdate.getIdPerson());
            dtoPerson.setFirstName(soUpdate.getFirstName());
            dtoPerson.setSurName(soUpdate.getSurName());
            dtoPerson.setDni(soUpdate.getDni());
            dtoPerson.setGender(soUpdate.isGender());
            dtoPerson.setBirthDate(new SimpleDateFormat("yyyy-MM-dd").parse(soUpdate.getBirthDate()));

            personService.update(dtoPerson);
        } catch (Exception e) {
        }
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
