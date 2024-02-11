package br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocal;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.com.ultraworks.erp.api.relacionamento.domain.parceiro.Parceiro;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocalEmail.ParceiroLocalEmail;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocalEndereco.ParceiroLocalEndereco;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocalFisica.ParceiroLocalFisica;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocalJuridica.ParceiroLocalJuridica;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocalTelefone.ParceiroLocalTelefone;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocalTipoParceiro.ParceiroLocalTipoParceiro;
import br.com.ultraworks.erp.core.annotation.UniqueValidation;
import br.com.ultraworks.erp.core.annotation.UniqueValidationGroup;
import br.com.ultraworks.erp.core.entity.UWEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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

	public ParceiroLocal() {
		super();
		this.dadosPessoaFisica = new ArrayList<>();
		this.dadosPessoaJuridica = new ArrayList<>();
		this.tiposParceiro = new ArrayList<>();
		this.enderecos = new ArrayList<>();
		this.telefones = new ArrayList<>();
		this.emails = new ArrayList<>();
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

	
	
}
