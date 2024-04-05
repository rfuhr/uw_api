package br.com.ultraworks.erp.api.tabela.domain.tipodocumento;

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
@Table(name = "tipo_documento")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class TipoDocumento extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "tipoDocumentoSeq", sequenceName = "seq_tipo_documento", allocationSize = 1)
	@GeneratedValue(generator = "tipoDocumentoSeq")
	private Long id;

	private String nome;
	private String sigla;
	@Column(name = "codigo_receita")
	private String codigoReceita;

}
