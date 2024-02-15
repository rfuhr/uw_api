create sequence seq_grupo_tributacao start with 1;

create table grupo_tributacao (
	id					bigint not null constraint grupo_tributacao_pkey primary key,
	codigo				integer not null,
	nome				varchar(250) not null,
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone
);