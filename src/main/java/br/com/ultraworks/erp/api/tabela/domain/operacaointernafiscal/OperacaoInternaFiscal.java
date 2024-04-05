package br.com.ultraworks.erp.api.tabela.domain.operacaointernafiscal;

import br.com.ultraworks.erp.api.tabela.domain.operacaointerna.OperacaoInterna;
import br.com.ultraworks.erp.core.entity.UWEntityBase;
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
@Table(name = "operacao_interna_fiscal")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class OperacaoInternaFiscal extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "opInternaFiscalSeq", sequenceName = "seq_operacao_interna_fiscal", allocationSize = 1)
	@GeneratedValue(generator = "opInternaFiscalSeq")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "operacao_interna_id")
	private OperacaoInterna operacaoInterna;

}
