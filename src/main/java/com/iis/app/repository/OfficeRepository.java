package com.iis.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iis.app.entity.TOffice;

public interface OfficeRepository extends JpaRepository<TOffice,String> {
    List<TOffice> findByDescripcionContaining(String descripcion);
}
