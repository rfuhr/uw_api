alter table parceiro_local_endereco add principal boolean not null default false;

create table base_calculo_agricola (
	value				varchar(3)  not null constraint base_calculo_agricola_pk primary key,
	name				varchar(80) not null
);

insert into base_calculo_agricola (value, name)
values ('1', 'Peso Bruto');

insert into base_calculo_agricola (value, name)
values ('2', 'Tara');

insert into base_calculo_agricola (value, name)
values ('3', 'Peso Líquido');

insert into base_calculo_agricola (value, name)
values ('99', 'Sem Base');

------------------------------------------------------------------

create sequence seq_tipo_calculo_agricola;

create table tipo_calculo_agricola (
	id					bigint not null constraint tipo_calculo_agricola_pk primary key,
	base_calculo_agricola varchar(3) not null,
	codigo   			bigint not null,
	nome				varchar(80) not null,
	indicador_dc		varchar(3),
	data_inicio_vigencia date not null,
	data_final_vigencia  date not null,
	user_create     	bigint not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone
);


-------------------------------------------------------
create sequence seq_grupo_classificacao_agricola;

create table grupo_classificacao_agricola (
	id					bigint not null constraint grupo_classificacao_agricola_pk primary key,
	codigo   			bigint not null,
	nome				varchar(80) not null,
	data_inicio_vigencia date not null,
	data_final_vigencia  date not null,
	user_create     	bigint not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone
);

insert into grupo_classificacao_agricola (id, codigo, nome, data_inicio_vigencia, data_final_vigencia, user_create, date_create, user_update, date_update)
values (nextval('seq_grupo_classificacao_agricola'), '0', 'Classificação', '2024-01-01 00:00:00', '2999-12-31 23:59:59', 1, NOW()::timestamp, 1, NOW()::timestamp);


-------------------------------------------------------
create sequence seq_item_classificacao_agricola;

create table item_classificacao_agricola (
	id					bigint not null constraint item_classificacao_agricola_pk primary key,
	grupo_classificacao_agricola_id bigint not null,
	codigo   			bigint not null,
	nome				varchar(80) not null,
	tipo_calculo_agricola_id_romaneio bigint,
	data_inicio_vigencia date not null,
	data_final_vigencia  date not null,
	user_create     	bigint not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone,
	CONSTRAINT grupo_classificacao_item_classificacao_agricola_foreign FOREIGN KEY (grupo_classificacao_agricola_id) REFERENCES grupo_classificacao_agricola (id),
	CONSTRAINT tipo_calculo_item_classificacao_agricola_foreign FOREIGN KEY (tipo_calculo_agricola_id_romaneio) REFERENCES tipo_calculo_agricola (id)
);

insert into item_classificacao_agricola (id, grupo_classificacao_agricola_id, codigo, nome, tipo_calculo_agricola_id_romaneio, data_inicio_vigencia, data_final_vigencia, user_create, date_create, user_update, date_update)
values (nextval('seq_item_classificacao_agricola'), (select id from grupo_classificacao_agricola where codigo = '0'), '0', 'Sem Classificação', null, '2024-01-01 00:00:00', '2999-12-31 23:59:59', 1, NOW()::timestamp, 1, NOW()::timestamp);

-------------------------------------------------------------

create sequence seq_sub_item_classificacao_agricola;

create table sub_item_classificacao_agricola (
	id					bigint not null constraint sub_item_classificacao_agricola_pk primary key,
	item_id 			bigint not null,
	item_classificacao_agricola_id bigint not null,
	codigo   			varchar(15) not null,
	nome				varchar(80) not null,
	indice_referencia   decimal(15,5) not null,
	item_classificacao_agricola_gerado_id bigint,
	sub_item_classificacao_agricola_gerado_id bigint,
	data_inicio_vigencia date not null,
	data_final_vigencia  date not null,
	user_create     	bigint not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone,
	CONSTRAINT item_sub_item_classificacao_agricola_foreign FOREIGN KEY (item_id) REFERENCES item (id),
	CONSTRAINT item_class_sub_item_classificacao_agricola_foreign FOREIGN KEY (item_classificacao_agricola_id) REFERENCES item_classificacao_agricola (id),
	CONSTRAINT item_class_gerado_sub_item_classificacao_agricola_foreign FOREIGN KEY (item_classificacao_agricola_gerado_id) REFERENCES item_classificacao_agricola (id),
	CONSTRAINT subitem_class_gerado_sub_item_classificacao_agricola_foreign FOREIGN KEY (sub_item_classificacao_agricola_gerado_id) REFERENCES sub_item_classificacao_agricola (id)
);

-------------------------------------------------------------

create sequence seq_valida_item_classificacao_agricola;

create table valida_item_classificacao_agricola (
	id					bigint not null constraint valida_item_classificacao_agricola_pk primary key,
	item_id   			bigint not null,
	item_classificacao_agricola_id bigint not null,
	tipo_uso_romaneio   varchar(2) not null,
	obrigatorio			boolean not null default false,
	ordem_tela			integer not null,
	data_inicio_vigencia date not null,
	data_final_vigencia  date not null,	
	user_create     	bigint not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone,
	CONSTRAINT item_valida_item_classificacao_foreign FOREIGN KEY (item_id) REFERENCES item (id),
	CONSTRAINT item_classif_valida_item_classificacao_foreign FOREIGN KEY (item_classificacao_agricola_id) REFERENCES item_classificacao_agricola (id)
);

