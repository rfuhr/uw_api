package br.com.ultraworks.erp.api.financeiro.domain.negociacao;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.ultraworks.erp.api.organograma.domain.departamento.Departamento;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocal.ParceiroLocal;
import br.com.ultraworks.erp.core.entity.UWEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "negociacao_financeira")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class NegociacaoFinanceira extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "negociacaoFinanceiraSeq", sequenceName = "seq_negociacao_financeira", allocationSize = 1)
	@GeneratedValue(generator = "negociacaoFinanceiraSeq")
	private Long id;

	@OneToOne
	@JoinColumn(name = "departamento_id")
	private Departamento departamento;

	@OneToOne
	@JoinColumn(name = "parceiro_local_id")
	private ParceiroLocal parceiroLocal;

	@Column(name = "vlr_total_atraso")
	private BigDecimal valorTotalAtraso;

	@Column(name = "vlr_juros_negociado")
	private BigDecimal valorJurosNegociado;

	@Column(name = "vlr_desc_negociado")
	private BigDecimal valorDescontoNegociado;

	@Column(name = "vlr_negociado_pagar")
	private BigDecimal valorNegociadoPagar;

	@Column(name = "data_negociacao")
	private LocalDate dataNegociacao;

	@Column(name = "nosso_numero")
	private Long nossoNumero;

	@Column(name = "observacao")
	private String observacao;
	
	@Column(name = "qtde_parcelas")
	private int quantidadeParcelas;

}
