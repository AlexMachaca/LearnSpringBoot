package com.iis.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iis.app.entity.TPerson;

public interface PersonRepository extends JpaRepository<TPerson, String> { }
