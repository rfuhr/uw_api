package br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocalTelefone;

import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocal.ParceiroLocal;
import br.com.ultraworks.erp.api.tabela.domain.tipotelefone.TipoTelefone;
import br.com.ultraworks.erp.api.tabela.domain.tipotelefone.TipoTelefoneConverter;
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
@Table(name = "parceiro_local_telefone")
@Data
@EqualsAndHashCode(callSuper=false)
public class ParceiroLocalTelefone extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "parceiroLocalTelefoneSeq", sequenceName = "seq_parceiro_local_telefone", allocationSize = 1)
	@GeneratedValue(generator = "parceiroLocalTelefoneSeq")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "parceiro_local_id")
	private ParceiroLocal parceiroLocal;
	
	@Convert(converter = TipoTelefoneConverter.class)
	@Column(name = "tipo_telefone")
	private TipoTelefone tipoTelefone;
	
	@Column(name = "identificacao")
	private String identificacao;
	
	private String numero;
}
