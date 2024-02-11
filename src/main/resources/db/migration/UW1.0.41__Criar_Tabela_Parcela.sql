create sequence seq_parcela_financeiro;

create table parcela_financeiro (
	id					bigint not null constraint parcela_financeiro_pkey primary key,
	titulo_id     		bigint not null,
	num_parcela			integer not null,
	seq_parcela			integer not null,
	data_vencimento		date not null,
	valor_parcela		decimal(15,2) not null,
	ult_seq_mvto		integer not null,
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone,
	CONSTRAINT titulo_parcela_id_foreign FOREIGN KEY (titulo_id) REFERENCES titulo (id)
);