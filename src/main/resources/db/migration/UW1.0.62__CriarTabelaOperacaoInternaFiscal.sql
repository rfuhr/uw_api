create table destino_operacao (
	value				char(1) not null constraint destino_operacao_pkey primary key,
	name				varchar(250) not null,
	codigo_receita		varchar(1) not null
);

insert into destino_operacao values ('1', 'Operação Interna', '1');
insert into destino_operacao values ('3', 'Operação Interestadual', '3');
insert into destino_operacao values ('2', 'Operação com Exterior', '2');


create table finalidade_nfe (
	value				char(1) not null constraint finalidade_nfe_pkey primary key,
	name				varchar(250) not null,
	codigo_receita		varchar(1) not null
);

insert into finalidade_nfe values ('1', 'NF-e Normal', '1');
insert into finalidade_nfe values ('2', 'NF-e Complementar', '2');
insert into finalidade_nfe values ('3', 'NF-e de  Ajuste', '3');
insert into finalidade_nfe values ('4', 'Devolução de Mercadoria', '4');

create table tipo_consumidor (
	value				char(1) not null constraint tipo_consumidor_pkey primary key,
	name				varchar(250) not null,
	codigo_receita		varchar(1) not null
);

insert into tipo_consumidor values ('0', 'Normal', '0');
insert into tipo_consumidor values ('1', 'Consumidor Final', '1');


create table tipo_presenca_comprador (
	value				char(1) not null constraint tipo_presenca_comprador_pkey primary key,
	name				varchar(250) not null,
	codigo_receita		varchar(1) not null
);

insert into tipo_presenca_comprador values ('0', 'Não se aplica', '0');
insert into tipo_presenca_comprador values ('1', 'Operação Presencial', '1');
insert into tipo_presenca_comprador values ('2', 'Operação não presencial, internet', '2');
insert into tipo_presenca_comprador values ('3', 'Operação não presencial, teleatendimento', '3');
insert into tipo_presenca_comprador values ('4', 'NFC-e em operação com entrega domícilio', '4');
insert into tipo_presenca_comprador values ('5', 'Operação presencial, fora estabelecimento', '5');
insert into tipo_presenca_comprador values ('9', 'Operação não presencial, outros', '9');

create sequence seq_operacao_interna_fiscal;

create table operacao_interna_fiscal (
	id					bigint not null constraint operacao_interna_fiscal_pkey primary key,
	operacao_interna_id bigint not null,
	destino_operacao	varchar(2),
	finalidade_nfe		varchar(2),
	tipo_consumidor varchar(2),
	tipo_presenca_comprador varchar(2),
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone,
	CONSTRAINT operacao_interna_fiscal_operacao_interna_id_foreign FOREIGN KEY (operacao_interna_id) REFERENCES operacao_interna(id)
);