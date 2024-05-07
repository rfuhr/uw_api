package br.com.ultraworks.erp.api.fiscal.domain.nfe.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.com.ultraworks.erp.core.entity.UWEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "nfe_ref")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class NFeRef extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "nfeRefSeq", sequenceName = "seq_nfe_ref", allocationSize = 1)
	@GeneratedValue(generator = "nfeRefSeq")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "ide_nfe_id")
	@JsonBackReference
	private NFeIde nfeIde;

	@Column(name = "refnfe")
	private String refNFe;

	private int cuf;
	private int aamm;
	private String cnpj;
	private String mod;
	private int serie;
	private int nnf;
	private String cpf;
	private String ie;
	private String refcte;
	private String necf;
	private String ncoo;

}
