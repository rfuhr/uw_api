package br.com.ultraworks.erp.api.tabela.domain.unidademedida;

import br.com.ultraworks.erp.api.tabela.domain.grandezaMedida.GrandezaMedida;
import br.com.ultraworks.erp.api.tabela.domain.grandezaMedida.GrandezaMedidaConverter;
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
@Table(name = "unidade_medida")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class UnidadeMedida extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "unidadeMedidaSeq", sequenceName = "seq_unidade_medida", allocationSize = 1)
	@GeneratedValue(generator = "unidadeMedidaSeq")
	private Long id;

	private String nome;
	private String sigla;
	@Convert(converter = GrandezaMedidaConverter.class)
	@Column(name = "grandeza")
	private GrandezaMedida grandezaMedida;

}
