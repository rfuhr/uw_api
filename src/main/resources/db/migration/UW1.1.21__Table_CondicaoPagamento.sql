create table tipo_condicao_pagamento (
	value				varchar(3) not null constraint tipo_condicao_pagamento_pkey primary key,
	name				varchar(250) not null
);

insert into tipo_condicao_pagamento values ('1', 'À Vista');
insert into tipo_condicao_pagamento values ('2', 'Dia Fixo');
insert into tipo_condicao_pagamento values ('3', 'Intervalo de Dias');
insert into tipo_condicao_pagamento values ('4', 'Divisão de Dias');
insert into tipo_condicao_pagamento values ('5', 'Composição');
insert into tipo_condicao_pagamento values ('6', 'Personalizada');


---------------------------------------------------------------------------------------

create sequence seq_condicao_pagamento;

create table condicao_pagamento (
	id						bigint not null constraint condicao_pagamento_pk primary key,
	codigo					integer not null,
	nome					varchar(80) not null,
	descricao				varchar(250) not null,
	indicador_forma_pagamento varchar(3) not null,
	possui_entrada			boolean not null,
	quantidade_parcelas		integer,
	tipo_condicao_pagamento varchar(3) not null,
	dia_vencimento			integer,
	dias_intervalo			integer,
	dias_divisao			varchar(80),
	composicao				varchar(80),
	user_create     		bigint not null,
	date_create				timestamp with time zone 	 not null,
	user_update     		bigint,
	date_update				timestamp with time zone,
	CONSTRAINT indformapagamento_condicao_pagamento_foreign FOREIGN KEY (indicador_forma_pagamento) REFERENCES indicador_forma_pagamento (value),
	CONSTRAINT tipocondpagamento_condicao_pagamento_foreign FOREIGN KEY (tipo_condicao_pagamento) REFERENCES tipo_condicao_pagamento (value)
);	