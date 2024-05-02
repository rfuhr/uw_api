package br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscalsituactrib;

import java.time.LocalDate;

import br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscal.ConfigMensagemFiscal;
import br.com.ultraworks.erp.api.fiscal.domain.situacaotributaria.SituacaoTributaria;
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
@Table(name = "config_mensagem_fiscal_situac_trib")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
@UniqueValidationGroup({
    @UniqueValidation(fields = {"situacao_tributaria_id"}, label = "Já existe Configuração de Mensagem Fiscal para esta está Situação Tributária")
})
public class ConfigMensagemFiscalSituacTrib extends UWEntityBase {
	
	@Id
	@SequenceGenerator(name = "configMensagemFiscalSituacTribSeq", sequenceName = "seq_config_mensagem_fiscal_situac_trib", allocationSize = 1)
	@GeneratedValue(generator = "configMensagemFiscalSituacTribSeq")
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "config_mensagem_fiscal_id")
	private ConfigMensagemFiscal configMensagemFiscal;
	
	@OneToOne
	@JoinColumn(name = "situacao_tributaria_id")
	private SituacaoTributaria situacaoTributaria;
	
	@Column(name = "data_inicio_vigencia")
	private LocalDate dataInicioVigencia;

	@Column(name = "data_final_vigencia")
	private LocalDate dataFinalVigencia;

}
