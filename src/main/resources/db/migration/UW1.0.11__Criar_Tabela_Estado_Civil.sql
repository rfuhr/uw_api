create sequence seq_estado_civil;

create table estado_civil (
	id					bigint       not null constraint estado_civil_pkey primary key,
	codigo      		integer not null,
	nome			 	varchar(120) NOT NULL,
	casado	 			boolean NOT NULL DEFAULT false,
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone
);

insert into estado_civil (id, codigo, nome, casado, user_create, date_create, user_update, date_update) 
values (nextval('seq_estado_civil'), 1, 'Solteiro(a)', false, 1, NOW()::timestamp, 1, NOW()::timestamp);
insert into estado_civil (id, codigo, nome, casado, user_create, date_create, user_update, date_update) 
values (nextval('seq_estado_civil'), 2, 'Casado(a)', true, 1, NOW()::timestamp, 1, NOW()::timestamp);
insert into estado_civil (id, codigo, nome, casado, user_create, date_create, user_update, date_update) 
values (nextval('seq_estado_civil'), 3, 'Divorciado(a)', false, 1, NOW()::timestamp, 1, NOW()::timestamp);
insert into estado_civil (id, codigo, nome, casado, user_create, date_create, user_update, date_update) 
values (nextval('seq_estado_civil'), 4, 'Viuvo(a)', false, 1, NOW()::timestamp, 1, NOW()::timestamp);
insert into estado_civil (id, codigo, nome, casado, user_create, date_create, user_update, date_update) 
values (nextval('seq_estado_civil'), 5, 'Amaziado(a)', false, 1, NOW()::timestamp, 1, NOW()::timestamp);