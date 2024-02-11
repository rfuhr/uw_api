create sequence seq_grupo_operacao_interna;

create table grupo_operacao_interna (
	id					bigint not null constraint grupo_operacao_interna_pkey primary key,
	nome				varchar(250) not null,
	sigla				varchar(30) not null,
	tipo_operacao_id	bigint not null,
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone,
	CONSTRAINT grupo_operacao_interna_tipo_operacao_id_foreign FOREIGN KEY (tipo_operacao_id) REFERENCES tipo_operacao(id)
);