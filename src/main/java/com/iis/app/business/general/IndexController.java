package com.iis.app.business.general;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//Decarador o data anotation
@RestController
@RequestMapping("")
public class IndexController {
    @GetMapping("")
    public ResponseEntity<SoIndexGet> actionIndexGet(){
        SoIndexGet soIndexGet=new SoIndexGet();

        soIndexGet.setMessage("Bienvenido a tu primera aplicacion con Speign Boot.");
        soIndexGet.setType("success");

        return new ResponseEntity<>(soIndexGet,HttpStatus.OK);

    }
}
