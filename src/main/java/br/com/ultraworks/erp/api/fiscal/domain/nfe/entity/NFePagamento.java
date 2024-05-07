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
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pagamentos_nfe")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class NFePagamento extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "nfePagamentosSeq", sequenceName = "seq_pagamentos_nfe", allocationSize = 1)
	@GeneratedValue(generator = "nfePagamentosSeq")
	private Long id;

	@OneToOne
	@JoinColumn(name = "nfe_id")
	@JsonBackReference
	private NFe nfe;

	private BigDecimal vtroco;
	
	@OneToMany(mappedBy = "nfePagamento", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<NFeDetPagamento> detalhesPagamento;
}
