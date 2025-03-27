create or replace view view_cache_nfe as
SELECT 
   c.nfe_id,
   (((CONVERT_FROM(cache::bytea, 'UTF8')::json ->> 'identificacaoNFe')::json ->> 'operacaoInterna')::json ->> 'sigla') as siglaOperacaoInterna,
   (((CONVERT_FROM(cache::bytea, 'UTF8')::json ->> 'destinatario')::json ->> 'endereco')::json ->> 'parceiroLocalId')::BIGINT as parceiroLocalId
FROM cache_nfe c;