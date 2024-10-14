package br.com.ultraworks.erp.api.configuracao.domain.configsistemaagricola;

import br.com.ultraworks.erp.api.agricola.domain.tipoprecoagricola.TipoPrecoAgricola;
import br.com.ultraworks.erp.api.configuracao.domain.configsistema.ConfigSistema;
import br.com.ultraworks.erp.api.tabela.domain.operacaointerna.OperacaoInterna;
import br.com.ultraworks.erp.api.tabela.domain.tipodocumento.TipoDocumento;
import br.com.ultraworks.erp.core.entity.UWEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "config_sistema_agricola")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class ConfigSistemaAgricola extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "configSistemaAgricolaSeq", sequenceName = "seq_config_sistema_agricola", allocationSize = 1)
	@GeneratedValue(generator = "configSistemaAgricolaSeq")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "config_sistema_id")
	private ConfigSistema configSistema;

	@OneToOne
	@JoinColumn(name = "tipo_documento_rom")
	private TipoDocumento tipoDocumentoRomaneio;
	
	@OneToOne
	@JoinColumn(name = "operacao_interna_fixacao_id")
	private OperacaoInterna operacaoInternaFixacao;
	
}
