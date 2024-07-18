package com.iis.app.business.office;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iis.app.business.office.request.SoInsertOffice;
import com.iis.app.business.office.request.SoUpdateOffice;
import com.iis.app.business.office.response.SoGetAllOffice;
import com.iis.app.dto.DtoOffice;
import com.iis.app.service.OfficeService;


@RestController
@RequestMapping("office")
public class OfficeController {
    @Autowired
    private OfficeService officeService;

    @PostMapping(path = "insert", consumes = { "multipart/form-data" })
    public ResponseEntity<Boolean> actionInsert(@ModelAttribute SoInsertOffice soInsert) {
        try {
            DtoOffice dtoOffice = new DtoOffice();

            dtoOffice.setDescripcion(soInsert.getDescripcion());
            dtoOffice.setPais(soInsert.getPais());
            dtoOffice.setEstado(soInsert.getEstado());
            dtoOffice.setFechaCreacion(new SimpleDateFormat("yyyy-MM-dd").parse(soInsert.getFechaCreacion()));

            officeService.insert(dtoOffice);
        } catch (Exception e) {
        }
        return new ResponseEntity<>(true, HttpStatus.CREATED);

    }

    @GetMapping(path="getall")
    public ResponseEntity<List<SoGetAllOffice>> actionGetAll() {
        List<DtoOffice> listDtoOffice = officeService.getAll();

        List<SoGetAllOffice> listSoOfficeGet = new ArrayList<>();

        for (DtoOffice dtoOffice : listDtoOffice) {
            listSoOfficeGet.add(new SoGetAllOffice(
                    dtoOffice.getCodigoOficina(),
                    dtoOffice.getDescripcion(),
                    dtoOffice.getPais(),
                    dtoOffice.getFechaCreacion(),
                    dtoOffice.getEstado()));
        }

        return new ResponseEntity<>(listSoOfficeGet, HttpStatus.OK);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Boolean> actionDelete(@PathVariable String id) {
        try {
            boolean isDeleted = officeService.delete(id);
            if (isDeleted) {
                return new ResponseEntity<>(true, HttpStatus.NO_CONTENT); // Retorna 204 No Content si la eliminación
                                                                          // fue exitosa
            } else {
                return new ResponseEntity<>(false, HttpStatus.NOT_FOUND); // Retorna 404 Not Found si el ID no existe
            }
        } catch (Exception e) {
            // Maneja excepciones adecuadamente, por ejemplo, retornando un estado de error
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR); // Retorna 500 Internal Server Error
                                                                                  // en caso de excepción
        }
    }

    @PostMapping(path = "update", consumes = {"multipart/form-data"})
    public ResponseEntity<Boolean>actionUpdate(@ModelAttribute SoUpdateOffice soUpdate){
        try {
            DtoOffice dtoOffice = new DtoOffice();

            dtoOffice.setCodigoOficina(soUpdate.getCodigoOficina());
            dtoOffice.setDescripcion(soUpdate.getDescripcion());
            dtoOffice.setPais(soUpdate.getPais());
            dtoOffice.setEstado(soUpdate.getEstado());
            dtoOffice.setFechaCreacion(new SimpleDateFormat("yyyy-MM-dd").parse(soUpdate.getFechaCreacion()));

            officeService.update(dtoOffice);
        } catch (Exception e) {
        }
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @GetMapping(path = "search")
public ResponseEntity<List<SoGetAllOffice>> actionSearch(@RequestParam String query) {
    try {
        // Aquí asumo que tienes un método en tu service que realiza la búsqueda
        List<DtoOffice> filteredList = officeService.searchByDescription(query);

        List<SoGetAllOffice> result = new ArrayList<>();
        for (DtoOffice dtoOffice : filteredList) {
            result.add(new SoGetAllOffice(
                    dtoOffice.getCodigoOficina(),
                    dtoOffice.getDescripcion(),
                    dtoOffice.getPais(),
                    dtoOffice.getFechaCreacion(),
                    dtoOffice.getEstado()));
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    } catch (Exception e) {
        // Maneja excepciones adecuadamente, por ejemplo, retornando un estado de error
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // Retorna 500 Internal Server Error en caso de excepción
    }
}
}
