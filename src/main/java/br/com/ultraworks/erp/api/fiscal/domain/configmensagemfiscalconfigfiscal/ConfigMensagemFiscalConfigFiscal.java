package br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscalconfigfiscal;

import java.time.LocalDate;

import br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscal.ConfigMensagemFiscal;
import br.com.ultraworks.erp.api.fiscal.domain.configuracaofiscal.ConfiguracaoFiscal;
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
@Table(name = "config_mensagem_fiscal_config_fiscal")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
@UniqueValidationGroup({
    @UniqueValidation(fields = {"configuracao_fiscal_id"}, label = "Já existe Configuração de Mensagem Fiscal para esta está Configuração Fiscal")
})
public class ConfigMensagemFiscalConfigFiscal extends UWEntityBase {
	
	@Id
	@SequenceGenerator(name = "configMensagemFiscalConfigFiscalSeq", sequenceName = "seq_config_mensagem_fiscal_config_fiscal", allocationSize = 1)
	@GeneratedValue(generator = "configMensagemFiscalConfigFiscalSeq")
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "config_mensagem_fiscal_id")
	private ConfigMensagemFiscal configMensagemFiscal;
	
	@OneToOne
	@JoinColumn(name = "configuracao_fiscal_id")
	private ConfiguracaoFiscal configuracaoFiscal;
	
	@Column(name = "data_inicio_vigencia")
	private LocalDate dataInicioVigencia;

	@Column(name = "data_final_vigencia")
	private LocalDate dataFinalVigencia;

}
