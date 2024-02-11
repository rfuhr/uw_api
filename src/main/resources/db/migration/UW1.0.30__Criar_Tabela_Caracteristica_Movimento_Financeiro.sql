create sequence seq_caracteristica_movimento_financeiro;

create table caracteristica_movimento_financeiro (
	id					bigint not null constraint caracteristica_movimento_financeiro_pkey primary key,
	codigo				integer not null,
	nome				varchar(250) not null,
	sigla				varchar(30) not null,
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone
);