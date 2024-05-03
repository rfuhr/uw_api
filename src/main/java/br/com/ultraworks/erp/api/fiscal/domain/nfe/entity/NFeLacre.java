package br.com.ultraworks.erp.api.fiscal.domain.nfe.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.com.ultraworks.erp.core.entity.UWEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "lacres_transp_nfe")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
public class NFeLacre extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "nfeLacreSeq", sequenceName = "lacres_transp_nfe_seq", allocationSize = 1)
	@GeneratedValue(generator = "nfeLacreSeq")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "vol_transp_nfe_id")
	@JsonBackReference
	private NFeVolume nfeVolume;

	private String nlacre;
}
