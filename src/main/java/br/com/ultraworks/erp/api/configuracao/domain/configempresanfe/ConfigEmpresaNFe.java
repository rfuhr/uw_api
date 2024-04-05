package br.com.ultraworks.erp.api.configuracao.domain.configempresanfe;

import br.com.ultraworks.erp.api.configuracao.domain.configempresa.ConfigEmpresa;
import br.com.ultraworks.erp.api.configuracao.domain.tipoambiente.TipoAmbiente;
import br.com.ultraworks.erp.api.configuracao.domain.tipoambiente.TipoAmbienteConverter;
import br.com.ultraworks.erp.api.configuracao.domain.tipocertificado.TipoCertificado;
import br.com.ultraworks.erp.api.configuracao.domain.tipocertificado.TipoCertificadoConverter;
import br.com.ultraworks.erp.api.configuracao.domain.tipoemissao.TipoEmissao;
import br.com.ultraworks.erp.api.configuracao.domain.tipoemissao.TipoEmissaoConverter;
import br.com.ultraworks.erp.api.organograma.domain.empresaFilial.EmpresaFilial;
import br.com.ultraworks.erp.core.entity.UWEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
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
@Table(name = "config_empresa_nfe")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class ConfigEmpresaNFe extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "configEmpresaNFeSeq", sequenceName = "seq_config_empresa_nfe", allocationSize = 1)
	@GeneratedValue(generator = "configEmpresaNFeSeq")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "config_empresa_id")
	private ConfigEmpresa configEmpresa;
	
	@OneToOne
	@JoinColumn(name = "empresa_filial_id")
	private EmpresaFilial empresaFilial;
	
	@Convert(converter = TipoCertificadoConverter.class)
	@Column(name = "tipo_certificado")
	private TipoCertificado tipoCertificado;
	
	@Convert(converter = TipoAmbienteConverter.class)
	@Column(name = "tipo_ambiente")
	private TipoAmbiente tipoAmbiente;
	
	@Convert(converter = TipoEmissaoConverter.class)
	@Column(name = "tipo_emissao")
	private TipoEmissao tipoEmissao;
	
	private int serie;
}
