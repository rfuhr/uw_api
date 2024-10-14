package br.com.ultraworks.erp.api.tabela.domain.operacaointernaagricola;

import br.com.ultraworks.erp.api.agricola.domain.identificacaodocumentoagricola.validator.ValidaIdentificacaoDocumentoAgricola;
import lombok.Data;

@Data
public class OperacaoInternaAgricolaDTO {

	private Long id;
	private Long operacaoInternaId;
	@ValidaIdentificacaoDocumentoAgricola
	private String identificacaoDocumentoAgricola;
	private boolean exigeNotaEntrada;
	private boolean fixaAutomatico;
	private boolean complementoPrecoFixacao;
	private boolean contratoAvista;
	private Long tipoPrecoAgricolaId;
	private Long tipoContratoAgricolaId;

	private String identificacaoDocumentoAgricolaNome;
	private String tipoPrecoAgricolaNome;
	private String tipoContratoAgricolaNome;
}