-------------------------------------------------------------

create sequence seq_grupo_operacao_agricola;

create table grupo_operacao_agricola (
	id					bigint not null constraint grupo_operacao_agricola_pk primary key,
	codigo   			bigint not null,
	nome				varchar(80) not null,
	data_inicio_vigencia date not null,
	data_final_vigencia  date not null,
	user_create     	bigint not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone
);

insert into grupo_operacao_agricola 
(id, codigo, nome, data_inicio_vigencia, data_final_vigencia, user_create, date_create, user_update, date_update)
values
(nextval('seq_grupo_operacao_agricola'), 1, 'Produtor Pessoa Física', '2024-01-01 00:00:00', '2999-12-31 23:59:59', 1, NOW()::timestamp, 1, NOW()::timestamp);

insert into grupo_operacao_agricola 
(id, codigo, nome, data_inicio_vigencia, data_final_vigencia, user_create, date_create, user_update, date_update)
values
(nextval('seq_grupo_operacao_agricola'), 2, 'Produtor Pessoa Jurídica', '2024-01-01 00:00:00', '2999-12-31 23:59:59', 1, NOW()::timestamp, 1, NOW()::timestamp);

-------------------------------------------------------------
create sequence seq_regra_atividade;

create table regra_atividade (
	id					bigint not null constraint regra_atividade_pk primary key,
	codigo   			bigint not null,
	nome				varchar(80) not null,
	data_inicio_vigencia date not null,
	data_final_vigencia  date not null,
	user_create     	bigint not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone
);

insert into regra_atividade 
(id, codigo, nome, data_inicio_vigencia, data_final_vigencia, user_create, date_create, user_update, date_update)
values
(nextval('seq_regra_atividade'), 0, 'Regra Geral', '2024-01-01 00:00:00', '2999-12-31 23:59:59', 1, NOW()::timestamp, 1, NOW()::timestamp);

-------------------------------------------------------------
create sequence seq_parceiro_regra_atividade;

create table parceiro_regra_atividade (
	id					bigint not null constraint parceiro_regra_atividade_pk primary key,
	parceiro_id			bigint not null,
	regra_atividade_id  bigint not null,
	data_inicio_vigencia date not null,
	data_final_vigencia  date not null,	
	user_create     	bigint not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone,
	CONSTRAINT parceiro_regra_atividade_foreign FOREIGN KEY (parceiro_id) REFERENCES parceiro (id),
	CONSTRAINT regra_parceiro_foreign FOREIGN KEY (regra_atividade_id) REFERENCES regra_atividade (id)
);
-------------------------------------------------------------

create table identificacao_documento_agricola (
	value				varchar(3) not null constraint identificacao_documento_agricola_pkey primary key,
	name				varchar(250) not null
);

insert into identificacao_documento_agricola values ('0', 'Regra Geral');
insert into identificacao_documento_agricola values ('1', 'Romaneio');
insert into identificacao_documento_agricola values ('2', 'Fixação');
insert into identificacao_documento_agricola values ('3', 'Contrato');
insert into identificacao_documento_agricola values ('4', 'Pesagem');

alter table operacao_interna_agricola drop column seleciona_pesagem;
alter table operacao_interna_agricola add column identificacao_documento_agricola varchar(3) not null default '0';
alter table operacao_interna_agricola add CONSTRAINT identificacao_documento_operacao_interna_foreign FOREIGN KEY (identificacao_documento_agricola) REFERENCES identificacao_documento_agricola (value);
alter table operacao_interna_agricola add exige_nota_entrada boolean not null default false;
alter table operacao_interna_agricola add fixa_automatico boolean not null default false;
alter table operacao_interna_agricola add complemento_fixacao boolean not null default false;
alter table operacao_interna_agricola add contrato_avista boolean not null default false;
alter table operacao_interna_agricola add tipo_preco_agricola_id bigint;
alter table operacao_interna_agricola add tipo_contrato_agricola_id bigint;
-------------------------------------------------------------

create sequence seq_tipo_preco_agricola;

create table tipo_preco_agricola (
	id					bigint not null constraint tipo_preco_agricola_pk primary key,
	codigo   			bigint not null,
	nome				varchar(80) not null,
	data_inicio_vigencia date not null,
	data_final_vigencia  date not null,
	user_create     	bigint not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone
);

insert into tipo_preco_agricola (id, codigo, nome, data_inicio_vigencia, data_final_vigencia, user_create, date_create, user_update, date_update)
values (nextval('seq_tipo_preco_agricola'), '1', 'Balcão', '2024-01-01 00:00:00', '2999-12-31 23:59:59', 1, NOW()::timestamp, 1, NOW()::timestamp);

