alter table carteira_financeira add informa_banco boolean not null default false;
alter table carteira_financeira add lista_pos_titulo boolean not null default true;
alter table movimento_financeiro add conta_id bigint;
alter table movimento_financeiro add CONSTRAINT conta_id_movimento_financeiro_foreign FOREIGN KEY (conta_id) REFERENCES conta (id);
alter table tipo_operacao_financeira add idn_sel_substcart  boolean not null default false;
alter table tipo_operacao_financeira add idn_sel_negociacao boolean not null default false;
alter table tipo_operacao_financeira add integra_cxbco varchar(1) not null default 'N';
alter table tipo_operacao_financeira add operacao_cxbco_id bigint;
alter table tipo_operacao_financeira add CONSTRAINT operacao_cxbco_operacao_financeira_foreign FOREIGN KEY (operacao_cxbco_id) REFERENCES operacao_cxbco (id);
alter table controle_numeracao add empresa_id bigint not null default 1;
alter table controle_numeracao add CONSTRAINT empresa_controle_numeracao_foreign FOREIGN KEY (empresa_id) REFERENCES empresa (id);
alter table controle_numeracao alter column empresa_filial_id drop not null;
alter table controle_numeracao drop constraint empresa_filial_controle_numeracao_id_foreign;

insert into tipo_documento (id, nome, sigla, codigo_receita, user_create, date_create, user_update, date_update) 
	values (nextval('seq_tipo_documento'), 'Documento de Baixa','DocBx', null, 1, NOW()::timestamp,1,NOW()::timestamp);	
	
create sequence seq_documento_baixa_financeiro;

create table documento_baixa_financeiro (
	id					bigint not null constraint documento_baixa_financeiro_pkey primary key,
	numero				integer not null,
	data_baixa			date not null,
	departamento_id     bigint not null,
	tipo_operacao_financeira_id bigint not null,
	conta_id			bigint,
	observacao			varchar(200),
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone not null,
	user_update     	bigint,
	date_update			timestamp with time zone,
	CONSTRAINT tipo_operacao_documento_baixa_foreign FOREIGN KEY (tipo_operacao_financeira_id) REFERENCES tipo_operacao_financeira(id),
	CONSTRAINT conta_documento_baixa_foreign FOREIGN KEY (conta_id) REFERENCES conta(id),
	CONSTRAINT departamento_documento_baixa_foreign FOREIGN KEY (departamento_id) REFERENCES departamento(id)
);

alter table movimento_financeiro add documento_baixa_financeiro_id bigint;
alter table movimento_financeiro add CONSTRAINT doc_baixa_movimento_financeiro_foreign FOREIGN KEY (documento_baixa_financeiro_id) REFERENCES documento_baixa_financeiro (id);

alter table movimento_financeiro drop column operacao_financeira_id;
drop  table operacao_financeira;

alter table movimento_financeiro add tipo_operacao_financeira_id bigint;
alter table movimento_financeiro add CONSTRAINT tipo_operacao_financeira_movimento_financeiro_foreign FOREIGN KEY (tipo_operacao_financeira_id) REFERENCES tipo_operacao_financeira (id);

alter table movimento_financeiro add operacao_movimento_financeiro_id bigint;
alter table movimento_financeiro add CONSTRAINT operacao_movimento_financeiro_movimento_financeiro_foreign FOREIGN KEY (operacao_movimento_financeiro_id) REFERENCES operacao_movimento_financeiro (id);

alter table movimento_financeiro add operacao_acessoria_financeira_id bigint;
alter table movimento_financeiro add CONSTRAINT operacao_acessoria_financeira_movimento_financeiro_foreign FOREIGN KEY (operacao_acessoria_financeira_id) REFERENCES operacao_acessoria_financeira (id);

alter table movimento_financeiro add negociacao_financeira_id bigint;
alter table movimento_financeiro add CONSTRAINT negociacao_movimento_financeiro_foreign FOREIGN KEY (negociacao_financeira_id) REFERENCES negociacao_financeira (id);

alter table titulo add negociacao_financeira_id bigint;
alter table titulo add CONSTRAINT negociacao_movimento_financeiro_foreign FOREIGN KEY (negociacao_financeira_id) REFERENCES negociacao_financeira (id);

alter table tipo_titulo add column indicador_dc char(1) not null default 'C';
UPDATE tipo_titulo tt set INDICADOR_DC = 'D' where sigla = 'PAG';

alter table caracteristica_movimento_financeiro add column inverte_indicador boolean not null default false;
update caracteristica_movimento_financeiro set inverte_indicador = true where codigo in (2,3);
