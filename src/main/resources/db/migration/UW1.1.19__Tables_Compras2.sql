create sequence seq_config_sistema_compra;

create table config_sistema_compra (
	id						bigint not null constraint config_sistema_compra_pk primary key,
	config_sistema_id   	bigint not null,			
	tipo_documento_solicitacao	bigint not null,
	tipo_documento_cotacao	bigint not null,
	user_create     		bigint not null,
	date_create				timestamp with time zone 	 not null,
	user_update     		bigint,
	date_update				timestamp with time zone,
	CONSTRAINT config_sistema_compra_foreign FOREIGN KEY (config_sistema_id) REFERENCES config_sistema (id),
	CONSTRAINT tipo_doc_config_sistema_compra_foreign FOREIGN KEY (tipo_documento_solicitacao) REFERENCES tipo_documento (id)
);	
-----------------------------

alter table config_sistema_financeiro add tipo_titulo_receber bigint not null default 1;
alter table config_sistema_financeiro add tipo_titulo_pagar bigint not null default 2;

alter table empresa_filial add general boolean not null default false;
alter table departamento add general boolean not null default false;
alter table grupo_contabil add general boolean not null default false;

insert into empresa_filial (id, empresa_id, nome, sigla, user_create, date_create, user_update, date_update, general)
select 	nextval('seq_empresa'), id, '<Todas>', 	'Todas', 1, current_timestamp, 1, current_timestamp , true
from empresa;

insert into departamento 
(id, empresa_filial_id, nome, sigla, user_create, date_create, user_update, date_update, general)
select nextval('seq_departamento'), id, '<Todas>', 'Todas', 1, current_timestamp, 1, current_timestamp, true
from empresa_filial;
	
-----------------------------------------------------------------------------

create sequence seq_valida_departamento_grupo_contabil;

create table valida_departamento_grupo_contabil (
	id						bigint not null constraint valida_departamento_grupo_contabil_pk primary key,
	departamento_id			bigint not null,
	grupo_contabil_id  		bigint not null,
	controla_estoque     	boolean not null default true,
	user_create     		bigint not null,
	date_create				timestamp with time zone 	 not null,
	user_update     		bigint,
	date_update				timestamp with time zone,
	CONSTRAINT departamento_valida_depto_grpctbil_foreign FOREIGN KEY (departamento_id) REFERENCES departamento (id),
	CONSTRAINT grupo_ctabil_valida_depto_grpctbil_foreign FOREIGN KEY (grupo_contabil_id) REFERENCES grupo_contabil (id)
);	

----------------------------------------------------


alter table item_simplificado add 	data_inicio_vigencia date not null;
alter table item_simplificado add 	data_final_vigencia  date not null;

-- drop sequence seq_solicitacao_compra;
-- drop sequence solicitacao_compra_item;

drop table situacao_solicitacao_compra;
drop table solicitacao_compra;
drop table solicitacao_compra_item;
drop table urgencia_solicitacao_compra;

create table situacao_solicitacao_mercadoria (
	value				varchar(3) not null constraint situacao_solicitacao_mercadoria_pkey primary key,
	name				varchar(250) not null
);

insert into situacao_solicitacao_mercadoria values ('1', 'Em Digitação');
insert into situacao_solicitacao_mercadoria values ('2', 'Digitada');
insert into situacao_solicitacao_mercadoria values ('3', 'Aguardando Autorização');
insert into situacao_solicitacao_mercadoria values ('4', 'Autorizada');
insert into situacao_solicitacao_mercadoria values ('5', 'Não Autorizada');
insert into situacao_solicitacao_mercadoria values ('6', 'Parcialmente Autorizada');
insert into situacao_solicitacao_mercadoria values ('7', 'Aberta');
insert into situacao_solicitacao_mercadoria values ('8', 'Fechada');
insert into situacao_solicitacao_mercadoria values ('9', 'Cancelada');
-------------------------------------------------------------------

create table status_solicitacao_mercadoria_item (
	value				varchar(3) not null constraint status_solicitacao_mercadoria_item_pkey primary key,
	name				varchar(250) not null
);

insert into status_solicitacao_mercadoria_item values ('1', 'Em Digitação');
insert into status_solicitacao_mercadoria_item values ('2', 'Digitada');
insert into status_solicitacao_mercadoria_item values ('3', 'Aguardando Autorização');
insert into status_solicitacao_mercadoria_item values ('4', 'Autorizada');
insert into status_solicitacao_mercadoria_item values ('5', 'Não Autorizada');
insert into status_solicitacao_mercadoria_item values ('6', 'Liberada');
insert into status_solicitacao_mercadoria_item values ('7', 'Cotada');
insert into status_solicitacao_mercadoria_item values ('9', 'Cancelada');

-------------------------------------------------------------------

