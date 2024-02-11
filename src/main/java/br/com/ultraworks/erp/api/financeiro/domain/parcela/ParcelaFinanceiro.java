package br.com.ultraworks.erp.api.financeiro.domain.parcela;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.ultraworks.erp.api.financeiro.domain.titulo.Titulo;
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
@Table(name = "parcela_financeiro")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
public class ParcelaFinanceiro extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "parcelaSeq", sequenceName = "seq_parcela_financeiro", allocationSize = 1)
	@GeneratedValue(generator = "parcelaSeq")
	private Long id;

	@OneToOne
	@JoinColumn(name = "titulo_id")
	private Titulo titulo;

	@Column(name = "num_parcela")
	private int numParcela;

	@Column(name = "seq_parcela")
	private int seqParcela;

	@Column(name = "data_vencimento")
	private LocalDate dataVencimento;

	@Column(name = "valor_parcela")
	private BigDecimal valorParcela;

	@Column(name = "ult_seq_mvto")
	private int ultimaSeqMvto;
}
