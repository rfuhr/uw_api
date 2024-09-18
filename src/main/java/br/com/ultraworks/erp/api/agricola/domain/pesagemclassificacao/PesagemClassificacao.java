package br.com.ultraworks.erp.api.agricola.domain.pesagemclassificacao;

import java.math.BigDecimal;

import br.com.ultraworks.erp.api.agricola.domain.pesagem.Pesagem;
import br.com.ultraworks.erp.api.agricola.domain.tipoclassificacaoagricola.TipoClassificacaoAgricola;
import br.com.ultraworks.erp.core.entity.UWEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pesagem_classificacao")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class PesagemClassificacao extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "pesagemClassificacaoSeq", sequenceName = "seq_pesagem_classificacao", allocationSize = 1)
	@GeneratedValue(generator = "pesagemClassificacaoSeq")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "pesagem_id")
	private Pesagem pesagem;
	
	@OneToOne
	@JoinColumn(name = "tipo_classificacao_agricola_id")
	private TipoClassificacaoAgricola tipoClassificacaoAgricola;
	
	@Column(name = "valor")
	private BigDecimal valor;
	
	@Column(name = "percentual_desconto")
	private BigDecimal percentualDesconto;
	
	@Column(name = "desconto")
	private BigDecimal desconto;
}
