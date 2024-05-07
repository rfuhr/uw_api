package br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscalgrupotrib;

import java.time.LocalDate;

import br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscal.ConfigMensagemFiscal;
import br.com.ultraworks.erp.api.fiscal.domain.grupotributacao.GrupoTributacao;
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
@Table(name = "config_mensagem_fiscal_grupo_trib")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
@UniqueValidationGroup({
    @UniqueValidation(fields = {"id"}, label = "Já existe Configuração de Mensagem Fiscal para este Grupo de Tributação")
})
public class ConfigMensagemFiscalGrupoTrib extends UWEntityBase {
	
	@Id
	@SequenceGenerator(name = "configMensagemFiscalGrupoTribSeq", sequenceName = "seq_config_mensagem_fiscal_grupo_trib", allocationSize = 1)
	@GeneratedValue(generator = "configMensagemFiscalGrupoTribSeq")
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "config_mensagem_fiscal_id")
	private ConfigMensagemFiscal configMensagemFiscal;
	
	@OneToOne
	@JoinColumn(name = "grupo_tributacao_id")
	private GrupoTributacao grupoTributacao;
	
	@Column(name = "data_inicio_vigencia")
	private LocalDate dataInicioVigencia;

	@Column(name = "data_final_vigencia")
	private LocalDate dataFinalVigencia;

}
