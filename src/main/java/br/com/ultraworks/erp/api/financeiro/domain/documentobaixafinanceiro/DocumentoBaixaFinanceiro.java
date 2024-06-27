package br.com.ultraworks.erp.api.financeiro.domain.documentobaixafinanceiro;

import java.time.LocalDate;

import br.com.ultraworks.erp.api.financeiro.domain.conta.Conta;
import br.com.ultraworks.erp.api.financeiro.domain.tipooperacaofinanceira.TipoOperacaoFinanceira;
import br.com.ultraworks.erp.api.organograma.domain.departamento.Departamento;
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
@Table(name = "documento_baixa_financeiro")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class DocumentoBaixaFinanceiro extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "docBaixaSeq", sequenceName = "seq_documento_baixa_financeiro", allocationSize = 1)
	@GeneratedValue(generator = "docBaixaSeq")
	private Long id;

	@Column(name = "numero")
	private int numero;

	@Column(name = "data_baixa")
	private LocalDate dataBaixa;

	@OneToOne
	@JoinColumn(name = "departamento_id")
	private Departamento departamento;
	
	@OneToOne
	@JoinColumn(name = "tipo_operacao_financeira_id")
	private TipoOperacaoFinanceira tipoOperacaoFinanceira;

	@OneToOne
	@JoinColumn(name = "conta_id")
	private Conta conta;

	@Column(name = "observacao")
	private String observacao;
}
