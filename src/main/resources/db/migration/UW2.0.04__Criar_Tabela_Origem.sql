create sequence seq_origem start with 1;

create table origem (
	id					bigint not null constraint origem_pkey primary key,
	codigo				integer not null,
	nome				varchar(2000) not null,
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone
);

insert into origem (id, codigo, nome, user_create, date_create, user_update, date_update) values
 (nextval('seq_origem'),0,'Nacional, exceto os indicados nos códigos 3, 4, 5 e 8.', 1, NOW()::timestamp,1,NOW()::timestamp),
 (nextval('seq_origem'),1,'Estrangeira: importação direta, exceto a indicada no código 6.', 1, NOW()::timestamp,1,NOW()::timestamp),
 (nextval('seq_origem'),2,'Estrangeira: Adquirida no mercado interno, exceto a indicada no código 7.', 1, NOW()::timestamp,1,NOW()::timestamp),
 (nextval('seq_origem'),3,'Nacional, mercadoria ou bem com Conteúdo de Importação superior a 40% e inferior ou igual a 70%.', 1, NOW()::timestamp,1,NOW()::timestamp),
 (nextval('seq_origem'),4,'Nacional, cuja produção tenha sido feita em conformidade com os processos produtivos básicos de que tratam o Decreto-Lei n. 288/1967, e as Leis n. 8.248/1991, 8.387/1991, 10.176/2001 e 11.484/2007.', 1, NOW()::timestamp,1,NOW()::timestamp),
 (nextval('seq_origem'),5,'Nacional, mercadoria ou bem com Conteúdo de importação inferior ou igual a 40%.', 1, NOW()::timestamp,1,NOW()::timestamp),
 (nextval('seq_origem'),6,'Estrangeira: Importação direta, sem similar nacional, constante em lista de Resolução Comex e gás natural.', 1, NOW()::timestamp,1,NOW()::timestamp),
 (nextval('seq_origem'),7,'Estrangeira: Adquirida no mercado interno, sem similar nacional, constante em lista de Resolução Comex e gás natural.', 1, NOW()::timestamp,1,NOW()::timestamp),
 (nextval('seq_origem'),8,'Nacional, mercadoria ou bem com conteúdo de importação superior a 70%.', 1, NOW()::timestamp,1,NOW()::timestamp);