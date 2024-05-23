create table tipo_sintetico_analitico (
	value				varchar(2) not null constraint tipo_sintetico_analitico_pkey primary key,
	name				varchar(80) not null
);

insert into tipo_sintetico_analitico values ('1', 'Sintético');
insert into tipo_sintetico_analitico values ('2', 'Analítico');