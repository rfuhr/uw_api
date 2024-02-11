package br.com.ultraworks.erp.core.security.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.core.security.domain.token.TokenResponse;
import br.com.ultraworks.erp.core.security.domain.user.AuthenticationRequest;
import br.com.ultraworks.erp.core.security.service.AuthenticationService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private final AuthenticationService authenticationService;
	
	
	public AuthController(AuthenticationService authenticationService) {
		super();
		this.authenticationService = authenticationService;
	}


	@PostMapping("/login")
	@Transactional
	public ResponseEntity<TokenResponse> login(@RequestBody @Valid AuthenticationRequest authRequest) {
		return ResponseEntity.ok(authenticationService.authenticate(authRequest));
	}
	
	@PostMapping("/logout")
	@Transactional
    public ResponseEntity<?> logout(@RequestHeader("Authorization") String authorizationHeader) {
		String token = extractTokenFromAuthorizationHeader(authorizationHeader); 
		if (authenticationService.logout(token)) {
			return ResponseEntity.ok().build();
		}
		
		throw new BadCredentialsException("invalid token");
	}
	
	@PostMapping("/refresh-token")
	@Transactional
    public ResponseEntity<?> refreshToken(@RequestHeader("X-Refresh-Token") String refreshToken) {
	
		Optional<TokenResponse> tokenResponse = authenticationService.refreshToken(refreshToken);
		if (tokenResponse.isPresent()) {
			return ResponseEntity.ok(tokenResponse.get());
		}

        throw new BadCredentialsException("invalid token");
    }
	
	private String extractTokenFromAuthorizationHeader(String authorizationHeader) {
	    if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
	        String[] parts = authorizationHeader.split(" ");
	        if (parts.length == 2) {
	            return parts[1];
	        }
	    }
	    return null;
	}
}
