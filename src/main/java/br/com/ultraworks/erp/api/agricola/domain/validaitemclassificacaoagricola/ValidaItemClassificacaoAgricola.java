package br.com.ultraworks.erp.api.agricola.domain.validaitemclassificacaoagricola;

import java.time.LocalDate;

import br.com.ultraworks.erp.api.agricola.domain.itemclassificacaoagricola.ItemClassificacaoAgricola;
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
@Table(name = "valida_item_classificacao_agricola")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
public class ValidaItemClassificacaoAgricola extends UWEntityBase {
	
	@Id
	@SequenceGenerator(name = "validaItemClassificacaoAgricolaSeq", sequenceName = "seq_valida_item_classificacao_agricola", allocationSize = 1)
	@GeneratedValue(generator = "validaItemClassificacaoAgricolaSeq")
	private Long id;

	@OneToOne
	@JoinColumn(name = "item_id")
	private Item item;
	
	@OneToOne
	@JoinColumn(name = "item_classificacao_agricola_id")
	private ItemClassificacaoAgricola itemClassificacaoAgricola;
	
	@Column(name = "tipo_uso_romaneio")
	private String tipoUsoRomaneio;
	
	@Column(name = "obrigatorio")
	private boolean obrigatorio;
	
	@Column(name = "ordem_tela")
	private int ordemTela;
	
	@Column(name = "data_inicio_vigencia")
	private LocalDate dataInicioVigencia;

	@Column(name = "data_final_vigencia")
	private LocalDate dataFinalVigencia;	
}
