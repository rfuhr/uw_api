create table tipo_telefone (
	value				char(3) not null constraint tipo_telefone_pkey primary key,
	name				varchar(250) not null
);

insert into tipo_telefone values ('COM', 'Comercial');
insert into tipo_telefone values ('REC', 'Recado');
insert into tipo_telefone values ('RES', 'Residencial');
insert into tipo_telefone values ('ZAP', 'WhatsApp');


create sequence seq_parceiro_local_telefone;

create table parceiro_local_telefone (
    id 					bigint       not null constraint parceiro_local_telefone_pkey primary key,
    parceiro_local_id 	bigint NOT NULL,
    tipo_telefone		varchar(3) NOT NULL,
    identificacao		varchar(120) NOT NULL,
    numero 				varchar(255) NOT NULL,
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone,
    CONSTRAINT parceiro_local_telefone_parceiro_local_id_foreign FOREIGN KEY (parceiro_local_id) REFERENCES parceiro_local (id)
);