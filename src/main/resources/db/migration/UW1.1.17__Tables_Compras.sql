create table situacao_solicitacao_compra (
	value				varchar(3) not null constraint situacao_solicitacao_compra_pkey primary key,
	name				varchar(250) not null
);

insert into situacao_solicitacao_compra values ('1', 'Aguardando Autorização');
insert into situacao_solicitacao_compra values ('2', 'Autorizada');
insert into situacao_solicitacao_compra values ('3', 'Fechada');
insert into situacao_solicitacao_compra values ('9', 'Cancelada');

-------------------------------------------------------------------

create table urgencia_solicitacao_compra (
	value				varchar(3) not null constraint urgencia_solicitacao_compra_pkey primary key,
	name				varchar(250) not null
);

insert into urgencia_solicitacao_compra values ('1', 'Baixa');
insert into urgencia_solicitacao_compra values ('2', 'Normal');
insert into urgencia_solicitacao_compra values ('3', 'Média');
insert into urgencia_solicitacao_compra values ('9', 'Alta');

-------------------------------------------------------------------

create sequence seq_item_simplificado;

create table item_simplificado (
	id					bigint not null constraint item_simplificado_pk primary key,
	codigo				bigint not null,
	nome				varchar(100) not null,
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone
);

-------------------------------------------------------------------
create sequence seq_solicitacao_compra;

create table solicitacao_compra (
	id						bigint not null constraint solicitacao_compra_pk primary key,
	numero					bigint not null,
	depto_solicitante_id	bigint not null,
	grupo_contabil_dest_id  bigint not null,
	depto_solicitado_id     bigint not null,
	data_solicitacao		date   not null,
	situacao_solicitacao_compra varchar(3) not null,
	user_create     		bigint not null,
	date_create				timestamp with time zone 	 not null,
	user_update     		bigint,
	date_update				timestamp with time zone,
	CONSTRAINT depto_solicitante_solicitacao_compra_foreign FOREIGN KEY (depto_solicitante_id) REFERENCES departamento (id),
	CONSTRAINT depto_solicitado_solicitacao_compra_foreign FOREIGN KEY (depto_solicitado_id) REFERENCES departamento (id),
	CONSTRAINT grupo_ctabil_dest_solicitacao_compra_foreign FOREIGN KEY (grupo_contabil_dest_id) REFERENCES grupo_contabil (id)
);	

-------------------------------------------------------------------

create sequence seq_solicitacao_compra_item;

create table solicitacao_compra_item (
	id						bigint not null constraint solicitacao_compra_item_pk primary key,
	item_id 				bigint,
	item_simplificado_id 	bigint,
	depto_entrega_id		bigint not null,
	qtd_solicitada			decimal(15,5) not null,
	qtd_enviada				decimal(15,5) not null,
	qtd_cancelada			decimal(15,5) not null,
	observacao				varchar(500),
	usuario_solicitacao_id	bigint not null,
	data_solicitacao		date not null,
	usuario_atendente_id	bigint,
	data_atendente			date,
	previsao_dias_utilizacao integer,
	urgencia_solicitacao_compra	varchar(3) not null,
	user_create     		bigint not null,
	date_create				timestamp with time zone 	 not null,
	user_update     		bigint,
	date_update				timestamp with time zone,
	CONSTRAINT item_solicitacao_compra_item_foreign FOREIGN KEY (item_id) REFERENCES item (id),
	CONSTRAINT item_simpl_solicitacao_compra_item_foreign FOREIGN KEY (item_simplificado_id) REFERENCES item_simplificado (id),
	CONSTRAINT urgencia_solicitacao_compra_item_foreign FOREIGN KEY (urgencia_solicitacao_compra) REFERENCES urgencia_solicitacao_compra (value),
	CONSTRAINT usuario_sol_solicitacao_compra_item_foreign FOREIGN KEY (usuario_solicitacao_id) REFERENCES usuario (id),
	CONSTRAINT usuario_ate_solicitacao_compra_item_foreign FOREIGN KEY (usuario_atendente_id) REFERENCES usuario (id)
);	

