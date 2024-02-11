package br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocalFisica;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocal.ParceiroLocal;
import br.com.ultraworks.erp.api.tabela.domain.estadocivil.EstadoCivil;
import br.com.ultraworks.erp.api.tabela.domain.nacionalidade.Nacionalidade;
import br.com.ultraworks.erp.api.tabela.domain.profissao.Profissao;
import br.com.ultraworks.erp.api.tabela.domain.sexo.Sexo;
import br.com.ultraworks.erp.core.entity.UWEntityBase;
import jakarta.persistence.Column;
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
@Table(name = "parceiro_local_fisica")
@Data
public class ParceiroLocalFisica extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "parceiroFisicaSeq", sequenceName = "seq_parceiro_fisica", allocationSize = 1)
	@GeneratedValue(generator = "parceiroFisicaSeq")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "parceiro_local_id")
	@JsonBackReference
	private ParceiroLocal parceiroLocal;
	
	@OneToOne
	@JoinColumn(name = "sexo_id")
	private Sexo sexo;
	
	@OneToOne
	@JoinColumn(name = "nacionalidade_id")
	private Nacionalidade nacionalidade;
	
	@OneToOne
	@JoinColumn(name = "profissao_id")
	private Profissao profissao;
	
	@OneToOne
	@JoinColumn(name = "estado_civil_id")
	private EstadoCivil estadoCivil;
	
	private String rg;
	
	@Column(name = "data_nascimento")
	private LocalDate dataNascimento;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ParceiroLocalFisica other = (ParceiroLocalFisica) obj;
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
