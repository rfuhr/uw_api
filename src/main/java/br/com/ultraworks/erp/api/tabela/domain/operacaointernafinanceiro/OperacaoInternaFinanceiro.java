package br.com.ultraworks.erp.api.tabela.domain.operacaointernafinanceiro;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.com.ultraworks.erp.api.agricola.domain.identificacaodocumentoagricola.IdentificacaoDocumentoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.tipocontratoagricola.TipoContratoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.tipoprecoagricola.TipoPrecoAgricola;
import br.com.ultraworks.erp.api.financeiro.domain.indicefinanceiro.IndiceFinanceiro;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocal.ParceiroLocal;
import br.com.ultraworks.erp.api.tabela.domain.operacaointerna.OperacaoInterna;
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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "operacao_interna_financeiro")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class OperacaoInternaFinanceiro extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "opInternaFinanceiroSeq", sequenceName = "seq_operacao_interna_financeiro", allocationSize = 1)
	@GeneratedValue(generator = "opInternaFinanceiroSeq")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "operacao_interna_id")
	@JsonBackReference
	private OperacaoInterna operacaoInterna;

	@OneToOne
	@JoinColumn(name = "indice_financeiro_padrao_id")
	private IndiceFinanceiro indiceFinanceiroPadrao;
	
}
