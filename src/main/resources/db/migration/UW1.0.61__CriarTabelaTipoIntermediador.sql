create table tipo_intermediador (
	value				char(3) not null constraint tipo_intermediador_pkey primary key,
	name				varchar(250) not null,
	codigo_receita      varchar(3) not null
);

insert into tipo_intermediador values ('0', 'Operação sem intermediador (em site ou plataforma própria', '0');
insert into tipo_intermediador values ('1', 'Operação com intermediador (em site ou plataforma de terceiros', '1');
