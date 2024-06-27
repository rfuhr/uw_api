package br.com.ultraworks.erp.api.comercial.domain.indicemarkup;

import br.com.ultraworks.erp.api.fiscal.domain.tipotributo.TipoTributo;
import br.com.ultraworks.erp.api.fiscal.domain.tipotributo.TipoTributoConverter;
import br.com.ultraworks.erp.core.annotation.UniqueValidation;
import br.com.ultraworks.erp.core.annotation.UniqueValidationGroup;
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
@Table(name = "indice_markup")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
@UniqueValidationGroup({
    @UniqueValidation(fields = {"codigo"}, label = "Já existe um código com este valor"),
    @UniqueValidation(fields = {"nome"}, label = "Já existe um Índice de MarkUp com este nome")
})
public class IndiceMarkup extends UWEntityBase {
	
	@Id
	@SequenceGenerator(name = "indiceMarkupSeq", sequenceName = "seq_indice_markup", allocationSize = 1)
	@GeneratedValue(generator = "indiceMarkupSeq")
	private Long id;

	private int codigo;
	
	private String nome;
	
	private boolean tributo;
	
	@Convert(converter = TipoTributoConverter.class)
	@Column(name = "tipo_tributo")
	private TipoTributo tipoTributo;
	
}
