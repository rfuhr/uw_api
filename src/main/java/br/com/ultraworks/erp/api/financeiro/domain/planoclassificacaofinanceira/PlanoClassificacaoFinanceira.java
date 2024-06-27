package br.com.ultraworks.erp.api.financeiro.domain.planoclassificacaofinanceira;

import br.com.ultraworks.erp.core.annotation.UniqueValidation;
import br.com.ultraworks.erp.core.annotation.UniqueValidationGroup;
import br.com.ultraworks.erp.core.entity.UWEntityBase;
import jakarta.persistence.Entity;
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
@Table(name = "plano_classificacao_financeira")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@UniqueValidationGroup({
    @UniqueValidation(fields = {"codigo"}, label = "Já existe conta com este código"),
    @UniqueValidation(fields = {"nivel","nome"}, label = "Já existe conta com este nome no mesmo nível")
})
public class PlanoClassificacaoFinanceira extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "planoClassificacaoFinanceiraSeq", sequenceName = "seq_plano_classificao_financeira", allocationSize = 1)
	@GeneratedValue(generator = "planoClassificacaoFinanceiraSeq")
	private Long id;

	@OneToOne
	@JoinColumn(name = "id_superior")
	private PlanoClassificacaoFinanceira contaSuperior;
	private String codigo;
	private String nome;
	private int nivel;
	private boolean sintetica;

}
