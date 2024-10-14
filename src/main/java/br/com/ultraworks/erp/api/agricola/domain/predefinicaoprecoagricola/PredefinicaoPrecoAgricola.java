package br.com.ultraworks.erp.api.agricola.domain.predefinicaoprecoagricola;

import java.time.LocalDate;

import br.com.ultraworks.erp.api.agricola.domain.itemclassificacaoagricola.ItemClassificacaoAgricola;
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
@Table(name = "predefinicao_preco_agricola")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@UniqueValidationGroup({ @UniqueValidation(fields = { "nome" }, label = "Já existe uma Predefinição de Preço com este nome"),
		@UniqueValidation(fields = { "codigo" }, label = "Já existe uma Predefinição de Preço com este código") })
public class PredefinicaoPrecoAgricola extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "predefinicaoPrecoAgricolaSeq", sequenceName = "seq_predefinicao_preco_agricola", allocationSize = 1)
	@GeneratedValue(generator = "predefinicaoPrecoAgricolaSeq")
	private Long id;

	private Long codigo;
	private String nome;
	
	@OneToOne
	@JoinColumn(name = "item_class_1_id")
	private ItemClassificacaoAgricola itemClassificacaoAgricola1;
	
	@OneToOne
	@JoinColumn(name = "item_class_2_id")
	private ItemClassificacaoAgricola itemClassificacaoAgricola2;
	
	@OneToOne
	@JoinColumn(name = "item_class_3_id")
	private ItemClassificacaoAgricola itemClassificacaoAgricola3;
	
	@OneToOne
	@JoinColumn(name = "item_class_4_id")
	private ItemClassificacaoAgricola itemClassificacaoAgricola4;

	@Column(name = "data_inicio_vigencia")
	private LocalDate dataInicioVigencia;

	@Column(name = "data_final_vigencia")
	private LocalDate dataFinalVigencia;

}
