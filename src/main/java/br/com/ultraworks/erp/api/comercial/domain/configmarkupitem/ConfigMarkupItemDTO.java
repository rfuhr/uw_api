package br.com.ultraworks.erp.api.comercial.domain.configmarkupitem;

import java.time.LocalDate;
import java.util.List;

import br.com.ultraworks.erp.api.comercial.domain.configmarkupitemindice.ConfigMarkupItemIndiceDTO;
import jakarta.validation.Valid;
import lombok.Data;

@Data
public class ConfigMarkupItemDTO {

	private Long id;
	private Long itemId;
	private String itemNome;
	private int itemCodigo;
	
	private LocalDate dataInicioVigencia;
	private LocalDate dataFinalVigencia;
	
	@Valid
	private List<ConfigMarkupItemIndiceDTO> configMarkupItemIndices;
}
