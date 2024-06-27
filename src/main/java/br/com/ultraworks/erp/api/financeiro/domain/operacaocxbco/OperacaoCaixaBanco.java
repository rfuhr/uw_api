package br.com.ultraworks.erp.api.financeiro.domain.operacaocxbco;

import br.com.ultraworks.erp.api.financeiro.domain.fatogerador.FatoGerador;
import br.com.ultraworks.erp.api.financeiro.domain.tipocontacxbco.TipoContaCxBco;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocal.ParceiroLocal;
import br.com.ultraworks.erp.api.tabela.domain.historicopadrao.HistoricoPadrao;
import br.com.ultraworks.erp.api.tabela.domain.indicadorDC.IndicadorDC;
import br.com.ultraworks.erp.api.tabela.domain.indicadorDC.IndicadorDCConverter;
import br.com.ultraworks.erp.core.entity.UWEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "operacao_cxbco")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class OperacaoCaixaBanco extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "operacaoCaixaBancoSeq", sequenceName = "seq_operacao_cxbco", allocationSize = 1)
	@GeneratedValue(generator = "operacaoCaixaBancoSeq")
	private Long id;

	private int codigo;
	private String nome;
	private String sigla;

	@Column(name = "digita_vencimento")
	private boolean digitaVencimento;

	@Column(name = "digita_data_mvtobco")
	private boolean digitaDataMovimento;

	@Column(name = "digita_hist_padrao")
	private boolean digitaHistoricoPadrao;

	@OneToOne
	@JoinColumn(name = "valor_default_hp_id")
	private HistoricoPadrao historicoPadraoDefault;

	@Column(name = "digita_fato_gerador")
	private boolean digitaFatoGerador;

	@OneToOne
	@JoinColumn(name = "valor_default_fg_id")
	private FatoGerador fatoGeradorDefault;

	@Column(name = "digita_compl_hp")
	private boolean digitaComplementoHp;

	@OneToOne
	@JoinColumn(name = "tipo_conta_cxbco_id")
	private TipoContaCxBco tipoContaCxBco;

	@Convert(converter = IndicadorDCConverter.class)
	@Column(name = "indicador_dc")
	private IndicadorDC indicadorDC;

	@Column(name = "digita_documento")
	private boolean digitaDocumento;

	@Column(name = "digita_parceiro")
	private boolean digitaParceiro;

	@OneToOne
	@JoinColumn(name = "valor_default_parceiro_local")
	private ParceiroLocal parceiroLocalDefault;

	@Column(name = "idn_transferencia")
	private boolean transferencia;
	
	@OneToOne
	@JoinColumn(name = "transf_operacao_cxbco_id")
	private OperacaoCaixaBanco operacaoCaixaBancoIdTransferencia;

	@Column(name = "emite_recibo")
	private boolean emiteRecibo;

}
