create database dbgeneral;
use dbgeneral;

create table tperson(
idPerson char(36) not null,
firstName varchar(70) not null,
surName varchar(40) not null,
dni char(8) not null,
gender boolean not null, /*true => masculino, false => femenino*/
birthDate date not null,
createdAt datetime not null,
updateAt datetime not null,
primary key(idPerson)
) engine=innodb;

create table tcommnent(
idComment char(36) not null,
idPerson char(36) not null,
description varchar(1000) not null,
likeMe int not null,
createdAt datetime not null,
updateAt datetime not null,
foreign key (idPerson) references tperson(idPerson) on delete cascade on update cascade,
primary key (idComment)
) engine=innodb;

INSERT INTO tperson (idPerson, firstName, surName, dni, gender, birthDate, createdAt, updateAt)
VALUES (
    '123e4567-e89b-12d3-a456-426614174000', /* UUID para idPerson */
    'Juan',                                 /* Nombre */
    'Pérez',                                /* Apellido */
    '12345678',                             /* DNI */
    true,                                   /* Género (masculino) */
    '1980-05-15',                           /* Fecha de nacimiento */
    '2024-06-06 12:00:00',                  /* Fecha de creación */
    '2024-06-06 12:00:00'                   /* Fecha de actualización */
);
