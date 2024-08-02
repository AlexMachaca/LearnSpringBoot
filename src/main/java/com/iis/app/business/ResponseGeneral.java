package com.iis.app.business;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//Tipos genericos <T>
public class ResponseGeneral<T> {
    private String type;
    private List<String> listMessage;
    private T dto;

    public ResponseGeneral(){
        this.type="Error";
        this.listMessage=new ArrayList<>();

        this.dto=null;
    }

    public void addResponseMessage(String message){
        this.listMessage.add(message);
    }
}
