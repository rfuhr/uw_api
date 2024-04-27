package br.com.ultraworks.erp.api.fiscal.domain.nfe.request;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ItensNFeRequest {

	private List<ItemNFeRequest> itens;
}
