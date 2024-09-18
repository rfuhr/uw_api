package br.com.ultraworks.erp.api.tabela.domain.operacaointernaagricola;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.com.ultraworks.erp.api.tabela.domain.operacaointerna.OperacaoInterna;
import br.com.ultraworks.erp.core.entity.UWEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "operacao_interna_agricola")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class OperacaoInternaAgricola extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "opInternaAgricolaSeq", sequenceName = "seq_operacao_interna_agricola", allocationSize = 1)
	@GeneratedValue(generator = "opInternaAgricolaSeq")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "operacao_interna_id")
	@JsonBackReference
	private OperacaoInterna operacaoInterna;

	@Column(name = "seleciona_pesagem")
	private boolean selecionaPesagem;
}
