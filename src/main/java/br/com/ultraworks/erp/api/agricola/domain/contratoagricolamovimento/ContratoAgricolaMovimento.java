package br.com.ultraworks.erp.api.agricola.domain.contratoagricolamovimento;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.ultraworks.erp.api.agricola.domain.contratoagricola.ContratoAgricola;
import br.com.ultraworks.erp.api.tabela.domain.operacaointerna.OperacaoInterna;
import br.com.ultraworks.erp.core.entity.UWEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "contrato_agricola_movimento")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class ContratoAgricolaMovimento extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "ContratoAgricolaMovimentoSeq", sequenceName = "seq_contrato_agricola_movimento", allocationSize = 1)
	@GeneratedValue(generator = "ContratoAgricolaMovimentoSeq")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "contrato_agricola_id")
	private ContratoAgricola contratoAgricola;

	@OneToOne
	@JoinColumn(name = "operacao_interna_id")
	private OperacaoInterna operacaoInterna;

	@Column(name = "num_nota")
	private int numeroNota;

	@Column(name = "data_movimento")
	private LocalDate dataMovimento;

	@Column(name = "qtd_movimento")
	private BigDecimal quantidade;

	@Column(name = "valor_movimento")
	private BigDecimal valor;

}
