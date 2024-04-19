alter table item add column grupo_tributacao_id bigint;
alter table item add CONSTRAINT item_grupo_tributacao_id_foreign FOREIGN KEY (grupo_tributacao_id) REFERENCES grupo_tributacao (id);