create sequence seq_tipo_titulo;

create table tipo_titulo (
	id					bigint not null constraint tipo_titulo_pkey primary key,
	codigo				integer not null,
	nome				varchar(250) not null,
	sigla				varchar(30) not null,
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone
);