package com.iis.app.business.person;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.iis.app.business.person.Response.ResponseDelete;
import com.iis.app.business.person.Response.ResponseGetAll;
import com.iis.app.business.person.Response.ResponseUpdate;

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

            responseSoInsert.success();
            responseSoInsert.addResponseMessage("Operación realizada correctamente.");

            return new ResponseEntity<>(responseSoInsert, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(responseSoInsert, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("getall")
    public ResponseEntity<ResponseGetAll> actionGetAll() {
		ResponseGetAll responseSoGetAll = new ResponseGetAll();

		List<DtoPerson> listDtoPerson = personService.getAll();

		for (DtoPerson dtoPerson : listDtoPerson) {
			Map<String, Object> map = new HashMap<>();

			map.put("idPerson", dtoPerson.getIdPerson());
			map.put("firstName", dtoPerson.getFirstName());
			map.put("surName", dtoPerson.getSurName());
			map.put("dni", dtoPerson.getDni());
			map.put("gender", dtoPerson.getGender());
			map.put("birthDate", dtoPerson.getBirthDate());

			responseSoGetAll.dto.listPerson.add(map);
		}

		responseSoGetAll.success();

		return new ResponseEntity<>(responseSoGetAll, HttpStatus.OK);
	}

    /*public ResponseEntity<ResponseGeneral<Map<String, Object>>> actionGetAll() {
        ResponseGeneral<Map<String, Object>> responseGetAll = new ResponseGeneral<Map<String, Object>>();
        List<DtoPerson> listDtoPerson = personService.getAll();

        List<SoGetAll> listPerson = new ArrayList<>();
        //responseGetAll.setDto(new ArrayList<>());

        for (DtoPerson dtoPerson : listDtoPerson) {
            listPerson.add(new SoGetAll(
                    dtoPerson.getIdPerson(),
                    dtoPerson.getFirstName(),
                    dtoPerson.getSurName(),
                    dtoPerson.getDni(),
                    dtoPerson.getGender(),
                    dtoPerson.getBirthDate()));
        }
        Map<String, Object> mapResponse = new HashMap<>();
        mapResponse.put("listPerson", listPerson);
        mapResponse.put("name", "");
        
        responseGetAll.setDto(mapResponse);
        responseGetAll.setType("success");
        responseGetAll.addResponseMessage("Se obtuvieron todos los datos correctamente");
        return new ResponseEntity<>(responseGetAll, HttpStatus.OK);
    }*/

    // ELIMINAR
    @DeleteMapping(path = "delete/{idPerson}")
    public ResponseEntity<ResponseDelete> actionDelete(@PathVariable String idPerson) {
		ResponseDelete responseDelete = new ResponseDelete();

		personService.delete(idPerson);

		responseDelete.success();
		responseDelete.addResponseMessage("Operación realizada correctamente.");

		return new ResponseEntity<>(responseDelete, HttpStatus.OK);
	}

    @PostMapping(path = "update", consumes = { "multipart/form-data" })
    public ResponseEntity<ResponseUpdate> actionUpdate(@Valid @ModelAttribute SoUpdate soUpdate,BindingResult bindingResult) {
        ResponseUpdate responseUpdate = new ResponseUpdate();
        try {
            if (bindingResult.hasErrors()) {
                bindingResult.getAllErrors().forEach(error -> {
                    responseUpdate.addResponseMessage(error.getDefaultMessage());
                });
                return new ResponseEntity<>(responseUpdate, HttpStatus.OK);
            }
            DtoPerson dtoPerson = new DtoPerson();

            dtoPerson.setIdPerson(soUpdate.getIdPerson());
            dtoPerson.setFirstName(soUpdate.getFirstName());
            dtoPerson.setSurName(soUpdate.getSurName());
            dtoPerson.setDni(soUpdate.getDni());
            dtoPerson.setGender(soUpdate.getGender());
            dtoPerson.setBirthDate(new SimpleDateFormat("yyyy-MM-dd").parse(soUpdate.getBirthDate()));

            personService.update(dtoPerson);

            responseUpdate.success();
            responseUpdate.addResponseMessage("Operación realizada correctamente.");

            return new ResponseEntity<>(responseUpdate,HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error al insertar persona: " + e.getMessage());
            responseUpdate.exception();
            return new ResponseEntity<>(responseUpdate, HttpStatus.BAD_REQUEST);
        }
    }
}
