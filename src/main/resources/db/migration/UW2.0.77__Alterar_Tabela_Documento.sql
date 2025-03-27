alter table documento add column titulo_id bigint;
alter table documento add CONSTRAINT documento_titulo_id_foreign FOREIGN KEY (titulo_id) REFERENCES titulo (id);