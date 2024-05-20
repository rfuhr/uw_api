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
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "det_pag_nfe")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
public class NFeDetPagamento extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "nfeDetalhePagSeq", sequenceName = "seq_det_pag_nfe", allocationSize = 1)
	@GeneratedValue(generator = "nfeDetalhePagSeq")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "pagamentos_nfe_id")
	@JsonBackReference
	private NFePagamento nfePagamento;

	private int indpag;
	private String tpag;
	private BigDecimal vpag;
	private int tpintegra;
	private String cnpj;
	private String tband;
	private String caut;

}
