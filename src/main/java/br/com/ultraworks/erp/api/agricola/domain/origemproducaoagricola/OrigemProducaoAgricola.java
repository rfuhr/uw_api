package br.com.ultraworks.erp.api.agricola.domain.origemproducaoagricola;

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
@Table(name = "origem_producao_agricola")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@UniqueValidationGroup({ @UniqueValidation(fields = { "nome" }, label = "Já existe uma Origem de Produção com este nome"),
		@UniqueValidation(fields = { "codigo" }, label = "Já existe uma Origem de Produção com este código") })
public class OrigemProducaoAgricola extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "origemProducaoAgricolaSeq", sequenceName = "seq_origem_producao_agricola", allocationSize = 1)
	@GeneratedValue(generator = "origemProducaoAgricolaSeq")
	private Long id;

	private Long codigo;
	private String nome;
	@Column(name = "data_inicio_vigencia")
	private LocalDate dataInicioVigencia;

	@Column(name = "data_final_vigencia")
	private LocalDate dataFinalVigencia;

}
