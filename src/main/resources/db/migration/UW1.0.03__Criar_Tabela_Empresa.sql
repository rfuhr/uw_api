create sequence seq_empresa;

create table empresa (
	id				bigint       not null constraint empresa_pkey primary key,
	nome            varchar(255) not null,
	sigla        	varchar(30)  not null,
	user_create     bigint 		 not null,
	date_create		timestamp with time zone 	 not null,
	user_update     bigint,
	date_update		timestamp with time zone
);