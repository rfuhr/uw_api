package br.com.ultraworks.erp.api.tabela.domain.operacaointernafiscalcfop;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.com.ultraworks.erp.api.fiscal.domain.cfop.Cfop;
import br.com.ultraworks.erp.api.tabela.domain.operacaointernafiscal.OperacaoInternaFiscal;
import br.com.ultraworks.erp.core.entity.UWEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "operacao_interna_fiscal_cfop")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class OperacaoInternaFiscalCfop extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "opInternaFiscalCfopSeq", sequenceName = "seq_operacao_interna_fiscal_cfop", allocationSize = 1)
	@GeneratedValue(generator = "opInternaFiscalCfopSeq")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "operacao_interna_fiscal_id")
	@JsonBackReference
	private OperacaoInternaFiscal operacaoInternaFiscal;

	@OneToOne
	@JoinColumn(name = "cfop_id")
	private Cfop cfop;

}
