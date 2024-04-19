create table tipo_transporte (
	value				char(3) not null constraint tipo_transporte_pkey primary key,
	name				varchar(250) not null
);

insert into tipo_transporte values ('1', 'Veículo');
insert into tipo_transporte values ('2', 'Vagão');
insert into tipo_transporte values ('3', 'Balsa');
