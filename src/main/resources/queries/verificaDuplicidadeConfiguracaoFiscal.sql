select cf.id 
from configuracao_fiscal cf
left outer join configuracao_fiscal_icms cf_icms on cf.id = cf_icms.configuracao_fiscal_id
left outer join configuracao_fiscal_ipi cf_ipi on cf.id = cf_ipi.configuracao_fiscal_id
left outer join configuracao_fiscal_pis cf_pis on cf.id = cf_pis.configuracao_fiscal_id
left outer join configuracao_fiscal_cofins cf_cofins on cf.id = cf_cofins.configuracao_fiscal_id
where uf_origem_id = :ufOrigemId 
and uf_destino_id = :ufDestinoId
and regime_tributario_id = :regimeTributarioId
and indicador_operacao = :indicadorOperacao
and ((:dataInicio between data_inicio_vigencia and data_final_vigencia) 
     or (:dataFinal between data_inicio_vigencia and data_final_vigencia)
     or (:dataInicio <= data_inicio_vigencia and :dataFinal >= data_inicio_vigencia))
and (1 = :validaId or cf.id <> :id)         
and (1 = :validaGrupoTributacao or grupo_tributacao_id = :grupoTributacaoId)
and (1 = :validaCfop or cfop_id = :cfopId)
and (1 = :validaNcm or ncm_id = :ncmId)
and (1 = :validaOrigem or origem_id = :origemId)
and (1 = :validaOperacaoInterna or operacao_interna_id = :operacaoInternaId)
and (1 = :validaClassificacaoOperacao or classificacao_operacao_id = :classificacaoOperacaoId)
and (1 = :validaItem or item_id = :itemId)
and (1 = :validaIcms or cf_icms.configuracao_fiscal_id is not null)
and (1 = :validaIpi or cf_ipi.configuracao_fiscal_id is not null)
and (1 = :validaPis or cf_pis.configuracao_fiscal_id is not null)
and (1 = :validaCofins or cf_cofins.configuracao_fiscal_id is not null)