create table operacao_estoque (
	value				varchar(2) not null constraint operacao_estoque_pkey primary key,
	name				varchar(80) not null,
	credito_debito      varchar(1) not null
);

insert into operacao_estoque values ('1', 'SOMA', 'C');
insert into operacao_estoque values ('2', 'SUBTRAI', 'D');
insert into operacao_estoque values ('3', 'NÃO OPERA', 'N');