create sequence seq_negociacao_financeira;

create table negociacao_financeira (
	id					bigint not null constraint negociacao_financeira_pkey primary key,
	departamento_id  	bigint not null,
	parceiro_local_id	bigint not null,
	vlr_total_atraso    decimal(15,2) not null,
	vlr_juros_negociado decimal(15,2) not null,
	vlr_desc_negociado  decimal(15,2) not null,
	vlr_negociado_pagar decimal(15,2) not null,
	data_negociacao     date not null,
	nosso_numero		bigint not null,
	observacao          varchar(255) not null,
	qtde_parcelas		integer not null,
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone not null,
	user_update     	bigint,
	date_update			timestamp with time zone,
	CONSTRAINT parceiro_local_negociacao_foreign FOREIGN KEY (parceiro_local_id) REFERENCES parceiro_local (id),
	CONSTRAINT departamento_negociacao_foreign FOREIGN KEY (departamento_id) REFERENCES departamento (id)
);

