alter table ncm add column tipo_sintetico_analitico varchar(2) NOT NULL default '1';
alter table ncm add CONSTRAINT ncm_tipo_sintetico_analitico_foreign FOREIGN KEY (tipo_sintetico_analitico) REFERENCES tipo_sintetico_analitico (value);

update ncm set tipo_sintetico_analitico = '2' where length(codigo) = 10;