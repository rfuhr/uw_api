package br.com.ultraworks.erp.api.financeiro.integrator.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.ultraworks.erp.api.financeiro.domain.caracteristicamovfin.CaracteristicaMovimentoFinanceiro;
import br.com.ultraworks.erp.api.financeiro.domain.carteirafinanceira.CarteiraFinanceira;
import br.com.ultraworks.erp.api.financeiro.domain.conta.Conta;
import br.com.ultraworks.erp.api.financeiro.domain.fatogerador.FatoGerador;
import br.com.ultraworks.erp.api.financeiro.domain.grupofinanceiro.GrupoFinanceiro;
import br.com.ultraworks.erp.api.financeiro.domain.negociacao.NegociacaoFinanceira;
import br.com.ultraworks.erp.api.financeiro.domain.tipotitulo.TipoTitulo;
import br.com.ultraworks.erp.api.organograma.domain.departamento.Departamento;
import br.com.ultraworks.erp.api.organograma.domain.empresaFilial.EmpresaFilial;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocal.ParceiroLocal;
import br.com.ultraworks.erp.api.tabela.domain.historicopadrao.HistoricoPadrao;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContainerTitulo {

	private EmpresaFilial empresaFilial;
	private Departamento departamento;
	private GrupoFinanceiro grupoFinanceiro;
	private ParceiroLocal parceiroLocal;
	private FatoGerador fatoGerador;
	private CaracteristicaMovimentoFinanceiro caracteristicaMovimentoFinanceiro;
	private HistoricoPadrao historicoPadrao;
	private CarteiraFinanceira carteiraFinanceira;
	private TipoTitulo tipoTitulo;
	private String documento;
	private String observacaoTitulo;
	private BigDecimal valorTotalTitulo;
	private LocalDate dataDocumento;
	private Conta conta;
	private String historico;
	private Long nossoNumero;
	private NegociacaoFinanceira negociacaoFinanceira;
}
