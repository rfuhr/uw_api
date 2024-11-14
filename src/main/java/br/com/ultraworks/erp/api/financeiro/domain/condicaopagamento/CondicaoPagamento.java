package br.com.ultraworks.erp.api.financeiro.domain.condicaopagamento;

import br.com.ultraworks.erp.api.financeiro.domain.tipocondicaopagamento.TipoCondicaoPagamento;
import br.com.ultraworks.erp.api.financeiro.domain.tipocondicaopagamento.TipoCondicaoPagamentoConverter;
import br.com.ultraworks.erp.api.tabela.domain.indicadorformapagamento.IndicadorFormaPagamento;
import br.com.ultraworks.erp.api.tabela.domain.indicadorformapagamento.IndicadorFormaPagamentoConverter;
import br.com.ultraworks.erp.core.entity.UWEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
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
@Table(name = "condicao_pagamento")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
public class CondicaoPagamento extends UWEntityBase {
	
	@Id
	@SequenceGenerator(name = "condicaoPagamentoSeq", sequenceName = "seq_condicao_pagamento", allocationSize = 1)
	@GeneratedValue(generator = "condicaoPagamentoSeq")
	private Long id;

	private int codigo;
	private String nome;
	private String descricao;
	
	@Convert(converter = IndicadorFormaPagamentoConverter.class)
	@Column(name = "indicador_forma_pagamento")
	private IndicadorFormaPagamento indicadorFormaPagamento;
	
	@Column(name = "possui_entrada")
	private boolean possuiEntrada;
	
	@Column(name = "quantidade_parcelas")
	private Integer quantidadeParcelas;
	
	@Convert(converter = TipoCondicaoPagamentoConverter.class)
	@Column(name = "tipo_condicao_pagamento")
	private TipoCondicaoPagamento tipoCondicaoPagamento;
	
	@Column(name = "dia_vencimento")
	private Integer diaVencimento;
	
	@Column(name = "dias_intervalo")
	private Integer diasIntervalo;
	
	@Column(name = "dias_divisao")
	private String diasDivisao;
	
	@Column(name = "composicao")
	private String composicao;
	
}
