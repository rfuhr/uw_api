package br.com.ultraworks.erp.api.financeiro.domain.tipotitulo;

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
@Table(name = "tipo_titulo")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
@UniqueValidationGroup({
    @UniqueValidation(fields = {"codigo"}, label = "Já existe Tipo de Título com este código"),
    @UniqueValidation(fields = {"nome"}, label = "Já existe Tipo de Título com este nome")
})
public class TipoTitulo extends UWEntityBase {
	
	@Id
	@SequenceGenerator(name = "tipoTituloSeq", sequenceName = "seq_tipo_titulo", allocationSize = 1)
	@GeneratedValue(generator = "tipoTituloSeq")
	private Long id;

	private int codigo;
	private String nome;
	private String sigla;
	
}
