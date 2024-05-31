create sequence seq_tabela_preco start with 1;

create table tabela_preco (
	id						bigint not null constraint tabela_preco_pkey primary key,
	codigo                  integer not null,
	nome					varchar(250) not null,
	tipo_preco_id			bigint not null,
	empresa_filial_id 		bigint not null,
	grupo_contabil_id       bigint,
	promocional				boolean not null,
	data_inicio_vigencia 	date NOT NULL,
	data_final_vigencia 	date NOT NULL,	
	user_create     		bigint 		 not null,
	date_create				timestamp with time zone 	 not null,
	user_update     		bigint,
	date_update				timestamp with time zone,
	CONSTRAINT tabela_preco_tipo_preco_id_foreign FOREIGN KEY (tipo_preco_id) REFERENCES tipo_preco (id),
	CONSTRAINT tabela_preco_grupo_contabil_id_foreign FOREIGN KEY (grupo_contabil_id) REFERENCES grupo_contabil (id),
	CONSTRAINT tabela_preco_empresa_filial_id_foreign FOREIGN KEY (empresa_filial_id) REFERENCES empresa_filial (id)
);