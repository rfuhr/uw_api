select	false as baixar,
		false as parcial,
		0.0 as valorBaixa,
		0.0 as valorJuros,
		0.0 as valorDesconto,
		mf.saldo_parcela  as valorMovimento,
		mf.carteira_financeira_id as carteiraFinanceiraId,
		cf.sigla as siglaCarteira,
		t.id as tituloId,
		t.documento,
		pf.data_vencimento as dataVencimento,
		t.parceiro_local_id as parceiroLocalId,
		pl.cpf_cnpj as parceiroLocalCpfCnpj,
		t.historico_padrao_id as historicoPadraoId,
		t.caracteristica_movimento_financeiro_id as caracteristicaMovimentoId,
		cm.sigla as caracteristicaMovimentoSigla,
		t.data_documento as dataDocumento,
		mf.id as movimentoId,
		t.tipo_titulo_id as tipoTituloId,
		pf.num_parcela  as numeroParcela,
		pf.id as parcelaFinanceiraId,
		mf.id as movimentoFinanceiroId,
		mf.seq_mvto as sequenciaMovimento,
		t.nosso_numero as nossoNumero,
		tt.sigla as tipoTituloSigla,
		ef.id as empresaFilialId,
		ef.sigla as empresaFilialSigla,
		dp.id as departamentoId,
		dp.sigla as departamentoSigla
from	titulo t
		join parcela_financeiro pf on pf.titulo_id = t.id
		join movimento_financeiro mf on mf.parcela_id = pf.id and mf.seq_mvto = pf.ult_seq_mvto 
		join carteira_financeira cf on cf.id = mf.carteira_financeira_id  
		join tipo_titulo tt on tt.id = t.tipo_titulo_id
		join empresa_filial ef on ef.id = t.empresa_filial_id
		join departamento dp on dp.id = t.departamento_id
		join caracteristica_movimento_financeiro cm on cm.id = t.caracteristica_movimento_financeiro_id
		join parceiro_local pl on pl.id = t.parceiro_local_id
where	mf.saldo_parcela > 0
and     mf.sub_seq_mvto = 1
and     t.empresa_filial_id = :empresaFilialId
and     mf.data_movimento between :dataMovimentoInicial and :dataMovimentoFinal
and     pf.data_vencimento  between :dataVencimentoInicial and :dataVencimentoFinal
and     ((0 = :tipoTituloId) or (tipo_titulo_id = :tipoTituloId))
and     ((0 = :departamentoId) or (t.departamento_id = :departamentoId))
and		((0 = :parceiroLocalId) or (t.parceiro_local_id = :parceiroLocalId))
and		((0 = :caracteristicaMovimentoFinanceiroId) or (caracteristica_movimento_financeiro_id = :caracteristicaMovimentoFinanceiroId))
and     ((0 = :carteiraFinanceiraId) or (carteira_financeira_id = :carteiraFinanceiraId))
and     ((0 = :grupoFinanceiroId) or (mf.grupo_financeiro_id = :grupoFinanceiroId))
and     ((0 = :fatoGeradorId) or (fato_gerador_id = :fatoGeradorId))
and     ((0 = :nossoNumero) or (nosso_numero = :nossoNumero))
and     (('' = :documento) or (documento = :documento))
order by t.data_documento, t.tipo_titulo_id, t.parceiro_local_id 