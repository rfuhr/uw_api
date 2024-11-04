package br.com.ultraworks.erp.api.configuracao.domain.configsistemacompra;

import br.com.ultraworks.erp.api.configuracao.domain.configsistema.ConfigSistema;
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
@Table(name = "config_sistema_compra")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class ConfigSistemaCompra extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "configSistemaCompraSeq", sequenceName = "seq_config_sistema_compra", allocationSize = 1)
	@GeneratedValue(generator = "configSistemaCompraSeq")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "config_sistema_id")
	private ConfigSistema configSistema;

	@OneToOne
	@JoinColumn(name = "tipo_documento_solicitacao")
	private TipoDocumento tipoDocumentoSolicitacao;

	@OneToOne
	@JoinColumn(name = "tipo_documento_cotacao")
	private TipoDocumento tipoDocumentoCotacao;

}
