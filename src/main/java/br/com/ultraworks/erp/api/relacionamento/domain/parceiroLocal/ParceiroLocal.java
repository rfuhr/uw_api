package br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocal;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import br.com.ultraworks.erp.api.relacionamento.domain.parceiro.Parceiro;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocalEmail.ParceiroLocalEmail;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocalEndereco.ParceiroLocalEndereco;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocalFisica.ParceiroLocalFisica;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocalJuridica.ParceiroLocalJuridica;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocalPropriedade.ParceiroLocalPropriedade;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocalTelefone.ParceiroLocalTelefone;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocalTipoParceiro.ParceiroLocalTipoParceiro;
import br.com.ultraworks.erp.api.tabela.domain.tipoemail.TipoEmail;
import br.com.ultraworks.erp.api.tabela.domain.tipoendereco.TipoEndereco;
import br.com.ultraworks.erp.api.tabela.domain.tipotelefone.TipoTelefone;
import br.com.ultraworks.erp.core.annotation.UniqueValidation;
import br.com.ultraworks.erp.core.annotation.UniqueValidationGroup;
import br.com.ultraworks.erp.core.entity.UWEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

@Entity
@Table(name = "parceiro_local")
@Data
@UniqueValidationGroup({
    @UniqueValidation(fields = {"cpfCnpj"}, label = "Já existe local de parceiro com este cnpj"),
    @UniqueValidation(fields = {"parceiro", "nomeLocal"}, label = "Já existe local de parceiro com este nome")
})
public class ParceiroLocal extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "parceiroLocalSeq", sequenceName = "seq_parceiro_local", allocationSize = 1)
	@GeneratedValue(generator = "parceiroLocalSeq")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "parceiro_id")
	private Parceiro parceiro;
	
	@Column(name = "cpf_cnpj")
	private String cpfCnpj;
	
	@Column(name = "nome_local")
	private String nomeLocal;
	
	@Transient
	private List<ParceiroLocalFisica> dadosPessoaFisica;
	
	@Transient
	private List<ParceiroLocalJuridica> dadosPessoaJuridica;
	
	@Transient
	private List<ParceiroLocalTipoParceiro> tiposParceiro;
	
	@Transient
	private List<ParceiroLocalEndereco> enderecos;	
	
	@Transient
	private List<ParceiroLocalTelefone> telefones;	
	
	@Transient
	private List<ParceiroLocalEmail> emails;
	
	@Transient
	private List<ParceiroLocalPropriedade> propriedades;	

	public ParceiroLocal() {
		super();
		this.dadosPessoaFisica = new ArrayList<>();
		this.dadosPessoaJuridica = new ArrayList<>();
		this.tiposParceiro = new ArrayList<>();
		this.enderecos = new ArrayList<>();
		this.telefones = new ArrayList<>();
		this.emails = new ArrayList<>();
		this.propriedades = new ArrayList<>();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ParceiroLocal other = (ParceiroLocal) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(id);
		return result;
	}	

	public ParceiroLocalEndereco getEnderecoNFe() {
		List<ParceiroLocalEndereco> enderecos = this.getEnderecos();
		Optional<ParceiroLocalEndereco> endereco = enderecos.stream().filter(end -> end.getTipoEndereco().equals(TipoEndereco.FISCAL)).findFirst();
		if (endereco.isPresent()) return endereco.get();
		
		endereco = enderecos.stream().filter(end -> end.getTipoEndereco().equals(TipoEndereco.COMERCIAL)).findFirst();
		if (endereco.isPresent()) return endereco.get();
		
		if (!enderecos.isEmpty()) return enderecos.get(0);
		
		return null;
	}
	
	public ParceiroLocalTelefone getTelefoneNFe() {
		List<ParceiroLocalTelefone> telefones = this.telefones;
		Optional<ParceiroLocalTelefone> telefone = telefones.stream()
				.filter(tel -> tel.getTipoTelefone().equals(TipoTelefone.COMERCIAL)).findFirst();
		if (telefone.isPresent())
			return telefone.get();

		return null;
	}
	
	public ParceiroLocalEmail getEmailNFe() {
		List<ParceiroLocalEmail> emails = this.emails;
		Optional<ParceiroLocalEmail> email = emails.stream()
				.filter(em -> em.getTipoEmail().equals(TipoEmail.DIVERSOS)).findFirst();
		if (email.isPresent())
			return email.get();

		return null;
	}
	
}
