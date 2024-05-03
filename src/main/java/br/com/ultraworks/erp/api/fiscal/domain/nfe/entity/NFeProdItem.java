package br.com.ultraworks.erp.api.fiscal.domain.nfe.entity;

import java.math.BigDecimal;

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
@Table(name = "prod_item_nfe")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class NFeProdItem extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "nfeProdItemSeq", sequenceName = "prod_item_nfe_seq", allocationSize = 1)
	@GeneratedValue(generator = "nfeProdItemSeq")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "det_item_nfe_id")
	@JsonBackReference
	private NFeDetItem nFeDetItem;

	private String cprod;
	private String cean;
	private String xprod;
	private String ncm;
	private String nve;
	private String cest;
	private String indescala;
	private String cnpjfab;
	private String cbenef;
	private String extipi;
	private String cfop;
	private String ucom;
	private BigDecimal qcom;
	private BigDecimal vuncom;
	private BigDecimal vprod;
	private String ceantrib;
	private String utrib;
	private BigDecimal qtrib;
	private BigDecimal vuntrib;
	private BigDecimal vfrete;
	private BigDecimal vseg;
	private BigDecimal vdesc;
	private BigDecimal voutro;
	private int indtot;
}
