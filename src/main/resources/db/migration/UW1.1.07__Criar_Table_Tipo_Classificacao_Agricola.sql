create sequence seq_tipo_classificacao_agricola;

create table tipo_classificacao_agricola (
	id					bigint not null constraint tipo_classificacao_agricola_pk primary key,
	codigo				integer not null,
	nome				varchar(100) not null,
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone
);

