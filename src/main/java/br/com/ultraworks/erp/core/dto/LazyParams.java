package br.com.ultraworks.erp.core.dto;

import java.io.Serializable;
import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class LazyParams implements Serializable {

	private static final long serialVersionUID = 1L;

	private HashMap<String, OpcaoFiltro> filters;

	private Integer first;

	private Integer last;

	private Integer rows;

	private String sortField;

	private Integer sortOrder;
	
	private Integer page;
	
	private Long id;

	public LazyParams() {
	}
	
	
}
