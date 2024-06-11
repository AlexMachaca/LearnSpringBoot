package com.iis.app.dto;

import java.util.Date;

public class DtoPerson {
    private String idPerson;
    private String firstName;
    private String surName;
    private String dni;
    private Boolean gender;
    private Date birthDate;
    private Date createdAt;
    private Date updatedAt;

    public DtoPerson(){
        
    }
    public String getIdPerson(){
        return this.idPerson;
    }

    public void setIdPerson(String idPerson){
        this.idPerson=idPerson;
    }

    public String getFirstName(){
        return this.firstName;
    }

    public void setFirstName(String firstName){
        this.firstName=firstName;
    }

    public String getSurName(){
        return this.surName;
    }

    public void setSurName(String surName){
        this.surName=surName;
    }

    public String getDni(){
        return this.dni;
    }

    public void setDni(String dni){
        this.dni=dni;
    }

    public Boolean getGender(){
        return this.gender;
    }

    public void setGender(Boolean gender){
        this.gender=gender;
    }

    public Date getBirthDate(){
        return this.birthDate;
    }

    public void setBirthDate(Date birthDate){
        this.birthDate=birthDate;
    }

    public Date getCreatedAt(){
        return this.createdAt;
    }

    public void setCreatedAt(Date createdAt){
        this.createdAt=createdAt;
    }

    public Date getUpdatedAt(){
        return this.updatedAt;
    }

    public void setUpdatedAt(Date updatedAt){
        this.updatedAt=updatedAt;
    }
}

