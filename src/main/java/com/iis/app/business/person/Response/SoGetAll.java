package com.iis.app.business.person.Response;

import java.util.Date;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SoGetAll {
    public String idPerson;
    public String firstName;
    public String surName;
    public String dni;
    public Boolean gender;
    public Date birthDate;
}
