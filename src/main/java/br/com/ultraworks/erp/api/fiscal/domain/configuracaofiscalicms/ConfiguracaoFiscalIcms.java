package br.com.ultraworks.erp.api.fiscal.domain.configuracaofiscalicms;

import java.math.BigDecimal;

import br.com.ultraworks.erp.api.fiscal.domain.configuracaofiscal.ConfiguracaoFiscal;
import br.com.ultraworks.erp.api.fiscal.domain.modalidadebasecalculo.ModalidadeBaseCalculo;
import br.com.ultraworks.erp.api.fiscal.domain.modalidadebasecalculo.ModalidadeBaseCalculoConverter;
import br.com.ultraworks.erp.api.fiscal.domain.motivodesoneracao.MotivoDesoneracao;
import br.com.ultraworks.erp.api.fiscal.domain.situacaotributaria.SituacaoTributaria;
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
@Table(name = "configuracao_fiscal_icms")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
public class ConfiguracaoFiscalIcms extends UWEntityBase {
	
	@Id
	@SequenceGenerator(name = "configuracaoFiscalIcmsSeq", sequenceName = "seq_configuracao_fiscal_icms", allocationSize = 1)
	@GeneratedValue(generator = "configuracaoFiscalIcmsSeq")
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
	
	@OneToOne
	@JoinColumn(name = "motivo_desoneracao_id")
	private MotivoDesoneracao motivoDesoneracao;
	
	@Column(name = "reducao_base_calculo")
	private BigDecimal reducaoBaseCalculo;
	
	@Column(name = "soma_ipi_base_calculo")
	private boolean somaIpiBaseCalculo;
	
	@Column(name = "soma_frete_base_calculo")
	private boolean somaFreteBaseCalculo;
	
	@Column(name = "aliquota")
	private BigDecimal aliquota;
	
	@Column(name = "aliquota_credito")
	private BigDecimal aliquotaCredito;
	
	@Column(name = "diferencial_aliquota")
	private BigDecimal diferencialAliquota;
	
	@Convert(converter = ModalidadeBaseCalculoConverter.class)
	@Column(name = "modalidade_base_calculo_st")
	private ModalidadeBaseCalculo modalidadeBaseCalculoST;
	
	@OneToOne
	@JoinColumn(name = "motivo_desoneracao_st_id")
	private MotivoDesoneracao motivoDesoneracaoST;
	
	@Column(name = "reducao_base_calculo_st")
	private BigDecimal reducaoBaseCalculoST;
	
	@Column(name = "soma_ipi_base_calculo_st")
	private boolean somaIpiBaseCalculoST;
	
	@Column(name = "soma_frete_base_calculo_st")
	private boolean somaFreteBaseCalculoST;
	
	@Column(name = "aliquota_st")
	private BigDecimal aliquotaST;
	
	@Column(name = "margem_valor_agregado_st")
	private BigDecimal margemValorAgregadoST;
}
