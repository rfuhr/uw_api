package br.com.ultraworks.erp.core.security.domain.token;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class TokenResponse {

	private Long userId;
	private String token;
	private String refreshToken;

	
}
