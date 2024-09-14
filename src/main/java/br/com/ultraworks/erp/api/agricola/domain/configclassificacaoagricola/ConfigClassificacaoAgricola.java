package br.com.ultraworks.erp.api.agricola.domain.configclassificacaoagricola;

import java.math.BigDecimal;

import br.com.ultraworks.erp.api.agricola.domain.safra.Safra;
import br.com.ultraworks.erp.api.agricola.domain.tipoclassificacaoagricola.TipoClassificacaoAgricola;
import br.com.ultraworks.erp.api.estoque.domain.item.Item;
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
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "config_classificacao_agricola")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class ConfigClassificacaoAgricola extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "configClassificacaoAgricolaSeq", sequenceName = "seq_config_classificacao_agricola", allocationSize = 1)
	@GeneratedValue(generator = "configClassificacaoAgricolaSeq")
	private Long id;

	@OneToOne
	@JoinColumn(name = "item_id")
	private Item item;

	@OneToOne
	@JoinColumn(name = "tipo_classificacao_agricola_id")
	private TipoClassificacaoAgricola tipoClassificacaoAgricola;

	@OneToOne
	@JoinColumn(name = "safra_id")
	private Safra safra;

	@Column(name = "faixa_inicial")
	private BigDecimal faixaInicial;

	@Column(name = "faixa_final")
	private BigDecimal faixaFinal;

	@Column(name = "desconto")
	private boolean desconto;

	@Column(name = "percentual_desconto")
	private BigDecimal percentualDesconto;
}
