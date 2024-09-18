create sequence seq_tipo_conta_cxbco;

create table tipo_conta_cxbco (
	id					bigint not null constraint tipo_conta_cxbco_pkey primary key,
	codigo				int not null,
	nome				varchar(100) not null,
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone not null,
	user_update     	bigint,
	date_update			timestamp with time zone
);

insert into tipo_conta_cxbco (id, codigo, nome, user_create, date_create, user_update, date_update)
 values (nextval('seq_tipo_conta_cxbco'), 1, 'Conta Caixa', 1, NOW()::timestamp, 1, NOW()::timestamp); 
insert into tipo_conta_cxbco (id, codigo, nome, user_create, date_create, user_update, date_update)
 values (nextval('seq_tipo_conta_cxbco'), 2, 'Conta Corrente', 1, NOW()::timestamp, 1, NOW()::timestamp); 
insert into tipo_conta_cxbco (id, codigo, nome, user_create, date_create, user_update, date_update)
 values (nextval('seq_tipo_conta_cxbco'), 3, 'Conta Poupança', 1, NOW()::timestamp, 1, NOW()::timestamp); 
 
 --------
 
 create sequence seq_operacao_cxbco;
 
 create table operacao_cxbco (
	id					bigint not null constraint operacao_cxbco_pkey primary key,
	codigo				int not null,
	nome                varchar(100) not null,
	sigla				varchar(20) not null,
	digita_vencimento	boolean not null,
	digita_data_mvtobco boolean not null,
	digita_hist_padrao  boolean not null,
	valor_default_hp_id bigint,
	digita_fato_gerador boolean not null,
	valor_default_fg_id bigint,
	digita_compl_hp     boolean not null,
	tipo_conta_cxbco_id bigint not null,
	indicador_dc		char(1) not null,
	digita_documento    boolean not null,
	digita_parceiro     boolean not null,
	valor_default_parceiro_local  bigint,
	idn_transferencia   boolean not null,
	transf_operacao_cxbco_id bigint,
	emite_recibo        boolean not null,
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone not null,
	user_update     	bigint,
	date_update			timestamp with time zone,
	CONSTRAINT operacao_cxbco_id_foreign FOREIGN KEY (transf_operacao_cxbco_id) REFERENCES operacao_cxbco (id)
);
	
--------

create sequence seq_movimento_cxbco;

CREATE TABLE movimento_cxbco
	(
	id                 bigint not null,
	departamento_id	   bigint not null,
	nosso_numero       bigint not null,
	operacao_cxbco_id  bigint not null,
	conta_id           bigint,
	conta_id_transf    bigint,
	data_movimento     date not null,
	numero_documento   varchar(15),
	data_movimento_bco date,
	data_vencimento    date,
	parceiro_local_id  bigint,
	fato_gerador_id    bigint,
	historico_padrao_id bigint,
	complemento_hp     varchar (80),
	valor              DECIMAL (15,2),
	indicador_dc       char(1),
	idn_compesado      boolean default false,
	data_compensado    date,
	grupo_financeiro_id bigint not null,
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone not null,
	user_update     	bigint,
	date_update			timestamp with time zone,
	CONSTRAINT departamento_id_movimento_caixa_banco FOREIGN KEY (departamento_id) REFERENCES departamento (id),
	CONSTRAINT conta_movimento_caixa_banco FOREIGN KEY (conta_id) REFERENCES conta (id),
	CONSTRAINT conta_transf_movimento_caixa_banco FOREIGN KEY (conta_id_transf) REFERENCES conta (id),
	CONSTRAINT operacao_cxbco_movimento_caixa_banco FOREIGN KEY (operacao_cxbco_id) REFERENCES operacao_cxbco (id),
	CONSTRAINT parceiro_local_movimento_caixa_banco FOREIGN KEY (parceiro_local_id) REFERENCES parceiro_local (id),
	CONSTRAINT fato_gerador_movimento_caixa_banco FOREIGN KEY (fato_gerador_id) REFERENCES fato_gerador (id),
	CONSTRAINT historico_padrao_movimento_caixa_banco FOREIGN KEY (historico_padrao_id) REFERENCES historico_padrao (id),
	PRIMARY KEY (id)
	);
	
alter table conta add column tipo_conta_cxbco_id bigint not null;
alter table conta add CONSTRAINT conta_tipo_conta_id_foreign FOREIGN KEY (tipo_conta_cxbco_id) REFERENCES tipo_conta_cxbco (id);
alter table conta add column nome varchar(100) not null;

---------------------

create sequence seq_saldo_cxbco;

CREATE TABLE saldo_cxbco
	(
	id                 bigint not null,
	conta_id           bigint not null,
	data     		   date not null,
	saldo_valor        DECIMAL (15,2),
	user_create        bigint 		 not null,
	date_create		   timestamp with time zone not null,
	user_update        bigint,
	date_update		   timestamp with time zone,
	CONSTRAINT conta_saldo_caixa_banco FOREIGN KEY (conta_id) REFERENCES conta (id),
	PRIMARY KEY (id)
	);