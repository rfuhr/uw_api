package br.com.ultraworks.erp.api.financeiro.domain.operacaocxbco;

import br.com.ultraworks.erp.api.financeiro.domain.fatogerador.FatoGerador;
import br.com.ultraworks.erp.api.financeiro.domain.tipocontacxbco.TipoContaCxBco;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocal.ParceiroLocal;
import br.com.ultraworks.erp.api.tabela.domain.historicopadrao.HistoricoPadrao;
import br.com.ultraworks.erp.api.tabela.domain.indicadorDC.IndicadorDC;
import br.com.ultraworks.erp.api.tabela.domain.indicadorDC.IndicadorDCConverter;
import br.com.ultraworks.erp.core.annotation.FriendlyName;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class OperacaoCaixaBancoDTO {

	private Long id;
	@NotNull
	@NotEmpty
	@Size(max = 250)
	@FriendlyName("Nome")
	private String nome;
	private String sigla;
	
	private boolean digitaVencimento;
	private boolean digitaDataMovimento;
	private boolean digitaHistoricoPadrao;
	private Long historicoPadraoIdDefault;
	private boolean digitaFatoGerador;
	private Long fatoGeradorIdDefault;
	private boolean digitaComplementoHp;
	private Long tipoContaCxBcoId;
	private String indicadorDC;
	private boolean digitaDocumento;
	private boolean digitaParceiro;
	private Long parceiroLocalIdDefault;
	private boolean transferencia;
	private Long operacaoCaixaBancoIdTransferencia;
	private boolean emiteRecibo;
}
