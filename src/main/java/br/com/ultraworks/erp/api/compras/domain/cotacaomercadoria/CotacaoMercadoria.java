package br.com.ultraworks.erp.api.compras.domain.cotacaomercadoria;

import java.time.LocalDate;
import java.util.List;

import br.com.ultraworks.erp.api.compras.domain.cotacaomercadoriaparceiro.CotacaoMercadoriaParceiro;
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
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cotacao_mercadoria")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class CotacaoMercadoria extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "cotacaoMercadoriaSeq", sequenceName = "seq_cotacao_mercadoria", allocationSize = 1)
	@GeneratedValue(generator = "cotacaoMercadoriaSeq")
	private Long id;

	@Column(name = "numero")
	private Long numero;

	@OneToOne
	@JoinColumn(name = "depto_cotacao_id")
	private Departamento departamentoCotacao;

	@Column(name = "data_cotacao")
	private LocalDate dataCotacao;

	@Transient
	private List<CotacaoMercadoriaParceiro> parceiros;
}
