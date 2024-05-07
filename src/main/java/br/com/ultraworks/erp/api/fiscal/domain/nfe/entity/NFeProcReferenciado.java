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
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "proc_ref_info_adic_nfe")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
public class NFeProcReferenciado extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "nfeProcReferenciadoSeq", sequenceName = "seq_proc_ref_info_adic_nfe", allocationSize = 1)
	@GeneratedValue(generator = "nfeProcReferenciadoSeq")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "info_adic_nfe_id")
	@JsonBackReference
	private NFeInfoAdic nfeInfoAdic;

	private String nproc;
	private String indproc;
}