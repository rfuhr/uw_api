package br.com.ultraworks.erp.api.fiscal.domain.nfe.request;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class IdentificacaoNFeRequest {

	private Long departamentoId;
	private Long operacaoInternaId;
	private String indicadorOperacao;
	private String naturezaOperacao;
	private String destinoOperacao;
	private String finalidadeNFe;
	private String tipoEmissao;
	private String tipoConsumidor;
	private String tipoPresencaComprador;
	private Long   cfopId;
	
	private int numero;
	private int serie;
	private String chaveNFe;
	private int modeloNFe;
	private LocalDateTime dataHoraEmissao;
	private LocalDateTime dataHoraSaidaEntrada;
	private Long regimeTributarioId;
	
	private boolean possuiDocumentoReferenciado;
	private boolean outroLocalRetirada;
	private boolean autorizarObterXml;
	
	private LocalRetiradaRequest localRetirada;
	private DocumentosReferenciadosRequest documentosReferenciados;
	private AutorizacoesRequest autorizacoes;
	
}
