create table origem_romaneio (
	value				varchar(3) not null constraint origem_romaneio_pkey primary key,
	name				varchar(250) not null
);

insert into origem_romaneio values ('1', 'Lançamento');
insert into origem_romaneio values ('2', 'Pesagem');

create table situacao_romaneio (
	value				varchar(3) not null constraint situacao_romaneio_pkey primary key,
	name				varchar(250) not null
);

insert into situacao_romaneio values ('1', 'Aberto');
insert into situacao_romaneio values ('2', 'Finalizado');
insert into situacao_romaneio values ('9', 'Cancelado');

create sequence seq_romaneio;

create table romaneio (
	id					bigint not null constraint romaneio_pk primary key,
	empresa_filial_id   bigint not null,
	origem				varchar(3) not null,
	numero              bigint not null,
	data_romaneio       date not null,
	operacao_interna_id bigint not null,
	parceiro_local_id   bigint,
	parceiro_local_propriedade_id bigint,
	item_id             bigint not null,
	safra_id            bigint not null,
	situacao 			varchar(3) not null,
	user_create     	bigint not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone,
	CONSTRAINT empresa_filial_romaneio_foreign FOREIGN KEY (empresa_filial_id) REFERENCES empresa_filial (id),
	CONSTRAINT situacao_romaneio_foreign FOREIGN KEY (situacao) REFERENCES situacao_romaneio (value),
	CONSTRAINT operacao_interna_romaneio_foreign FOREIGN KEY (operacao_interna_id) REFERENCES operacao_interna (id),
	CONSTRAINT parceiro_local_romaneio_foreign FOREIGN KEY (parceiro_local_id) REFERENCES parceiro_local (id),
	CONSTRAINT parceiro_local_propriedade_romaneio_foreign FOREIGN KEY (parceiro_local_propriedade_id) REFERENCES parceiro_local_propriedade (id),
	CONSTRAINT item_romaneio_foreign FOREIGN KEY (item_id) REFERENCES item (id),
	CONSTRAINT safra_romaneio_foreign FOREIGN KEY (safra_id) REFERENCES safra (id)
);

