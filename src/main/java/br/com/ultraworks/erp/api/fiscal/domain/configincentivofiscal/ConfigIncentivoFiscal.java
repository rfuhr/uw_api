package br.com.ultraworks.erp.api.fiscal.domain.configincentivofiscal;

import java.time.LocalDate;
import java.util.List;

import br.com.ultraworks.erp.api.fiscal.domain.configincentivofiscalparceiro.ConfigIncentivoFiscalParceiro;
import br.com.ultraworks.erp.api.fiscal.domain.tipoincentivofiscal.TipoIncentivoFiscal;
import br.com.ultraworks.erp.core.annotation.UniqueValidation;
import br.com.ultraworks.erp.core.annotation.UniqueValidationGroup;
import br.com.ultraworks.erp.core.entity.UWEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "config_incentivo_fiscal")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
@UniqueValidationGroup({
    @UniqueValidation(fields = {"id"}, label = "Já existe Configuração de Incentivo Fiscal com esse Identificador")
})
public class ConfigIncentivoFiscal extends UWEntityBase {
	
	@Id
	@SequenceGenerator(name = "configIncentivoFiscalSeq", sequenceName = "seq_config_incentivo_fiscal", allocationSize = 1)
	@GeneratedValue(generator = "configIncentivoFiscalSeq")
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "tipo_incentivo_fiscal_id")
	private TipoIncentivoFiscal tipoIncentivoFiscal;
	
	@Column(name = "data_inicio_vigencia")
	private LocalDate dataInicioVigencia;

	@Column(name = "data_final_vigencia")
	private LocalDate dataFinalVigencia;
	
	@Transient
	private List<ConfigIncentivoFiscalParceiro> configIncentivoFiscalParceiros;

}
