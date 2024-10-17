alter table operacao_interna_agricola add contrato_afixar boolean not null default false;

---------------------------------------------------------
drop sequence if exists seq_romaneio_agricola_parcela_fixacao;
drop table if exists romaneio_agricola_parcela_fixacao;

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