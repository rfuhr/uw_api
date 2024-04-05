package br.com.ultraworks.erp.api.fiscal.domain.cfop;

import java.io.Serializable;
import java.time.LocalDate;

import br.com.ultraworks.erp.api.fiscal.domain.destinooperacao.DestinoOperacao;
import br.com.ultraworks.erp.api.fiscal.domain.destinooperacao.DestinoOperacaoConverter;
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
@Table(name = "cfop")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
@UniqueValidationGroup({
    @UniqueValidation(fields = {"codigo"}, label = "Já existe um código com este valor"),
    @UniqueValidation(fields = {"nome"}, label = "Já existe CFOP com este nome")
})
public class Cfop extends UWEntityBase implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "cfopSeq", sequenceName = "seq_cfop", allocationSize = 1)
	@GeneratedValue(generator = "cfopSeq")
	private Long id;

	private int codigo;
	private String nome;
	
	@Convert(converter = DestinoOperacaoConverter.class)
	@Column(name = "destino_operacao")
	private DestinoOperacao destinoOperacao;

	@Column(name = "data_inicio_vigencia")
	private LocalDate dataInicioVigencia;

	@Column(name = "data_final_vigencia")
	private LocalDate dataFinalVigencia;
}
