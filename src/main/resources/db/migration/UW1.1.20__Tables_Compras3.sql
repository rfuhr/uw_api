create table situacao_cotacao_mercadoria (
	value				varchar(3) not null constraint situacao_cotacao_mercadoria_pkey primary key,
	name				varchar(250) not null
);

insert into situacao_cotacao_mercadoria values ('1', 'Em Digitação');
insert into situacao_cotacao_mercadoria values ('2', 'Digitada');
insert into situacao_cotacao_mercadoria values ('3', 'Aguardando Retorno');
insert into situacao_cotacao_mercadoria values ('4', 'Retorno Parcial');
insert into situacao_cotacao_mercadoria values ('5', 'Aguardando Mapa');
insert into situacao_cotacao_mercadoria values ('6', 'Finalizada');
insert into situacao_cotacao_mercadoria values ('9', 'Cancelada');

---------------------------------------------------------------------------------------


create table situacao_cotacao_mercadoria_parceiro (
	value				varchar(3) not null constraint situacao_cotacao_mercadoria_parceiro_pkey primary key,
	name				varchar(250) not null
);

insert into situacao_cotacao_mercadoria_parceiro values ('1', 'Em Digitação');
insert into situacao_cotacao_mercadoria_parceiro values ('2', 'Digitada');
insert into situacao_cotacao_mercadoria_parceiro values ('3', 'Aguardando Retorno');
insert into situacao_cotacao_mercadoria_parceiro values ('4', 'Retorno Parcial');
insert into situacao_cotacao_mercadoria_parceiro values ('5', 'Aguardando Mapa');
insert into situacao_cotacao_mercadoria_parceiro values ('6', 'Aprovada Total');
insert into situacao_cotacao_mercadoria_parceiro values ('7', 'Aprovada Parcialmente');
insert into situacao_cotacao_mercadoria_parceiro values ('8', 'Desclassificada');
insert into situacao_cotacao_mercadoria_parceiro values ('9', 'Cancelada');

---------------------------------------------------------------------------------------


create table status_cotacao_mercadoria_item (
	value				varchar(3) not null constraint status_cotacao_mercadoria_item_pkey primary key,
	name				varchar(250) not null
);

insert into status_cotacao_mercadoria_item values ('1', 'Em Digitação');
insert into status_cotacao_mercadoria_item values ('2', 'Digitada');
insert into status_cotacao_mercadoria_item values ('3', 'Aguardando Retorno');
insert into status_cotacao_mercadoria_item values ('4', 'Aguardando Mapa');
insert into status_cotacao_mercadoria_item values ('5', 'Aprovado');
insert into status_cotacao_mercadoria_item values ('6', 'Desclassificado');
insert into status_cotacao_mercadoria_item values ('9', 'Cancelado');


------------------------------------------------------------------------------------------

alter table cotacao_mercadoria add situacao_cotacao_mercadoria varchar(3) not null default '1';
alter table cotacao_mercadoria_parceiro add situacao varchar(3) not null default '1';
alter table cotacao_mercadoria_item add status varchar(3) not null default '1';

alter table cotacao_mercadoria add CONSTRAINT situacao_cotacao_mercadoria_foreign FOREIGN KEY (situacao_cotacao_mercadoria) REFERENCES situacao_cotacao_mercadoria (value);
alter table cotacao_mercadoria_parceiro add CONSTRAINT situacao_cotacao_mercadoria_parceiro_foreign FOREIGN KEY (situacao) REFERENCES situacao_cotacao_mercadoria_parceiro (value);
alter table cotacao_mercadoria_item add CONSTRAINT status_cotacao_mercadoria_item_foreign FOREIGN KEY (status) REFERENCES status_cotacao_mercadoria_item (value);

