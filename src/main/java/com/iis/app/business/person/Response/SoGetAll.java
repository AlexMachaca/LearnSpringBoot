package com.iis.app.business.person.Response;

import java.util.Date;
import java.util.List;

import com.iis.app.business.ResponseGeneral;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class SoGetAll {
    public String idPerson;
    public String firstName;
    public String surName;
    public String dni;
    public Boolean gender;
    public Date birthDate;
}
