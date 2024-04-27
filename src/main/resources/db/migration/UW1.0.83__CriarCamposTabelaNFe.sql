create table situacao_documento (
	value				char(3) not null constraint situacao_documento_pkey primary key,
	name				varchar(250) not null
);

insert into situacao_documento values ('1', 'Pendente');
insert into situacao_documento values ('2', 'Em Digitação');
insert into situacao_documento values ('3', 'Concluído');
insert into situacao_documento values ('4', 'Aguardando Aprovação');
insert into situacao_documento values ('5', 'Autorizado');
insert into situacao_documento values ('6', 'Inutilizado');
insert into situacao_documento values ('7', 'Rejeitado');
insert into situacao_documento values ('8', 'Denegado');
insert into situacao_documento values ('9', 'Cancelado');

alter table documento add situacao varchar(2) not null;

alter table nfe add situacao varchar(2) not null;