package br.com.ultraworks.erp.api.fiscal.integrator.nfe.dto;

import java.time.LocalDateTime;

import br.com.ultraworks.erp.api.fiscal.domain.destinooperacao.DestinoOperacao;
import br.com.ultraworks.erp.api.fiscal.domain.finalidadenfe.FinalidadeNfe;
import br.com.ultraworks.erp.api.fiscal.domain.tipoconsumidor.TipoConsumidor;
import br.com.ultraworks.erp.api.fiscal.domain.tipoimpressaodanfe.TipoImpressaoDanfe;
import br.com.ultraworks.erp.api.fiscal.domain.tipointermediador.TipoIntermediador;
import br.com.ultraworks.erp.api.fiscal.domain.tipopresencacomprador.TipoPresencaComprador;
import br.com.ultraworks.erp.api.fiscal.domain.tipoprocessoemissao.TipoProcessoEmissao;
import br.com.ultraworks.erp.api.tabela.domain.cidade.Cidade;
import br.com.ultraworks.erp.api.tabela.domain.indicadoroperacao.IndicadorOperacao;
import br.com.ultraworks.erp.api.tabela.domain.tipodocumento.TipoDocumento;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContainerIntegracaoNFeIdentificacao {

	private TipoDocumento tipoDocumento;
	private int serie;
	private int numeroNFe;
	private String cnf;
	private LocalDateTime dataEmissao;
	
	private String naturezaOperacao;
	private LocalDateTime dataHoraSaidaEntrada;
	private IndicadorOperacao indicadorOperacao;
	private DestinoOperacao localDestinoOperacao;
	private Cidade municipioOcorrenciaFatoGeradorICMS;
	private TipoImpressaoDanfe tipoImpressaoDanfe;
	private FinalidadeNfe finalidadeNfe;
	private TipoConsumidor tipoConsumidor;
	private TipoPresencaComprador tipoPresencaComprador;
	private TipoIntermediador tipoIntermediador;
	private TipoProcessoEmissao processoEmissao;
}
