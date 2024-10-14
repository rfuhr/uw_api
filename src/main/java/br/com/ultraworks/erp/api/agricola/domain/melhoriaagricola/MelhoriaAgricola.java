package br.com.ultraworks.erp.api.agricola.domain.melhoriaagricola;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.ultraworks.erp.api.agricola.domain.itemclassificacaoagricola.ItemClassificacaoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.subitemclassificacaoagricola.SubItemClassificacaoAgricola;
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
@Table(name = "melhoria_agricola")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class MelhoriaAgricola extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "melhoriaAgricolaSeq", sequenceName = "seq_melhoria_agricola", allocationSize = 1)
	@GeneratedValue(generator = "melhoriaAgricolaSeq")
	private Long id;

	@OneToOne
	@JoinColumn(name = "item_id")
	private Item item;

	@OneToOne
	@JoinColumn(name = "item_classificacao_principal")
	private ItemClassificacaoAgricola itemClassificacaoAgricolaPrincipal;

	@OneToOne
	@JoinColumn(name = "sub_item_classificacao_principal")
	private SubItemClassificacaoAgricola subItemClassificacaoAgricolaPrincipal;

	@OneToOne
	@JoinColumn(name = "item_classificacao_secundario")
	private ItemClassificacaoAgricola itemClassificacaoAgricolaSecundario;

	@OneToOne
	@JoinColumn(name = "sub_item_classificacao_secundario")
	private SubItemClassificacaoAgricola subItemClassificacaoAgricolaSecundario;

	@Column(name = "valor_adiciona_principal")
	private BigDecimal valorAdicionaPrincipal;

	@Column(name = "valor_adiciona_secundario")
	private BigDecimal valorAdicionaSecundario;

	@OneToOne
	@JoinColumn(name = "item_classificacao_gerado")
	private ItemClassificacaoAgricola itemClassificacaoAgricolaGerado;

	@OneToOne
	@JoinColumn(name = "sub_item_classificacao_gerado")
	private SubItemClassificacaoAgricola subItemClassificacaoAgricolaGerado;

	@Column(name = "data_inicio_vigencia")
	private LocalDate dataInicioVigencia;

	@Column(name = "data_final_vigencia")
	private LocalDate dataFinalVigencia;
}
