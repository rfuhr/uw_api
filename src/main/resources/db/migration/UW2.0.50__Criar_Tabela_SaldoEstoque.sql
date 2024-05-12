create sequence seq_saldo_estoque start with 1;

create table saldo_estoque (
	id					bigint not null constraint saldo_estoque_pkey primary key,
	empresa_filial_id	bigint not null,
	local_estoque_id	bigint not null,
	grupo_contabil_id	bigint not null,
	item_id				bigint not null,
	data		 		date not null,
	saldo_quantidade	decimal(20,5) not null,
	saldo_valor			decimal(15,2) not null,
	custo_medio			decimal(20,10) not null,
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone,
	CONSTRAINT saldo_estoque_empresa_filial_id_foreign FOREIGN KEY (empresa_filial_id) REFERENCES empresa_filial (id),
	CONSTRAINT saldo_estoque_local_estoque_id_foreign FOREIGN KEY (local_estoque_id) REFERENCES local_estoque (id),
	CONSTRAINT saldo_estoque_grupo_contabil_id_foreign FOREIGN KEY (grupo_contabil_id) REFERENCES grupo_contabil (id),
	CONSTRAINT saldo_estoque_item_id_foreign FOREIGN KEY (item_id) REFERENCES item (id)
);
