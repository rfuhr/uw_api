package br.com.ultraworks.erp.api.financeiro.domain.saldocaixabanco;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.com.ultraworks.erp.api.financeiro.domain.conta.Conta;
import br.com.ultraworks.erp.core.entity.UWEntityBase;
import jakarta.persistence.Column;
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
@Table(name = "saldo_cxbco")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class SaldoCaixaBanco extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "saldoCaixaBancoSeq", sequenceName = "seq_saldo_cxbco", allocationSize = 1)
	@GeneratedValue(generator = "saldoCaixaBancoSeq")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "conta_id")
	@JsonBackReference
	private Conta conta;
	
	private LocalDate data;
	
	@Column(name = "saldo_valor")
	private BigDecimal saldoValor;
	
}
