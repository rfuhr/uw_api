package br.com.ultraworks.erp.api.fiscal.domain.nfe.entity;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.com.ultraworks.erp.core.entity.UWEntityBase;
import jakarta.persistence.CascadeType;
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
@Table(name = "impostos_item_nfe")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class NFeImpostosItem extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "nfeImpostosItemSeq", sequenceName = "seq_impostos_item_nfe", allocationSize = 1)
	@GeneratedValue(generator = "nfeImpostosItemSeq")
	private Long id;

	@OneToOne
	@JoinColumn(name = "det_item_nfe_id")
	@JsonBackReference
	private NFeDetItem nFeDetItem;

	private BigDecimal vtottrib;

	@OneToOne(mappedBy = "nFeImpostosItem", cascade = CascadeType.ALL, orphanRemoval = true)
	private NFeIcmsItem nfeIcmsItem;

	@OneToOne(mappedBy = "nFeImpostosItem", cascade = CascadeType.ALL, orphanRemoval = true)
	private NFeIpiItem nfeIpiItem;

	@OneToOne(mappedBy = "nFeImpostosItem", cascade = CascadeType.ALL, orphanRemoval = true)
	private NFePisItem nfePisItem;
	
	@OneToOne(mappedBy = "nFeImpostosItem", cascade = CascadeType.ALL, orphanRemoval = true)
	private NFeCofinsItem nfeCofinsItem;	
}
