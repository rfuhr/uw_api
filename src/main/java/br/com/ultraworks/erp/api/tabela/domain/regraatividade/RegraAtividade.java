package br.com.ultraworks.erp.api.tabela.domain.regraatividade;

import java.time.LocalDate;

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
@Table(name = "regra_atividade")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
public class RegraAtividade extends UWEntityBase {
	
	@Id
	@SequenceGenerator(name = "regraAtividadeSeq", sequenceName = "seq_regra_atividade", allocationSize = 1)
	@GeneratedValue(generator = "regraAtividadeSeq")
	private Long id;

	private Long codigo;
	private String nome;
	@Column(name = "data_inicio_vigencia")
	private LocalDate dataInicioVigencia;

	@Column(name = "data_final_vigencia")
	private LocalDate dataFinalVigencia;	
}
