create table origem_documento (
	value				char(3) not null constraint origem_documento_pkey primary key,
	name				varchar(250) not null
);

insert into origem_documento values ('1', 'Emissor de NFe');