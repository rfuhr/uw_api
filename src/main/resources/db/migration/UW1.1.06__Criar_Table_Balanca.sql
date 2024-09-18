create sequence seq_balanca;

create table balanca (
	id					bigint not null constraint balanca_pk primary key,
	empresa_filial_id   bigint not null,
	nome				varchar(100) not null,
	porta				varchar(10) not null,
	velocidade			integer not null,
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone,
	CONSTRAINT empresa_filial_balanca_foreign FOREIGN KEY (empresa_filial_id) REFERENCES empresa_filial (id)						
);


insert into modulo (id, nome, sigla, icone, path_base) values
(nextval('seq_modulo'), 'Agrícola', 'AGR', 'fa-solid fa-tractor', 'agricola')