package br.com.ultraworks.erp.api.fiscal.domain.situacaotributaria;

import br.com.ultraworks.erp.api.fiscal.domain.tipotributo.validador.ValidaTipoTributo;
import br.com.ultraworks.erp.core.annotation.FriendlyName;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SituacaoTributariaDTO {

	private Long id;
	@NotNull
	@NotEmpty
	@Size(max = 250)
	@FriendlyName("Nome")
	private String nome;
	private int codigo;
	
	@ValidaTipoTributo
	private String tipoTributo;

	private boolean simplesNacional;
	private boolean aliquotaZero;
	private boolean requerMensagemFiscal;
	private boolean destacaStSaida;
	private boolean destacaIcmsDesonerada;
	private boolean controlaImpostoRetido;
	private boolean destacaIcms;
	private boolean excluirIcmsBcPiscofins;
	private boolean naoExcluirIcmsEntrada;
}
