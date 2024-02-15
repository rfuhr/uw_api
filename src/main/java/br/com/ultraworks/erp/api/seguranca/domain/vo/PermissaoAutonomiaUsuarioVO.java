package br.com.ultraworks.erp.api.seguranca.domain.vo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PermissaoAutonomiaUsuarioVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long autonomiaId;
	private int order;
}