insert into tipo_preco_agricola (id, codigo, nome, data_inicio_vigencia, data_final_vigencia, user_create, date_create, user_update, date_update)
values (nextval('seq_tipo_preco_agricola'), '2', 'Depósito', '2024-01-01 00:00:00', '2999-12-31 23:59:59', 1, NOW()::timestamp, 1, NOW()::timestamp);

-------------------------------------------------------------

create sequence seq_valida_operacao_interna_agricola;

create table valida_operacao_interna_agricola (
	id					bigint not null constraint valida_operacao_interna_agricola_pk primary key,
	item_id   			bigint not null,
	operacao_interna_id bigint not null,
	grupo_operacao_agricola_id  bigint not null,
	tipo_preco_agricola_id bigint not null,
	fato_gerador_contrato_id bigint,
	data_inicio_vigencia date not null,
	data_final_vigencia  date not null,	
	user_create     	bigint not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone,
	CONSTRAINT item_valida_operacao_interna_agricola_foreign FOREIGN KEY (item_id) REFERENCES item (id),
	CONSTRAINT oi_valida_operacao_interna_agricola_foreign FOREIGN KEY (operacao_interna_id) REFERENCES operacao_interna (id),
	CONSTRAINT goa_valida_operacao_interna_agricola_foreign FOREIGN KEY (grupo_operacao_agricola_id) REFERENCES grupo_operacao_agricola (id),
	CONSTRAINT fgci_valida_operacao_interna_agricola_foreign FOREIGN KEY (fato_gerador_contrato_id) REFERENCES fato_gerador (id)
);
-------------------------------------------------------------

create sequence seq_origem_producao_agricola;

create table origem_producao_agricola (
	id					bigint not null constraint origem_producao_agricola_pk primary key,
	codigo   			bigint not null,
	nome				varchar(80) not null,
	data_inicio_vigencia date not null,
	data_final_vigencia  date not null,
	user_create     	bigint not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone
);

insert into origem_producao_agricola (id, codigo, nome, data_inicio_vigencia, data_final_vigencia, user_create, date_create, user_update, date_update)
values (nextval('seq_origem_producao_agricola'), '1', 'Direto Produtor', '2024-01-01 00:00:00', '2999-12-31 23:59:59', 1, NOW()::timestamp, 1, NOW()::timestamp);

----------------------------------------------------
drop sequence seq_config_classificacao_agricola;
drop sequence seq_tipo_classificacao_agricola;
drop sequence seq_pesagem;
drop sequence seq_romaneio;
drop table pesagem_classificacao ;
drop table pesagem;
drop table config_classificacao_agricola;
drop table tipo_classificacao_agricola;
drop table romaneio;
drop table safra;

alter table item drop column informa_pesagem_agricola;
alter table item drop column usa_classificacao_agricola;

alter table item add column produto_agricola boolean not null default false;
alter table item add column usa_romaneio_agricola boolean not null default false;

-------------------------------------------------------------
create table safra (
	id					bigint not null constraint safra_pk primary key,
	item_id             bigint not null,
	codigo              varchar(30) not null,
	nome				varchar(100) not null,
	data_inicio_vigencia date not null,
	data_final_vigencia  date not null,	
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone,
	CONSTRAINT item_safra_foreign FOREIGN KEY (item_id) REFERENCES item (id)
);

-------------------------------------------------------------
create sequence seq_romaneio_agricola;

