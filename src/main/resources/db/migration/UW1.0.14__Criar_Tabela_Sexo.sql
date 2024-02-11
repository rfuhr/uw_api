create sequence seq_sexo;

create table sexo (
	id					bigint       not null constraint sexo_pkey primary key,
	nome			 	varchar(120) NOT NULL,
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone
);

insert into sexo (id, nome, user_create, date_create, user_update, date_update) 
values (nextval('seq_sexo'), 'Masculino', 1, NOW()::timestamp, 1, NOW()::timestamp);
insert into sexo (id, nome, user_create, date_create, user_update, date_update) 
values (nextval('seq_sexo'), 'Feminino', 1, NOW()::timestamp, 1, NOW()::timestamp);
insert into sexo (id, nome, user_create, date_create, user_update, date_update) 
values (nextval('seq_sexo'), 'Outros', 1, NOW()::timestamp, 1, NOW()::timestamp);