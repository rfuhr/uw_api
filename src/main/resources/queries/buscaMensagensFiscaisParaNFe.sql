with mensagem (config_mensagem_fiscal_id, mensagem_fiscal_id, obs_fiscal) 
as (
select cmf.id, mf.id as mensagem_fiscal_id, mf.obs_fiscal
from config_mensagem_fiscal cmf
inner join mensagem_fiscal mf on cmf.mensagem_fiscal_id = mf.id
where :data between cmf.data_inicio_vigencia and cmf.data_final_vigencia
)
select obs_fiscal
from mensagem m
inner join config_mensagem_fiscal_config_fiscal conf on m.config_mensagem_fiscal_id = conf.config_mensagem_fiscal_id
inner join configuracao_fiscal_icms icms on conf.configuracao_fiscal_id = icms.configuracao_fiscal_id
where :data between conf.data_inicio_vigencia and conf.data_final_vigencia and icms.id in (:listaConfiguracoesICMS) 
UNION
select obs_fiscal
from mensagem m
inner join config_mensagem_fiscal_config_fiscal conf on m.config_mensagem_fiscal_id = conf.config_mensagem_fiscal_id
inner join configuracao_fiscal_ipi ipi on conf.configuracao_fiscal_id = ipi.configuracao_fiscal_id
where :data between conf.data_inicio_vigencia and conf.data_final_vigencia and ipi.id in (:listaConfiguracoesIPI) 
UNION
select obs_fiscal
from mensagem m
inner join config_mensagem_fiscal_config_fiscal conf on m.config_mensagem_fiscal_id = conf.config_mensagem_fiscal_id
inner join configuracao_fiscal_pis pis on conf.configuracao_fiscal_id = pis.configuracao_fiscal_id
where :data between conf.data_inicio_vigencia and conf.data_final_vigencia and pis.id in (:listaConfiguracoesPIS) 
UNION
select obs_fiscal
from mensagem m
inner join config_mensagem_fiscal_config_fiscal conf on m.config_mensagem_fiscal_id = conf.config_mensagem_fiscal_id
inner join configuracao_fiscal_cofins cofins on conf.configuracao_fiscal_id = cofins.configuracao_fiscal_id
where :data between conf.data_inicio_vigencia and conf.data_final_vigencia and cofins.id in (:listaConfiguracoesCOFINS) 
UNION
select obs_fiscal
from mensagem m
inner join config_mensagem_fiscal_situac_trib conf on m.config_mensagem_fiscal_id = conf.config_mensagem_fiscal_id
inner join configuracao_fiscal_icms icms on conf.situacao_tributaria_id = icms.situacao_tributaria_id
where :data between conf.data_inicio_vigencia and conf.data_final_vigencia and icms.id in (:listaConfiguracoesICMS)  
UNION
select obs_fiscal
from mensagem m
inner join config_mensagem_fiscal_situac_trib conf on m.config_mensagem_fiscal_id = conf.config_mensagem_fiscal_id
inner join configuracao_fiscal_ipi ipi on conf.situacao_tributaria_id = ipi.situacao_tributaria_id
where :data between conf.data_inicio_vigencia and conf.data_final_vigencia and ipi.id in (:listaConfiguracoesIPI) 
UNION
select obs_fiscal
from mensagem m
inner join config_mensagem_fiscal_situac_trib conf on m.config_mensagem_fiscal_id = conf.config_mensagem_fiscal_id
inner join configuracao_fiscal_pis pis on conf.situacao_tributaria_id = pis.situacao_tributaria_id
where :data between conf.data_inicio_vigencia and conf.data_final_vigencia and pis.id in (:listaConfiguracoesPIS) 
UNION
select obs_fiscal
from mensagem m
inner join config_mensagem_fiscal_situac_trib conf on m.config_mensagem_fiscal_id = conf.config_mensagem_fiscal_id
inner join configuracao_fiscal_cofins cofins on conf.situacao_tributaria_id = cofins.situacao_tributaria_id
where :data between conf.data_inicio_vigencia and conf.data_final_vigencia and cofins.id in (:listaConfiguracoesCOFINS) 
UNION
select obs_fiscal
from mensagem m
inner join config_mensagem_fiscal_grupo_trib conf on m.config_mensagem_fiscal_id = conf.config_mensagem_fiscal_id
inner join item item on conf.grupo_tributacao_id = item.grupo_tributacao_id
where :data between conf.data_inicio_vigencia and conf.data_final_vigencia and item.id in (:listaItens) 
UNION
select obs_fiscal
from mensagem m
inner join config_mensagem_fiscal_item conf on m.config_mensagem_fiscal_id = conf.config_mensagem_fiscal_id
where :data between conf.data_inicio_vigencia and conf.data_final_vigencia and conf.item_id in (:listaItens) 
UNION
select obs_fiscal
from mensagem m
inner join config_mensagem_fiscal_oper_interna conf on m.config_mensagem_fiscal_id = conf.config_mensagem_fiscal_id
where :data between conf.data_inicio_vigencia and conf.data_final_vigencia and conf.operacao_interna_id = :operacaoInternaId 
UNION
select obs_fiscal
from mensagem m
inner join config_mensagem_fiscal_tipo_incent_fiscal conf on m.config_mensagem_fiscal_id = conf.config_mensagem_fiscal_id
inner join config_incentivo_fiscal cf on conf.tipo_incentivo_fiscal_id = cf.tipo_incentivo_fiscal_id
inner join config_incentivo_fiscal_parceiro cp on cf.id = cp.config_incentivo_fiscal_id
where :data between conf.data_inicio_vigencia and conf.data_final_vigencia and 
:data between cf.data_inicio_vigencia and cf.data_final_vigencia and
:data between cp.data_inicio_vigencia and cp.data_final_vigencia and
cp.parceiro_local_id = :parceiroLocalId