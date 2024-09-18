select	distinct t.id as tituloId,
		tt.sigla as tipoTituloSigla,
		d.sigla as departamentoSigla,
		t.nosso_numero as nossoNumero,
		t.data_documento as dataDocumento,
		p.nome_razao_social as parceiroNome,
		pl.nome_local as parceiroLocalNome,
		gf.sigla as grupoFinanceiroSigla,
		fg.sigla as fatoGeradorSigla,
		cmf.sigla as caracteristicaMovimentoFinanceiroSigla,
		hp.sigla as historicoPadraoSigla,
		t.valor_total as valorTitulo
from	movimento_financeiro mf 
		join parcela_financeiro pf on pf.id = mf.parcela_id 
		join titulo t on t.id = pf.titulo_id 
		join tipo_titulo tt on tt.id = t.tipo_titulo_id 
		join tipo_operacao_financeira tof on tof.id = mf.tipo_operacao_financeira_id 
		join departamento d on d.id = t.departamento_id
		join parceiro_local pl on pl.id = t.parceiro_local_id
		join parceiro p on p.id = pl.parceiro_id
		join grupo_financeiro gf on gf.id = t.grupo_financeiro_id
		join fato_gerador fg on fg.id = t.fato_gerador_id
		join caracteristica_movimento_financeiro cmf on cmf.id = t.caracteristica_movimento_financeiro_id
		join historico_padrao hp on hp.id = t.historico_padrao_id
where	t.empresa_filial_id = :empresaFilialId
and     mf.data_movimento between :dataMovimentoInicial and :dataMovimentoFinal
and     ((0 = :tipoTituloId) or (tipo_titulo_id = :tipoTituloId))
and     ((0 = :departamentoId) or (t.departamento_id = :departamentoId))
and		((0 = :parceiroLocalId) or (t.parceiro_local_id = :parceiroLocalId))
and		((0 = :caracteristicaMovimentoFinanceiroId) or (caracteristica_movimento_financeiro_id = :caracteristicaMovimentoFinanceiroId))
and     ((0 = :carteiraFinanceiraId) or (carteira_financeira_id = :carteiraFinanceiraId))
and     ((0 = :grupoFinanceiroId) or (mf.grupo_financeiro_id = :grupoFinanceiroId))
and     ((0 = :fatoGeradorId) or (fato_gerador_id = :fatoGeradorId))
and     ((0 = :nossoNumero) or (nosso_numero = :nossoNumero))
and     (('' = :documento) or (documento = :documento))
order by t.id


