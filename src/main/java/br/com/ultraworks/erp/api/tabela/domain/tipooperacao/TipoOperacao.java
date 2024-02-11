package br.com.ultraworks.erp.api.tabela.domain.tipooperacao;

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
@Table(name = "tipo_operacao")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
public class TipoOperacao extends UWEntityBase {
	
	@Id
	@SequenceGenerator(name = "tipoOperacaoSeq", sequenceName = "seq_tipo_operacao", allocationSize = 1)
	@GeneratedValue(generator = "tipoOperacaoSeq")
	private Long id;

	private String nome;
	
	private boolean entrada;
}
