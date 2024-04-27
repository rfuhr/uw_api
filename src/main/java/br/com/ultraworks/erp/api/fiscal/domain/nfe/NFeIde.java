package br.com.ultraworks.erp.api.fiscal.domain.nfe;

import java.time.Instant;
import java.time.LocalDateTime;

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
@Table(name = "ide_nfe")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class NFeIde extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "nfeIdeSeq", sequenceName = "ide_nfe_seq", allocationSize = 1)
	@GeneratedValue(generator = "nfeIdeSeq")
	private Long id;

	@OneToOne
	@JoinColumn(name = "nfeid")
	@JsonBackReference
	private NFe nfe;

	private int cuf;
	private int cnf;
	private String natop;
	private int mod;
	private int serie;
	private int nnf;
	private LocalDateTime dhemi;
	private LocalDateTime dhsaient;
	private int tpnf;
	private int iddest;
	private int cmunfg;
	private int tpimp;
	private int tpemis;
	private int cdv;
	private int tpamb;
	private int finnfe;
	private int indfinal;
	private int indpres;
	private int indintermed;
	private int procemi;
	private int verproc;
	private LocalDateTime dhcont;
	private String xjust;
}
