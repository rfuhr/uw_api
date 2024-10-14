package br.com.ultraworks.erp.api.agricola.domain.grupooperacaoagricola;

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
@Table(name = "grupo_operacao_agricola")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
@UniqueValidationGroup({
    @UniqueValidation(fields = {"nome"}, label = "Já existe um Grupo de Operação com este nome"),
    @UniqueValidation(fields = {"codigo"}, label = "Já existe um Grupo de Operação com este código")
})
public class GrupoOperacaoAgricola extends UWEntityBase {
	
	@Id
	@SequenceGenerator(name = "grupoOperacaoAgricolaSeq", sequenceName = "seq_grupo_operacao_agricola", allocationSize = 1)
	@GeneratedValue(generator = "grupoOperacaoAgricolaSeq")
	private Long id;

	private Long codigo;
	private String nome;
	
	@Column(name = "data_inicio_vigencia")
	private LocalDate dataInicioVigencia;

	@Column(name = "data_final_vigencia")
	private LocalDate dataFinalVigencia;
	
}