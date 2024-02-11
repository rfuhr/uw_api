package br.com.ultraworks.erp.api.financeiro.domain.tipooperacaofinanceira;

import br.com.ultraworks.erp.core.entity.UWEntityBase;
import jakarta.persistence.Column;
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
@Table(name = "tipo_operacao_financeira")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
public class TipoOperacaoFinanceira extends UWEntityBase {
	
	@Id
	@SequenceGenerator(name = "tipoOperacaoFinanceiraSeq", sequenceName = "seq_tipo_operacao_financeira", allocationSize = 1)
	@GeneratedValue(generator = "tipoOperacaoFinanceiraSeq")
	private Long id;

	private String nome;
	private String sigla;
	
}
