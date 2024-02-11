package br.com.ultraworks.erp.api.tabela.domain.pais;

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
@Table(name = "pais")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
@UniqueValidationGroup({
    @UniqueValidation(fields = {"nome"}, label = "Já existe pais com este nome"),
    @UniqueValidation(fields = {"sigla"}, label = "Já existe sigla com este valor")
})
public class Pais extends UWEntityBase implements Serializable{
	
	@Id
	@SequenceGenerator(name = "paisSeq", sequenceName = "seq_pais", allocationSize = 1)
	@GeneratedValue(generator = "paisSeq")
	private Long id;

	private String nome;
	private String sigla;
	
	@Column(name = "codigo_ibge")
	private String codigoIBGE;

	@Column(name = "codigo_siscomex")
	private String codigoSiscomex;


}
