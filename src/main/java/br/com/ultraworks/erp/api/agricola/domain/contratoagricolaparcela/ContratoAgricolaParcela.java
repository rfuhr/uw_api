package br.com.ultraworks.erp.api.agricola.domain.contratoagricolaparcela;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.ultraworks.erp.api.agricola.domain.contratoagricola.ContratoAgricola;
import br.com.ultraworks.erp.core.entity.UWEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "contrato_agricola_parcela")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class ContratoAgricolaParcela extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "ContratoAgricolaParcelaSeq", sequenceName = "seq_contrato_agricola_parcela", allocationSize = 1)
	@GeneratedValue(generator = "ContratoAgricolaParcelaSeq")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "contrato_agricola_id")
	private ContratoAgricola contratoAgricola;

	@Column(name = "num_parcela")
	private int numeroParcela;

	@Column(name = "data_vencimento")
	private LocalDate dataVencimento;

	@Column(name = "valor_parcela")
	private BigDecimal valorParcela;

}
