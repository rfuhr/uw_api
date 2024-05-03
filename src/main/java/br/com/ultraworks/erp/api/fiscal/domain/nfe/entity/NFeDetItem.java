package br.com.ultraworks.erp.api.fiscal.domain.nfe.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.com.ultraworks.erp.core.entity.UWEntityBase;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "det_item_nfe")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class NFeDetItem extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "nfeDetItemSeq", sequenceName = "det_item_nfe_seq", allocationSize = 1)
	@GeneratedValue(generator = "nfeDetItemSeq")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "nfe_id")
	@JsonBackReference
	private NFe nfe;

	private int nItem;

	@OneToOne(mappedBy = "nFeDetItem", cascade = CascadeType.ALL, orphanRemoval = true)
	private NFeProdItem nfeProdItem;

	@OneToOne(mappedBy = "nFeDetItem", cascade = CascadeType.ALL, orphanRemoval = true)
	private NFeImpostosItem nfeImpostosItem;
	
	@OneToOne(mappedBy = "nFeInfoAdicItem", cascade = CascadeType.ALL, orphanRemoval = true)
	private NFeInfoAdicItem nfeInfoAdicItem;

}