create table romaneio_agricola (
	id					bigint not null constraint romaneio_pk primary key,
	departamento_id   bigint not null,
	origem				varchar(3) not null,
	numero              bigint not null,
	data_documento      date not null,
	parceiro_local_id   bigint,
	parceiro_local_propriedade_id bigint,
	item_id             bigint not null,
	safra_id            bigint,
	grupo_operacao_agricola_id bigint not null,
	operacao_interna_id bigint not null,
	origem_producao_agricola_id bigint not null,
	balanca_id			bigint,
	regra_atividade_id  bigint not null,
	peso_entrada		decimal(15,5) not null default 0.0,
	peso_saida          decimal(15,5) not null default 0.0,
	peso_liquido		decimal(15,5) not null default 0.0,
	descontos_acrescimo	decimal(15,5) not null default 0.0,
	peso_final			decimal(15,5) not null default 0.0,
	peso_entrada_manual boolean not null default false,
	justificativa_peso_entrada varchar(255),
	peso_saida_manual   boolean not null default false,
	justificativa_peso_saida varchar(255),
	placa_1				varchar(10),
	placa_2             varchar(10),
	placa_3             varchar(10),
	nome_motorista      varchar(80),
	contato_motorista   varchar(30),
	entrada				boolean not null,
	fixar_automatico	boolean not null default false,
	observacao			varchar(500),
	situacao 			varchar(3) not null,
	nivel1_pred_preco 	bigint,
	nivel2_pred_preco 	bigint,
	nivel3_pred_preco 	bigint,
	nivel4_pred_preco 	bigint,
	preco_deposito		decimal(15,5),
	peso_final_faturar decimal(15,5) not null default 0.0,
	saldo_fixar			decimal(15,5) not null default 0.0,
	controle			varchar(10),
	controle_dv			integer,
	numero_nf_deposito	integer,
	user_create     	bigint not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone,
	CONSTRAINT departamento_romaneio_foreign FOREIGN KEY (departamento_id) REFERENCES departamento (id),
	CONSTRAINT situacao_romaneio_foreign FOREIGN KEY (situacao) REFERENCES situacao_romaneio (value),
	CONSTRAINT parceiro_local_romaneio_foreign FOREIGN KEY (parceiro_local_id) REFERENCES parceiro_local (id),
	CONSTRAINT parceiro_local_propriedade_romaneio_foreign FOREIGN KEY (parceiro_local_propriedade_id) REFERENCES parceiro_local_propriedade (id),
	CONSTRAINT item_romaneio_foreign FOREIGN KEY (item_id) REFERENCES item (id),
	CONSTRAINT grupo_operacao_agricola_romaneio_foreign FOREIGN KEY (grupo_operacao_agricola_id) REFERENCES grupo_operacao_agricola (id),
	CONSTRAINT balanca_romaneio_foreign FOREIGN KEY (balanca_id) REFERENCES balanca (id),
	CONSTRAINT regra_atividade_romaneio_foreign FOREIGN KEY (regra_atividade_id) REFERENCES regra_atividade (id),
	CONSTRAINT nivel1_predpreco_romaneio_foreign FOREIGN KEY (nivel1_pred_preco) REFERENCES sub_item_classificacao_agricola (id),
	CONSTRAINT nivel2_predpreco_romaneio_foreign FOREIGN KEY (nivel2_pred_preco) REFERENCES sub_item_classificacao_agricola (id),
	CONSTRAINT nivel3_predpreco_romaneio_foreign FOREIGN KEY (nivel3_pred_preco) REFERENCES sub_item_classificacao_agricola (id),
	CONSTRAINT nivel4_predpreco_romaneio_foreign FOREIGN KEY (nivel4_pred_preco) REFERENCES sub_item_classificacao_agricola (id)
);

------------------------------------

create sequence seq_ordem_calculo_agricola;

create table ordem_calculo_agricola (
	id					bigint not null constraint seq_ordem_calculo_agricola_pk primary key,
	item_id   			bigint not null,
	identificacao_documento_agricola varchar(3) not null,
	tipo_calculo_agricola_id bigint not null,
	ordem				integer not null,
	data_inicio_vigencia date not null,
	data_final_vigencia  date not null,	
	user_create     	bigint not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone,
	CONSTRAINT item_ordem_calculo_foreign FOREIGN KEY (item_id) REFERENCES item (id),
	CONSTRAINT tipo_calculo_ordem_calculo_foreign FOREIGN KEY (tipo_calculo_agricola_id) REFERENCES tipo_calculo_agricola (id),
	CONSTRAINT doc_agricola_ordem_calculo_foreign FOREIGN KEY (identificacao_documento_agricola) REFERENCES identificacao_documento_agricola (value)
);

------------------------------------

create sequence seq_valida_calculo_agricola;

create table valida_calculo_agricola (
	id							bigint not null constraint valida_calculo_agricola_pk primary key,
	item_id   					bigint not null,
	tipo_calculo_agricola_id 	bigint not null,
	operacao_interna_id 		bigint not null,
	grupo_operacao_agricola_id 	bigint not null,
	regra_atividade_id			bigint not null,
	data_inicio_vigencia 		date not null,
	data_final_vigencia  		date not null,	
	user_create     			bigint not null,
	date_create					timestamp with time zone 	 not null,
	user_update     			bigint,
	date_update					timestamp with time zone,
	CONSTRAINT item_valida_calculo_foreign FOREIGN KEY (item_id) REFERENCES item (id),
	CONSTRAINT tipo_calculo_valida_calculo_foreign FOREIGN KEY (tipo_calculo_agricola_id) REFERENCES tipo_calculo_agricola (id),
	CONSTRAINT operacao_interna_valida_calculo_foreign FOREIGN KEY (operacao_interna_id) REFERENCES operacao_interna (id),	
	CONSTRAINT grupo_operacao_valida_calculo_foreign FOREIGN KEY (grupo_operacao_agricola_id) REFERENCES grupo_operacao_agricola (id),
	CONSTRAINT regra_atividade_valida_calculo_foreign FOREIGN KEY (regra_atividade_id) REFERENCES regra_atividade (id)
);

----------------------------------------

create table tipo_taxa_agricola (
	value				varchar(3)  not null constraint tipo_taxa_agricola_pk primary key,
	name				varchar(80) not null
);

insert into tipo_taxa_agricola (value, name)
values ('1', 'Percentual');

insert into tipo_taxa_agricola (value, name)
values ('2', 'Indíce');

insert into tipo_taxa_agricola (value, name)
values ('3', 'Valor Absoluto');


-----------------------------------------


create sequence seq_taxa_calculo_agricola;

