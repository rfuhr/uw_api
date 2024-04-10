package br.com.ultraworks.erp.api.tabela.domain.operacaointernafiscal;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.com.ultraworks.erp.api.fiscal.domain.destinooperacao.DestinoOperacao;
import br.com.ultraworks.erp.api.fiscal.domain.destinooperacao.DestinoOperacaoConverter;
import br.com.ultraworks.erp.api.fiscal.domain.finalidadenfe.FinalidadeNfe;
import br.com.ultraworks.erp.api.fiscal.domain.finalidadenfe.FinalidadeNfeConverter;
import br.com.ultraworks.erp.api.fiscal.domain.tipoconsumidor.TipoConsumidor;
import br.com.ultraworks.erp.api.fiscal.domain.tipoconsumidor.TipoConsumidorConverter;
import br.com.ultraworks.erp.api.fiscal.domain.tipopresencacomprador.TipoPresencaComprador;
import br.com.ultraworks.erp.api.fiscal.domain.tipopresencacomprador.TipoPresencaCompradorConverter;
import br.com.ultraworks.erp.api.tabela.domain.operacaointerna.OperacaoInterna;
import br.com.ultraworks.erp.api.tabela.domain.operacaointernafiscalcfop.OperacaoInternaFiscalCfop;
import br.com.ultraworks.erp.core.entity.UWEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "operacao_interna_fiscal")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class OperacaoInternaFiscal extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "opInternaFiscalSeq", sequenceName = "seq_operacao_interna_fiscal", allocationSize = 1)
	@GeneratedValue(generator = "opInternaFiscalSeq")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "operacao_interna_id")
	@JsonBackReference
	private OperacaoInterna operacaoInterna;

	@Convert(converter = DestinoOperacaoConverter.class)
	@Column(name = "destino_operacao")
	private DestinoOperacao destinoOperacao;

	@Convert(converter = FinalidadeNfeConverter.class)
	@Column(name = "finalidade_nfe")
	private FinalidadeNfe finalidadeNfe;

	@Convert(converter = TipoConsumidorConverter.class)
	@Column(name = "tipo_consumidor")
	private TipoConsumidor tipoConsumidor;

	@Convert(converter = TipoPresencaCompradorConverter.class)
	@Column(name = "tipo_presenca_comprador")
	private TipoPresencaComprador tipoPresencaComprador;

	@Transient
	private List<OperacaoInternaFiscalCfop> cfops;
}
