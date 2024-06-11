package com.iis.app.business.person;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iis.app.business.person.Response.SoPersonGetAll;
import com.iis.app.dto.DtoPerson;
import com.iis.app.service.PersonService;

@RestController
@RequestMapping("person")
public class PersonController {
    @Autowired
    private PersonService personService;

    @GetMapping("getall")
    public ResponseEntity<List<SoPersonGetAll>>actionPersonGetAll(){
        List<DtoPerson>listDtoPerson=personService.getAll();

        List<SoPersonGetAll>listSoPersonGet=new ArrayList<>();

        for (DtoPerson dtoPerson:listDtoPerson){
            listSoPersonGet.add(new SoPersonGetAll(
                dtoPerson.getIdPerson(),
                dtoPerson.getFirstName(),
                dtoPerson.getSurName(),
                dtoPerson.getDni(),
                dtoPerson.getGender(),
                dtoPerson.getBirthDate()
            ));
        }

        return new ResponseEntity<>(listSoPersonGet, HttpStatus.OK);
    }
}
