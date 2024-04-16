create table selo_ipi (
	value				varchar(10) not null constraint selo_ipi_pkey primary key,
	name				varchar(250) not null,
	cor      			varchar(100) not null
);

insert into selo_ipi values ('9710-01', 'Produto Nacional', 'Verde combinado com marrom');
insert into selo_ipi values ('9710-10', 'Produto Nacional para Exportação - Tipo "1"', 'Verde Escuro combinado com marrom');
insert into selo_ipi values ('9710-11', 'Produto Nacional para Exportação - Tipo "2" ', 'Verde Escuro combinado com marrom');
insert into selo_ipi values ('9710-12', 'Produto Nacional para Exportação - Tipo "3"', 'Verde Escuro combinado com marrom');
insert into selo_ipi values ('8610-09', 'Produto Estrangeiro', 'Vermelho combinado com azul');
