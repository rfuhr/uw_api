package br.com.ultraworks.erp.api.financeiro.domain.estornobaixafinanceiro;

import java.time.LocalDate;
import java.time.LocalDateTime;

import br.com.ultraworks.erp.api.financeiro.domain.motivoestornofinanceiro.MotivoEstornoFinanceiro;
import br.com.ultraworks.erp.api.financeiro.domain.movimento.MovimentoFinanceiro;
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
@Table(name = "estorno_baixa_financeiro")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class EstornoBaixaFinanceiro extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "estornoBaixaFinanceiroSeq", sequenceName = "seq_estorno_baixa_financeiro", allocationSize = 1)
	@GeneratedValue(generator = "estornoBaixaFinanceiroSeq")
	private Long id;

	@OneToOne
	@JoinColumn(name = "movimento_gerado_id")
	private MovimentoFinanceiro movimentoGerado;

	@OneToOne
	@JoinColumn(name = "movimento_estornado_id")
	private MovimentoFinanceiro movimentoEstornado;

	@OneToOne
	@JoinColumn(name = "motivo_estorno_id")
	private MotivoEstornoFinanceiro motivoEstorno;

	@Column(name = "obs_estorno")
	private String observacao;

	@Column(name = "data_estorno")
	private LocalDate dataEstorno;

	@Column(name = "data_lancamento")
	private LocalDateTime dataLancamento;

}
