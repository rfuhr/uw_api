create sequence seq_grupo_autonomia;

create table grupo_autonomia (
	id					bigint not null constraint grupo_autonomia_pkey primary key,
	nome				varchar(100) not null	
);

create sequence seq_autonomia;

create table autonomia (
	id					bigint not null constraint autonomia_pkey primary key,
	grupo_autonomia_id	bigint not null,
	nome   				varchar(100) not null,
	descricao			varchar(250) not null,
	tag					varchar(50)  not null,
	CONSTRAINT grupo_autonomia_autonomia_id_foreign FOREIGN KEY (grupo_autonomia_id) REFERENCES grupo_autonomia(id)
);

insert into grupo_autonomia(id, nome) values (nextval('seq_grupo_autonomia'), 'Financeiro');

insert into autonomia (id, grupo_autonomia_id, tag, nome, descricao) values 
(nextval('seq_autonomia'), (select id from grupo_autonomia where nome = 'Financeiro'), 'ALTDTMOVLCTOFIN', 'Alterar Data Movimento no Lançamento', 'Permite alterar data de movimento no lançamento de títulos');