package br.com.ultraworks.erp.api.fiscal.domain.nfe.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.com.ultraworks.erp.core.entity.UWEntityBase;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
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
	@SequenceGenerator(name = "nfeIdeSeq", sequenceName = "seq_ide_nfe", allocationSize = 1)
	@GeneratedValue(generator = "nfeIdeSeq")
	private Long id;

	@OneToOne
	@JoinColumn(name = "nfe_id")
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
	
	@OneToMany(mappedBy = "nfeIde", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<NFeRef> nfesRefs;
}
