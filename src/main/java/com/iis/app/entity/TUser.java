package com.iis.app.entity;



import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
@Table(name = "tuser")
public class TUser {
    @Id
	@Column(name = "idUser")
	private String idUser;

	@Column(name = "nameUser")
	private String userName;

	@Column(name = "password")
	private String password;

    @Column(name = "createdAt")
	private Date createdAt;

	@Column(name = "updatedAt")
	private Date updatedAt;
}
