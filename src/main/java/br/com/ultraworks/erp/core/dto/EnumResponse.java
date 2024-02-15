package br.com.ultraworks.erp.core.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EnumResponse {

	String name;
	String value;
}
