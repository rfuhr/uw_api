package br.com.ultraworks.erp.api.fiscal.domain.nfe;

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
@Table(name = "emit_nfe")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class NFeEmit extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "nfeEmitSeq", sequenceName = "emit_nfe_seq", allocationSize = 1)
	@GeneratedValue(generator = "nfeEmitSeq")
	private Long id;

	@OneToOne
	@JoinColumn(name = "nfeid")
	@JsonBackReference
	private NFe nfe;

	private String cnpj;
	private String cpf;
	private String xnome;
	private String xfant;
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
	private String ie;
	private String iest;
	private String im;
	private String cnae;
	private int crt;
}
