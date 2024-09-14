create table situacao_pesagem (
	value				char(3) not null constraint situacao_pesagem_pkey primary key,
	name				varchar(250) not null
);

insert into situacao_pesagem values ('1', 'Aberta');
insert into situacao_pesagem values ('2', 'Finalizada');
insert into situacao_pesagem values ('9', 'Cancelada');

create sequence seq_pesagem;

create table pesagem (
	id					bigint not null constraint pesagem_pk primary key,
	empresa_filial_id   bigint not null,
	data_pesagem        date not null,
	operacao_interna_id bigint not null,
	tem_cadastro        boolean not null default false,
	parceiro_local_id   bigint,
	parceiro_local_propriedade_id bigint,
	nome_parceiro		varchar(250),
	nome_propriedade	varchar(250),
	item_id             bigint not null,
	safra_id            bigint not null,
	peso_entrada		decimal(15,5) not null default 0.0,
	peso_saida          decimal(15,5) not null default 0.0,
	peso_bruto 			decimal(15,5) not null default 0.0,
	descontos			decimal(15,5) not null default 0.0,
	peso_liquido		decimal(15,5) not null default 0.0,
	placa_1             varchar(10) not null,
	placa_2				varchar(10),
	placa_3             varchar(10),
	nome_motorista      varchar(255) not null,
	contato_motorista   varchar(60)  not null,
	observacao          varchar(255),
	peso_entrada_manual boolean not null default false,
	justificativa_peso_entrada varchar(255),
	peso_saida_manual   boolean not null default false,
	justificativa_peso_saida varchar(255),
	situacao 			varchar(3) not null,
	user_create     	bigint not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone,
	CONSTRAINT empresa_filial_pesagem_foreign FOREIGN KEY (empresa_filial_id) REFERENCES empresa_filial (id),
	CONSTRAINT situacao_pesagem_foreign FOREIGN KEY (situacao) REFERENCES situacao_pesagem (value),
	CONSTRAINT operacao_interna_pesagem_foreign FOREIGN KEY (operacao_interna_id) REFERENCES operacao_interna (id),
	CONSTRAINT parceiro_local_pesagem_foreign FOREIGN KEY (parceiro_local_id) REFERENCES parceiro_local (id),
	CONSTRAINT parceiro_local_propriedade_pesagem_foreign FOREIGN KEY (parceiro_local_propriedade_id) REFERENCES parceiro_local_propriedade (id),
	CONSTRAINT item_pesagem_foreign FOREIGN KEY (item_id) REFERENCES item (id),
	CONSTRAINT safra_pesagem_foreign FOREIGN KEY (safra_id) REFERENCES safra (id)
);


create sequence seq_pesagem_classificacao;

create table pesagem_classificacao (
	id					bigint not null constraint pesagem_classificacao_pk primary key,
	pesagem_id   		bigint not null,
	tipo_classificacao_agricola_id bigint not null,
	valor				decimal(15,5) not null,
	percentual_desconto	decimal(15,5) not null,
	desconto			decimal(15,5) not null,
	user_create     	bigint not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone,
	CONSTRAINT classificacao_pesagem_foreign FOREIGN KEY (pesagem_id) REFERENCES pesagem (id),
	CONSTRAINT tipo_classificacao_pesagem_foreign FOREIGN KEY (tipo_classificacao_agricola_id) REFERENCES tipo_classificacao_agricola (id)
);