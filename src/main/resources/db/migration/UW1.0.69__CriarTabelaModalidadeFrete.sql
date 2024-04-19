create table modalidade_frete (
	value				char(3) not null constraint modalidade_frete_pkey primary key,
	name				varchar(250) not null
);

insert into modalidade_frete values ('0', 'Contratação do Frete por conta do Remetente (CIF)');
insert into modalidade_frete values ('1', 'Contratação do Frete por conta do Destinatário (FOB)');
insert into modalidade_frete values ('2', 'Contratação do Frete por conta de Terceiros');
insert into modalidade_frete values ('3', 'Transporte Próprio por conta do Remetente');
insert into modalidade_frete values ('4', 'Transporte Próprio por conta do Destinatário');
insert into modalidade_frete values ('9', 'Sem Ocorrência de Transporte');
