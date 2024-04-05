create sequence seq_modulo;

create table modulo (
	id				bigint       not null constraint modulo_pkey primary key,
	nome            varchar(255) not null,
	sigla        	varchar(30)  not null,
	icone     		varchar(30)  not null,
	path_base       varchar(30)  not null	
);

insert into modulo (id,  nome, sigla, icone, path_base) values (nextval('seq_modulo'), 'Administrativo', 'ADM', 'pi-database', 'admin');
insert into modulo (id,  nome, sigla, icone, path_base) values (nextval('seq_modulo'), 'Financeiro', 'FIN', 'pi-dollar', 'financeiro');
insert into modulo (id,  nome, sigla, icone, path_base) values (nextval('seq_modulo'), 'Estoque', 'EST', 'pi-box', 'estoque');
insert into modulo (id,  nome, sigla, icone, path_base) values (nextval('seq_modulo'), 'Fiscal', 'FIS', 'pi-bullseye', 'fiscal');