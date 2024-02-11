package br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocalEndereco;

import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocal.ParceiroLocal;
import br.com.ultraworks.erp.api.tabela.domain.cidade.Cidade;
import br.com.ultraworks.erp.api.tabela.domain.tipoendereco.TipoEndereco;
import br.com.ultraworks.erp.api.tabela.domain.tipoendereco.TipoEnderecoConverter;
import br.com.ultraworks.erp.core.entity.UWEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "parceiro_local_endereco")
@Data
@EqualsAndHashCode(callSuper=false)
public class ParceiroLocalEndereco extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "parceiroLocalEnderecoSeq", sequenceName = "seq_parceiro_local_endereco", allocationSize = 1)
	@GeneratedValue(generator = "parceiroLocalEnderecoSeq")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "parceiro_local_id")
	private ParceiroLocal parceiroLocal;
	
	@Convert(converter = TipoEnderecoConverter.class)
	@Column(name = "tipo_endereco")
	private TipoEndereco tipoEndereco;
	
	@Column(name = "identificacao")
	private String identificacao;
	
	private String cep;
	
	@OneToOne
	@JoinColumn(name = "cidade_id")
	private Cidade cidade;
	
	private String endereco;
	
	private String complemento;
	
	private String numero;
	
	private String bairro;
	
}
