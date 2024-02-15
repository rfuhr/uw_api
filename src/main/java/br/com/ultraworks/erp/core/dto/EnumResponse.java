package br.com.ultraworks.erp.core.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ENumResponse {

	private String name;
	private String value;
}
