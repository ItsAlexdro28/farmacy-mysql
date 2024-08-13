DROP DATABASE IF EXISTS public;
CREATE DATABASE public;

USE public;

CREATE TABLE country (
	codecountry VARCHAR(5),
	namecountry VARCHAR(50),
	CONSTRAINT pk_country_codecountry PRIMARY KEY (codecountry)
);

-- desde_hacia_varLocal
CREATE TABLE region (
	codereg VARCHAR(5),
	namereg VARCHAR(50),
	codecountry VARCHAR(5),
	CONSTRAINT pk_region_codereg PRIMARY KEY (codereg),
	CONSTRAINT fk_region_country_codecountry FOREIGN KEY (codecountry) REFERENCES country(codecountry)
);

CREATE TABLE city (
	codecity VARCHAR(5),
	namecity VARCHAR(50),
	codereg VARCHAR(5),
	CONSTRAINT pk_city_codecity PRIMARY KEY (codecity),
	CONSTRAINT fk_city_region_codereg FOREIGN KEY (codereg) REFERENCES region(codereg)
);

CREATE TABLE modeadministration (
	id INT AUTO_INCREMENT,
	descriptionmode VARCHAR(60),
	CONSTRAINT pk_nodeadministration_id PRIMARY KEY (id)
);

CREATE TABLE unitmeasurement (
	idum INT AUTO_INCREMENT,
	nameum VARCHAR(50),
	CONSTRAINT pk_unitmeaserement_idum PRIMARY KEY (idum)
);

CREATE TABLE activeprinciple (
	idap INT AUTO_INCREMENT,
	nameap VARCHAR(60),
	CONSTRAINT pk_activeprinciple_idap PRIMARY KEY (idap)
);

CREATE TABLE laboratory (
	id INT AUTO_INCREMENT,
	namelab VARCHAR(50),
	codecityreg VARCHAR(5),
	CONSTRAINT pk_laboratory_id PRIMARY KEY (id),
	CONSTRAINT fk_laboratory_city_codecityreg FOREIGN KEY (codecityreg) REFERENCES city(codecity)
);

CREATE TABLE customer (
	idcustomer VARCHAR(20),
	namecustomer VARCHAR(50),
	lastnamecustomer VARCHAR(50),
	codecitycustomer VARCHAR(5),
	emailcustomer VARCHAR(100),
	birthdate DATE,
	longitude FLOAT(8),
	latitude FLOAT(8),
	CONSTRAINT pk_customer_idcustomer PRIMARY KEY (idcustomer),
	CONSTRAINT fk_customer_city_codecitycustomer FOREIGN KEY (codecitycustomer) REFERENCES city(codecity)
);

CREATE TABLE farmacy (
	idfarmacy INT AUTO_INCREMENT,
	namefarmacy VARCHAR(60),
	addressfarmacy VARCHAR(100),
	longitude FLOAT(8),
	latitude FLOAT(8),
	codecityfarm VARCHAR(5),
	logofarmacy VARCHAR(50),
	CONSTRAINT pk_farmacy_idfarmacy PRIMARY KEY (idfarmacy),
	CONSTRAINT fk_farmacy_city_codecityfarm FOREIGN KEY (codecityfarm) REFERENCES city(codecity)
); 

CREATE TABLE medicine (
	id INT AUTO_INCREMENT,
	proceedings VARCHAR(100),
	namemedicine VARCHAR(100),
	healthregister VARCHAR(50),
	description VARCHAR(200),
	descriptionshort VARCHAR(60),
	codemodeadmin INT(4),
	codeap INT(4),
	codeum INT(4),
	namerol VARCHAR(100),
	codelab INT(4),
	CONSTRAINT pk_medicine_id PRIMARY KEY (id),
	CONSTRAINT fk_medicine_modeadministration_codemodeadmin FOREIGN KEY (codemodeadmin) REFERENCES modeadministration(id),
	CONSTRAINT fk_medicine_unitmeasurement_codeum FOREIGN KEY (codeum) REFERENCES unitmeasurement(idum),
	CONSTRAINT fk_medicine_activeprinciple_codeap FOREIGN KEY (codeap) REFERENCES activeprinciple(idap),
	CONSTRAINT fk_medicine_laboratory_codelab FOREIGN KEY (codelab) REFERENCES laboratory(id)
);

CREATE TABLE farmacymedicine (
	idfarmacy INT(4),
	idmedicinefatrm INT(4),
	price FLOAT(8),
	CONSTRAINT pk_farmacymedicine_idfarmacy_idmedicinefatrm PRIMARY KEY (idfarmacy, idmedicinefatrm),
	CONSTRAINT fk_farmacymedicine_farmacy_idfarmacy FOREIGN KEY (idfarmacy) REFERENCES farmacy(idfarmacy),
	CONSTRAINT fk_farmacymedicine_medicine_idmedicinefatrm FOREIGN KEY (idmedicinefatrm) REFERENCES medicine(id)
);
