package br.com.ultraworks.erp.api.agricola.domain.finalidadecontratoagricola;

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
@Table(name = "finalidade_contrato_agricola")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@UniqueValidationGroup({
		@UniqueValidation(fields = { "codigo" }, label = "Já existe finalidade de contrato agrícola com este código"),
		@UniqueValidation(fields = { "nome" }, label = "Já existe finalidade de contrato agrícola com este nome"),
		@UniqueValidation(fields = { "sigla" }, label = "Já existe finalidade de contrato agrícola com esta sigla") })
public class FinalidadeContratoAgricola extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "finalidadeContratoAgricolaSeq", sequenceName = "seq_finalidade_contrato_agricola", allocationSize = 1)
	@GeneratedValue(generator = "finalidadeContratoAgricolaSeq")
	private Long id;

	private int codigo;
	private String nome;
	private String sigla;

	@Column(name = "data_inicio_vigencia")
	private LocalDate dataInicioVigencia;

	@Column(name = "data_final_vigencia")
	private LocalDate dataFinalVigencia;
}
