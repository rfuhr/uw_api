create table tipo_movimento_estoque (
	value				varchar(2) not null constraint tipo_movimento_estoque_pkey primary key,
	name				varchar(80) not null
);

insert into tipo_movimento_estoque values ('1', 'SALDO INICIAL');
insert into tipo_movimento_estoque values ('2', 'INCLUSÃO');
insert into tipo_movimento_estoque values ('3', 'BAIXA');
insert into tipo_movimento_estoque values ('4', 'CANCELAMENTO');