create table taxa_calculo_agricola (
	id							bigint not null constraint taxa_calculo_agricola_pk primary key,
	item_id   					bigint not null,
	tipo_calculo_agricola_id 	bigint not null,
	regra_atividade_id			bigint not null,
	faixa_limite				decimal(15,5) not null,
	tipo_taxa_agricola			varchar(3) not null,
	fator_calculo				decimal(15,5),
	data_inicio_vigencia 		date not null,
	data_final_vigencia  		date not null,	
	user_create     			bigint not null,
	date_create					timestamp with time zone 	 not null,
	user_update     			bigint,
	date_update					timestamp with time zone,
	CONSTRAINT item_taxa_calculo_foreign FOREIGN KEY (item_id) REFERENCES item (id),
	CONSTRAINT tipo_taxa_valida_calculo_foreign FOREIGN KEY (tipo_calculo_agricola_id) REFERENCES tipo_calculo_agricola (id),
	CONSTRAINT regra_atividade_taxa_calculo_foreign FOREIGN KEY (regra_atividade_id) REFERENCES regra_atividade (id)
);

----------------------------------------------

create sequence seq_compl_ordem_calculo_agricola;

create table compl_ordem_calculo_agricola (
	id					bigint not null constraint compl_ordem_calculo_agricola_pk primary key,
	item_id   			bigint not null,
	identificacao_documento_agricola varchar(3) not null,
	tipo_calculo_agricola_id bigint not null,
	ordem				integer not null,
	ordem_compl			integer not null,
	tipo_operacao_calc  char(1) not null,
	data_inicio_vigencia date not null,
	data_final_vigencia  date not null,	
	user_create     	bigint not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone,
	CONSTRAINT item_ordem_calculo_foreign FOREIGN KEY (item_id) REFERENCES item (id),
	CONSTRAINT tipo_calculo_ordem_calculo_foreign FOREIGN KEY (tipo_calculo_agricola_id) REFERENCES tipo_calculo_agricola (id),
	CONSTRAINT doc_agricola_ordem_calculo_foreign FOREIGN KEY (identificacao_documento_agricola) REFERENCES identificacao_documento_agricola (value)
);

create sequence seq_romaneio_agricola_classificacao;

create table romaneio_agricola_classificacao (
	id						bigint not null constraint romaneio_agricola_classificacao_pk primary key,
	romaneio_agricola_id   	bigint not null,
	item_classificacao_agricola_id bigint not null,
	sub_item_classificacao_agricola_id bigint,
	base_calculo_agricola varchar(3),
	valor_base_calculo decimal(15,5),
	valor_base_calculo_compl decimal(15,5),
	valor				decimal(15,5),
	indicador_dc		varchar(1),
	ordem				integer,
	fator_calculo		decimal(15,5),
	tipo_taxa_agricola	varchar(3),
	gerado				boolean not null default false,
	sub_item_classificacao_agricola_origem_id bigint,
	user_create     	bigint not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone,
	CONSTRAINT romaneio_agricola_classificacao_foreign FOREIGN KEY (romaneio_agricola_id) REFERENCES romaneio_agricola (id),
	CONSTRAINT item_romaneio_agricola_classificacao_foreign FOREIGN KEY (item_classificacao_agricola_id) REFERENCES item_classificacao_agricola (id),
	CONSTRAINT subitem_romaneio_agricola_classificacao_foreign FOREIGN KEY (sub_item_classificacao_agricola_id) REFERENCES sub_item_classificacao_agricola (id),
	CONSTRAINT tipo_taxa_romaneio_agricola_classificacao_foreign FOREIGN KEY (tipo_taxa_agricola) REFERENCES tipo_taxa_agricola (value),
	CONSTRAINT subitem_origem_romaneio_agricola_classificacao_foreign FOREIGN KEY (sub_item_classificacao_agricola_origem_id) REFERENCES sub_item_classificacao_agricola (id)
);

--------------------------------------------------

create sequence seq_config_sistema_agricola;

create table config_sistema_agricola (
	id					bigint not null constraint pk_config_sistema_agricola primary key,
	config_sistema_id   bigint not null,
	tipo_documento_rom	bigint not null,
	operacao_interna_fixacao_id bigint,
	tipo_preco_agricola_balcao_id bigint,
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone not null,
	user_update     	bigint,
	date_update			timestamp with time zone,	
	CONSTRAINT config_sistema_financeiro_foreign FOREIGN KEY (config_sistema_id) REFERENCES config_sistema (id),
	CONSTRAINT tipo_documento_rom_config_sistema FOREIGN KEY (tipo_documento_rom) REFERENCES tipo_documento (id),
	CONSTRAINT operacao_interna_fixacao_config_sistema FOREIGN KEY (operacao_interna_fixacao_id) REFERENCES operacao_interna (id),
	CONSTRAINT tipo_preco_balcao_config_sistema FOREIGN KEY (tipo_preco_agricola_balcao_id) REFERENCES tipo_preco_agricola (id)
);

------------------------------------------------

delete from situacao_romaneio;

insert into situacao_romaneio values ('1', 'Pendente');
insert into situacao_romaneio values ('2', 'Aberto');
insert into situacao_romaneio values ('3', 'Finalizado');
insert into situacao_romaneio values ('9', 'Cancelado');

------------------------------------------------

