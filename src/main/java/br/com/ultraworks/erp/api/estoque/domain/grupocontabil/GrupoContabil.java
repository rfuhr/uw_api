package br.com.ultraworks.erp.api.estoque.domain.grupocontabil;

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
@Table(name = "grupo_contabil")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
@UniqueValidationGroup({
    @UniqueValidation(fields = {"codigo"}, label = "Já existe um código com este valor"),
    @UniqueValidation(fields = {"nome"}, label = "Já existe um Grupo Contábil com este nome")
})
public class GrupoContabil extends UWEntityBase {
	
	@Id
	@SequenceGenerator(name = "grupoContabilSeq", sequenceName = "seq_grupo_contabil", allocationSize = 1)
	@GeneratedValue(generator = "grupoContabilSeq")
	private Long id;

	private int codigo;
	
	private String nome;
	private boolean general;
	
}
