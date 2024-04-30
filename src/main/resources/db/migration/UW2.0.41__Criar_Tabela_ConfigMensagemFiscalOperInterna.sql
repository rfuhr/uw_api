create sequence seq_config_mensagem_fiscal_oper_interna start with 1;

create table config_mensagem_fiscal_oper_interna (
	id					bigint not null constraint config_mensagem_fiscal_oper_interna_pkey primary key,
	operacao_interna_id	bigint 		not null,
	data_inicio_vigencia 		date not null,
	data_final_vigencia 		date not null,
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone,
	CONSTRAINT config_mensagem_fiscal_oper_interna_operacao_interna_id_foreign FOREIGN KEY (operacao_interna_id) REFERENCES operacao_interna (id)
);