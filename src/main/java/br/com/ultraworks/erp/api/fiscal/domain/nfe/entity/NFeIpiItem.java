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
@Table(name = "ipi_item_nfe")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class NFeIpiItem extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "nfeIpiItemSeq", sequenceName = "ipi_item_nfe_seq", allocationSize = 1)
	@GeneratedValue(generator = "nfeIpiItemSeq")
	private Long id;

	@OneToOne
	@JoinColumn(name = "impostos_item_nfe_id")
	@JsonBackReference
	private NFeImpostosItem nFeImpostosItem;

	private String cnpjprod;
	private String cselo;
	private int qselo;
	private int cenq;
	private String cst;
	private BigDecimal vbc;
	private BigDecimal pipi;
	private BigDecimal qunid;
	private BigDecimal vunid;
	private BigDecimal vipi;

}
