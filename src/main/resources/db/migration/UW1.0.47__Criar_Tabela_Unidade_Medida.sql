create sequence seq_unidade_medida;

create table unidade_medida (
	id					bigint not null constraint unidade_medida_pkey primary key,
	nome				varchar(100) not null,
	sigla				varchar(20)  not null,
	grandeza			varchar(2)   not null,
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone not null,
	user_update     	bigint,
	date_update			timestamp with time zone
);

insert into unidade_medida(id, nome, sigla, grandeza, user_create, date_create, user_update, date_update) values (nextval('seq_unidade_medida'), 'METRO QUADRADO', 'm2', 'A', 1, NOW()::timestamp, 1, NOW()::timestamp);
insert into unidade_medida(id, nome, sigla, grandeza, user_create, date_create, user_update, date_update) values (nextval('seq_unidade_medida'), 'CENTIMETRO QUADRADO', 'cm2', 'A', 1, NOW()::timestamp, 1, NOW()::timestamp);

insert into unidade_medida(id, nome, sigla, grandeza, user_create, date_create, user_update, date_update) values (nextval('seq_unidade_medida'), 'METRO', 'm', 'C', 1, NOW()::timestamp, 1, NOW()::timestamp);
insert into unidade_medida(id, nome, sigla, grandeza, user_create, date_create, user_update, date_update) values (nextval('seq_unidade_medida'), 'CENTÍMETROS', 'cm', 'C', 1, NOW()::timestamp, 1, NOW()::timestamp);

insert into unidade_medida(id, nome, sigla, grandeza, user_create, date_create, user_update, date_update) values (nextval('seq_unidade_medida'), 'LITRO', 'l', 'V', 1, NOW()::timestamp, 1, NOW()::timestamp);
insert into unidade_medida(id, nome, sigla, grandeza, user_create, date_create, user_update, date_update) values (nextval('seq_unidade_medida'), 'MILIITRO', 'ml', 'V', 1, NOW()::timestamp, 1, NOW()::timestamp);
insert into unidade_medida(id, nome, sigla, grandeza, user_create, date_create, user_update, date_update) values (nextval('seq_unidade_medida'), 'METRO CÚBICO', 'm3', 'V', 1, NOW()::timestamp, 1, NOW()::timestamp);

insert into unidade_medida(id, nome, sigla, grandeza, user_create, date_create, user_update, date_update) values (nextval('seq_unidade_medida'), 'KILOGRAMA', 'kg', 'P', 1, NOW()::timestamp, 1, NOW()::timestamp);
insert into unidade_medida(id, nome, sigla, grandeza, user_create, date_create, user_update, date_update) values (nextval('seq_unidade_medida'), 'GRAMA', 'g', 'P', 1, NOW()::timestamp, 1, NOW()::timestamp);
insert into unidade_medida(id, nome, sigla, grandeza, user_create, date_create, user_update, date_update) values (nextval('seq_unidade_medida'), 'SACA 60KG', 'SC60', 'P', 1, NOW()::timestamp, 1, NOW()::timestamp);
insert into unidade_medida(id, nome, sigla, grandeza, user_create, date_create, user_update, date_update) values (nextval('seq_unidade_medida'), 'TONELADA', 't', 'P', 1, NOW()::timestamp, 1, NOW()::timestamp);

insert into unidade_medida(id, nome, sigla, grandeza, user_create, date_create, user_update, date_update) values (nextval('seq_unidade_medida'), 'UNIDADE', 'UN', 'E', 1, NOW()::timestamp, 1, NOW()::timestamp);
insert into unidade_medida(id, nome, sigla, grandeza, user_create, date_create, user_update, date_update) values (nextval('seq_unidade_medida'), 'AMPOLA', 'AP', 'E', 1, NOW()::timestamp, 1, NOW()::timestamp);
insert into unidade_medida(id, nome, sigla, grandeza, user_create, date_create, user_update, date_update) values (nextval('seq_unidade_medida'), 'BALDE', 'BD', 'E', 1, NOW()::timestamp, 1, NOW()::timestamp);
insert into unidade_medida(id, nome, sigla, grandeza, user_create, date_create, user_update, date_update) values (nextval('seq_unidade_medida'), 'BANDEJA', 'BJ', 'E', 1, NOW()::timestamp, 1, NOW()::timestamp);
insert into unidade_medida(id, nome, sigla, grandeza, user_create, date_create, user_update, date_update) values (nextval('seq_unidade_medida'), 'BARRA', 'BR', 'E', 1, NOW()::timestamp, 1, NOW()::timestamp);
insert into unidade_medida(id, nome, sigla, grandeza, user_create, date_create, user_update, date_update) values (nextval('seq_unidade_medida'), 'BISNAGA', 'BS', 'E', 1, NOW()::timestamp, 1, NOW()::timestamp);

insert into unidade_medida(id, nome, sigla, grandeza, user_create, date_create, user_update, date_update) values (nextval('seq_unidade_medida'), 'BLOCO', 'BL', 'E', 1, NOW()::timestamp, 1, NOW()::timestamp);
insert into unidade_medida(id, nome, sigla, grandeza, user_create, date_create, user_update, date_update) values (nextval('seq_unidade_medida'), 'BOBINA', 'BO', 'E', 1, NOW()::timestamp, 1, NOW()::timestamp);
insert into unidade_medida(id, nome, sigla, grandeza, user_create, date_create, user_update, date_update) values (nextval('seq_unidade_medida'), 'BOMBONA', 'BB', 'E', 1, NOW()::timestamp, 1, NOW()::timestamp);
insert into unidade_medida(id, nome, sigla, grandeza, user_create, date_create, user_update, date_update) values (nextval('seq_unidade_medida'), 'CAPSULA', 'CA', 'E', 1, NOW()::timestamp, 1, NOW()::timestamp);

