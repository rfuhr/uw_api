select cp.id, cp.config_mensagem_fiscal_id
from config_mensagem_fiscal_tipo_incent_fiscal cp
inner join config_mensagem_fiscal cf on cp.config_mensagem_fiscal_id = cf.id
where tipo_incentivo_fiscal_id = :tipoIncentivoFiscalId 
and cf.mensagem_fiscal_id = :mensagemFiscalId
and ((:dataInicio between cp.data_inicio_vigencia and cp.data_final_vigencia) 
     or (:dataFinal between cp.data_inicio_vigencia and cp.data_final_vigencia)
     or (:dataInicio <= cp.data_inicio_vigencia and :dataFinal >= cp.data_inicio_vigencia))
and (1 = :validaId or cp.id <> :id)         