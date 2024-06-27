create sequence seq_tabela_preco_item start with 1;

create table tabela_preco_item (
	id						bigint not null constraint tabela_preco_item_pkey primary key,
	tabela_preco_id			bigint not null,
	item_id 				bigint not null,
	data_inicio_vigencia 	date NOT NULL,
	data_final_vigencia 	date NOT NULL,	
	valor_custo				decimal(20,10) NOT NULL,
	valor_markup			decimal(20,10) NOT NULL,
	valor_calculado			decimal(20,10) NOT NULL,
	valor_atual				decimal(20,10) NOT NULL,
	valor					decimal(20,10) NOT NULL,
	percentual_maximo_desconto decimal(15,3) NOT NULL,
	user_create     		bigint 		 not null,
	date_create				timestamp with time zone 	 not null,
	user_update     		bigint,
	date_update				timestamp with time zone,
	CONSTRAINT tabela_preco_item_tabela_preco_id_foreign FOREIGN KEY (tabela_preco_id) REFERENCES tabela_preco (id),
	CONSTRAINT tabela_preco_item_item_id_foreign FOREIGN KEY (item_id) REFERENCES item (id)
);