
CREATE OR REPLACE VIEW v_Matiere_unite as 
    select Matiere_unite.id  ,Matiere_unite.id_matiere  ,Matiere_unite.id_unite ,unite.nom from Matiere_unite join unite on matiere_unite.id_unite=unite.id_unite; /* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  ASUS
 * Created: 14 déc. 2023
 */

