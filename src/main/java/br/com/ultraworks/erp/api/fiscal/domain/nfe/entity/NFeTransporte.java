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
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "transp_nfe")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class NFeTransporte extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "nfeTransporteSeq", sequenceName = "transp_nfe_seq", allocationSize = 1)
	@GeneratedValue(generator = "nfeTransporteSeq")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "nfe_id")
	@JsonBackReference
	private NFe nfe;

	private int modfrete;
	private String cnpj;
	private String cpf;
	private String xnome;
	private String ie;
	private String xender;
	private String xmun;
	private String uf;
	private BigDecimal vserv;
	private BigDecimal vbcret;
	private BigDecimal picmsret;
	private BigDecimal vicmsret;
	private String cfop;
	private String cmunfg;
	private String placa;
	private String ufplaca;
	private String rntc;
	private String vagao;
	private String balsa;
	
	@OneToMany(mappedBy = "nfeTransporte", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<NFeReboque> reboques;
	
	@OneToMany(mappedBy = "nfeTransporte", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<NFeVolume> volumes;
	
}
