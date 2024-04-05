create table tipo_obrigatoriedade (
	value				char(3) not null constraint tipo_obrigatoriedade_pkey primary key,
	name				varchar(250) not null
);

insert into tipo_obrigatoriedade values ('S', 'Obrigatório');
insert into tipo_obrigatoriedade values ('O', 'Opcional');
insert into tipo_obrigatoriedade values ('N', 'Não informa');

create sequence seq_historico_padrao;

create table historico_padrao (
	id					bigint not null constraint historico_padrao_pkey primary key,
	codigo				integer not null,
	nome				varchar(250) not null,
	sigla				varchar(30) not null,
	tipo_obrigatoriedade char(1) not null,
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone
);