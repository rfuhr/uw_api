package br.com.ultraworks.erp.api.agricola.domain.itemclassificacaoagricola;

import java.time.LocalDate;

import br.com.ultraworks.erp.api.agricola.domain.basecalculoagricola.BaseCalculoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.grupoclassificacaoagricola.GrupoClassificacaoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.tipocalculoagricola.TipoCalculoAgricola;
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
@Table(name = "item_classificacao_agricola")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
@UniqueValidationGroup({
    @UniqueValidation(fields = {"nome"}, label = "Já existe um item de classificação com este nome"),
    @UniqueValidation(fields = {"codigo"}, label = "Já existe um item de classificação com este código")
})
public class ItemClassificacaoAgricola extends UWEntityBase {
	
	@Id
	@SequenceGenerator(name = "itemClassificacaoAgricolaSeq", sequenceName = "seq_item_classificacao_agricola", allocationSize = 1)
	@GeneratedValue(generator = "itemClassificacaoAgricolaSeq")
	private Long id;

	@OneToOne
	@JoinColumn(name = "grupo_classificacao_agricola_id")
	private GrupoClassificacaoAgricola grupoClassificacaoAgricola;
	
	private Long codigo;
	private String nome;
	
	@OneToOne
	@JoinColumn(name = "tipo_calculo_agricola_id_romaneio")
	private TipoCalculoAgricola tipoCalculoAgricolaRomaneio;
	
	
	
	@Column(name = "data_inicio_vigencia")
	private LocalDate dataInicioVigencia;

	@Column(name = "data_final_vigencia")
	private LocalDate dataFinalVigencia;
	
}
