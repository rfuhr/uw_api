package br.com.ultraworks.erp.api.configuracao.domain.controlenumeracao;

import br.com.ultraworks.erp.api.organograma.domain.empresaFilial.EmpresaFilial;
import br.com.ultraworks.erp.api.tabela.domain.tipodocumento.TipoDocumento;
import br.com.ultraworks.erp.core.entity.UWEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "controle_numeracao")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class ControleNumeracao extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "controleNumeracaoSeq", sequenceName = "seq_controle_numeracao", allocationSize = 1)
	@GeneratedValue(generator = "controleNumeracaoSeq")
	private Long id;

	@OneToOne
	@JoinColumn(name = "empresa_filial_id")
	private EmpresaFilial empresaFilial;

	@OneToOne
	@JoinColumn(name = "tipo_documento_id")
	private TipoDocumento tipoDocumento;

	private int serie;

	@Column(name = "ultimo_numero")
	private int ultimoNumero;
}