create sequence seq_romaneio_agricola_nota;

create table romaneio_agricola_nota (
	id						bigint not null constraint romaneio_agricola_nota_pk primary key,
	romaneio_agricola_id   	bigint not null,
	operacao_interna_id 	bigint not null,
	cfop_id 				bigint not null,
	numero_nota				integer not null,
	serie					integer not null,
	data_emissao			date not null,
	chave_nfe				varchar(80),
	tipo_documento_id		bigint not null,
	quantidade				decimal(15,5) not null,
	valor_unitario			decimal(15,5) not null,
	valor_total				decimal(15,5) not null,
	user_create     	bigint not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone,
	CONSTRAINT romaneio_agricola_nota_foreign FOREIGN KEY (romaneio_agricola_id) REFERENCES romaneio_agricola (id),
	CONSTRAINT operacao_interna_romaneio_agricola_nota_foreign FOREIGN KEY (operacao_interna_id) REFERENCES operacao_interna (id),
	CONSTRAINT cfop_romaneio_agricola_nota_foreign FOREIGN KEY (cfop_id) REFERENCES cfop (id),
	CONSTRAINT tipo_documento_romaneio_agricola_nota_foreign FOREIGN KEY (tipo_documento_id) REFERENCES tipo_documento (id)
);

-------------------------------------------------------------------------
create sequence seq_indice_financeiro;

create table indice_financeiro (
	id				bigint not null constraint indice_financeiro_pk primary key, 
	codigo			integer not null,
	nome			varchar(100) not null,
	sigla			varchar(30) not null,
	data_inicio_vigencia 		date not null,
	data_final_vigencia  		date not null,		
	user_create     bigint not null,
	date_create		timestamp with time zone 	 not null,
	user_update     bigint,
	date_update		timestamp with time zone
);
-------------------------------------------------------------------------

create table caracteristica_contrato_agricola (
	value				varchar(3)  not null constraint caracteristica_contrato_agricola_pk primary key,
	name				varchar(80) not null
);

insert into caracteristica_contrato_agricola (value, name)
values ('1', 'Depósito a Fixar');

insert into caracteristica_contrato_agricola (value, name)
values ('2', 'Preço Fixo');

insert into caracteristica_contrato_agricola (value, name)
values ('3', 'Dólar');

-------------------------------------------------------------------------
create sequence seq_tipo_contrato_agricola;

create table tipo_contrato_agricola (
	id				bigint not null constraint tipo_contrato_agricola_pk primary key, 
	codigo			integer not null,
	nome			varchar(100) not null,
	sigla			varchar(30) not null,
	caracteristica_contrato_agricola varchar(3) not null,
	data_inicio_vigencia 		date not null,
	data_final_vigencia  		date not null,		
	user_create     bigint not null,
	date_create		timestamp with time zone 	 not null,
	user_update     bigint,
	date_update		timestamp with time zone,
	CONSTRAINT caracteristica_tipo_contrato_agricola_foreign FOREIGN KEY (caracteristica_contrato_agricola) REFERENCES caracteristica_contrato_agricola (value)
);
-------------------------------------------------------------------------
create sequence seq_predefinicao_preco_agricola;

create table predefinicao_preco_agricola (
	id				bigint not null constraint predefinicao_preco_agricola_pk primary key, 
	codigo			integer not null,
	nome			varchar(100) not null,
	item_class_1_id bigint not null,
	item_class_2_id bigint not null,
	item_class_3_id bigint not null,
	item_class_4_id bigint not null,
	data_inicio_vigencia 		date not null,
	data_final_vigencia  		date not null,		
	user_create     bigint not null,
	date_create		timestamp with time zone 	 not null,
	user_update     bigint,
	date_update		timestamp with time zone,
	CONSTRAINT item_classif1_predefinicao_preco_foreign FOREIGN KEY (item_class_1_id) REFERENCES item_classificacao_agricola (id),
	CONSTRAINT item_classif2_predefinicao_preco_foreign FOREIGN KEY (item_class_2_id) REFERENCES item_classificacao_agricola (id),
	CONSTRAINT item_classif3_predefinicao_preco_foreign FOREIGN KEY (item_class_3_id) REFERENCES item_classificacao_agricola (id),
	CONSTRAINT item_classif4_predefinicao_preco_foreign FOREIGN KEY (item_class_4_id) REFERENCES item_classificacao_agricola (id)
);

-------------------------------------------------------------------------
create sequence seq_finalidade_contrato_agricola;

create table finalidade_contrato_agricola (
	id				bigint not null constraint finalidade_contrato_agricola_pk primary key, 
	codigo			integer not null,
	nome			varchar(100) not null,
	sigla			varchar(30) not null,
	data_inicio_vigencia 		date not null,
	data_final_vigencia  		date not null,		
	user_create     bigint not null,
	date_create		timestamp with time zone 	 not null,
	user_update     bigint,
	date_update		timestamp with time zone
);
-------------------------------------------------------------------------
create sequence seq_contrato_agricola;

