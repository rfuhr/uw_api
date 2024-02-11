package br.com.ultraworks.erp.api.relacionamento.domain.parceiro;

import java.util.ArrayList;
import java.util.List;

import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocal.ParceiroLocal;
import br.com.ultraworks.erp.api.tabela.domain.tipopessoa.TipoPessoa;
import br.com.ultraworks.erp.api.tabela.domain.tipopessoa.TipoPessoaConverter;
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
import jakarta.persistence.Transient;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "parceiro")
@Data
@EqualsAndHashCode(callSuper=false)
@UniqueValidationGroup({
    @UniqueValidation(fields = {"nomeRazaoSocial"}, label = "Já existe parceiro com esta razão social"),
    @UniqueValidation(fields = {"raizCnpjCpf"}, label = "Já existe parceiro com esta raiz cnpj")
})
public class Parceiro extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "parceiroSeq", sequenceName = "seq_parceiro", allocationSize = 1)
	@GeneratedValue(generator = "parceiroSeq")
	private Long id;
	
	@Column(name = "nome_razao_social")
	private String nomeRazaoSocial;
	
	@Column(name = "nome_fantasia")
	private String nomeFantasia;
	
	@Convert(converter = TipoPessoaConverter.class)
	@Column(name = "tipo_pessoa")
	private TipoPessoa tipoPessoa;
	
	@Column(name = "raiz_cnpj_cpf")
	private String raizCnpjCpf;
	
	@Transient
	private List<ParceiroLocal> locais;

	public Parceiro() {
		super();
		this.locais = new ArrayList<>();
	}
	
	
}
