create sequence seq_profissao;

create table profissao (
	id					bigint       not null constraint profissao_pkey primary key,
	codigo      		integer not null,
	nome			 	varchar(120) NOT NULL,
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone
);