package br.com.ultraworks.erp.api.fiscal.domain.regimetributario;

import java.io.Serializable;

import br.com.ultraworks.erp.core.annotation.UniqueValidation;
import br.com.ultraworks.erp.core.annotation.UniqueValidationGroup;
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
@Table(name = "regime_tributario")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
@UniqueValidationGroup({
    @UniqueValidation(fields = {"codigo"}, label = "Já existe um código com este valor"),
    @UniqueValidation(fields = {"nome"}, label = "Já existe Regime Tributário com este nome")
})
public class RegimeTributario extends UWEntityBase implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "regimeTributarioSeq", sequenceName = "seq_regime_tributario", allocationSize = 1)
	@GeneratedValue(generator = "regimeTributarioSeq")
	private Long id;

	private int codigo;
	private String nome;

	@Column(name = "simples_nacional")
	private boolean simplesNacional;
}