insert into unidade_medida(id, nome, sigla, grandeza, user_create, date_create, user_update, date_update) values (nextval('seq_unidade_medida'), 'CARTELA', 'CR', 'E', 1, NOW()::timestamp, 1, NOW()::timestamp);
insert into unidade_medida(id, nome, sigla, grandeza, user_create, date_create, user_update, date_update) values (nextval('seq_unidade_medida'), 'CENTO', 'CT', 'E', 1, NOW()::timestamp, 1, NOW()::timestamp);
insert into unidade_medida(id, nome, sigla, grandeza, user_create, date_create, user_update, date_update) values (nextval('seq_unidade_medida'), 'CONJUNTO', 'CJ', 'E', 1, NOW()::timestamp, 1, NOW()::timestamp);

insert into unidade_medida(id, nome, sigla, grandeza, user_create, date_create, user_update, date_update) values (nextval('seq_unidade_medida'), 'CAIXA', 'CX', 'E', 1, NOW()::timestamp, 1, NOW()::timestamp);
insert into unidade_medida(id, nome, sigla, grandeza, user_create, date_create, user_update, date_update) values (nextval('seq_unidade_medida'), 'CAIXA COM 2 UNIDADES', 'CX2', 'E', 1, NOW()::timestamp, 1, NOW()::timestamp);
insert into unidade_medida(id, nome, sigla, grandeza, user_create, date_create, user_update, date_update) values (nextval('seq_unidade_medida'), 'CAIXA COM 3 UNIDADES', 'CX3', 'E', 1, NOW()::timestamp, 1, NOW()::timestamp);
insert into unidade_medida(id, nome, sigla, grandeza, user_create, date_create, user_update, date_update) values (nextval('seq_unidade_medida'), 'CAIXA COM 5 UNIDADES', 'CX5', 'E', 1, NOW()::timestamp, 1, NOW()::timestamp);
insert into unidade_medida(id, nome, sigla, grandeza, user_create, date_create, user_update, date_update) values (nextval('seq_unidade_medida'), 'CAIXA COM 10 UNIDADES', 'CX10', 'E',  1, NOW()::timestamp, 1, NOW()::timestamp);
insert into unidade_medida(id, nome, sigla, grandeza, user_create, date_create, user_update, date_update) values (nextval('seq_unidade_medida'), 'CAIXA COM 15 UNIDADES', 'CX15', 'E',  1, NOW()::timestamp, 1, NOW()::timestamp);
insert into unidade_medida(id, nome, sigla, grandeza, user_create, date_create, user_update, date_update) values (nextval('seq_unidade_medida'), 'CAIXA COM 20 UNIDADES', 'CX20', 'E',  1, NOW()::timestamp, 1, NOW()::timestamp);
insert into unidade_medida(id, nome, sigla, grandeza, user_create, date_create, user_update, date_update) values (nextval('seq_unidade_medida'), 'CAIXA COM 25 UNIDADES', 'CX25', 'E',  1, NOW()::timestamp, 1, NOW()::timestamp);
insert into unidade_medida(id, nome, sigla, grandeza, user_create, date_create, user_update, date_update) values (nextval('seq_unidade_medida'), 'CAIXA COM 50 UNIDADES', 'CX50', 'E',  1, NOW()::timestamp, 1, NOW()::timestamp);
insert into unidade_medida(id, nome, sigla, grandeza, user_create, date_create, user_update, date_update) values (nextval('seq_unidade_medida'), 'CAIXA COM 100 UNIDADES', 'CX100', 'E', 1, NOW()::timestamp, 1, NOW()::timestamp);
insert into unidade_medida(id, nome, sigla, grandeza, user_create, date_create, user_update, date_update) values (nextval('seq_unidade_medida'), 'DUZIA', 'DZ', 'E', 1, NOW()::timestamp, 1, NOW()::timestamp);
insert into unidade_medida(id, nome, sigla, grandeza, user_create, date_create, user_update, date_update) values (nextval('seq_unidade_medida'), 'GROSSA', 'GS', 'E', 1, NOW()::timestamp, 1, NOW()::timestamp);
insert into unidade_medida(id, nome, sigla, grandeza, user_create, date_create, user_update, date_update) values (nextval('seq_unidade_medida'), 'PAR', 'PAR', 'E', 1, NOW()::timestamp, 1, NOW()::timestamp);
insert into unidade_medida(id, nome, sigla, grandeza, user_create, date_create, user_update, date_update) values (nextval('seq_unidade_medida'), 'PACOTE', 'PCT', 'E', 1, NOW()::timestamp, 1, NOW()::timestamp);
insert into unidade_medida(id, nome, sigla, grandeza, user_create, date_create, user_update, date_update) values (nextval('seq_unidade_medida'), 'PEÇA', 'PÇ', 'E', 1, NOW()::timestamp, 1, NOW()::timestamp);
insert into unidade_medida(id, nome, sigla, grandeza, user_create, date_create, user_update, date_update) values (nextval('seq_unidade_medida'), 'ROLO', 'RL', 'E', 1, NOW()::timestamp, 1, NOW()::timestamp);
insert into unidade_medida(id, nome, sigla, grandeza, user_create, date_create, user_update, date_update) values (nextval('seq_unidade_medida'), 'TUBO', 'TB', 'E', 1, NOW()::timestamp, 1, NOW()::timestamp);