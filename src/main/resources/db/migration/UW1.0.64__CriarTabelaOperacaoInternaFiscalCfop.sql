create sequence seq_operacao_interna_fiscal_cfop;

create table operacao_interna_fiscal_cfop (
	id							bigint not null constraint operacao_interna_fiscal_cfop_pkey primary key,
	operacao_interna_fiscal_id 	bigint not null,
	cfop_id						bigint not null,
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone,
	CONSTRAINT operacao_interna_fiscal_cfop_operacao_interna_fiscal_id_foreign FOREIGN KEY (operacao_interna_fiscal_id) REFERENCES operacao_interna_fiscal(id),
	CONSTRAINT operacao_interna_fiscal_cfop_cfop_id_foreign FOREIGN KEY (cfop_id) REFERENCES cfop(id)
);