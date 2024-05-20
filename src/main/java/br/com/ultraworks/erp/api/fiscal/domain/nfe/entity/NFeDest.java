package br.com.ultraworks.erp.api.fiscal.domain.nfe.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.com.ultraworks.erp.core.entity.UWEntityBase;
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
@Table(name = "dest_nfe")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class NFeDest extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "nfeDestSeq", sequenceName = "seq_dest_nfe", allocationSize = 1)
	@GeneratedValue(generator = "nfeDestSeq")
	private Long id;

	@OneToOne
	@JoinColumn(name = "nfe_id")
	@JsonBackReference
	private NFe nfe;

	private String cnpj;
	private String cpf;
	private String idestrangeiro;
	private String xnome;
	private String xlgr;
	private String nro;
	private String xcpl;
	private String xbairro;
	private int cmun;
	private String xmun;
	private String uf;
	private String cep;
	private String cpais;
	private String xpais;
	private String fone;
	private int indiedest;
	private String ie;
	private String isuf;
	private String im;
	private String email;
}
