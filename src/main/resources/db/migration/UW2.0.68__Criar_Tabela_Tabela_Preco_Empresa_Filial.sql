create sequence seq_tabela_preco_empresa_filial start with 1;

create table tabela_preco_empresa_filial (
	id						bigint not null constraint tabela_preco_empresa_filial_pkey primary key,
	tabela_preco_id			bigint not null,
	empresa_filial_id 		bigint not null,
	user_create     		bigint 		 not null,
	date_create				timestamp with time zone 	 not null,
	user_update     		bigint,
	date_update				timestamp with time zone,
	CONSTRAINT tabela_preco_empresa_filial_tabela_preco_id_foreign FOREIGN KEY (tabela_preco_id) REFERENCES tabela_preco (id),
	CONSTRAINT tabela_preco_empresa_filial_empresa_filial_id_foreign FOREIGN KEY (empresa_filial_id) REFERENCES empresa_filial (id)
);