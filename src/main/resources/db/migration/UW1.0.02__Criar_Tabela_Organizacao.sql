create sequence seq_organizacao;

create table organizacao (
	id				bigint       not null constraint organizacao_pkey primary key,
	nome   			varchar(120) not null,
	sigla			varchar(30)  not null,
	tenant     		varchar(30)  not null,
	user_create     bigint 		 not null,
	date_create		timestamp with time zone 	 not null,
	user_update     bigint,
	date_update		timestamp with time zone
);