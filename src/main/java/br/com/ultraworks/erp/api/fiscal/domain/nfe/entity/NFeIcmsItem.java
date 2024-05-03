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
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "icms_item_nfe")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class NFeIcmsItem extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "nfeIcmsItemSeq", sequenceName = "icms_item_nfe_seq", allocationSize = 1)
	@GeneratedValue(generator = "nfeIcmsItemSeq")
	private Long id;

	@OneToOne
	@JoinColumn(name = "impostos_item_nfe_id")
	@JsonBackReference
	private NFeImpostosItem nFeImpostosItem;

	private String cst;
	private int orig;
	private int modbc;
	private BigDecimal vbc;
	private BigDecimal picms;
	private BigDecimal vicms;
	private BigDecimal vbcfcp;
	private BigDecimal pfcp;
	private BigDecimal vfcp;
	private int modbcst;
	private BigDecimal pmvast;
	private BigDecimal predbcst;
	private BigDecimal vbcst;
	private BigDecimal picmsst;
	private BigDecimal vicmsst;
	private BigDecimal vbcfcpst;
	private BigDecimal pfcpst;
	private BigDecimal vfcpst;
	private BigDecimal predbc;
	private BigDecimal vicmsdeson;
	private int motdesicms;
	private BigDecimal vicmsop;
	private BigDecimal pdif;
	private BigDecimal vicmsdif;
	private BigDecimal vbcstret;
	private BigDecimal pst;
	private BigDecimal vicmssubstituto;
	private BigDecimal vicmsstret;
	private BigDecimal vbcfcpstret;
	private BigDecimal pfcpstret;
	private BigDecimal vfcpstret;
	private BigDecimal predbcefet;
	private BigDecimal vbcefet;
	private BigDecimal picmsefet;
	private BigDecimal vicmsefet;
	private int csosn;
	private BigDecimal pcredsn;
	private BigDecimal vcredicmssn;

}
