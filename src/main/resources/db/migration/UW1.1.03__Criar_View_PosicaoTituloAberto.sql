create view vw_fin_posicaotituloaberto as 
select	t.id as tituloId,
		pf.id as parcelaFinanceiraId,
		pf.seq_parcela as parcelaFinanceiraSequencia,
		mf.id as movimentoFinanceiroId,
		mf.seq_mvto as movimentoFinanceiroSequencia,
		tt.id as tipoTituloId,
		tt.codigo as tipoTituloCodigo,
		tt.nome   as tipoTituloNome,
		tt.sigla  as tipoTituloSigla,
		p.id as parceiroId,
		p.nome_razao_social as parceiroNomeRazaoSocial,
		pl.id as parceiroLocalId,
		pl.cpf_cnpj as parceiroLocalCpfCnpj,
		pl.nome_local as parceiroLocalNome,
		cmf.id as caracteristicaMovimentoFinanceiroId,
		cmf.codigo as caracteristicaMovimentoFinanceiroCodigo,
		cmf.nome as caracteristicaMovimentoFinanceiroNome,
		cmf.sigla as caracteristicaMovimentoFinanceiroSigla,
		d.nome as departamentoNome,
		d.sigla as departamentoSigla,
		ef.nome as empresaFilialNome,
		ef.sigla as empresaFilialSigla,
		e.nome as empresaNome,
		e.sigla as empresaSigla,
		cf.id as carteiraId,
		cf.codigo as carteiraCodigo,
		cf.nome as carteiraNome,
		cf.sigla as carteiraSigla,
		gf.codigo as grupoFinanceiroCodigo,
		gf.nome as grupoFinanceiroNome,
		gf.sigla as grupoFinanceiroSigla,
		pf.data_vencimento as parcelaFinanceiraDataVencimento,
		t.data_documento as tituloDataDocumento,
		mf.data_movimento as movimentoFinanceiroDataMovimento,
		t.documento as tituloDocumento,
		t.nosso_numero as tituloNossoNumero,
		pf.num_parcela as parcelaFinanceiraNumero,
		fg.codigo as fatoGeradorCodigo,
		fg.nome as fatoGeradorNome,
		fg.sigla as fatoGeradorSigla,
		t.observacao as tituloObservacao,
		hp.codigo as historicoPadraoCodigo,
		hp.nome as historicoPadraoNome,
		hp.sigla as historicoPadraoSigla,
		case when tt.indicador_dc = 'C' and cmf.inverte_indicador = false then mf.saldo_parcela
			 when tt.indicador_dc = 'C' and cmf.inverte_indicador = true then (mf.saldo_parcela * -1)
		     when tt.indicador_dc = 'D' and cmf.inverte_indicador = false then (mf.saldo_parcela * -1)
		     when tt.indicador_dc = 'D' and cmf.inverte_indicador = true then mf.saldo_parcela end as valoresSaldoParcela,
		case when (CURRENT_DATE - pf.data_vencimento) < 0 then 0
        	 else (CURRENT_DATE - pf.data_vencimento) end as atraso,
		case when tt.indicador_dc = 'C' and cmf.inverte_indicador = false then 'C'
			 when tt.indicador_dc = 'C' and cmf.inverte_indicador = true then 'D'
		     when tt.indicador_dc = 'D' and cmf.inverte_indicador = false then 'D'
		     when tt.indicador_dc = 'D' and cmf.inverte_indicador = true then 'C' end as indicadorDC,
		vwfininctitulo.data_movimento_inclusao as tituloDataMovimentoInclusao
from	movimento_financeiro mf
		join parcela_financeiro pf on pf.id = mf.parcela_id
		join titulo t on t.id = pf.titulo_id 
		join tipo_titulo tt on tt.id = t.tipo_titulo_id 
		join caracteristica_movimento_financeiro cmf on cmf.id = t.caracteristica_movimento_financeiro_id 
		join carteira_financeira cf on cf.id = mf.carteira_financeira_id
		join parceiro_local pl on pl.id = t.parceiro_local_id 
		join parceiro p on p.id = pl.parceiro_id
		join departamento d on d.id = t.departamento_id 
		join empresa_filial ef on ef.id = d.empresa_filial_id 
		join empresa e on e.id = ef.empresa_id 
		join grupo_financeiro gf on gf.id = t.grupo_financeiro_id 
		join fato_gerador fg on fg.id = t.fato_gerador_id 
		join vw_fin_infoinclusaotitulo vwfininctitulo on vwfininctitulo.titulo_id = t.id
		left join historico_padrao hp on hp.id = t.historico_padrao_id 
where	pf.ult_seq_mvto = mf.seq_mvto 
and     cf.lista_pos_titulo = true
and     pf.seq_parcela = (select max(pfMax.seq_parcela) from parcela_financeiro pfMax
							where pfMax.titulo_id = t.id and pfMax.num_parcela = pf.num_parcela);
							
				