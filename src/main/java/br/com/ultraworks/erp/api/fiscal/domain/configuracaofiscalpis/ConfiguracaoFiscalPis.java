package br.com.ultraworks.erp.api.fiscal.domain.configuracaofiscalpis;

import java.math.BigDecimal;

import br.com.ultraworks.erp.api.fiscal.domain.configuracaofiscal.ConfiguracaoFiscal;
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
@Table(name = "configuracao_fiscal_pis")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
public class ConfiguracaoFiscalPis extends UWEntityBase {
	
	@Id
	@SequenceGenerator(name = "configuracaoFiscalPisSeq", sequenceName = "seq_configuracao_fiscal_pis", allocationSize = 1)
	@GeneratedValue(generator = "configuracaoFiscalPisSeq")
	private Long id;

	@OneToOne
	@JoinColumn(name = "configuracao_fiscal_id")
	private ConfiguracaoFiscal configuracaoFiscal;
	
	@OneToOne
	@JoinColumn(name = "situacao_tributaria_id")
	private SituacaoTributaria situacaoTributaria;
	
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
