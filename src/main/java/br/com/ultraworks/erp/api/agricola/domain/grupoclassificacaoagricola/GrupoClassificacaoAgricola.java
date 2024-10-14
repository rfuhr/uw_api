package br.com.ultraworks.erp.api.agricola.domain.grupoclassificacaoagricola;

import java.time.LocalDate;

import br.com.ultraworks.erp.core.annotation.UniqueValidation;
import br.com.ultraworks.erp.core.annotation.UniqueValidationGroup;
import br.com.ultraworks.erp.core.entity.UWEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "grupo_classificacao_agricola")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@UniqueValidationGroup({ @UniqueValidation(fields = { "nome" }, label = "Já existe um Grupo de Classificação com este nome"),
		@UniqueValidation(fields = { "codigo" }, label = "Já existe um Grupo de Classificação com este código") })
public class GrupoClassificacaoAgricola extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "grupoClassificacaoAgricolaSeq", sequenceName = "seq_grupo_classificacao_agricola", allocationSize = 1)
	@GeneratedValue(generator = "grupoClassificacaoAgricolaSeq")
	private Long id;

	private Long codigo;
	private String nome;
	@Column(name = "data_inicio_vigencia")
	private LocalDate dataInicioVigencia;

	@Column(name = "data_final_vigencia")
	private LocalDate dataFinalVigencia;

}
