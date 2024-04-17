with configuracoes (id, grupo_tributacao_id, cfop_id, ncm_id, origem_id, operacao_interna_id, classificacao_operacao_id,
                    item_id, icms_id, ipi_id, pis_id, cofins_id) 
as (
select cf.id,
       cf.grupo_tributacao_id,
       cf.cfop_id,
       cf.ncm_id,
       cf.origem_id,
       cf.operacao_interna_id,
       cf.classificacao_operacao_id,
       cf.item_id,
       cf_icms.configuracao_fiscal_id as icms_id, 
       cf_ipi.configuracao_fiscal_id as ipi_id, 
       cf_pis.configuracao_fiscal_id as pis_id,
       cf_cofins.configuracao_fiscal_id as cofins_id
from configuracao_fiscal cf
left outer join configuracao_fiscal_icms cf_icms on cf.id = cf_icms.configuracao_fiscal_id
left outer join configuracao_fiscal_ipi cf_ipi on cf.id = cf_ipi.configuracao_fiscal_id
left outer join configuracao_fiscal_pis cf_pis on cf.id = cf_pis.configuracao_fiscal_id
left outer join configuracao_fiscal_cofins cf_cofins on cf.id = cf_cofins.configuracao_fiscal_id
where uf_origem_id = :ufOrigemId 
and uf_destino_id = :ufDestinoId
and ((:dataEmissao between data_inicio_vigencia and data_final_vigencia))
and indicador_operacao = :indicadorOperacao
and regime_tributario_id = :regimeTributario
and (1 = :validaIcms or icms = :icms)
and (1 = :validaIpi or ipi = :ipi)
and (1 = :validaPis or pis = :pis)
and (1 = :validaCofins or cofins = :cofins)
)
select 'Por Item' as consulta, 1 as ordem, 
       case when item_id is null then 1 else 0 end +
       case when grupo_tributacao_id is null then 1 else 0 end +
       case when cfop_id is null then 1 else 0 end +
       case when ncm_id is null then 1 else 0 end +
       case when origem_id is null then 1 else 0 end +
       case when classificacao_operacao_id is null then 1 else 0 end +
       case when operacao_interna_id is null then 1 else 0 end 
       as subordem,
       id, grupo_tributacao_id, cfop_id, ncm_id, origem_id, 
       operacao_interna_id, classificacao_operacao_id, item_id, icms_id, ipi_id, pis_id, cofins_id
from configuracoes
where item_id = :itemId 
 and (classificacao_operacao_id is null or classificacao_operacao_id = :classificacaoOperacao)
 and (operacao_interna_id is null or operacao_interna_id = :operacaoInterna)
 and (origem_id is null or origem_id = :origemId)
 and (ncm_id is null or ncm_id = :ncmId)
 and (cfop_id is null or cfop_id = :cfopId)
 and (grupo_tributacao_id is null or grupo_tributacao_id = :grupoTributacao)
UNION ALL 
select 'Por Ncm' as consulta, 2 as ordem, 
       case when item_id is null then 1 else 0 end +
       case when grupo_tributacao_id is null then 1 else 0 end +
       case when cfop_id is null then 1 else 0 end +
       case when ncm_id is null then 1 else 0 end +
       case when origem_id is null then 1 else 0 end +
       case when classificacao_operacao_id is null then 1 else 0 end +
       case when operacao_interna_id is null then 1 else 0 end 
       as subordem,
       id, grupo_tributacao_id, cfop_id, ncm_id, origem_id, 
       operacao_interna_id, classificacao_operacao_id, item_id, icms_id, ipi_id, pis_id, cofins_id
from configuracoes
where ncm_id = :ncmId
 and (classificacao_operacao_id is null or classificacao_operacao_id = :classificacaoOperacao)
 and (operacao_interna_id is null or operacao_interna_id = :operacaoInterna)
 and (origem_id is null or origem_id = :origemId)
 and (item_id is null or item_id = :itemId)
 and (cfop_id is null or cfop_id = :cfopId)
 and (grupo_tributacao_id is null or grupo_tributacao_id = :grupoTributacao)
UNION ALL 
select 'Por Grupo Tributação' as consulta, 3 as ordem,  
       case when item_id is null then 1 else 0 end +
       case when grupo_tributacao_id is null then 1 else 0 end +
       case when cfop_id is null then 1 else 0 end +
       case when ncm_id is null then 1 else 0 end +
       case when origem_id is null then 1 else 0 end +
       case when classificacao_operacao_id is null then 1 else 0 end +
       case when operacao_interna_id is null then 1 else 0 end 
       as subordem,
       id, grupo_tributacao_id, cfop_id, ncm_id, origem_id, 
       operacao_interna_id, classificacao_operacao_id, item_id, icms_id, ipi_id, pis_id, cofins_id
