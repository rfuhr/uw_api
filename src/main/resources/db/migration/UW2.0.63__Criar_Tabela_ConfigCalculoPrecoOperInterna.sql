create sequence seq_config_calculo_preco_oper_interna start with 1;

create table config_calculo_preco_oper_interna (
	id						bigint not null constraint config_calculo_preco_oper_interna_pkey primary key,
	config_calculo_preco_id 	bigint not null,
    operacao_interna_id 		bigint not null,
    operacao_estoque			varchar(2) not null,
	data_inicio_vigencia 	date NOT NULL,
	data_final_vigencia 	date NOT NULL,	
	user_create     		bigint 		 not null,
	date_create				timestamp with time zone 	 not null,
	user_update     		bigint,
	date_update				timestamp with time zone,
	CONSTRAINT config_calculo_preco_oper_interna_config_calculo_preco_id_foreign FOREIGN KEY (config_calculo_preco_id) REFERENCES config_calculo_preco (id),
	CONSTRAINT config_calculo_preco_oper_interna_operacao_interna_id_foreign FOREIGN KEY (operacao_interna_id) REFERENCES operacao_interna (id)
);