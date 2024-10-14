package br.com.ultraworks.erp.api.agricola.domain.romaneioagricolaclassificacao;

import java.math.BigDecimal;

import br.com.ultraworks.erp.api.agricola.domain.basecalculoagricola.BaseCalculoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.basecalculoagricola.BaseCalculoAgricolaConverter;
import br.com.ultraworks.erp.api.agricola.domain.itemclassificacaoagricola.ItemClassificacaoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.romaneioagricola.RomaneioAgricola;
import br.com.ultraworks.erp.api.agricola.domain.subitemclassificacaoagricola.SubItemClassificacaoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.tipotaxaagricola.TipoTaxaAgricola;
import br.com.ultraworks.erp.api.agricola.domain.tipotaxaagricola.TipoTaxaAgricolaConverter;
import br.com.ultraworks.erp.core.entity.UWEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
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
@Table(name = "romaneio_agricola_classificacao")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class RomaneioAgricolaClassificacao extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "romaneioAgricolaClassificacaoSeq", sequenceName = "seq_romaneio_agricola_classificacao", allocationSize = 1)
	@GeneratedValue(generator = "romaneioAgricolaClassificacaoSeq")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "romaneio_agricola_id")
	private RomaneioAgricola romaneioAgricola;

	@OneToOne
	@JoinColumn(name = "item_classificacao_agricola_id")
	private ItemClassificacaoAgricola itemClassificacaoAgricola;

	@OneToOne
	@JoinColumn(name = "sub_item_classificacao_agricola_id")
	private SubItemClassificacaoAgricola subItemClassificacaoAgricola;

	@Convert(converter = BaseCalculoAgricolaConverter.class)
	@Column(name = "base_calculo_agricola")
	private BaseCalculoAgricola baseCalculoAgricola;

	@Column(name = "valor_base_calculo")
	private BigDecimal valorBaseCalculo;

	@Column(name = "valor_base_calculo_compl")
	private BigDecimal valorBaseCalculoComplementar;

	@Column(name = "valor")
	private BigDecimal valor;

	@Column(name = "indicador_dc")
	private String indicadorDc;

	@Column(name = "ordem")
	private int ordem;

	@Column(name = "fator_calculo")
	private BigDecimal fatorCalculo;

	@Convert(converter = TipoTaxaAgricolaConverter.class)
	@Column(name = "tipo_taxa_agricola")
	private TipoTaxaAgricola tipoTaxaAgricola;
	
	@Column(name = "gerado")
	private boolean gerado;

	@OneToOne
	@JoinColumn(name = "sub_item_classificacao_agricola_origem_id")
	private SubItemClassificacaoAgricola subItemClassificacaoAgricolaOrigem;
}
