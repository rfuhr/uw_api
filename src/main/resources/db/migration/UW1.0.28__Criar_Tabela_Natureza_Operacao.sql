create sequence seq_natureza_operacao;

create table natureza_operacao (
	id					bigint not null constraint natureza_operacao_pkey primary key,
	nome				varchar(250) 	not null,
	sigla				varchar(30) 	not null,
	indicador_operacao  char(1) 		not null,
	user_create     	bigint 		 	not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone,
	CONSTRAINT natureza_operacao_indicador_operacao_id_foreign FOREIGN KEY (indicador_operacao) REFERENCES indicador_operacao(value)
);

insert into natureza_operacao (id, nome, sigla, indicador_operacao, user_create, date_create, user_update, date_update) 
	values (nextval('seq_natureza_operacao'), 'Venda de mercadorias','VDA', 'S', 1, NOW()::timestamp,1,NOW()::timestamp); 
insert into natureza_operacao (id, nome, sigla, indicador_operacao, user_create, date_create, user_update, date_update) 
	values (nextval('seq_natureza_operacao'), 'Devolução de Venda','DEVVDA', 'E', 1, NOW()::timestamp,1,NOW()::timestamp); 	
insert into natureza_operacao (id, nome, sigla, indicador_operacao, user_create, date_create, user_update, date_update) 
	values (nextval('seq_natureza_operacao'), 'Remessa de bens e mercadorias','REM', 'S', 1, NOW()::timestamp,1,NOW()::timestamp);
insert into natureza_operacao (id, nome, sigla, indicador_operacao, user_create, date_create, user_update, date_update) 
	values (nextval('seq_natureza_operacao'), 'Retorno de bens e mercadorias','RET', 'E', 1, NOW()::timestamp,1,NOW()::timestamp);
insert into natureza_operacao (id, nome, sigla, indicador_operacao, user_create, date_create, user_update, date_update) 
	values (nextval('seq_natureza_operacao'), 'Exportação','EXP', 'S', 1, NOW()::timestamp,1,NOW()::timestamp); 			 			 			 		
insert into natureza_operacao (id, nome, sigla, indicador_operacao, user_create, date_create, user_update, date_update) 
	values (nextval('seq_natureza_operacao'), 'Entrega Futura','ENTFUT', 'S', 1, NOW()::timestamp,1,NOW()::timestamp); 		
insert into natureza_operacao (id, nome, sigla, indicador_operacao, user_create, date_create, user_update, date_update) 
	values (nextval('seq_natureza_operacao'), 'Compra de mercadorias','CPR', 'E', 1, NOW()::timestamp,1,NOW()::timestamp); 		 			 			 			
insert into natureza_operacao (id, nome, sigla, indicador_operacao, user_create, date_create, user_update, date_update) 
	values (nextval('seq_natureza_operacao'), 'Devolução de Compra','DEVCPR', 'S', 1, NOW()::timestamp,1,NOW()::timestamp); 		 			 			 				