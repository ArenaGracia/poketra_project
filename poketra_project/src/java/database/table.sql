CREATE TABLE unite (
    id_unite VARCHAR(10) DEFAULT ('UNI') || LPAD(nextval('unite_sequence')::TEXT,4,'0') PRIMARY KEY, 
    nom VARCHAR(225) NOT NULL);

CREATE TABLE look (
    id_look VARCHAR(10) DEFAULT ('LOO') || LPAD(nextval('look_sequence')::TEXT,4,'0') PRIMARY KEY, 
    nom VARCHAR(225) NOT NULL);

CREATE TABLE matiere (
    id_matiere VARCHAR(10) DEFAULT ('MTR') || LPAD(nextval('matiere_sequence')::TEXT,4,'0') PRIMARY KEY, 
    nom VARCHAR(225) NOT NULL);

CREATE TABLE Matiere_unite (
    id VARCHAR(10) DEFAULT ('MTU') || LPAD(nextval('matiere_unite_sequence')::TEXT,4,'0') PRIMARY KEY, 
    id_matiere VARCHAR(10), 
    id_unite VARCHAR(10) ,
    FOREIGN KEY (id_matiere) references matiere(id_matiere) ,
    FOREIGN KEY (id_unite) references unite(id_unite));

CREATE TABLE Look_matiere (
    id VARCHAR(10) DEFAULT ('LMK') || LPAD(nextval('look_matiere_sequence')::TEXT,4,'0') PRIMARY KEY, 
    id_look VARCHAR(10), 
    id_matiere VARCHAR(10) ,
    FOREIGN KEY (id_matiere) references matiere(id_matiere) ,
    FOREIGN KEY (id_look) references look(id_look));

CREATE TABLE taille (
    id_taille VARCHAR(10) DEFAULT ('UNI') || LPAD(nextval('taille_sequence')::TEXT,4,'0') PRIMARY KEY, 
    nom VARCHAR(225) NOT NULL);

CREATE TABLE type (
    id_type VARCHAR(10) DEFAULT ('UNI') || LPAD(nextval('type_sequence')::TEXT,4,'0') PRIMARY KEY, 
    nom VARCHAR(225) NOT NULL);

CREATE TABLE Produit (
    id VARCHAR(10) DEFAULT ('PRO') || LPAD(nextval('produit_sequence')::TEXT,4,'0') PRIMARY KEY,
    id_type VARCHAR(10),
    id_taille VARCHAR (10),
    FOREIGN KEY (id_type) REFERENCES Type(id_type),
    FOREIGN KEY (id_taille) REFERENCES Taille(id_taille)
);

CREATE TABLE Detail_Produit (
    id VARCHAR(10) DEFAULT ('DTP') || LPAD(nextval('Detail_Produuit_sequence')::TEXT,4,'0') PRIMARY KEY,
    id_produit VARCHAR(10),
    id_matiere VARCHAR (10),
    qte INT,
    FOREIGN KEY (id_produit) REFERENCES Produit(id),
    FOREIGN KEY (id_matiere) REFERENCES Matiere(id_matiere)
);
/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  ASUS
 * Created: 14 déc. 2023
 */