create table contrato_agricola (
	id				bigint not null constraint contrato_agricola_pk primary key,
	numero			integer not null,
	departamento_id	bigint not null,
	item_id			bigint not null,
	parceiro_local_id bigint not null,
	avalista_id 	bigint not null,
	indice_financeiro_id bigint not null,
	tipo_contrato_agricola_id bigint not null,
	tipo_preco_agricola_id bigint not null,
	operacao_interna_id bigint not null,
	grupo_operacao_agricola_id bigint not null,
	finalidade_contrato_agricola_id bigint not null,
	predefinicao_preco_agricola_id bigint not null,
	safra_id bigint not null,
	qtd_contratada		decimal(15,5) not null,
	valor_unitario_liquido decimal(15,5) not null,
	valor_unitario_bruto   decimal(15,5) not null,
	valor_bruto			decimal(15,5) not null,
	valor_desconto		decimal(15,5) not null,
	valor_liquido		decimal(15,5) not null,
	data_documento		date not null,
	data_movimento		date not null,
	qtd_baixada			decimal(15,5) not null,
	valor_baixado		decimal(15,5) not null,
	data_vencimento		date not null,
	data_limite_entrega date not null,
	regra_atividade_id  bigint not null,
	valor_unitario_base	decimal(15,5) not null,
	valor_premio		decimal(15,5) not null,
	qtd_acordo_premio	decimal(15,5) not null,
	observacao			varchar(2000),
	cod_nivel_class1	decimal(15,5) not null,
	cod_nivel_class2	decimal(15,5) not null,				
	cod_nivel_class3	decimal(15,5) not null,
	cod_nivel_class4	decimal(15,5) not null,
	user_create     	bigint not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone,
	CONSTRAINT departamento_contrato_agricola_foreign FOREIGN KEY (departamento_id) REFERENCES departamento (id),
	CONSTRAINT item_contrato_agricola_foreign FOREIGN KEY (item_id) REFERENCES item (id),
	CONSTRAINT parceiro_local_contrato_agricola_foreign FOREIGN KEY (parceiro_local_id) REFERENCES parceiro_local (id),
	CONSTRAINT avalista_contrato_agricola_foreign FOREIGN KEY (avalista_id) REFERENCES parceiro (id),
	CONSTRAINT indice_financeiro_contrato_agricola_foreign FOREIGN KEY (indice_financeiro_id) REFERENCES indice_financeiro (id),
	CONSTRAINT tipo_contrato_contrato_agricola_foreign FOREIGN KEY (tipo_contrato_agricola_id) REFERENCES tipo_contrato_agricola (id),
	CONSTRAINT tipo_preco_contrato_agricola_foreign FOREIGN KEY (tipo_preco_agricola_id) REFERENCES tipo_preco_agricola (id),
	CONSTRAINT operacao_interna_contrato_agricola_foreign FOREIGN KEY (operacao_interna_id) REFERENCES operacao_interna (id),
	CONSTRAINT grupo_operacao_agricola_contrato_agricola_foreign FOREIGN KEY (grupo_operacao_agricola_id) REFERENCES grupo_operacao_agricola (id),
	CONSTRAINT finalidade_contrato_agricola_foreign FOREIGN KEY (finalidade_contrato_agricola_id) REFERENCES finalidade_contrato_agricola (id),
	CONSTRAINT predefinicao_preco_contrato_agricola_foreign FOREIGN KEY (predefinicao_preco_agricola_id) REFERENCES predefinicao_preco_agricola (id),
	CONSTRAINT safra_contrato_agricola_foreign FOREIGN KEY (safra_id) REFERENCES safra (id),
	CONSTRAINT regra_atividade_contrato_agricola_foreign FOREIGN KEY (regra_atividade_id) REFERENCES regra_atividade (id)
);

-----------------------------------------------------------------

create sequence seq_romaneio_agricola_parcela_fixacao;

create table romaneio_agricola_parcela_fixacao (
	id					bigint not null constraint romaneio_agricola_parcela_fixacao_pk primary key,
	data_vencimento		date   not null,
	valor_parcela		decimal(15,5) not null,
	user_create     	bigint not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone
);	

----------------------------------------------------------------------
create sequence seq_valida_preco_agricola_item;

create table valida_preco_agricola_item (
	id					bigint not null constraint valida_preco_agricola_item_pk primary key,
	item_id   			bigint not null,
	tipo_preco_agricola_id bigint not null,
	predefinicao_preco_agricola_id bigint not null,
	data_inicio_vigencia date not null,
	data_final_vigencia  date not null,	
	user_create     	bigint not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone,
	CONSTRAINT item_valida_preco_agricola_item_foreign FOREIGN KEY (item_id) REFERENCES item (id),
	CONSTRAINT tipo_preco_valida_preco_agricola_item_foreign FOREIGN KEY (tipo_preco_agricola_id) REFERENCES tipo_preco_agricola (id),
	constraint predefinicao_preco_agricola_valida_preco_agricola_item_foreign FOREIGN KEY (predefinicao_preco_agricola_id) REFERENCES predefinicao_preco_agricola (id)
);

--------------------------------------------------------------------------

create sequence seq_melhoria_agricola;

