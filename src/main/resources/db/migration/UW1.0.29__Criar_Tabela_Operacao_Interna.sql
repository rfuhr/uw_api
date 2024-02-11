create sequence seq_operacao_interna;

create table operacao_interna (
	id					bigint not null constraint operacao_interna_pkey primary key,
	nome				varchar(250) not null,
	sigla				varchar(30) not null,
	grupo_operacao_interna_id	bigint not null,
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone,
	CONSTRAINT operacao_interna_grupo_operacao_interna_id_foreign FOREIGN KEY (grupo_operacao_interna_id) REFERENCES grupo_operacao_interna(id)
);