create sequence seq_nacionalidade;

create table nacionalidade (
	id					bigint       not null constraint nacionalidade_pkey primary key,
	codigo      		integer not null,
	nome			 	varchar(120) NOT NULL,
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone
);

insert into nacionalidade (id, codigo, nome, user_create, date_create, user_update, date_update) 
values (nextval('seq_nacionalidade'), 1, 'Brasileira', 1, NOW()::timestamp, 1, NOW()::timestamp);
insert into nacionalidade (id, codigo, nome, user_create, date_create, user_update, date_update) 
values (nextval('seq_nacionalidade'), 2, 'Estrangeira', 1, NOW()::timestamp, 1, NOW()::timestamp);