package com.iis.app.business.person.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SoUpdate {
    @NotBlank(message = "El id no puede estar en blanco")
    private String idPerson;
    @NotBlank(message = "El nombre no puede estar en blanco")
    private String firstName;
    @NotBlank(message = "El apellido no puede estar en blanco")
    private String surName;
    @NotBlank(message = "El dni no puede estar en blanco")
    @Pattern(regexp = "^\\d{8}$", message = "El dni deberia ser de 8 digitos")
    private String dni;
    @NotNull(message = "El genero no puede estar en blanco")
    private Boolean gender;
    @NotBlank(message = "La fecha no puede estar en blanco")
    private String birthDate;
}
