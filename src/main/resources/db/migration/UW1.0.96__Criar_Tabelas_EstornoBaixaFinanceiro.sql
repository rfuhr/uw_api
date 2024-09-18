create sequence seq_estorno_baixa_financeiro;

create table estorno_baixa_financeiro (
	id					bigint not null constraint estorno_baixa_financeiro_pkey primary key,
	movimento_gerado_id	bigint not null,
	movimento_estornado_id bigint not null,
	motivo_estorno_id   bigint not null,
	obs_estorno         varchar(255) not null,
	data_estorno        date not null,
	data_lancamento     timestamp not null,
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone not null,
	user_update     	bigint,
	date_update			timestamp with time zone,
	CONSTRAINT mov_gerador_id_estorno_foreign FOREIGN KEY (movimento_gerado_id) REFERENCES movimento_financeiro (id),
	CONSTRAINT mov_estornado_id_estorno_foreign FOREIGN KEY (movimento_estornado_id) REFERENCES movimento_financeiro (id),
	CONSTRAINT motivo_id_estorno_foreign FOREIGN KEY (motivo_estorno_id) REFERENCES motivo_estorno_financeiro (id)
);

