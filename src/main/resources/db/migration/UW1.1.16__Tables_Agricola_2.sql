alter table operacao_interna_agricola add contrato_afixar boolean not null default false;
alter table contrato_agricola add carteira_financeira_id bigint;
alter table contrato_agricola add CONSTRAINT carteira_contrato_agricola_foreign FOREIGN KEY (carteira_financeira_id) REFERENCES carteira_financeira (id);
---------------------------------------------------------
create sequence seq_contrato_agricola_parcela;

create table contrato_agricola_parcela (
	id					bigint not null constraint contrato_agricola_parcela_pk primary key,
	contrato_agricola_id bigint not null,
	num_parcela         integer not null,
	data_vencimento		date   not null,
	valor_parcela		decimal(15,5) not null,
	user_create     	bigint not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone,
	CONSTRAINT parcela_contrato_agricola_foreign FOREIGN KEY (contrato_agricola_id) REFERENCES contrato_agricola (id)
);	

------------------------------------------------------------

create sequence seq_contrato_agricola_movimento;

create table contrato_agricola_movimento (
	id					bigint not null constraint contrato_agricola_movimento_pk primary key,
	contrato_agricola_id bigint not null,
	operacao_interna_id  bigint not null,
	num_nota			integer not null,
	data_movimento 		date not null,
	qtd_movimento		decimal(15,5) not null,
	valor_movimento		decimal(15,5) not null,
	user_create     	bigint not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone,
	CONSTRAINT movimento_contrato_agricola_foreign FOREIGN KEY (contrato_agricola_id) REFERENCES contrato_agricola (id),
	CONSTRAINT opint_movimento_contrato_agricola_foreign FOREIGN KEY (operacao_interna_id) REFERENCES operacao_interna (id)
);	