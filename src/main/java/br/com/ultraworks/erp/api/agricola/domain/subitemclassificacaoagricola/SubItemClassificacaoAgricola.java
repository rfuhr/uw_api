package br.com.ultraworks.erp.api.agricola.domain.subitemclassificacaoagricola;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.ultraworks.erp.api.agricola.domain.itemclassificacaoagricola.ItemClassificacaoAgricola;
import br.com.ultraworks.erp.api.estoque.domain.item.Item;
import br.com.ultraworks.erp.core.annotation.UniqueValidation;
import br.com.ultraworks.erp.core.annotation.UniqueValidationGroup;
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
@Table(name = "sub_item_classificacao_agricola")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@UniqueValidationGroup({
		@UniqueValidation(fields = { "nome" }, label = "Já existe um sub. item de classificação com este nome") })
public class SubItemClassificacaoAgricola extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "subItemClassificacaoAgricolaSeq", sequenceName = "seq_sub_item_classificacao_agricola", allocationSize = 1)
	@GeneratedValue(generator = "subItemClassificacaoAgricolaSeq")
	private Long id;

	@OneToOne
	@JoinColumn(name = "item_id")
	private Item item;

	private String codigo;
	private String nome;
	@Column(name = "indice_referencia")
	private BigDecimal indiceReferencia;

	@OneToOne
	@JoinColumn(name = "item_classificacao_agricola_id")
	private ItemClassificacaoAgricola itemClassificacaoAgricola;

	@OneToOne
	@JoinColumn(name = "item_classificacao_agricola_gerado_id")
	private ItemClassificacaoAgricola itemClassificacaoAgricolaGerado;

	@OneToOne
	@JoinColumn(name = "sub_item_classificacao_agricola_gerado_id")
	private SubItemClassificacaoAgricola subItemClassificacaoAgricolaGerado;

	@Column(name = "data_inicio_vigencia")
	private LocalDate dataInicioVigencia;

	@Column(name = "data_final_vigencia")
	private LocalDate dataFinalVigencia;

}
