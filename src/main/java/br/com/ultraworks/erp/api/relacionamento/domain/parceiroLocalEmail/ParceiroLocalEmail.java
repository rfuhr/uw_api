package br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocalEmail;

import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocal.ParceiroLocal;
import br.com.ultraworks.erp.api.tabela.domain.tipoemail.TipoEmail;
import br.com.ultraworks.erp.api.tabela.domain.tipoemail.TipoEmailConverter;
import br.com.ultraworks.erp.core.entity.UWEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "parceiro_local_email")
@Data
@EqualsAndHashCode(callSuper=false)
public class ParceiroLocalEmail extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "parceiroLocalEmailSeq", sequenceName = "seq_parceiro_local_email", allocationSize = 1)
	@GeneratedValue(generator = "parceiroLocalEmailSeq")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "parceiro_local_id")
	private ParceiroLocal parceiroLocal;
	
	@Convert(converter = TipoEmailConverter.class)
	@Column(name = "tipo_email")
	private TipoEmail tipoEmail;
	
	@Column(name = "identificacao")
	private String identificacao;
	
	private String email;
}
