create table indicador_ie_destinatario (
	value				char(3) not null constraint indicador_ie_destinatario_pkey primary key,
	name				varchar(250) not null,
	codigo_receita      varchar(3) not null
);

insert into indicador_ie_destinatario values ('1', 'Contribuinte ICMS', '1');
insert into indicador_ie_destinatario values ('2', 'Contribuinte isento de inscrição', '2');
insert into indicador_ie_destinatario values ('9', 'Não Contribuinte', '9');