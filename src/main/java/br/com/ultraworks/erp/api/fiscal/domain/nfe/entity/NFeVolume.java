package br.com.ultraworks.erp.api.fiscal.domain.nfe.entity;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.com.ultraworks.erp.core.entity.UWEntityBase;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "vol_transp_nfe")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
public class NFeVolume extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "nfeVolumeSeq", sequenceName = "seq_vol_transp_nfe", allocationSize = 1)
	@GeneratedValue(generator = "nfeVolumeSeq")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "transp_nfe_id")
	@JsonBackReference
	private NFeTransporte nfeTransporte;

	private BigDecimal qvol;
	private String esp;
	private String marca;
	private String nvol;
	private BigDecimal pesol;
	private BigDecimal pesob;
	
	@OneToMany(mappedBy = "nfeVolume", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<NFeLacre> lacres;
}
