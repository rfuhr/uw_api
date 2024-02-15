package br.com.ultraworks.erp.api.tabela.domain.historicopadrao;

import br.com.ultraworks.erp.api.tabela.domain.tipoobrigatoriedade.TipoObrigatoriedade;
import br.com.ultraworks.erp.api.tabela.domain.tipoobrigatoriedade.TipoObrigatoriedadeConverter;
import br.com.ultraworks.erp.api.tabela.domain.tipopessoa.TipoPessoa;
import br.com.ultraworks.erp.api.tabela.domain.tipopessoa.TipoPessoaConverter;
import br.com.ultraworks.erp.core.entity.UWEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "historico_padrao")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
public class HistoricoPadrao extends UWEntityBase {
	
	@Id
	@SequenceGenerator(name = "historicoPadraoSeq", sequenceName = "seq_historico_padrao", allocationSize = 1)
	@GeneratedValue(generator = "historicoPadraoSeq")
	private Long id;

	private Long codigo;
	
	private String nome;
	private String sigla;
	
	@Convert(converter = TipoObrigatoriedadeConverter.class)
	@Column(name = "informa_documento")
	private TipoObrigatoriedade informaDocumento;
	
}
