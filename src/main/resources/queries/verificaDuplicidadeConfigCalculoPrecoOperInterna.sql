 select cp.id, cp.config_calculo_preco_id
from config_calculo_preco_oper_interna cp
inner join config_calculo_preco cf on cp.config_calculo_preco_id = cf.id
where cp.operacao_interna_id = :operacaoInternaId 
and cf.tipo_preco_id = :tipoPrecoId
and ((:dataInicio between cp.data_inicio_vigencia and cp.data_final_vigencia) 
     or (:dataFinal between cp.data_inicio_vigencia and cp.data_final_vigencia)
     or (:dataInicio <= cp.data_inicio_vigencia and :dataFinal >= cp.data_inicio_vigencia))
and (1 = :validaId or cp.id <> :id)                