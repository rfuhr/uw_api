alter table empresa_filial add column parceiro_local_id bigint;

alter table empresa_filial add CONSTRAINT empresa_filial_parceiro_local_id_foreign FOREIGN KEY (parceiro_local_id) REFERENCES parceiro_local(id);