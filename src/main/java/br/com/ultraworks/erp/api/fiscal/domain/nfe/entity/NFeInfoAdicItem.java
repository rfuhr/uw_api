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
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "info_adic_item_nfe")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class NFeInfoAdicItem extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "nfeInfoAdicItemSeq", sequenceName = "info_adic_item_nfe_seq", allocationSize = 1)
	@GeneratedValue(generator = "nfeInfoAdicItemSeq")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "det_item_nfe_id")
	@JsonBackReference
	private NFeDetItem nFeDetItem;

	private String infadprod;
}
