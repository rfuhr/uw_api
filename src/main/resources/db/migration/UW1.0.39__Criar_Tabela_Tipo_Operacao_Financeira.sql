create sequence seq_tipo_operacao_financeira;

create table tipo_operacao_financeira (
	id					bigint not null constraint tipo_operacao_financeira_pkey primary key,
	nome				varchar(250) not null,
	sigla				varchar(20) not null,
	idn_subst_carteira  boolean not null,
	idn_retencao        boolean not null,
	idn_cria_titulo     boolean not null,
	idn_baixa_titulo   boolean not null,
	idn_cria_parcela    boolean not null,
	idn_cria_seq_parcela boolean not null,
	idn_cria_seq_mvto   boolean not null,
	idn_sel_baixa       boolean not null,
	idn_negociacao      boolean not null,
	idn_cancelamento    boolean not null,
	idn_estorno         boolean not null,
	idn_baixa_npr       boolean not null,
	idn_estorno_baixa   boolean not null,
	idn_baixa_chq_avulso boolean not null,
	idn_lista_postitbaixa boolean not null,
	idn_rel_despesas    boolean not null,
	idn_lista_analisevda boolean not null,
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone
);

create sequence seq_operacao_movimento_financeiro;

create table operacao_movimento_financeiro (
	id					bigint not null constraint operacao_movimento_financeiro_pkey primary key,
	nome				varchar(250) not null,
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone
);

create sequence seq_operacao_acessoria_financeira;

create table operacao_acessoria_financeira (
	id					bigint not null constraint operacao_acessoria_financeira_pkey primary key,
	nome				varchar(250) not null,
	idn_juro_desconto   char(1) not null,
	idn_cria_sub_seq_movto boolean not null,
	idn_compoe_saldo    boolean not null,
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone
);

create sequence seq_operacao_financeira;

create table operacao_financeira (
	id					bigint not null constraint operacao_financeira_pkey primary key,
	tipo_operacao_financeira_id bigint not null,
	operacao_movimento_financeiro_id bigint not null,
	operacao_acessoria_financeira_id bigint not null,
	idn_gera_ficha      boolean not null,
	idn_integra_bco     boolean not null,
	operacao_banco_id   bigint not null,
	idn_gera_apurfin    boolean not null,
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone
);
