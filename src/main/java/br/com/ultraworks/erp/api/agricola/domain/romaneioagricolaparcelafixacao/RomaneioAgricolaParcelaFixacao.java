package br.com.ultraworks.erp.api.agricola.domain.romaneioagricolaparcelafixacao;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.ultraworks.erp.api.agricola.domain.romaneioagricola.RomaneioAgricola;
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
@Table(name = "romaneio_agricola_parcela_fixacao")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class RomaneioAgricolaParcelaFixacao extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "romaneioAgricolaParcelaFixacaoSeq", sequenceName = "seq_romaneio_agricola_parcela_fixacao", allocationSize = 1)
	@GeneratedValue(generator = "romaneioAgricolaParcelaFixacaoSeq")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "romaneio_agricola_id")
	private RomaneioAgricola romaneioAgricola;

	@Column(name = "data_vencimento")
	private LocalDate dataVencimento;

	@Column(name = "valor_parcela")
	private BigDecimal valorParcela;

}
