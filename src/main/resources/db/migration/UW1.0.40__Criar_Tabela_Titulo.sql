create sequence seq_titulo;

create table titulo (
	id					bigint not null constraint titulo_pkey primary key,
	departamento_id     bigint not null,
	parceiro_local_id   bigint not null,
	tipo_titulo_id      bigint not null,	
	grupo_financeiro_id bigint not null,
	fato_gerador_id     bigint not null,
	caracteristica_movimento_financeiro_id bigint not null,
	historico_padrao_id bigint not null,
	documento           varchar(40),
	data_documento		date,
	observacao			varchar(200),
	valor_total			decimal(15,2),
	historico			varchar(200),
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone,	
	CONSTRAINT departamento_titulo_id_foreign FOREIGN KEY (departamento_id) REFERENCES departamento (id),
	CONSTRAINT parceiro_local_titulo_id_foreign FOREIGN KEY (parceiro_local_id) REFERENCES parceiro_local (id),
	CONSTRAINT tipo_titulo_titulo_id_foreign FOREIGN KEY (tipo_titulo_id) REFERENCES tipo_titulo (id),
	CONSTRAINT grupo_financeiro_titulo_id_foreign FOREIGN KEY (grupo_financeiro_id) REFERENCES grupo_financeiro (id),
	CONSTRAINT fato_gerador_id_foreign FOREIGN KEY (fato_gerador_id) REFERENCES fato_gerador (id),
	CONSTRAINT caracteristica_movimento_financeiro_id_foreign FOREIGN KEY (caracteristica_movimento_financeiro_id) REFERENCES caracteristica_movimento_financeiro (id),
	CONSTRAINT historico_padrao_id_foreign FOREIGN KEY (historico_padrao_id) REFERENCES historico_padrao (id)
);