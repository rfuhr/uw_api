select dc.id
from documento dc
where dc.numero = :numero 
and dc.data_documento = :dataDocumento
and dc.parceiro_local_id = :parceiroLocalId
and dc.operacao_interna_id = :operacaoInternaId
and dc.valor = :valor
and (1 = :validaId or dc.id <> :id)       