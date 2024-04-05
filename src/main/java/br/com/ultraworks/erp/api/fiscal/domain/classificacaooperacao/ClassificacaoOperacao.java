package br.com.ultraworks.erp.api.fiscal.domain.classificacaooperacao;

import br.com.ultraworks.erp.core.annotation.UniqueValidation;
import br.com.ultraworks.erp.core.annotation.UniqueValidationGroup;
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
@Table(name = "classificacao_operacao")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
@UniqueValidationGroup({
    @UniqueValidation(fields = {"codigo"}, label = "Já existe um código com este valor"),
    @UniqueValidation(fields = {"nome"}, label = "Já existe uma Classificação de Operação com este nome")
})
public class ClassificacaoOperacao extends UWEntityBase {
	
	@Id
	@SequenceGenerator(name = "classificacaoOperacaoSeq", sequenceName = "seq_classificacao_operacao", allocationSize = 1)
	@GeneratedValue(generator = "classificacaoOperacaoSeq")
	private Long id;

	private int codigo;
	
	private String nome;
	
}
