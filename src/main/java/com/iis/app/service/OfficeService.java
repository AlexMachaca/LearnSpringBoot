package com.iis.app.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iis.app.dto.DtoOffice;

import com.iis.app.entity.TOffice;

import com.iis.app.repository.OfficeRepository;

import jakarta.persistence.EntityNotFoundException;


@Service
public class OfficeService {
    @Autowired
    private OfficeRepository officeRepository;

    public boolean insert(DtoOffice dtoOffice){
        dtoOffice.setCodigoOficina(UUID.randomUUID().toString());
		dtoOffice.setCreated_at(new Date());
		dtoOffice.setUpdated_at(new Date());
        
        TOffice tOffice=new TOffice();

        tOffice.setCodigoOficina(dtoOffice.getCodigoOficina());
		tOffice.setDescripcion(dtoOffice.getDescripcion());
		tOffice.setPais(dtoOffice.getPais());
		tOffice.setFechaCreacion(dtoOffice.getFechaCreacion());
		tOffice.setEstado(dtoOffice.getEstado());
		tOffice.setCreated_at(dtoOffice.getCreated_at());
		tOffice.setUpdated_at(dtoOffice.getUpdated_at());

		officeRepository.save(tOffice);
        return true;
    }

    public List<DtoOffice> getAll() {
        List<TOffice> listTOffice = officeRepository.findAll();

        List<DtoOffice> listDtoOffice = new ArrayList<>();

        for (TOffice tOffice : listTOffice) {
            DtoOffice dtoOffice = new DtoOffice();

            dtoOffice.setCodigoOficina(tOffice.getCodigoOficina());
            dtoOffice.setDescripcion(tOffice.getDescripcion());
            dtoOffice.setPais(tOffice.getPais());
            dtoOffice.setFechaCreacion(tOffice.getFechaCreacion());
            dtoOffice.setEstado(tOffice.getEstado());
            dtoOffice.setCreated_at(tOffice.getCreated_at());
            dtoOffice.setUpdated_at(tOffice.getUpdated_at());

            listDtoOffice.add(dtoOffice);
        }


        return listDtoOffice;
    }

    public boolean delete(String idOffice) {
        Optional<TOffice> optionalTOffice = officeRepository.findById(idOffice);

        if (!optionalTOffice.isPresent()) {
            throw new EntityNotFoundException("Oficina con ID " + idOffice + " no encontrada");
        }

        officeRepository.delete(optionalTOffice.get());
        return true;
    }

    public boolean update(DtoOffice dtoOffice){
        dtoOffice.setUpdated_at(new Date());
        try{
            Optional<TOffice>optionalOffice=officeRepository.findById(dtoOffice.getCodigoOficina());
            if(optionalOffice.isPresent()){
                TOffice officeToUpdate=optionalOffice.get();
                officeToUpdate.setDescripcion(dtoOffice.getDescripcion());
                officeToUpdate.setPais(dtoOffice.getPais());
                officeToUpdate.setFechaCreacion(dtoOffice.getFechaCreacion());
                officeToUpdate.setEstado(dtoOffice.getEstado());
                officeToUpdate.setUpdated_at(dtoOffice.getUpdated_at());

                officeRepository.save(officeToUpdate);
                return true;
            }else{
                return false;
            }
        } catch(Exception e){
            throw new RuntimeException("Error al actualizar la oficina ",e);
        }
    }

    public List<DtoOffice> searchByDescription(String description) {
        List<TOffice> officesFound = officeRepository.findByDescripcionContaining(description);

        List<DtoOffice> dtoOffices = officesFound.stream()
                .map(this::convertToDtoOffice)
                .collect(Collectors.toList());

        return dtoOffices;
    }

    private DtoOffice convertToDtoOffice(TOffice tOffice) {
        DtoOffice dtoOffice = new DtoOffice();

        dtoOffice.setCodigoOficina(tOffice.getCodigoOficina());
        dtoOffice.setDescripcion(tOffice.getDescripcion());
        dtoOffice.setPais(tOffice.getPais());
        dtoOffice.setFechaCreacion(tOffice.getFechaCreacion());
        dtoOffice.setEstado(tOffice.getEstado());
        dtoOffice.setCreated_at(tOffice.getCreated_at());
        dtoOffice.setUpdated_at(tOffice.getUpdated_at());

        return dtoOffice;
    }
}
