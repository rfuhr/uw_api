create table indicador_operacao (
	value				char(1) not null constraint indicador_operacao_pkey primary key,
	name				varchar(250) not null,
	codigoReceita		char(1) not null
);

insert into indicador_operacao values ('E', 'Entrada', '0');
insert into indicador_operacao values ('S', 'Saída', '1');