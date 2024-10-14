package br.com.ultraworks.erp.api.relacionamento.domain.parceiroRegraAtividade;

import java.time.LocalDate;

import br.com.ultraworks.erp.api.relacionamento.domain.parceiro.Parceiro;
import br.com.ultraworks.erp.api.tabela.domain.regraatividade.RegraAtividade;
import br.com.ultraworks.erp.core.entity.UWEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "parceiro_regra_atividade")
@Data
@EqualsAndHashCode(callSuper=false)
public class ParceiroRegraAtividade extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "parceiroRegraAtividadeSeq", sequenceName = "seq_parceiro_regra_atividade", allocationSize = 1)
	@GeneratedValue(generator = "parceiroRegraAtividadeSeq")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "parceiro_id")
	private Parceiro parceiro;
	
	@OneToOne
	@JoinColumn(name = "regra_atividade_id")
	private RegraAtividade regraAtividade;	
	
	@Column(name = "data_inicio_vigencia")
	private LocalDate dataInicioVigencia;

	@Column(name = "data_final_vigencia")
	private LocalDate dataFinalVigencia;	
}
