package br.com.ultraworks.erp.api.agricola.domain.romaneioagricolanota;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.ultraworks.erp.api.agricola.domain.romaneioagricola.RomaneioAgricola;
import br.com.ultraworks.erp.api.fiscal.domain.cfop.Cfop;
import br.com.ultraworks.erp.api.tabela.domain.operacaointerna.OperacaoInterna;
import br.com.ultraworks.erp.api.tabela.domain.tipodocumento.TipoDocumento;
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
@Table(name = "romaneio_agricola_nota")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class RomaneioAgricolaNota extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "romaneioAgricolaNotaSeq", sequenceName = "seq_romaneio_agricola_nota", allocationSize = 1)
	@GeneratedValue(generator = "romaneioAgricolaNotaSeq")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "romaneio_agricola_id")
	private RomaneioAgricola romaneioAgricola;

	@OneToOne
	@JoinColumn(name = "operacao_interna_id")
	private OperacaoInterna operacaoInterna;

	@OneToOne
	@JoinColumn(name = "cfop_id")
	private Cfop cfop;

	@Column(name = "numero_nota")
	private int numeroNota;

	@Column(name = "serie")
	private int serie;

	@Column(name = "data_emissao")
	private LocalDate dataEmissao;

	@Column(name = "chave_nfe")
	private String chaveNFe;

	@OneToOne
	@JoinColumn(name = "tipo_documento_id")
	private TipoDocumento tipoDocumento;

	@Column(name = "quantidade")
	private BigDecimal quantidade;

	@Column(name = "valor_unitario")
	private BigDecimal valorUnitario;

	@Column(name = "valor_total")
	private BigDecimal valorTotal;

}
