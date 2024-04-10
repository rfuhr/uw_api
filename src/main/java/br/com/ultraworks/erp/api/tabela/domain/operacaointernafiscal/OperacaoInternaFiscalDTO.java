package br.com.ultraworks.erp.api.tabela.domain.operacaointernafiscal;

import java.util.List;

import br.com.ultraworks.erp.api.tabela.domain.operacaointernafiscalcfop.OperacaoInternaFiscalCfopDTO;
import lombok.Data;

@Data
public class OperacaoInternaFiscalDTO {

	private Long id;
	private Long operacaoInternaId;
	private String destinoOperacao;
	private String finalidadeNfe;
	private String tipoConsumidor;
	private String tipoPresencaComprador;
	private List<OperacaoInternaFiscalCfopDTO> cfops;
}
