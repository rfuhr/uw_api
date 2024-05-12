create sequence seq_operacao_interna_estoque start with 1;

create table operacao_interna_estoque (
	id					bigint not null constraint operacao_interna_estoque_pkey primary key,
	operacao_interna_id		bigint not null,
	informa_local_estoque	boolean not null,
	informa_grupo_contabil	boolean not null,
	calcula_custo_medio		boolean not null,
	local_estoque_id		bigint,
	grupo_contabil_id		bigint,
	operacao_estoque_fisico varchar(2) not null,
	operacao_estoque_financeiro varchar(2) not null,
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone,
	CONSTRAINT operacao_interna_estoque_operacao_interna_id_foreign FOREIGN KEY (operacao_interna_id) REFERENCES operacao_interna (id),
	CONSTRAINT operacao_interna_estoque_local_estoque_id_foreign FOREIGN KEY (local_estoque_id) REFERENCES local_estoque (id),
	CONSTRAINT operacao_interna_estoque_grupo_contabil_id_foreign FOREIGN KEY (grupo_contabil_id) REFERENCES grupo_contabil (id),
	CONSTRAINT operacao_interna_estoque_operacao_estoque_fisico_foreign FOREIGN KEY (operacao_estoque_fisico) REFERENCES operacao_estoque (value),
	CONSTRAINT operacao_interna_estoque_operacao_estoque_financeiro_foreign FOREIGN KEY (operacao_estoque_financeiro) REFERENCES operacao_estoque (value)
);
