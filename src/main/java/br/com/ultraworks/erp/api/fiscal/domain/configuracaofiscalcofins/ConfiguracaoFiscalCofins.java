package br.com.ultraworks.erp.api.fiscal.domain.configuracaofiscalcofins;

import java.math.BigDecimal;

import br.com.ultraworks.erp.api.fiscal.domain.configuracaofiscal.ConfiguracaoFiscal;
import br.com.ultraworks.erp.api.fiscal.domain.modalidadebasecalculo.ModalidadeBaseCalculo;
import br.com.ultraworks.erp.api.fiscal.domain.modalidadebasecalculo.ModalidadeBaseCalculoConverter;
import br.com.ultraworks.erp.api.fiscal.domain.situacaotributaria.SituacaoTributaria;
import br.com.ultraworks.erp.api.fiscal.domain.tipocalculo.TipoCalculo;
import br.com.ultraworks.erp.api.fiscal.domain.tipocalculo.TipoCalculoConverter;
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
@Table(name = "configuracao_fiscal_cofins")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
public class ConfiguracaoFiscalCofins extends UWEntityBase {
	
	@Id
	@SequenceGenerator(name = "configuracaoFiscalCofinsSeq", sequenceName = "seq_configuracao_fiscal_cofins", allocationSize = 1)
	@GeneratedValue(generator = "configuracaoFiscalCofinsSeq")
	private Long id;

	@OneToOne
	@JoinColumn(name = "configuracao_fiscal_id")
	private ConfiguracaoFiscal configuracaoFiscal;
	
	@OneToOne
	@JoinColumn(name = "situacao_tributaria_id")
	private SituacaoTributaria situacaoTributaria;
	
	@Convert(converter = ModalidadeBaseCalculoConverter.class)
	@Column(name = "modalidade_base_calculo")
	private ModalidadeBaseCalculo modalidadeBaseCalculo;
	
	@Column(name = "aliquota")
	private BigDecimal aliquota;
	
	@Convert(converter = TipoCalculoConverter.class)
	@Column(name = "tipo_calculo")
	private TipoCalculo tipoCalculo;
	
	@Column(name = "aliquota_st")
	private BigDecimal aliquotaST;
	
	@Convert(converter = TipoCalculoConverter.class)
	@Column(name = "tipo_calculo_st")
	private TipoCalculo tipoCalculoST;

}
