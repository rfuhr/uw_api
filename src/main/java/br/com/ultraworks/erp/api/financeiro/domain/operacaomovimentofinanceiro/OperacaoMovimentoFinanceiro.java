package br.com.ultraworks.erp.api.financeiro.domain.operacaomovimentofinanceiro;

import br.com.ultraworks.erp.core.entity.UWEntityBase;
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
@Table(name = "operacao_movimento_financeiro")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
public class OperacaoMovimentoFinanceiro extends UWEntityBase {
	
	@Id
	@SequenceGenerator(name = "operacaoMovimentoFinanceiroSeq", sequenceName = "seq_operacao_movimento_financeiro", allocationSize = 1)
	@GeneratedValue(generator = "operacaoMovimentoFinanceiroSeq")
	private Long id;

	private String nome;
}
