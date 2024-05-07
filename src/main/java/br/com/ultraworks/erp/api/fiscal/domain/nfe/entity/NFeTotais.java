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
@Table(name = "totais_nfe")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class NFeTotais extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "nfeTotaisSeq", sequenceName = "seq_totais_nfe", allocationSize = 1)
	@GeneratedValue(generator = "nfeTotaisSeq")
	private Long id;

	@OneToOne
	@JoinColumn(name = "nfe_id")
	@JsonBackReference
	private NFe nfe;

	private BigDecimal vbc;
	private BigDecimal vicms;
	private BigDecimal vicmsdeson;
	private BigDecimal vfcpufdest;
	private BigDecimal vicmsufdest;
	private BigDecimal vicmsufremet;
	private BigDecimal vfcp;
	private BigDecimal vbcst;
	private BigDecimal vst;
	private BigDecimal vfcpst;
	private BigDecimal vfcpstret;
	private BigDecimal vprod;
	private BigDecimal vfrete;
	private BigDecimal vseg;
	private BigDecimal vdesc;
	private BigDecimal vii;
	private BigDecimal vipi;
	private BigDecimal vipidevol;
	private BigDecimal vpis;
	private BigDecimal vcofins;
	private BigDecimal voutro;
	private BigDecimal vnf;
	private BigDecimal vtottrib;
}
