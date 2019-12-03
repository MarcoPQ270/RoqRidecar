CREATE DATABASE bdalumnos;

CREATE TABLE alumno(
        NoControl int AUTO_INCREMENT NOT NULL, 
        Nombre varchar(80),
        Carrera varchar(80),
        Telefono varchar(22),
        CONSTRAINT pkcontrol PRIMARY KEY (NoControl));