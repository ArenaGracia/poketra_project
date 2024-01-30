
CREATE OR REPLACE VIEW v_Matiere_unite as (
    select m.id  ,m.id_matiere  ,m.id_unite ,u.nom nom_unite, ma.nom nom_matiere
        from Matiere_unite m
        join matiere ma on ma.id_matiere=m.id_matiere
        join unite u on m.id_unite=u.id_unite
); 

CREATE OR REPLACE VIEW v_Matiere_prix1 as (
    select id_matiere,max(date) date
        from matiere_prix
    GROUP BY id_matiere
);

CREATE OR REPLACE VIEW v_Matiere_prix2 as (
    select m.id_matiere,prix,m.date
        from v_matiere_prix1 v
        JOIN matiere_prix m ON m.id_matiere=v.id_matiere AND m.date=v.date
);

CREATE OR REPLACE VIEW v_Matiere_prix as (
    select v.*,mp.prix
        from v_Matiere_unite v
        join v_matiere_prix2 mp on mp.id_matiere=v.id_matiere
); 

CREATE OR REPLACE VIEW v_matiere_look AS (
    SELECT l.id_look,l.nom,vmu.id_matiere,vmu.nom_matiere,vmu.id_unite,vmu.nom_unite
        FROM look l
        LEFT JOIN look_matiere vml ON l.id_look=vml.id_look
        LEFT JOIN v_matiere_unite vmu ON vmu.id_matiere=vml.id_matiere
);

CREATE OR REPLACE VIEW v_modele1 AS (
    SELECT  p.id,m.id_matiere,m.nom,p.id_type,p.id_look,p.id_taille,dp.qte
        FROM Detail_modele as dp  
        JOIN matiere as m ON m.id_matiere=dp.id_matiere
        JOIN Modele p ON p.id=dp.id_modele
);

CREATE OR REPLACE VIEW v_modele AS (
    SELECT  v1.id_matiere,v1.nom as nom_matiere,v1.id_type,ty.nom as nom_type,v1.id_look,l.nom as nom_look,v1.id,v1.id_taille,ta.nom as nom_taille,v1.qte
        FROM v_modele1 as v1  
        JOIN Look l ON l.id_look=v1.id_look
        JOIN type as ty ON v1.id_type=ty.id_type
        JOIN taille ta ON ta.id_taille=v1.id_taille  
);

CREATE OR REPLACE VIEW v_modele_Sans_taille AS(
    SELECT m.id_look,m.id_type,l.nom nom_look,t.nom nom_type
        FROM Modele m 
        JOIN Look l ON m.id_look=l.id_look
        JOIN Type t ON t.id_type=m.id_type
    GROUP BY m.id_look,m.id_type,l.nom,t.nom
);

CREATE OR REPLACE VIEW v_modele_Sans_taille AS(
    SELECT m.id_look,m.id_type,l.nom nom_look,t.nom nom_type
        FROM Modele m 
        JOIN Look l ON m.id_look=l.id_look
        JOIN Type t ON t.id_type=m.id_type
    GROUP BY m.id_look,m.id_type,l.nom,t.nom
);

CREATE OR REPLACE VIEW v_modele_sans_detail AS (
    SELECT m.id,m.id_taille,ta.nom nom_taille,m.id_look,m.id_type,l.nom nom_look,t.nom nom_type
        FROM Modele m 
        JOIN Taille ta ON ta.id_taille=m.id_taille
        JOIN Look l ON m.id_look=l.id_look
        JOIN Type t ON t.id_type=m.id_type  
);

CREATE OR REPLACE view v_prix_modele AS(
    SELECT v.id id,v.id_type,v.nom_type,v.id_look,v.nom_look,v.id_taille,v.nom_taille,sum(m.prix*qte) prix_confection 
        FROM v_modele v
        JOIN Matiere_prix m ON v.id_matiere=m.id_matiere
    GROUP BY v.id,v.id_type,v.nom_type,v.id_look,v.nom_look,v.id_taille,v.nom_taille
); 


CREATE OR REPLACE view v_stock AS(
    SELECT 
        s.id_matiere, 
        m.nom,
        sum(entrer) entrer,
        sum(sortie) sortie,
        sum(entrer)-sum(sortie) reste
    FROM
        Stock s
    JOIN Matiere m ON m.id_matiere=s.id_matiere
    GROUP BY 
        s.id_matiere,m.nom
);

CREATE OR REPLACE VIEW v_salaire1 AS (
    SELECT id_specialite,max(date) date
        FROM specialite_salaire ss 
        GROUP BY id_specialite
);

CREATE OR REPLACE VIEW v_salaire AS (
    SELECT ss.id_specialite,ss.salaire_heure,ss.date
        FROM specialite_salaire ss 
        JOIN v_salaire1 v ON v.id_specialite=ss.id_specialite AND v.date=ss.date
);

CREATE OR REPLACE VIEW v_salaire_modele AS (
    SELECT ms.id_modele,sum(ss.salaire_heure*ms.nombre*(ms.duree_mn/60.0)) totalSalaire
        FROM Modele_specialite ms 
        JOIN specialite s ON s.id_specialite=ms.id_specialite
        JOIN v_salaire ss ON s.id_specialite=ss.id_specialite
        GROUP BY ms.id_modele
);

CREATE OR REPLACE VIEW v_prix_revient AS (
    SELECT id,id_type,nom_type,id_look,nom_look,id_taille,nom_taille,(prix_confection + totalSalaire) prix_revient
        FROM v_prix_modele vpm 
        JOIN v_salaire_modele vsm ON vpm.id=vsm.id_modele
); 

CREATE OR REPLACE VIEW v_vente1 AS(
    SELECT id_modele,max(date) date
        FROM Modele_prix_vente m
        GROUP BY id_modele
);  

CREATE OR REPLACE VIEW v_vente AS(
    SELECT m.id_modele,m.prix_vente,m.date
        FROM Modele_prix_vente m
        JOIN v_vente1 v ON m.id_modele=v.id_modele AND m.date=v.date
);  

CREATE OR REPLACE VIEW v_modele_benefice AS(
    SELECT id,id_type,nom_type,id_look,nom_look,id_taille,nom_taille,(prix_vente - prix_revient) benefice
        FROM v_vente m
        JOIN v_prix_revient vpr ON vpr.id=m.id_modele 
);