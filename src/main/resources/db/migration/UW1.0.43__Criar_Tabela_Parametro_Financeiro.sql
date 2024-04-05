create table indicador_dc (
	value				char(1) not null constraint indicador_dc_pkey primary key,
	name				varchar(250) not null
);

insert into indicador_dc values ('D', 'Débito');
insert into indicador_dc values ('C', 'Crédito');


create sequence seq_parametro_financeiro;

create table parametro_financeiro (
	id					bigint not null constraint parametro_financeiro_pkey primary key,
	empresa_id			bigint not null,
	tipo_titulo_id		bigint not null,
	caracteristica_movimento_financeiro_id	bigint not null,
	carteira_financeira_id bigint not null,
	fato_gerador_id     bigint not null,
	indicador_dc        char(1) not null,
	operacao_financeira_id bigint not null,
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone,	
	CONSTRAINT empresa_paranetro_financeiro_id_foreign FOREIGN KEY (empresa_id) REFERENCES empresa(id),
	CONSTRAINT tipo_titulo_parametro_financeiro_id_foreign FOREIGN KEY (tipo_titulo_id) REFERENCES tipo_titulo(id),
	CONSTRAINT caracteristica_movimento_financeiro_parametro_financeiro_id_foreign FOREIGN KEY (caracteristica_movimento_financeiro_id) REFERENCES caracteristica_movimento_financeiro(id),
	CONSTRAINT carteira_parametro_financeiro_id_foreign FOREIGN KEY (carteira_financeira_id) REFERENCES carteira_financeira(id),
	CONSTRAINT fato_gerador_parametro_financeiro_id_foreign FOREIGN KEY (fato_gerador_id) REFERENCES fato_gerador(id),
	CONSTRAINT operacao_financeira_parametro_financeiro_id_foreign FOREIGN KEY (operacao_financeira_id) REFERENCES operacao_financeira(id)
);