create table urgencia_solicitacao_mercadoria (
	value				varchar(3) not null constraint urgencia_solicitacao_mercadoria_pkey primary key,
	name				varchar(250) not null
);

insert into urgencia_solicitacao_mercadoria values ('1', 'Baixa');
insert into urgencia_solicitacao_mercadoria values ('2', 'Normal');
insert into urgencia_solicitacao_mercadoria values ('3', 'Média');
insert into urgencia_solicitacao_mercadoria values ('9', 'Alta');

-------------------------------------------------------------------

create sequence seq_solicitacao_mercadoria;

create table solicitacao_mercadoria (
	id						bigint not null constraint solicitacao_mercadoria_pk primary key,
	numero					bigint not null,
	depto_solicitante_id	bigint not null,
	grupo_contabil_dest_id  bigint not null,
	depto_solicitado_id     bigint not null,
	data_solicitacao		date   not null,
	situacao_solicitacao_mercadoria varchar(3) not null,
	observacao varchar(500),
	user_create     		bigint not null,
	date_create				timestamp with time zone 	 not null,
	user_update     		bigint,
	date_update				timestamp with time zone,
	CONSTRAINT depto_solicitante_solicitacao_mercadoria_foreign FOREIGN KEY (depto_solicitante_id) REFERENCES departamento (id),
	CONSTRAINT depto_solicitado_solicitacao_mercadoria_foreign FOREIGN KEY (depto_solicitado_id) REFERENCES departamento (id),
	CONSTRAINT grupo_ctabil_dest_solicitacao_mercadoria_foreign FOREIGN KEY (grupo_contabil_dest_id) REFERENCES grupo_contabil (id),
	CONSTRAINT situacao_solicitacao_mercadoria_foreign FOREIGN KEY (situacao_solicitacao_mercadoria) REFERENCES situacao_solicitacao_mercadoria (value)
);	

-------------------------------------------------------------------

create sequence seq_solicitacao_mercadoria_item;

create table solicitacao_mercadoria_item (
	id						bigint not null constraint solicitacao_mercadoria_item_pk primary key,
	solicitacao_mercadoria_id bigint not null,
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
	urgencia_solicitacao_mercadoria	varchar(3) not null,
	status varchar(3) not null,
	user_create     		bigint not null,
	date_create				timestamp with time zone 	 not null,
	user_update     		bigint,
	date_update				timestamp with time zone,
	CONSTRAINT solicitacao_solicitacao_mercadoria_item_foreign FOREIGN KEY (solicitacao_mercadoria_id) REFERENCES solicitacao_mercadoria (id),
	CONSTRAINT item_solicitacao_mercadoria_item_foreign FOREIGN KEY (item_id) REFERENCES item (id),
	CONSTRAINT item_simpl_solicitacao_mercadoria_item_foreign FOREIGN KEY (item_simplificado_id) REFERENCES item_simplificado (id),
	CONSTRAINT urgencia_solicitacao_mercadoria_item_foreign FOREIGN KEY (urgencia_solicitacao_mercadoria) REFERENCES urgencia_solicitacao_mercadoria (value),
	CONSTRAINT usuario_sol_solicitacao_mercadoria_item_foreign FOREIGN KEY (usuario_solicitacao_id) REFERENCES usuario (id),
	CONSTRAINT usuario_ate_solicitacao_mercadoria_item_foreign FOREIGN KEY (usuario_atendente_id) REFERENCES usuario (id),
	CONSTRAINT status_solicitacao_mercadoria_item_foreign FOREIGN KEY (status) REFERENCES status_solicitacao_mercadoria_item (value)
);	

----------------------------------------

create table tipo_autorizacao (
	value				varchar(3) not null constraint tipo_autorizacao_pkey primary key,
	name				varchar(250) not null
);

insert into tipo_autorizacao values ('1', 'Solicitação de Compra');

-------------------------------------------------------------------
create table status_autorizacao (
	value				varchar(3) not null constraint status_autorizacao_pkey primary key,
	name				varchar(250) not null
);

insert into status_autorizacao values ('1', 'Aguardando Autorização');
insert into status_autorizacao values ('2', 'Autorização Confirmada');
insert into status_autorizacao values ('3', 'Autorização Negada');
insert into status_autorizacao values ('9', 'Autorização Cancelada');

-------------------------------------------------------------------
create sequence seq_autorizacao;

