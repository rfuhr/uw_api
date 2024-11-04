package br.com.ultraworks.erp.api.compras.domain.configautorizacaosolicitacaomercadoria;

import br.com.ultraworks.erp.api.estoque.domain.grupocontabil.GrupoContabil;
import br.com.ultraworks.erp.api.organograma.domain.departamento.Departamento;
import br.com.ultraworks.erp.api.organograma.domain.empresa.Empresa;
import br.com.ultraworks.erp.api.organograma.domain.empresaFilial.EmpresaFilial;
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
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "config_autorizacao_solicitacao_mercadoria")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
public class ConfigAutorizacaoSolicitacaoMercadoria extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "configAutorizacaoSolicitacaoMercadoriaSeq", sequenceName = "seq_config_autorizacao_solicitacao_mercadoria", allocationSize = 1)
	@GeneratedValue(generator = "configAutorizacaoSolicitacaoMercadoriaSeq")
	private Long id;

	@OneToOne
	@JoinColumn(name = "empresa_id")
	private Empresa empresa;
	
	@OneToOne
	@JoinColumn(name = "empresa_filial_id")
	private EmpresaFilial empresaFilial;	
	
	@OneToOne
	@JoinColumn(name = "departamento_id")
	private Departamento departamento;
	
	@OneToOne
	@JoinColumn(name = "grupo_contabil_id")
	private GrupoContabil grupoContabil;
	
	@Column(name = "autorizadores_id")
	private String autorizadoresId;
}
