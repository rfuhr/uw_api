package br.com.ultraworks.erp.api.fiscal.domain.nfe;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChacheNFeRequest {

	private Long nfeId;
	private String cache;
}
