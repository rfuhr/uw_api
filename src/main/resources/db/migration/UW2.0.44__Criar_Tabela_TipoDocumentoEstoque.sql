create table tipo_documento_estoque (
	value				varchar(2) not null constraint tipo_documento_estoque_pkey primary key,
	name				varchar(80) not null
);

insert into tipo_documento_estoque values ('1', 'NFe');
insert into tipo_documento_estoque values ('2', 'Documento Interno');