package com.iis.app.business.person;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iis.app.business.person.Response.SoGetAll;
import com.iis.app.business.person.Response.SoInsertResponse;
import com.iis.app.business.person.request.SoInsert;
import com.iis.app.business.person.request.SoUpdate;
import com.iis.app.dto.DtoPerson;
import com.iis.app.service.PersonService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("person")
public class PersonController {
    @Autowired
    private PersonService personService;

    @PostMapping(path = "insert", consumes = { "multipart/form-data" })
    public ResponseEntity<SoInsertResponse> actionInsert(@Valid @ModelAttribute SoInsert soInsert,
            BindingResult bindingResult) {
        SoInsertResponse responseSoInsert = new SoInsertResponse();
        try {
            if (bindingResult.hasErrors()) {
                bindingResult.getAllErrors().forEach(error -> {
                    responseSoInsert.addResponseMessage(error.getDefaultMessage());
                });
                return new ResponseEntity<>(responseSoInsert, HttpStatus.OK);
            }
            DtoPerson dtoPerson = new DtoPerson();

            dtoPerson.setFirstName(soInsert.getFirstName());
            dtoPerson.setSurName(soInsert.getSurName());
            dtoPerson.setDni(soInsert.getDni());
            dtoPerson.setGender(soInsert.getGender());
            dtoPerson.setBirthDate(new SimpleDateFormat("yyyy-MM-dd").parse(soInsert.getBirthDate()));

            personService.insert(dtoPerson);

            responseSoInsert.setType("succes");
            responseSoInsert.addResponseMessage("Operación realizada correctamente.");

            return new ResponseEntity<>(responseSoInsert, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(responseSoInsert, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("getall")
    public ResponseEntity<SoGetAll> actionGetAll() {
        SoGetAll responseGetAll = new SoGetAll();
        List<DtoPerson> listDtoPerson = personService.getAll();

        responseGetAll.setDto(new ArrayList<>());

        for (DtoPerson dtoPerson : listDtoPerson) {
            responseGetAll.getDto().add(new SoGetAll(
                    dtoPerson.getIdPerson(),
                    dtoPerson.getFirstName(),
                    dtoPerson.getSurName(),
                    dtoPerson.getDni(),
                    dtoPerson.getGender(),
                    dtoPerson.getBirthDate()));
        }
        responseGetAll.setType("succes");
        return new ResponseEntity<>(responseGetAll, HttpStatus.OK);
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

    @PostMapping(path = "update", consumes = { "multipart/form-data" })
    public ResponseEntity<Boolean> actionUpdate(@Valid @ModelAttribute SoUpdate soUpdate) {
        try {
            DtoPerson dtoPerson = new DtoPerson();

            dtoPerson.setIdPerson(soUpdate.getIdPerson());
            dtoPerson.setFirstName(soUpdate.getFirstName());
            dtoPerson.setSurName(soUpdate.getSurName());
            dtoPerson.setDni(soUpdate.getDni());
            dtoPerson.setGender(soUpdate.getGender());
            dtoPerson.setBirthDate(new SimpleDateFormat("yyyy-MM-dd").parse(soUpdate.getBirthDate()));

            personService.update(dtoPerson);
        } catch (Exception e) {
            System.err.println("Error al insertar persona: " + e.getMessage());
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
