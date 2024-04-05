create table tipo_processo_emissao (
	value				char(3) not null constraint tipo_processo_emissao_pkey primary key,
	name				varchar(250) not null,
	codigo_receita      varchar(3) not null
);

insert into tipo_processo_emissao values ('0', 'Emissão de NF-e com aplicativo do contribuinte', '0');
insert into tipo_processo_emissao values ('1', 'Emissão de NF-e avulsa pelo fisco', '1');
insert into tipo_processo_emissao values ('2', 'Emissão de NF-e, pelo contribuinte com seu certificado digial, através do site do fisco', '2');
insert into tipo_processo_emissao values ('3', 'Emissão de NF-e pelo contribuinte com aplicativo fornecido pelo fisco', '3');
