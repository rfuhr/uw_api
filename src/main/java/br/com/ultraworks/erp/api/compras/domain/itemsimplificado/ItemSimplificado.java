package br.com.ultraworks.erp.api.compras.domain.itemsimplificado;

import java.time.LocalDate;

import br.com.ultraworks.erp.core.entity.UWEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "item_simplificado")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class ItemSimplificado extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "itemSimplificadoSeq", sequenceName = "seq_item_simplificado", allocationSize = 1)
	@GeneratedValue(generator = "itemSimplificadoSeq")
	private Long id;

	@Column(name = "codigo")
	private int codigo;

	@Column(name = "nome")
	private String nome;

	@Column(name = "data_inicio_vigencia")
	private LocalDate dataInicioVigencia;

	@Column(name = "data_final_vigencia")
	private LocalDate dataFinalVigencia;	
}
