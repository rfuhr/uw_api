package br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscal;

import java.time.LocalDate;
import java.util.List;

import br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscalconfigfiscal.ConfigMensagemFiscalConfigFiscal;
import br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscalgrupotrib.ConfigMensagemFiscalGrupoTrib;
import br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscalitem.ConfigMensagemFiscalItem;
import br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscaloperinterna.ConfigMensagemFiscalOperInterna;
import br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscalsituactrib.ConfigMensagemFiscalSituacTrib;
import br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscaltipoincentfiscal.ConfigMensagemFiscalTipoIncentFiscal;
import br.com.ultraworks.erp.api.fiscal.domain.mensagemfiscal.MensagemFiscal;
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
@Table(name = "config_mensagem_fiscal")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
@UniqueValidationGroup({
    @UniqueValidation(fields = {"id"}, label = "Já existe Configuração de Incentivo Fiscal com esse Identificador")
})
public class ConfigMensagemFiscal extends UWEntityBase {
	
	@Id
	@SequenceGenerator(name = "configMensagemFiscalSeq", sequenceName = "seq_config_mensagem_fiscal", allocationSize = 1)
	@GeneratedValue(generator = "configMensagemFiscalSeq")
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "mensagem_fiscal_id")
	private MensagemFiscal mensagemFiscal;
	
	@Column(name = "data_inicio_vigencia")
	private LocalDate dataInicioVigencia;

	@Column(name = "data_final_vigencia")
	private LocalDate dataFinalVigencia;
	
	@Transient
	private List<ConfigMensagemFiscalConfigFiscal> configMensagemFiscalConfigFiscals;
	
	@Transient
	private List<ConfigMensagemFiscalSituacTrib> configMensagemFiscalSituacTribs;
	
	@Transient
	private List<ConfigMensagemFiscalItem> configMensagemFiscalItems;
	
	@Transient
	private List<ConfigMensagemFiscalGrupoTrib> configMensagemFiscalGrupoTribs;
	
	@Transient
	private List<ConfigMensagemFiscalOperInterna> configMensagemFiscalOperInternas;
	
	@Transient
	private List<ConfigMensagemFiscalTipoIncentFiscal> configMensagemFiscalTipoIncentFiscals;

}
