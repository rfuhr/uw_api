create sequence seq_motivo_desoneracao start with 1;

create table motivo_desoneracao (
	id					bigint not null constraint motivo_desoneracao_pkey primary key,
	codigo				integer not null,
	nome				varchar(250) not null,
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone
);

insert into motivo_desoneracao (id, codigo, nome, user_create, date_create, user_update, date_update) values
 (nextval('seq_motivo_desoneracao'),1,'Táxi', 1, NOW()::timestamp,1,NOW()::timestamp),
 (nextval('seq_motivo_desoneracao'),3,'Produto agropecuário/Uso na agropecuária', 1, NOW()::timestamp,1,NOW()::timestamp),
 (nextval('seq_motivo_desoneracao'),4,'Frotista/Locadora', 1, NOW()::timestamp,1,NOW()::timestamp),
 (nextval('seq_motivo_desoneracao'),5,'Diplomático/Consular', 1, NOW()::timestamp,1,NOW()::timestamp),
 (nextval('seq_motivo_desoneracao'),6,'Utilitários e Motocicletas da Amazônia Ocidental e Áreas de Livre Comércio', 1, NOW()::timestamp,1,NOW()::timestamp),
 (nextval('seq_motivo_desoneracao'),7,'SUFRAMA', 1, NOW()::timestamp,1,NOW()::timestamp),
 (nextval('seq_motivo_desoneracao'),8,'Venda a Órgão Público', 1, NOW()::timestamp,1,NOW()::timestamp),
 (nextval('seq_motivo_desoneracao'),9,'Outros', 1, NOW()::timestamp,1,NOW()::timestamp),
 (nextval('seq_motivo_desoneracao'),10,'Deficiente Condutor', 1, NOW()::timestamp,1,NOW()::timestamp),
 (nextval('seq_motivo_desoneracao'),11,'Deficiente Não Condutor', 1, NOW()::timestamp,1,NOW()::timestamp),
 (nextval('seq_motivo_desoneracao'),12,'Órgão de fomento e desenvolvimento agropecuário', 1, NOW()::timestamp,1,NOW()::timestamp);