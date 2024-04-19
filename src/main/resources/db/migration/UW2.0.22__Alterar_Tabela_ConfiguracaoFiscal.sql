alter table configuracao_fiscal add column icms boolean NOT NULL default false;
alter table configuracao_fiscal add column ipi boolean NOT NULL default false;
alter table configuracao_fiscal add column pis boolean NOT NULL default false;
alter table configuracao_fiscal add column cofins boolean NOT NULL default false;