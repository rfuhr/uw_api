package br.com.ultraworks.erp.api.fiscal.domain.configincentivofiscalparceiro;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.ultraworks.erp.api.fiscal.domain.configincentivofiscal.ConfigIncentivoFiscal;
import br.com.ultraworks.erp.api.fiscal.domain.tipotributo.TipoTributo;
import br.com.ultraworks.erp.api.fiscal.domain.tipotributo.TipoTributoConverter;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocal.ParceiroLocal;
import br.com.ultraworks.erp.api.tabela.domain.uf.Uf;
import br.com.ultraworks.erp.core.annotation.UniqueValidation;
import br.com.ultraworks.erp.core.annotation.UniqueValidationGroup;
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
@Table(name = "config_incentivo_fiscal_parceiro")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
@UniqueValidationGroup({
    @UniqueValidation(fields = {"parceiroLocalId"}, label = "Já existe Configuração de Incentivo Fiscal para este Parceiro")
})
public class ConfigIncentivoFiscalParceiro extends UWEntityBase {
	
	@Id
	@SequenceGenerator(name = "configIncentivoFiscalParceiroSeq", sequenceName = "seq_config_incentivo_fiscal_parceiro", allocationSize = 1)
	@GeneratedValue(generator = "configIncentivoFiscalParceiroSeq")
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "config_incentivo_fiscal_id")
	private ConfigIncentivoFiscal configIncentivoFiscal;
	
	@OneToOne
	@JoinColumn(name = "parceiro_local_id")
	private ParceiroLocal parceiroLocal;
	
	@Column(name = "data_inicio_vigencia")
	private LocalDate dataInicioVigencia;

	@Column(name = "data_final_vigencia")
	private LocalDate dataFinalVigencia;
	
	@Convert(converter = TipoTributoConverter.class)
	@Column(name = "tipo_tributo")
	private TipoTributo tipoTributo;
	
	@OneToOne
	@JoinColumn(name = "uf_id")
	private Uf uf;
	
	@Column(name = "percentual_aproveitamento")
	private BigDecimal percentualAproveitamento;

}
