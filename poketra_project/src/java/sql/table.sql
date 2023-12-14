/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Toky
 * Created: 14 déc. 2023
 */
CREATE DATABASE poketra;


CREATE TABLE unite (id VARCHAR(20) DEFAULT('UNT' || LPAD(nextval('sequence_unite')::TEXT,4,'0')) PRIMARY KEY, nom VARCHAR(225) NOT NULL);

CREATE TABLE matiere(id VARCHAR(20) DEFAULT('MAT' || LPAD(nextval('sequence_matiere')::TEXT,4,'0')) PRIMARY KEY, nom VARCHAR(225) NOT NULL);

CREATE TABLE unite_matiere(id VARCHAR(20) DEFAULT('UMT' || LPAD(nextval('sequence_matiere_unite')::TEXT,4,'0')) PRIMARY KEY, id_unite VARCHAR(20), id_matiere VARCHAR(20));


INSERT INTO unite VALUES
    (DEFAULT,'u1'),
    (DEFAULT,'u2');

INSERT INTO matiere VALUES
    (DEFAULT,'m1'),
    (DEFAULT,'m2');

INSERT INTO unite_matiere VALUES
    (DEFAULT,'1','2'),
    (DEFAULT,'1','1');
    