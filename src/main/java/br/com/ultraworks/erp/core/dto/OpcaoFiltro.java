package br.com.ultraworks.erp.core.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OpcaoFiltro implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8927447202412895233L;

	private String value;

	private String matchMode;

	private String tipo;

	private String fieldFilter;
	
}
