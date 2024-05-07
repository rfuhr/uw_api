package br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscaltipoincentfiscal;

import java.time.LocalDate;

import br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscal.ConfigMensagemFiscal;
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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "config_mensagem_fiscal_tipo_incent_fiscal")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
@UniqueValidationGroup({
    @UniqueValidation(fields = {"id"}, label = "Já existe Configuração de Mensagem Fiscal para este Tipo de Incentivo Fiscal")
})
public class ConfigMensagemFiscalTipoIncentFiscal extends UWEntityBase {
	
	@Id
	@SequenceGenerator(name = "configMensagemFiscalTipoIncentFiscalSeq", sequenceName = "seq_config_mensagem_fiscal_tipo_incent_fiscal", allocationSize = 1)
	@GeneratedValue(generator = "configMensagemFiscalTipoIncentFiscalSeq")
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "config_mensagem_fiscal_id")
	private ConfigMensagemFiscal configMensagemFiscal;
	
	@OneToOne
	@JoinColumn(name = "tipo_incentivo_fiscal_id")
	private TipoIncentivoFiscal tipoIncentivoFiscal;
	
	@Column(name = "data_inicio_vigencia")
	private LocalDate dataInicioVigencia;

	@Column(name = "data_final_vigencia")
	private LocalDate dataFinalVigencia;

}
