package br.com.ultraworks.erp.api.fiscal.domain.nfe.entity;

import java.math.BigDecimal;

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
@Table(name = "cofins_item_nfe")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class NFeCofinsItem extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "nfeCofinsItemSeq", sequenceName = "seq_cofins_item_nfe", allocationSize = 1)
	@GeneratedValue(generator = "nfeCofinsItemSeq")
	private Long id;

	@OneToOne
	@JoinColumn(name = "impostos_item_nfe_id")
	@JsonBackReference
	private NFeImpostosItem nFeImpostosItem;

	private String cst;
	private BigDecimal vbc;
	private BigDecimal pcofins;
	private BigDecimal vcofins;
	private BigDecimal qbcprod;
	private BigDecimal valiqprod;
}
