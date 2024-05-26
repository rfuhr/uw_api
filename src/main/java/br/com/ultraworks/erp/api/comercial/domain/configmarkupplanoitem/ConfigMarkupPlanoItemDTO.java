package br.com.ultraworks.erp.api.comercial.domain.configmarkupplanoitem;

import java.time.LocalDate;
import java.util.List;

import br.com.ultraworks.erp.api.comercial.domain.configmarkupplanoitemindice.ConfigMarkupPlanoItemIndiceDTO;
import jakarta.validation.Valid;
import lombok.Data;

@Data
public class ConfigMarkupPlanoItemDTO {

	private Long id;
	private Long planoClassificacaoItemId;
	private String planoClassificacaoItemNome;
	private String planoClassificacaoItemCodigo;
	
	private LocalDate dataInicioVigencia;
	private LocalDate dataFinalVigencia;
	
	@Valid
	private List<ConfigMarkupPlanoItemIndiceDTO> configMarkupPlanoItemIndices;
}
