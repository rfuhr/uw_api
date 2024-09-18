package br.com.ultraworks.erp.api.financeiro.domain.carteirafinanceira;

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
@Table(name = "carteira_financeira")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class CarteiraFinanceira extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "carteiraFinanceiraSeq", sequenceName = "seq_carteira_financeira", allocationSize = 1)
	@GeneratedValue(generator = "carteiraFinanceiraSeq")
	private Long id;

	private int codigo;
	private String nome;
	private String sigla;

	@Column(name = "informa_banco")
	private boolean informaBanco;

	@Column(name = "lista_pos_titulo")
	private boolean listaPosicaoTitulo;

}
