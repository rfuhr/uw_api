create sequence seq_config_classificacao_agricola;

create table config_classificacao_agricola (
	id					bigint not null constraint config_classificacao_agricola_pk primary key,
	item_id   			bigint not null,
	tipo_classificacao_agricola_id bigint not null,
	safra_id            bigint not null,
	faixa_inicial		decimal(15,5) not null,
	faixa_final         decimal(15,5) not null,
	desconto			boolean not null,
	percentual_desconto decimal(15,5),
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone,
	CONSTRAINT item_config_classificacao_agricola_foreign FOREIGN KEY (item_id) REFERENCES item (id),
	CONSTRAINT tipo_config_classificacao_agricola_foreign FOREIGN KEY (tipo_classificacao_agricola_id) REFERENCES tipo_classificacao_agricola (id),
	CONSTRAINT safra_config_classificacao_agricola_foreign FOREIGN KEY (safra_id) REFERENCES safra (id)
);
