package br.com.ultraworks.erp.api.fiscal.domain.documentoparcela;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.com.ultraworks.erp.api.fiscal.domain.documento.Documento;
import br.com.ultraworks.erp.api.fiscal.domain.meiopagamento.MeioPagamento;
import br.com.ultraworks.erp.api.fiscal.domain.meiopagamento.MeioPagamentoConverter;
import br.com.ultraworks.erp.api.tabela.domain.indicadorformapagamento.IndicadorFormaPagamento;
import br.com.ultraworks.erp.api.tabela.domain.indicadorformapagamento.IndicadorFormaPagamentoConverter;
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
@Table(name = "documento_parcela")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
public class DocumentoParcela extends UWEntityBase {
	
	@Id
	@SequenceGenerator(name = "documentoParcelaSeq", sequenceName = "seq_documento_parcela", allocationSize = 1)
	@GeneratedValue(generator = "documentoParcelaSeq")
	private Long id;

	@OneToOne
	@JoinColumn(name = "documento_id")
	@JsonBackReference
	private Documento documento;
	
	private int numero;

	@Column(name = "data_vencimento")
	private LocalDate dataVencimento;

	private BigDecimal valor;
	
	@Convert(converter = MeioPagamentoConverter.class)
	@Column(name = "meio_pagamento")
	private MeioPagamento meioPagamento;

	@Convert(converter = IndicadorFormaPagamentoConverter.class)
	@Column(name = "indicador_forma_pagamento")
	private IndicadorFormaPagamento indicadorFormaPagamento;
	
}
