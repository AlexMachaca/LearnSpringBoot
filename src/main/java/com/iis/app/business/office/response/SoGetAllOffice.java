package com.iis.app.business.office.response;

import java.util.Date;

public class SoGetAllOffice {
    public String codigoOficina;
    public String descripcion;
    public String pais;
    public Date fechaCreacion;
    public Boolean estado;

    public SoGetAllOffice(
            String codigoOficina,
            String descripcion,
            String pais,
            Date fechaCreacion,
            boolean estado) {

        this.codigoOficina = codigoOficina;
        this.descripcion = descripcion;
        this.pais = pais;
        this.fechaCreacion = fechaCreacion;
        this.estado = estado;
    }
}
