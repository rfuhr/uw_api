package br.com.ultraworks.erp.api.financeiro.domain.tipooperacaofinanceira;

import br.com.ultraworks.erp.api.financeiro.domain.operacaocxbco.OperacaoCaixaBanco;
import br.com.ultraworks.erp.api.financeiro.domain.tipointegracaocaixabanco.TipoIntegracaoCaixaBanco;
import br.com.ultraworks.erp.api.financeiro.domain.tipointegracaocaixabanco.TipoIntegracaoCaixaBancoConverter;
import br.com.ultraworks.erp.core.entity.UWEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tipo_operacao_financeira")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
public class TipoOperacaoFinanceira extends UWEntityBase {
	
	@Id
	@SequenceGenerator(name = "tipoOperacaoFinanceiraSeq", sequenceName = "seq_tipo_operacao_financeira", allocationSize = 1)
	@GeneratedValue(generator = "tipoOperacaoFinanceiraSeq")
	private Long id;

	private String nome;
	private String sigla;
	
	@Column(name = "idn_cria_titulo")
	private boolean criaTitulo;
	
	@Column(name = "idn_baixa_titulo")
	private boolean baixaTitulo;
	
	@Column(name = "idn_sel_baixa")
	private boolean selecionaBaixa;
	
	@Column(name = "idn_sel_negociacao")
	private boolean selecionaNegociacao;
	
	@Convert(converter = TipoIntegracaoCaixaBancoConverter.class)
	@Column(name = "integra_cxbco")
	private TipoIntegracaoCaixaBanco tipoIntegracaoCaixaBanco;
	
	@OneToOne
	@JoinColumn(name = "operacao_cxbco_id")
	private OperacaoCaixaBanco operacaoCaixaBanco;	
	
	@Column(name = "idn_sel_substcart")
	private boolean selecionaSubstituicaoCarteira;
}
