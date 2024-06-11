package com.iis.app.business.general;

public class SoIndexGet {
    private String message;
    private String type;


    public SoIndexGet(String message, String type){
        this.message = message;
        this.type = type;
    }

    public SoIndexGet(){
    }

    public String getMessage(){
        return this.message;
    }

    public void setMessage(String message){
        this.message=message;
    }

    public String getType(){
        return this.type;
    }
    public void setType(String type){
        this.type=type;
    }
}
