alter table historico_padrao add column informa_documento varchar(1);

update historico_padrao set informa_documento = 'O';

alter table historico_padrao alter column informa_documento set not null;