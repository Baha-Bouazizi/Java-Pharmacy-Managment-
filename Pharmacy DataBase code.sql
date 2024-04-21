CREATE DATABASE  gestionpharmacie;
USE gestionpharmacie;

CREATE TABLE clients (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(255),
    adresse VARCHAR(255),
    telephone VARCHAR(20),
    credit DECIMAL(10,2)
);



CREATE TABLE ordonnances (
    id INT AUTO_INCREMENT PRIMARY KEY,
    numero_ordonnance INT,
    nom_client VARCHAR(20),
 
    FOREIGN KEY (nom_client) REFERENCES clients(id)
);

CREATE TABLE utilisateurs (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom_utilisateur VARCHAR(255),
    mot_de_passe VARCHAR(255),
    role ENUM('administrateur', 'pharmacien')
);

CREATE TABLE clients_medicaments (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_client INT,
    id_medicament INT,
    quantite INT,
    FOREIGN KEY (id_client) REFERENCES clients(id),
    FOREIGN KEY (id_medicament) REFERENCES medicaments(id)
);


drop table medicaments_ordonnances;
INSERT INTO clients VALUES (1,"bouazizi","Baha","50024865",3.5);

SELECT * FROM clients;
drop table medicaments;
CREATE TABLE medicaments (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(20),
    prix INT(10),
    quantite INT(10)
);
select * from medicaments;
DROP TABLE ordonnances;

drop table medicaments_ordonnances;
drop table ordonnances;
CREATE TABLE medicaments_ordonnances (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_ordonnance INT,
    id_medicament INT,
    quantite INT,
    FOREIGN KEY (id_ordonnance) REFERENCES ordonnances(id),
    FOREIGN KEY (id_medicament) REFERENCES medicaments(id)
);
CREATE TABLE ordonnances (
    id INT AUTO_INCREMENT PRIMARY KEY,
    numero_ordonnance INT,
    id_client Int,
 
    FOREIGN KEY (id_client) REFERENCES clients(id)
);

ALTER TABLE ordonnances ADD COLUMN date DATE;
ALTER TABLE ordonnances ADD COLUMN remarque VARCHAR(255);
ALTER TABLE ordonnances DROP COLUMN date;
ALTER TABLE ordonnances ADD COLUMN date DATE;
ALTER TABLE ordonnances ADD COLUMN remarque VARCHAR(255);
ALTER TABLE ordonnances DROP COLUMN remarque;
select * from ordonnances ;
select * from medicaments ;

