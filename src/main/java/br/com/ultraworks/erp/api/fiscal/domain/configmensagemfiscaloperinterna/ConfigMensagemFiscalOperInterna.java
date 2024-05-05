package br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscaloperinterna;

import java.time.LocalDate;

import br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscal.ConfigMensagemFiscal;
import br.com.ultraworks.erp.api.tabela.domain.operacaointerna.OperacaoInterna;
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
@Table(name = "config_mensagem_fiscal_oper_interna")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
@UniqueValidationGroup({
    @UniqueValidation(fields = {"operacao_interna_id"}, label = "Já existe Configuração de Mensagem Fiscal para esta Operação Interna")
})
public class ConfigMensagemFiscalOperInterna extends UWEntityBase {
	
	@Id
	@SequenceGenerator(name = "configMensagemFiscalOperInternaSeq", sequenceName = "seq_config_mensagem_fiscal_oper_interna", allocationSize = 1)
	@GeneratedValue(generator = "configMensagemFiscalOperInternaSeq")
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "config_mensagem_fiscal_id")
	private ConfigMensagemFiscal configMensagemFiscal;
	
	@OneToOne
	@JoinColumn(name = "operacao_interna_id")
	private OperacaoInterna operacaoInterna;
	
	@Column(name = "data_inicio_vigencia")
	private LocalDate dataInicioVigencia;

	@Column(name = "data_final_vigencia")
	private LocalDate dataFinalVigencia;

}
