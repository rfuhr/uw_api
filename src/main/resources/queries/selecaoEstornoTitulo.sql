select	t.id as tituloId,
		t.parceiro_local_id as parceiroLocalId,
		t.documento,
		t.nosso_numero as nossoNumero,
		pf.id as parcelaFinanceiraId,
		pf.num_parcela as numeroParcela, 
		pf.data_vencimento as dataVencimento,
		mf.id as movimentoFinanceiroId,
		mf.data_movimento as dataMovimento,
		mf.seq_mvto as sequenciaMovimento,
		mf.valor_movimento as valorMovimento,
		tof.id as tipoOperacaoFinanceiraId,
		tof.nome as tipoOperacaoFinanceiraNome,
		mf.operacao_movimento_financeiro_id as operacaoMovimentoFinanceiroId,
		mf.operacao_acessoria_financeira_id as operacaoAcessoriaFinanceiraId,
		d.sigla as departamentoSigla,
		pl.cpf_cnpj as parceiroLocalCpfCnpj
from	movimento_financeiro mf 
		join parcela_financeiro pf on pf.id = mf.parcela_id 
		join titulo t on t.id = pf.titulo_id 
		join tipo_titulo tt on tt.id = t.tipo_titulo_id 
		join tipo_operacao_financeira tof on tof.id = mf.tipo_operacao_financeira_id 
		join departamento d on d.id = t.departamento_id
		join parceiro_local pl on pl.id = t.parceiro_local_id
where	mf.operacao_movimento_financeiro_id = 51
and		mf.sub_seq_mvto = 1
and     tof.idn_estorna_baixa = true
and     not exists (Select * from estorno_baixa_financeiro ebf where ebf.movimento_estornado_id = mf.id)
and     t.empresa_filial_id = :empresaFilialId
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
order by mf.data_movimento , t.nosso_numero , mf.id


