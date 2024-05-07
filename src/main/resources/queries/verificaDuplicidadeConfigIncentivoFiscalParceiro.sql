select cp.id, cp.config_incentivo_fiscal_id
from config_incentivo_fiscal_parceiro cp
inner join config_incentivo_fiscal cf on cp.config_incentivo_fiscal_id = cf.id
where parceiro_local_id = :parceiroLocalId 
and cf.tipo_incentivo_fiscal_id = :tipoIncentivoFiscalId
and ((:dataInicio between cp.data_inicio_vigencia and cp.data_final_vigencia) 
     or (:dataFinal between cp.data_inicio_vigencia and cp.data_final_vigencia)
     or (:dataInicio <= cp.data_inicio_vigencia and :dataFinal >= cp.data_inicio_vigencia))
and (1 = :validaId or cp.id <> :id)         