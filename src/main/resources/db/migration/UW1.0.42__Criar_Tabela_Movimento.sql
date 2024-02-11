create sequence seq_movimento_financeiro;

create table movimento_financeiro (
	id					bigint not null constraint movimento_financeiro_pkey primary key,
	parcela_id			bigint not null,
	departamento_id		bigint not null,
	seq_mvto			integer not null,
	sub_seq_mvto		integer not null,
	operacao_financeira_id integer not null,
	grupo_financeiro_id integer not null,
	carteira_id         integer not null,
	valor_movimento     decimal(15,2) not null,
	data_movimento      date not null,
	saldo_parcela       decimal(15,2) not null,
	data_lancamento     timestamp not null,
	observacao          varchar(200) not null,
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone,	
	CONSTRAINT parcela_movimento_id_foreign FOREIGN KEY (parcela_id) REFERENCES parcela_financeiro(id),
	CONSTRAINT departamento_movimento_id_foreign FOREIGN KEY (departamento_id) REFERENCES departamento(id),
	CONSTRAINT operacao_financeira_movimento_id_foreign FOREIGN KEY (operacao_financeira_id) REFERENCES operacao_financeira(id),
	CONSTRAINT grupo_financeiro_movimento_id_foreign FOREIGN KEY (grupo_financeiro_id) REFERENCES grupo_financeiro(id)
);