create table autorizacao (
	id						bigint not null constraint autorizacao_pk primary key,
	documento_origem_id		bigint not null,
	documento_identificacao varchar(60) not null,
	tipo_autorizacao		varchar(3) not null,
	status_autorizacao  	varchar(3) not null,
	usuario_solicitante_id	bigint not null,
	usuario_autorizador_id	bigint not null,
	data_solicitacao		date   not null,
	data_autorizacao		date,
	user_create     		bigint not null,
	date_create				timestamp with time zone 	 not null,
	user_update     		bigint,
	date_update				timestamp with time zone,
	CONSTRAINT tipo_autorizacao_foreign FOREIGN KEY (tipo_autorizacao) REFERENCES tipo_autorizacao (value),
	CONSTRAINT status_autorizacao_foreign FOREIGN KEY (status_autorizacao) REFERENCES status_autorizacao (value),
	CONSTRAINT usuario_solicitante_autorizacao_foreign FOREIGN KEY (usuario_solicitante_id) REFERENCES usuario (id),
	CONSTRAINT usuario_autorizador_autorizacao_foreign FOREIGN KEY (usuario_autorizador_id) REFERENCES usuario (id)
);	

--------------------------------------------------------------------

create sequence seq_config_autorizacao_solicitacao_mercadoria;

create table config_autorizacao_solicitacao_mercadoria (
	id						bigint not null constraint config_autorizacao_solicitacao_mercadoria_pk primary key,
	empresa_id				bigint not null,
	empresa_filial_id		bigint not null,
	departamento_id			bigint not null,
	grupo_contabil_id		bigint not null,
	autorizadores_id		varchar(200) not null,
	user_create     		bigint not null,
	date_create				timestamp with time zone 	 not null,
	user_update     		bigint,
	date_update				timestamp with time zone,
	CONSTRAINT empresa_config_aut_solicitacao_mercadoria_foreign FOREIGN KEY (empresa_id) REFERENCES empresa (id),
	CONSTRAINT empresa_filial_config_aut_solicitacao_mercadoria_foreign FOREIGN KEY (empresa_filial_id) REFERENCES empresa_filial (id),
	CONSTRAINT depto_config_aut_solicitacao_mercadoria_foreign FOREIGN KEY (departamento_id) REFERENCES departamento (id),
	CONSTRAINT grupo_ctabil_config_aut_solicitacao_mercadoria_foreign FOREIGN KEY (grupo_contabil_id) REFERENCES grupo_contabil (id)
);

-----------------------------------------------------------------------

create sequence seq_cotacao_mercadoria;

create table cotacao_mercadoria (
	id						bigint not null constraint cotacao_mercadoria_pk primary key,
	numero					bigint not null,
	depto_cotacao_id		bigint not null,
	data_cotacao			date   not null,
	user_create     		bigint not null,
	date_create				timestamp with time zone 	 not null,
	user_update     		bigint,
	date_update				timestamp with time zone,
	CONSTRAINT depto_cotacao_cotacao_mercadoria_foreign FOREIGN KEY (depto_cotacao_id) REFERENCES departamento (id)
);	

---------------------------------------------------------------------------

create sequence seq_cotacao_mercadoria_parceiro;

create table cotacao_mercadoria_parceiro (
	id						bigint not null constraint cotacao_mercadoria_parceiro_pk primary key,
	cotacao_mercadoria_id	bigint not null,
	parceiro_local_id  		bigint not null,
	user_create     		bigint not null,
	date_create				timestamp with time zone 	 not null,
	user_update     		bigint,
	date_update				timestamp with time zone,
	CONSTRAINT cotacao_cotacao_mercadoria_parceiro_foreign FOREIGN KEY (cotacao_mercadoria_id) REFERENCES cotacao_mercadoria (id),
	CONSTRAINT parceiro_local_cotacao_mercadoria_foreign FOREIGN KEY (parceiro_local_id) REFERENCES parceiro_local (id)
);	

-------------------------------------------------------------------------------

create sequence seq_cotacao_mercadoria_item;

create table cotacao_mercadoria_item (
	id						bigint not null constraint cotacao_mercadoria_item_pk primary key,
	cotacao_mercadoria_parceiro_id	bigint not null,
	item_id 				bigint,
	item_simplificado_id 	bigint,	
	solicitacao_mercadoria_item_id bigint not null,
	user_create     		bigint not null,
	date_create				timestamp with time zone 	 not null,
	user_update     		bigint,
	date_update				timestamp with time zone,
	CONSTRAINT cotacao_parceiro_cotacao_mercadoria_item_foreign FOREIGN KEY (cotacao_mercadoria_parceiro_id) REFERENCES cotacao_mercadoria_parceiro (id),
	CONSTRAINT item_cotacao_mercadoria_item_foreign FOREIGN KEY (item_id) REFERENCES item (id),
	CONSTRAINT item_simpl_cotacao_mercadoria_item_foreign FOREIGN KEY (item_simplificado_id) REFERENCES item_simplificado (id),
	CONSTRAINT solmercitem_cotacao_mercadoria_item_foreign FOREIGN KEY (cotacao_mercadoria_parceiro_id) REFERENCES cotacao_mercadoria_parceiro (id)
);