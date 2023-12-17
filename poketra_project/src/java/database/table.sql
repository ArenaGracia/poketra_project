
CREATE SEQUENCE unite_sequence
    START 1
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 999999
    CACHE 1;

CREATE SEQUENCE matiere_sequence
    START 1
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 999999
    CACHE 1;

CREATE SEQUENCE matiere_unite_sequence
    START 1
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 999999
    CACHE 1;

CREATE TABLE unite (
    id_unite VARCHAR(10) DEFAULT ('MTR') || LPAD(nextval('unite_sequence')::TEXT,4,'0') PRIMARY KEY, 
    nom VARCHAR(225) NOT NULL);

CREATE TABLE matiere (
    id_matiere VARCHAR(10) DEFAULT ('MTR') || LPAD(nextval('matiere_sequence')::TEXT,4,'0') PRIMARY KEY, 
    nom VARCHAR(225) NOT NULL);

CREATE TABLE Matiere_unite (
    id VARCHAR(10) DEFAULT ('MTR') || LPAD(nextval('matiere_unite_sequence')::TEXT,4,'0') PRIMARY KEY, 
    id_matiere VARCHAR(10), 
    id_unite VARCHAR(10) ,
    FOREIGN KEY (id_matiere) references matiere(id_matiere) ,
    FOREIGN KEY (id_unite) references unite(id_unite));

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  ASUS
 * Created: 14 déc. 2023
 */

