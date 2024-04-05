create table tipo_impressao_danfe (
	value				char(3) not null constraint tipo_impressao_danfe_pkey primary key,
	name				varchar(250) not null,
	codigo_receita      varchar(3) not null
);

insert into tipo_impressao_danfe values ('0', 'Sem Danfe', '0');
insert into tipo_impressao_danfe values ('1', 'Danfe Normal - Retrato', '1');
insert into tipo_impressao_danfe values ('2', 'Danfe Normal - Paisagem', '2');
insert into tipo_impressao_danfe values ('3', 'Danfe Simplificado', '3');
insert into tipo_impressao_danfe values ('4', 'Danfe NFCe', '4');
insert into tipo_impressao_danfe values ('5', 'Danfe NFCe Mensagem Eletrônica', '5');