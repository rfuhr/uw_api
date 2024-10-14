package br.com.ultraworks.erp.api.agricola.domain.taxacalculoagricola;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.ultraworks.erp.api.agricola.domain.tipocalculoagricola.TipoCalculoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.tipotaxaagricola.TipoTaxaAgricola;
import br.com.ultraworks.erp.api.agricola.domain.tipotaxaagricola.TipoTaxaAgricolaConverter;
import br.com.ultraworks.erp.api.estoque.domain.item.Item;
import br.com.ultraworks.erp.api.tabela.domain.regraatividade.RegraAtividade;
import br.com.ultraworks.erp.core.entity.UWEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "taxa_calculo_agricola")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class TaxaCalculoAgricola extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "taxaCalculoAgricolaSeq", sequenceName = "seq_taxa_calculo_agricola", allocationSize = 1)
	@GeneratedValue(generator = "taxaCalculoAgricolaSeq")
	private Long id;

	@OneToOne
	@JoinColumn(name = "item_id")
	private Item item;

	@OneToOne
	@JoinColumn(name = "tipo_calculo_agricola_id")
	private TipoCalculoAgricola tipoCalculoAgricola;

	@OneToOne
	@JoinColumn(name = "regra_atividade_id")
	private RegraAtividade regraAtividade;

	@Column(name = "faixa_limite")
	private BigDecimal faixaLimite;

	@Convert(converter = TipoTaxaAgricolaConverter.class)
	@Column(name = "tipo_taxa_agricola")
	private TipoTaxaAgricola tipoTaxaAgricola;

	@Column(name = "fator_calculo")
	private BigDecimal fatorCalculo;

	@Column(name = "data_inicio_vigencia")
	private LocalDate dataInicioVigencia;

	@Column(name = "data_final_vigencia")
	private LocalDate dataFinalVigencia;
}
