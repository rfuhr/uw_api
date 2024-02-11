create sequence seq_forma_baixa;

create table forma_baixa (
	id					bigint not null constraint forma_baixa_pkey primary key,
	nome				varchar(250) not null,
	sigla				varchar(30) not null,
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone
);