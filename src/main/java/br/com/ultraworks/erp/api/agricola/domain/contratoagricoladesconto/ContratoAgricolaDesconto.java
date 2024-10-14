package br.com.ultraworks.erp.api.agricola.domain.contratoagricoladesconto;

import java.math.BigDecimal;

import br.com.ultraworks.erp.api.agricola.domain.contratoagricola.ContratoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.tipocalculoagricola.TipoCalculoAgricola;
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
@Table(name = "contrato_agricola_desconto")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class ContratoAgricolaDesconto extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "ContratoAgricolaDescontoSeq", sequenceName = "seq_contrato_agricola_desconto", allocationSize = 1)
	@GeneratedValue(generator = "ContratoAgricolaDescontoSeq")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "contrato_agricola_id")
	private ContratoAgricola contratoAgricola;

	@OneToOne
	@JoinColumn(name = "tipo_calculo_agricola_id")
	private TipoCalculoAgricola tipoCalculoAgricola;

	@Column(name = "perc_taxa_contrato")
	private BigDecimal percentualTaxaContrato;

	@Column(name = "perc_taxa_atual")
	private BigDecimal percentualTaxaAtual;

	@Column(name = "valor")
	private BigDecimal valor;

}
