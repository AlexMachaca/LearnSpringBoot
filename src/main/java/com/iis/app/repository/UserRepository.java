package com.iis.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.iis.app.entity.TUser;

public interface UserRepository extends JpaRepository<TUser, String> {
    @Query(value = "select * from tuser where nameUser=:userName and password=:password;", nativeQuery=true)
    TUser getLogin(String userName, String password);
}
