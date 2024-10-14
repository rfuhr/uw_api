package br.com.ultraworks.erp.api.tabela.domain.operacaointernaagricola;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.com.ultraworks.erp.api.agricola.domain.identificacaodocumentoagricola.IdentificacaoDocumentoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.tipocontratoagricola.TipoContratoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.tipoprecoagricola.TipoPrecoAgricola;
import br.com.ultraworks.erp.api.tabela.domain.operacaointerna.OperacaoInterna;
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
@Table(name = "operacao_interna_agricola")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class OperacaoInternaAgricola extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "opInternaAgricolaSeq", sequenceName = "seq_operacao_interna_agricola", allocationSize = 1)
	@GeneratedValue(generator = "opInternaAgricolaSeq")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "operacao_interna_id")
	@JsonBackReference
	private OperacaoInterna operacaoInterna;

	@Column(name = "identificacao_documento_agricola")
	private IdentificacaoDocumentoAgricola identificacaoDocumentoAgricola;

	@Column(name = "exige_nota_entrada")
	private boolean exigeNotaEntrada;

	@Column(name = "fixa_automatico")
	private boolean fixaAutomatico;

	@Column(name = "complemento_fixacao")
	private boolean complementoPrecoFixacao;

	@Column(name = "contrato_avista")
	private boolean contratoAvista;

	@OneToOne
	@JoinColumn(name = "tipo_preco_agricola_id")
	private TipoPrecoAgricola tipoPrecoAgricola;

	@OneToOne
	@JoinColumn(name = "tipo_contrato_agricola_id")
	private TipoContratoAgricola tipoContratoAgricola;

}
