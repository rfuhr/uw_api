create table tipo_email (
	value				char(3) not null constraint tipo_email_pkey primary key,
	name				varchar(250) not null
);

insert into tipo_email values ('NFE', 'NFE');
insert into tipo_email values ('COM', 'Compra');
insert into tipo_email values ('RES', 'Residencial');
insert into tipo_email values ('OUT', 'Outros');

create sequence seq_parceiro_local_email;

create table parceiro_local_email (
    id 					bigint       not null constraint parceiro_local_email_pkey primary key,
    parceiro_local_id 	bigint NOT NULL,
    tipo_email			varchar(3) NOT NULL,
    identificacao		varchar(120) NOT NULL,
    email 				varchar(120) NOT NULL,
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone,
    CONSTRAINT parceiro_local_email_parceiro_local_id_foreign FOREIGN KEY (parceiro_local_id) REFERENCES parceiro_local (id)
);