from configuracoes
where grupo_tributacao_id = :grupoTributacao 
 and (classificacao_operacao_id is null or classificacao_operacao_id = :classificacaoOperacao)
 and (operacao_interna_id is null or operacao_interna_id = :operacaoInterna)
 and (origem_id is null or origem_id = :origemId)
 and (item_id is null or item_id = :itemId)
 and (cfop_id is null or cfop_id = :cfopId)
 and (ncm_id is null or ncm_id = :ncmId) 
UNION ALL 
select 'Por Classificação Operação' as consulta, 4 as ordem,  
       case when item_id is null then 1 else 0 end +
       case when grupo_tributacao_id is null then 1 else 0 end +
       case when cfop_id is null then 1 else 0 end +
       case when ncm_id is null then 1 else 0 end +
       case when origem_id is null then 1 else 0 end +
       case when classificacao_operacao_id is null then 1 else 0 end +
       case when operacao_interna_id is null then 1 else 0 end 
       as subordem,
       id, grupo_tributacao_id, cfop_id, ncm_id, origem_id, 
       operacao_interna_id, classificacao_operacao_id, item_id, icms_id, ipi_id, pis_id, cofins_id
from configuracoes
where classificacao_operacao_id = :classificacaoOperacao 
 and (grupo_tributacao_id is null or grupo_tributacao_id = :grupoTributacao)
 and (operacao_interna_id is null or operacao_interna_id = :operacaoInterna)
 and (origem_id is null or origem_id = :origemId)
 and (item_id is null or item_id = :itemId)
 and (cfop_id is null or cfop_id = :cfopId)
 and (ncm_id is null or ncm_id = :ncmId)
UNION ALL 
select 'Por Origem' as consulta, 5 as ordem,  
       case when item_id is null then 1 else 0 end +
       case when grupo_tributacao_id is null then 1 else 0 end +
       case when cfop_id is null then 1 else 0 end +
       case when ncm_id is null then 1 else 0 end +
       case when origem_id is null then 1 else 0 end +
       case when classificacao_operacao_id is null then 1 else 0 end +
       case when operacao_interna_id is null then 1 else 0 end 
       as subordem,
       id, grupo_tributacao_id, cfop_id, ncm_id, origem_id, 
       operacao_interna_id, classificacao_operacao_id, item_id, icms_id, ipi_id, pis_id, cofins_id
from configuracoes
where origem_id = :origemId 
 and (grupo_tributacao_id is null or grupo_tributacao_id = :grupoTributacao)
 and (operacao_interna_id is null or operacao_interna_id = :operacaoInterna)
 and (classificacao_operacao_id is null or classificacao_operacao_id = :classificacaoOperacao)
 and (item_id is null or item_id = :itemId)
 and (cfop_id is null or cfop_id = :cfopId)
 and (ncm_id is null or ncm_id = :ncmId)   
UNION ALL 
select 'Por Cfop' as consulta, 6 as ordem,  
       case when item_id is null then 1 else 0 end +
       case when grupo_tributacao_id is null then 1 else 0 end +
       case when cfop_id is null then 1 else 0 end +
       case when ncm_id is null then 1 else 0 end +
       case when origem_id is null then 1 else 0 end +
       case when classificacao_operacao_id is null then 1 else 0 end +
       case when operacao_interna_id is null then 1 else 0 end 
       as subordem,
       id, grupo_tributacao_id, cfop_id, ncm_id, origem_id, 
       operacao_interna_id, classificacao_operacao_id, item_id, icms_id, ipi_id, pis_id, cofins_id
from configuracoes
where cfop_id = :cfopId 
 and (grupo_tributacao_id is null or grupo_tributacao_id = :grupoTributacao)
 and (operacao_interna_id is null or operacao_interna_id = :operacaoInterna)
 and (classificacao_operacao_id is null or classificacao_operacao_id = :classificacaoOperacao)
 and (item_id is null or item_id = :itemId)
 and (origem_id is null or origem_id = :origemId)
 and (ncm_id is null or ncm_id = :ncmId)  
UNION ALL 
select 'Por Operação Interna' as consulta, 7 as ordem,  
       case when item_id is null then 1 else 0 end +
       case when grupo_tributacao_id is null then 1 else 0 end +
       case when cfop_id is null then 1 else 0 end +
       case when ncm_id is null then 1 else 0 end +
       case when origem_id is null then 1 else 0 end +
       case when classificacao_operacao_id is null then 1 else 0 end +
       case when operacao_interna_id is null then 1 else 0 end 
       as subordem,
       id, grupo_tributacao_id, cfop_id, ncm_id, origem_id, 
       operacao_interna_id, classificacao_operacao_id, item_id, icms_id, ipi_id, pis_id, cofins_id
from configuracoes
where operacao_interna_id = :operacaoInterna 
 and (grupo_tributacao_id is null or grupo_tributacao_id = :grupoTributacao)
 and (cfop_id is null or cfop_id = :cfopId)
 and (classificacao_operacao_id is null or classificacao_operacao_id = :classificacaoOperacao)
 and (item_id is null or item_id = :itemId)
 and (origem_id is null or origem_id = :origemId)
 and (ncm_id is null or ncm_id = :ncmId)   
order by ordem, subordem 