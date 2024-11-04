select 	sm.id, 
		sm.numero,
		deptoSolicitado.id as departamentoSolicitadoId,
		deptoSolicitado.sigla as departamentoSolicitadoSigla,
		deptoSolicitante.id as departamentoSolicitanteId,
		deptoSolicitante.sigla as departamentoSolicitanteSigla,
		sm.data_solicitacao as dataSolicitacao,
		smi.id as solicitacaoMercadoriaItemId,
		smi.item_id as itemId,
		item.nome as itemNome,
		isimp.id as itemSimplificadoId,
		isimp.nome as itemSimplificadoNome,
		deptoEntrega.id as departamentoEntregaId,
		deptoEntrega.sigla as departamentoEntregaSigla,
		smi.qtd_solicitada as quantidadeSolicitada,
		smi.observacao as observacaoItem,
		smi.previsao_dias_utilizacao as previsaoDiasUtilizacao,
		usm."name" as urgenciaSolicitacaoMercadoria,
		u.nome  as usuarioSolicitacaoNome
from 	solicitacao_mercadoria sm 
		join solicitacao_mercadoria_item smi on smi.solicitacao_mercadoria_id = sm.id
		join departamento deptoSolicitado on deptoSolicitado.id = sm.depto_solicitado_id
		join departamento deptoSolicitante on deptoSolicitante.id = sm.depto_solicitante_id
		join departamento deptoEntrega on deptoEntrega.id = smi.depto_entrega_id 
		join urgencia_solicitacao_mercadoria usm on usm.value = smi.urgencia_solicitacao_mercadoria 
		join usuario u on u.id = smi.usuario_solicitacao_id 
		left join item on item.id = smi.item_id
		left join item_simplificado isimp on isimp.id = smi.item_simplificado_id 
where 	sm.situacao_solicitacao_mercadoria in ('4', '6', '7')
and     sm.depto_solicitado_id = :departamentoSolicitadoId
and     smi.data_atendente is null;