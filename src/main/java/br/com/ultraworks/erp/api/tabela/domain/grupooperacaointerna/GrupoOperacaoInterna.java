package br.com.ultraworks.erp.api.tabela.domain.grupooperacaointerna;

import br.com.ultraworks.erp.api.tabela.domain.tipooperacao.TipoOperacao;
import br.com.ultraworks.erp.core.entity.UWEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
@Table(name = "grupo_operacao_interna")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
public class GrupoOperacaoInterna extends UWEntityBase {
	
	@Id
	@SequenceGenerator(name = "grupoOperacaoInternaSeq", sequenceName = "seq_grupo_operacao_interna", allocationSize = 1)
	@GeneratedValue(generator = "grupoOperacaoInternaSeq")
	private Long id;

	private String nome;

	private String sigla;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "tipo_operacao_id")
	private TipoOperacao tipoOperacao;
	
}
