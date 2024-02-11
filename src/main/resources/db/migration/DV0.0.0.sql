create sequence hibernate_sequence;
create sequence clientidseq start with 2;

create table if not exists client (
    id                  bigint       not null constraint client_pkey primary key,
    datasource_name     varchar(255) not null,
    datasource_password varchar(255) not null,
    datasource_username varchar(255) not null,
    tenant_id           varchar(255) not null
);

INSERT INTO public.client (id, datasource_password, datasource_name, datasource_username, tenant_id) VALUES (1, 'postgres', 'uw_erp', 'postgres', 'ultra');
