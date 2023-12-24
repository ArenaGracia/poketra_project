/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  ASUS
 * Created: 14 déc. 2023
 */

CREATE OR REPLACE VIEW v_Matiere_unite as (
    select m.id  ,m.id_matiere  ,m.id_unite ,u.nom nom_unite, ma.nom nom_matiere
        from Matiere_unite m
        join matiere ma on ma.id_matiere=m.id_matiere
        join unite u on m.id_unite=u.id_unite
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