package br.com.ultraworks.erp.api.fiscal.domain.nfe.entity;

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
@Table(name = "info_adic_nfe")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class NFeInfoAdic extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "nfeInfoAdicSeq", sequenceName = "seq_info_adic_nfe", allocationSize = 1)
	@GeneratedValue(generator = "nfeInfoAdicSeq")
	private Long id;

	@OneToOne
	@JoinColumn(name = "nfe_id")
	@JsonBackReference
	private NFe nfe;

	private String infadfisco;
	private String infcpl;

	@OneToMany(mappedBy = "nfeInfoAdic", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<NFeObsCont> observacoesContribuinte;

	@OneToMany(mappedBy = "nfeInfoAdic", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<NFeObsFisco> observacoesFisco;

	@OneToMany(mappedBy = "nfeInfoAdic", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<NFeProcReferenciado> processosReferenciados;
}
