create sequence seq_perfil;

create table perfil (
	id				bigint not null constraint perfil_pkey primary key,
	nome       		varchar(60) not null,
	descricao  		varchar(255) not null,
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone
);
