create sequence seq_historico_padrao;

create table historico_padrao (
	id					bigint not null constraint historico_padrao_pkey primary key,
	codigo				integer not null,
	nome				varchar(250) not null,
	sigla				varchar(30) not null,
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone
);