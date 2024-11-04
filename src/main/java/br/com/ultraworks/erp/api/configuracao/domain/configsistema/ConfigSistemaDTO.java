package br.com.ultraworks.erp.api.configuracao.domain.configsistema;

import java.util.List;

import br.com.ultraworks.erp.api.configuracao.domain.configsistemaagricola.ConfigSistemaAgricolaDTO;
import br.com.ultraworks.erp.api.configuracao.domain.configsistemacompra.ConfigSistemaCompraDTO;
import br.com.ultraworks.erp.api.configuracao.domain.configsistemafinanceiro.ConfigSistemaFinanceiroDTO;
import jakarta.validation.Valid;
import lombok.Data;

@Data
public class ConfigSistemaDTO {

	private Long id;

	@Valid
	private List<ConfigSistemaFinanceiroDTO> configuracoesFinanceiro;

	@Valid
	private List<ConfigSistemaAgricolaDTO> configuracoesAgricola;

	@Valid
	private List<ConfigSistemaCompraDTO> configuracoesCompra;
}
