create sequence seq_config_sistema;

create table config_sistema (
	id					bigint not null constraint pk_config_sistema primary key,
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone not null,
	user_update     	bigint,
	date_update			timestamp with time zone
);

create sequence seq_config_sistema_financeiro;

create table config_sistema_financeiro (
	id					bigint not null constraint pk_config_sistema_financeiro primary key,
	config_sistema_id   bigint not null,
	oper_movfin_inc		bigint not null,
	oper_movfin_bx		bigint not null,
	oper_acefin_princ	bigint not null,
	oper_acefin_jurpac	bigint not null,
	tipo_opfin_lcto		bigint not null,
	tipo_opfin_estorno	bigint not null,
	grupo_financeiro_neg bigint not null,
	fato_gerador_neg	bigint not null,
	carteira_financeira_neg bigint not null,
	caracter_movfin_neg	bigint not null,
	hist_padrao_neg 	bigint not null,
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone not null,
	user_update     	bigint,
	date_update			timestamp with time zone,	
	CONSTRAINT config_sistema_financeiro_foreign FOREIGN KEY (config_sistema_id) REFERENCES config_sistema (id),
	CONSTRAINT oper_movfin_inc_config_sistema FOREIGN KEY (oper_movfin_inc) REFERENCES operacao_movimento_financeiro (id),
	CONSTRAINT oper_movfin_bx_config_sistema FOREIGN KEY (oper_movfin_bx) REFERENCES operacao_movimento_financeiro (id),
	CONSTRAINT oper_acefin_princ_config_sistema FOREIGN KEY (oper_acefin_princ) REFERENCES operacao_acessoria_financeira (id),
	CONSTRAINT oper_acefin_jurpac_config_sistema FOREIGN KEY (oper_acefin_jurpac) REFERENCES operacao_acessoria_financeira (id),
	CONSTRAINT tipo_opfin_lcto_config_sistema FOREIGN KEY (tipo_opfin_lcto) REFERENCES tipo_operacao_financeira (id),
	CONSTRAINT tipo_opfin_estorno_config_sistema FOREIGN KEY (tipo_opfin_estorno) REFERENCES tipo_operacao_financeira (id),
	CONSTRAINT grupo_financeiro_neg_config_sistema FOREIGN KEY (grupo_financeiro_neg) REFERENCES grupo_financeiro (id),
	CONSTRAINT fato_gerador_neg_config_sistema FOREIGN KEY (fato_gerador_neg) REFERENCES fato_gerador (id),
	CONSTRAINT carteira_financeira_neg_config_sistema FOREIGN KEY (carteira_financeira_neg) REFERENCES carteira_financeira (id),
	CONSTRAINT caracter_movfin_neg_config_sistema FOREIGN KEY (caracter_movfin_neg) REFERENCES caracteristica_movimento_financeiro (id),
	CONSTRAINT hist_padrao_neg_config_sistema FOREIGN KEY (hist_padrao_neg) REFERENCES historico_padrao (id)
);

insert into config_sistema (id, user_create, date_create, user_update, date_update) values (nextval('seq_config_sistema'), 1, NOW()::timestamp,1,NOW()::timestamp);	