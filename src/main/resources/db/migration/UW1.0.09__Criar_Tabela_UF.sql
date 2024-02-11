create sequence seq_uf start with 100;

create table uf (
	id				bigint       not null constraint uf_pkey primary key,
	codigo		    bigint not null,
	nome       		varchar(255) not null,
	sigla			varchar(2) 	 not null,
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone
);

insert into uf (id, codigo, nome, sigla, user_create, date_create, user_update, date_update) 
values (11, 11, 'Rondônia', 'RO', 1, NOW()::timestamp, 1, NOW()::timestamp);
insert into uf (id, codigo, nome, sigla, user_create, date_create, user_update, date_update) 
values (12, 12, 'Acre', 'AC', 1, NOW()::timestamp, 1, NOW()::timestamp);
insert into uf (id, codigo, nome, sigla, user_create, date_create, user_update, date_update) 
values (13, 13, 'Amazonas', 'AM', 1, NOW()::timestamp, 1, NOW()::timestamp);
insert into uf (id, codigo, nome, sigla, user_create, date_create, user_update, date_update) 
values (14, 14, 'Roraima', 'RR', 1, NOW()::timestamp, 1, NOW()::timestamp);
insert into uf (id, codigo, nome, sigla, user_create, date_create, user_update, date_update) 
values (15, 15, 'Pará', 'PA', 1, NOW()::timestamp, 1, NOW()::timestamp);
insert into uf (id, codigo, nome, sigla, user_create, date_create, user_update, date_update) 
values (16, 16, 'Amapá', 'AP', 1, NOW()::timestamp, 1, NOW()::timestamp);
insert into uf (id, codigo, nome, sigla, user_create, date_create, user_update, date_update) 
values (17, 17, 'Tocantins', 'TO', 1, NOW()::timestamp, 1, NOW()::timestamp);
insert into uf (id, codigo, nome, sigla, user_create, date_create, user_update, date_update) 
values (21, 21, 'Maranhão', 'MA', 1, NOW()::timestamp, 1, NOW()::timestamp);
insert into uf (id, codigo, nome, sigla, user_create, date_create, user_update, date_update) 
values (22, 22, 'Piauí', 'PI', 1, NOW()::timestamp, 1, NOW()::timestamp);
insert into uf (id, codigo, nome, sigla, user_create, date_create, user_update, date_update) 
values (23, 23, 'Ceará', 'CE', 1, NOW()::timestamp, 1, NOW()::timestamp);
insert into uf (id, codigo, nome, sigla, user_create, date_create, user_update, date_update) 
values (24, 24, 'Rio Grande do Norte', 'RN', 1, NOW()::timestamp, 1, NOW()::timestamp);
insert into uf (id, codigo, nome, sigla, user_create, date_create, user_update, date_update) 
values (25, 25, 'Paraíba', 'PB', 1, NOW()::timestamp, 1, NOW()::timestamp);
insert into uf (id, codigo, nome, sigla, user_create, date_create, user_update, date_update) 
values (26, 26, 'Pernambuco', 'PE', 1, NOW()::timestamp, 1, NOW()::timestamp);
insert into uf (id, codigo, nome, sigla, user_create, date_create, user_update, date_update) 
values (27, 27, 'Alagoas', 'AL', 1, NOW()::timestamp, 1, NOW()::timestamp);
insert into uf (id, codigo, nome, sigla, user_create, date_create, user_update, date_update) 
values (28, 28, 'Sergipe', 'SE', 1, NOW()::timestamp, 1, NOW()::timestamp);
insert into uf (id, codigo, nome, sigla, user_create, date_create, user_update, date_update) 
values (29, 29, 'Bahia', 'BA', 1, NOW()::timestamp, 1, NOW()::timestamp);
insert into uf (id, codigo, nome, sigla, user_create, date_create, user_update, date_update) 
values (31, 31, 'Minas Gerais', 'MG', 1, NOW()::timestamp, 1, NOW()::timestamp);
insert into uf (id, codigo, nome, sigla, user_create, date_create, user_update, date_update) 
values (32, 32, 'Espírito Santo', 'ES', 1, NOW()::timestamp, 1, NOW()::timestamp);
insert into uf (id, codigo, nome, sigla, user_create, date_create, user_update, date_update) 
values (33, 33, 'Rio de Janeiro', 'RJ', 1, NOW()::timestamp, 1, NOW()::timestamp);
insert into uf (id, codigo, nome, sigla, user_create, date_create, user_update, date_update) 
values (35, 35, 'São Paulo', 'SP', 1, NOW()::timestamp, 1, NOW()::timestamp);
insert into uf (id, codigo, nome, sigla, user_create, date_create, user_update, date_update) 
values (41, 41, 'Paraná', 'PR', 1, NOW()::timestamp, 1, NOW()::timestamp);
insert into uf (id, codigo, nome, sigla, user_create, date_create, user_update, date_update) 
values (42, 42, 'Santa Catarina', 'SC', 1, NOW()::timestamp, 1, NOW()::timestamp);
insert into uf (id, codigo, nome, sigla, user_create, date_create, user_update, date_update) 
values (43, 43, 'Rio Grande do Sul', 'RS', 1, NOW()::timestamp, 1, NOW()::timestamp);
insert into uf (id, codigo, nome, sigla, user_create, date_create, user_update, date_update) 
values (50, 50, 'Mato Grosso do Sul', 'MS', 1, NOW()::timestamp, 1, NOW()::timestamp);
insert into uf (id, codigo, nome, sigla, user_create, date_create, user_update, date_update) 
values (51, 51, 'Mato Grosso', 'MT', 1, NOW()::timestamp, 1, NOW()::timestamp);
insert into uf (id, codigo, nome, sigla, user_create, date_create, user_update, date_update) 
values (52, 52, 'Goiás', 'GO', 1, NOW()::timestamp, 1, NOW()::timestamp);
insert into uf (id, codigo, nome, sigla, user_create, date_create, user_update, date_update) 
values (53, 53, 'Distrito Federal', 'DF', 1, NOW()::timestamp, 1, NOW()::timestamp);
insert into uf (id, codigo, nome, sigla, user_create, date_create, user_update, date_update) 
values (99, 99, 'Exterior', 'EX', 1, NOW()::timestamp, 1, NOW()::timestamp);