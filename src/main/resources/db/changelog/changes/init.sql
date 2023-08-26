--liquibase formatted sql

--changeset nitin:1
CREATE TABLE public.employee (
	id bigserial NOT NULL,
	"name" varchar(255) NOT NULL,
	email varchar(255) NOT NULL,
	address varchar(255) NOT NULL,
	mobile_number varchar(255) NOT NULL,
	bio varchar(1000) NOT NULL,
	CONSTRAINT employee_un UNIQUE (email),
	CONSTRAINT employee_pk PRIMARY KEY (id)
);