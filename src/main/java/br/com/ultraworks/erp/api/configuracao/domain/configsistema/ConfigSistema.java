package br.com.ultraworks.erp.api.configuracao.domain.configsistema;

import java.util.List;

import br.com.ultraworks.erp.api.configuracao.domain.configsistemafinanceiro.ConfigSistemaFinanceiro;
import br.com.ultraworks.erp.core.entity.UWEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "config_sistema")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class ConfigSistema extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "configSistemaSeq", sequenceName = "seq_config_sistema", allocationSize = 1)
	@GeneratedValue(generator = "configSistemaSeq")
	private Long id;

	@Transient
	private List<ConfigSistemaFinanceiro> configuracoesFinanceiro;	
}
