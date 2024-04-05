package br.com.ultraworks.erp.api.configuracao.domain.configempresa;

import java.util.List;

import br.com.ultraworks.erp.api.configuracao.domain.configempresanfe.ConfigEmpresaNFe;
import br.com.ultraworks.erp.api.fiscal.domain.regimetributario.RegimeTributario;
import br.com.ultraworks.erp.api.organograma.domain.empresa.Empresa;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocalTelefone.ParceiroLocalTelefone;
import br.com.ultraworks.erp.core.entity.UWEntityBase;
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
@Table(name = "config_empresa")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class ConfigEmpresa extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "configEmpresaSeq", sequenceName = "seq_config_empresa", allocationSize = 1)
	@GeneratedValue(generator = "configEmpresaSeq")
	private Long id;

	@OneToOne
	@JoinColumn(name = "empresa_id")
	private Empresa empresa;
	
	@OneToOne
	@JoinColumn(name = "regime_tributario_id")
	private RegimeTributario regimeTributario;
	
	@Transient
	private List<ConfigEmpresaNFe> configuracoesNFe;	
}
