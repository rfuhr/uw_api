create table tipo_documento_referenciado (
	value				char(3) not null constraint tipo_documento_referenciado_pkey primary key,
	name				varchar(250) not null,
	codigo_receita      varchar(3) not null
);

insert into tipo_documento_referenciado values ('1', 'NFe / NFC-e', '1');
insert into tipo_documento_referenciado values ('2', 'NF Modelos 1/1A, 2', '2');
insert into tipo_documento_referenciado values ('3', 'NF de Produtor Rural', '3');
insert into tipo_documento_referenciado values ('4', 'Cupom Fiscal Referenciado', '4');
insert into tipo_documento_referenciado values ('5', 'CT-e Referenciado', '5');
