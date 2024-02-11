create sequence seq_grupo_financeiro;

create table grupo_financeiro (
	id					bigint not null constraint grupo_financeiro_pkey primary key,
	codigo				integer not null,
	nome				varchar(250) not null,
	sigla				varchar(30) not null,
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone
);