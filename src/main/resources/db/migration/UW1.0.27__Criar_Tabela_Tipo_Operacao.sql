create sequence seq_tipo_operacao;

create table tipo_operacao (
	id					bigint not null constraint tipo_operacao_pkey primary key,
	nome				varchar(250) not null,
	entrada				boolean not null,
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone
);