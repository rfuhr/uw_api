package br.com.ultraworks.erp.core.dto;

import java.util.List;

import br.com.ultraworks.erp.core.interfaces.IDataEstruturaContaResponse;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EstruturaContaResponse {

	private Long key;
	private IDataEstruturaContaResponse data;
	private List<EstruturaContaResponse> children;
	
}
