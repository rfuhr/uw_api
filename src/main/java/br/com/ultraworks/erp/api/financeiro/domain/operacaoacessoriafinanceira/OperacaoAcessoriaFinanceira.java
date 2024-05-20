package br.com.ultraworks.erp.api.financeiro.domain.operacaoacessoriafinanceira;

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
@Table(name = "operacao_acessoria_financeira")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
public class OperacaoAcessoriaFinanceira extends UWEntityBase {
	
	@Id
	@SequenceGenerator(name = "operacaoAcessoriaFinanceiraSeq", sequenceName = "seq_operacao_acessoria_financeira", allocationSize = 1)
	@GeneratedValue(generator = "operacaoAcessoriaFinanceiraSeq")
	private Long id;

	private String nome;
	
	@Column(name = "idn_juro_desconto")
	private String juroDesconto; 
	

	
}
