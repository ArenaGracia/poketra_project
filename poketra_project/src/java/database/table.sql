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
    id_taille VARCHAR(10) DEFAULT ('TAL') || LPAD(nextval('taille_sequence')::TEXT,4,'0') PRIMARY KEY, 
    nom VARCHAR(225) NOT NULL);

CREATE TABLE type (
    id_type VARCHAR(10) DEFAULT ('TYP') || LPAD(nextval('type_sequence')::TEXT,4,'0') PRIMARY KEY, 
    nom VARCHAR(225) NOT NULL);

CREATE TABLE Modele (
    id VARCHAR(10) DEFAULT ('MDL') || LPAD(nextval('modele_sequence')::TEXT,4,'0') PRIMARY KEY,
    id_look VARCHAR(10),
    id_type VARCHAR(10),
    id_taille VARCHAR (10),
    FOREIGN KEY (id_type) REFERENCES Type(id_type),
    FOREIGN KEY (id_look) REFERENCES Look(id_look),
    FOREIGN KEY (id_taille) REFERENCES Taille(id_taille)
);

CREATE TABLE Detail_Modele (
    id VARCHAR(10) DEFAULT ('DML') || LPAD(nextval('Detail_modele_sequence')::TEXT,4,'0') PRIMARY KEY,
    id_modele VARCHAR(10),
    id_matiere VARCHAR (10),
    qte INT,
    FOREIGN KEY (id_modele) REFERENCES Modele(id),
    FOREIGN KEY (id_matiere) REFERENCES Matiere(id_matiere)
);

CREATE TABLE Matiere_prix (
    id_matiere_prix VARCHAR(10) DEFAULT ('MTP') || LPAD(nextval('matiere_prix_sequence')::TEXT,4,'0') PRIMARY KEY,
    id_matiere VARCHAR(10),
    prix DOUBLE PRECISION,
    FOREIGN KEY (id_matiere) REFERENCES Matiere(id_matiere)
);

CREATE TABLE Stock (
    id_stock VARCHAR(10) DEFAULT ('STK') || LPAD(nextval('stock_sequence')::TEXT,4,'0') PRIMARY KEY,
    id_matiere VARCHAR(10),
    entrer INTEGER,
    sortie INTEGER,
    date TIMESTAMP  default CURRENT_TIMESTAMP,
    FOREIGN KEY (id_matiere) REFERENCES matiere(id_matiere)
);

CREATE TABLE Fabrication (
    id_frabrication VARCHAR(10) DEFAULT ('FBR') || LPAD(nextval('fabrication_sequence')::TEXT,4,'0') PRIMARY KEY,
    id_modele VARCHAR(10),
    nb INTEGER,
    date TIMESTAMP  default CURRENT_TIMESTAMP,
    FOREIGN KEY (id_modele) REFERENCES Modele(id)
);

CREATE TABLE Genre( 
    id VARCHAR(10) DEFAULT ('GRN') || LPAD(nextval('genre_sequence')::TEXT,4,'0') PRIMARY KEY,
    nom VARCHAR(20)
);

CREATE TABLE Employe(
    id VARCHAR(10) DEFAULT ('EMP') || LPAD(nextval('employe_sequence')::TEXT,4,'0') PRIMARY KEY,
    nom VARCHAR(40),
    prenom VARCHAR(40),
    id_genre VARCHAR(10),
    dtn DATE,
    FOREIGN KEY (id_genre) REFERENCES Genre(id)
);

CREATE TABLE Specialite(
    id_specialite VARCHAR(10) DEFAULT ('SPC') || LPAD(nextval('specialite_sequence')::TEXT,4,'0') PRIMARY KEY,
    nom VARCHAR(20),
    salaire DOUBLE PRECISION
);

CREATE TABLE Employe_specialite(
    id_employe_specialite VARCHAR(10) DEFAULT ('ESL') || LPAD(nextval('employe_specialite_sequence')::TEXT,4,'0') PRIMARY KEY,
    id_employe VARCHAR(10),
    id_specialite VARCHAR(10),
    FOREIGN KEY (id_Employe) REFERENCES Employe(id),
    FOREIGN KEY (id_Specialite) REFERENCES Specialite(id_specialite)
);

CREATE TABLE Modele_specialite (
    id_modele_specilaite VARCHAR(10) DEFAULT ('FBR') || LPAD(nextval('modele_specialite_sequence')::TEXT,4,'0') PRIMARY KEY,
    id_modele VARCHAR(10),
    id_specialite VARCHAR(10),
    nombre INTEGER,
    duree DOUBLE PRECISION,
    FOREIGN KEY (id_modele) REFERENCES Modele(id),
    FOREIGN KEY (id_Specialite) REFERENCES Specialite(id_specialite)
);

CREATE TABLE Modele_prix_vente (
    id_modele_prix_vente VARCHAR(10) DEFAULT ('FBR') || LPAD(nextval('modele_prix_vente_sequence')::TEXT,4,'0') PRIMARY KEY,
    id_modele VARCHAR(10),
    prix_vente DOUBLE PRECISION,
    date TIMESTAMP  default CURRENT_TIMESTAMP,
    FOREIGN KEY (id_modele) REFERENCES Modele(id)
);