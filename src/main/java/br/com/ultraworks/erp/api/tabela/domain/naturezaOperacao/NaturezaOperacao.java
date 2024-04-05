package br.com.ultraworks.erp.api.tabela.domain.naturezaOperacao;

import br.com.ultraworks.erp.api.tabela.domain.indicadoroperacao.IndicadorOperacao;
import br.com.ultraworks.erp.api.tabela.domain.indicadoroperacao.IndicadorOperacaoConverter;
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
@Table(name = "natureza_operacao")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
public class NaturezaOperacao extends UWEntityBase {
	
	@Id
	@SequenceGenerator(name = "naturezaOperacaoSeq", sequenceName = "seq_natureza_operacao", allocationSize = 1)
	@GeneratedValue(generator = "naturezaOperacaoSeq")
	private Long id;

	private String nome;

	private String sigla;
	
	@Convert(converter = IndicadorOperacaoConverter.class)
	@Column(name = "indicador_operacao")	
	private IndicadorOperacao indicadorOperacao;
	
}
