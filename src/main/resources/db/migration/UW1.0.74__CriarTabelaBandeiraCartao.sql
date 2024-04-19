create table bandeira_cartao (
	value				char(3) not null constraint bandeira_cartao_pkey primary key,
	name				varchar(250) not null
);

insert into bandeira_cartao values ('1', 'Visa');
insert into bandeira_cartao values ('2', 'Mastercard');
insert into bandeira_cartao values ('3', 'American Express');
insert into bandeira_cartao values ('4', 'Sorocred');
insert into bandeira_cartao values ('5', 'Diners Club');
insert into bandeira_cartao values ('6', 'Elo');
insert into bandeira_cartao values ('7', 'Hipercard');
insert into bandeira_cartao values ('8', 'Aura');
insert into bandeira_cartao values ('9', 'Cabal');
insert into bandeira_cartao values ('99', 'Outros');