create table melhoria_agricola (
	id					bigint not null constraint melhoria_agricola_pk primary key,
	item_id   			bigint not null,
	item_classificacao_principal bigint not null,
	sub_item_classificacao_principal bigint not null,
	item_classificacao_secundario bigint not null,
	sub_item_classificacao_secundario bigint not null,
	valor_adiciona_principal decimal(15,5),
	valor_adiciona_secundario decimal(15,5),
	item_classificacao_gerado bigint not null,
	sub_item_classificacao_gerado bigint not null,	
	data_inicio_vigencia date not null,
	data_final_vigencia  date not null,	
	user_create     	bigint not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone,
	CONSTRAINT item_melhoria_agricola_foreign FOREIGN KEY (item_id) REFERENCES item (id),
	CONSTRAINT item_classif_principal_melhoria_foreign FOREIGN KEY (item_classificacao_principal) REFERENCES item_classificacao_agricola (id),
	CONSTRAINT subitem_classif_principal_melhoria_foreign FOREIGN KEY (sub_item_classificacao_principal) REFERENCES sub_item_classificacao_agricola (id),
	CONSTRAINT item_classif_secundario_melhoria_foreign FOREIGN KEY (item_classificacao_secundario) REFERENCES item_classificacao_agricola (id),	
	CONSTRAINT subitem_classif_secundario_melhoria_foreign FOREIGN KEY (sub_item_classificacao_secundario) REFERENCES sub_item_classificacao_agricola (id),
	CONSTRAINT item_classif_gerado_melhoria_foreign FOREIGN KEY (item_classificacao_gerado) REFERENCES item_classificacao_agricola (id),
	CONSTRAINT subitem_classif_gerado_melhoria_foreign FOREIGN KEY (sub_item_classificacao_gerado) REFERENCES sub_item_classificacao_agricola (id)
);

--------------------------------------------------------------------------

create sequence seq_preco_agricola;

create table preco_agricola (
	id						bigint not null constraint preco_agricola_pk primary key,
	item_id					bigint not null,
	tipo_preco_agricola_id 	bigint not null,
	empresa_id				bigint not null,
	empresa_filial_id		bigint,
	departamento_id			bigint,
	predefinicao_preco_agricola_id bigint not null,
	cod_nivel_class1		bigint,
	cod_nivel_class2		bigint,
	cod_nivel_class3		bigint,
	cod_nivel_class4		bigint,	
	valor_unitario			decimal(15,5) not null,
	data_inicio_vigencia date not null,
	data_final_vigencia  date not null,	
	user_create     	bigint not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone,
	CONSTRAINT item_preco_agricola_foreign FOREIGN KEY (item_id) REFERENCES item (id),
	CONSTRAINT empresa_preco_agricola_foreign FOREIGN KEY (empresa_id) REFERENCES item (id),
	CONSTRAINT empresa_filial_preco_agricola_foreign FOREIGN KEY (empresa_filial_id) REFERENCES empresa_filial (id),
	CONSTRAINT departamento_preco_agricola_foreign FOREIGN KEY (departamento_id) REFERENCES departamento (id),
	CONSTRAINT tipo_preco_preco_agricola_foreign FOREIGN KEY (tipo_preco_agricola_id) REFERENCES tipo_preco_agricola (id),
	CONSTRAINT predefinicao_preco_preco_agricola_foreign FOREIGN KEY (predefinicao_preco_agricola_id) REFERENCES predefinicao_preco_agricola (id),
	CONSTRAINT cod_nivel_class1_preco_agricola_foreign FOREIGN KEY (cod_nivel_class1) REFERENCES sub_item_classificacao_agricola (id),
	CONSTRAINT cod_nivel_class2_preco_agricola_foreign FOREIGN KEY (cod_nivel_class2) REFERENCES sub_item_classificacao_agricola (id),
	CONSTRAINT cod_nivel_class3_preco_agricola_foreign FOREIGN KEY (cod_nivel_class3) REFERENCES sub_item_classificacao_agricola (id),
	CONSTRAINT cod_nivel_class4_preco_agricola_foreign FOREIGN KEY (cod_nivel_class4) REFERENCES sub_item_classificacao_agricola (id)
);

--------------------------------------------------------------------------------------

create sequence seq_fixacao_agricola;

create table fixacao_agricola (
	id						bigint not null constraint fixacao_agricola_pk primary key,
	user_create     	bigint not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone
);	

----------------------------------------------------------------------------------------

alter table operacao_interna add caracteristica_financeira boolean not null default false;

create sequence seq_operacao_interna_financeiro;

create table operacao_interna_financeiro (
	id					bigint not null constraint operacao_interna_financeiro_pkey primary key,
	operacao_interna_id bigint not null,
	indice_financeiro_padrao_id bigint, 
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone,
	CONSTRAINT operacao_interna_financeiro_operacao_interna_id_foreign FOREIGN KEY (operacao_interna_id) REFERENCES operacao_interna(id),
	CONSTRAINT operacao_interna_financeiro_indice_financeiro_id_foreign FOREIGN KEY (indice_financeiro_padrao_id) REFERENCES indice_financeiro(id)
);