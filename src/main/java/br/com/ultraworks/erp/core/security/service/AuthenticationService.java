package br.com.ultraworks.erp.core.security.service;

import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.core.security.domain.token.Token;
import br.com.ultraworks.erp.core.security.domain.token.TokenResponse;
import br.com.ultraworks.erp.core.security.domain.user.AuthenticationRequest;
import br.com.ultraworks.erp.core.security.domain.user.User;
import br.com.ultraworks.erp.core.security.helper.JwtHelper;
import br.com.ultraworks.erp.core.security.repository.TokenRepository;
import br.com.ultraworks.erp.core.security.repository.UserRepository;

@Service
public class AuthenticationService {

	private final AuthenticationManager authenticationManager;
	private final TokenRepository tokenRepository;
	private final JwtHelper jwtHelper;
	private final UserRepository userRepository;

	public AuthenticationService(AuthenticationManager authenticationManager, TokenRepository tokenRepository, JwtHelper jwtHelper, UserRepository userRepository) {
		super();
		this.authenticationManager = authenticationManager;
		this.tokenRepository = tokenRepository;
		this.jwtHelper = jwtHelper;
		this.userRepository = userRepository;
	}

	public TokenResponse authenticate(AuthenticationRequest request) {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		User user = (User) authentication.getPrincipal();
		
		tokenRepository.deleteByOwnerId(user.getId());
		
		Token token = new Token(user);
		token = tokenRepository.save(token);
		
		String jwtToken = jwtHelper.generateAccessToken(user, token);

		
		String refreshTokenString = jwtHelper.generateRefreshToken(user, token);
		
		token.setToken(jwtToken);
		token.setRefreshToken(refreshTokenString);
		tokenRepository.saveAndFlush(token);
		
		return new TokenResponse(user.getId().longValue(), jwtToken, refreshTokenString);
	}
	
	public boolean logout(String token) {
		if (tokenRepository.existsById(jwtHelper.getTokenIdFromAccessToken(token))) {
			tokenRepository.deleteById(jwtHelper.getTokenIdFromAccessToken(token));
			return true;
		}
		return false;
	}
	
	public Optional<TokenResponse> refreshToken(String refreshToken) {
		if (jwtHelper.validateRefreshToken(refreshToken)
				&& tokenRepository.existsById(jwtHelper.getTokenIdFromRefreshToken(refreshToken))) {

			tokenRepository.deleteById(jwtHelper.getTokenIdFromRefreshToken(refreshToken));

			Optional<User> user = userRepository.findById(jwtHelper.getUserIdFromRefreshToken(refreshToken));
			if (user.isPresent()) {
				Token token = new Token(user.get());
				tokenRepository.save(token);
				
				String accessToken = jwtHelper.generateAccessToken(user.get(), token);
				String newRefreshTokenString = jwtHelper.generateRefreshToken(user.get(), token);

				token.setToken(accessToken);
				token.setRefreshToken(newRefreshTokenString);
				tokenRepository.saveAndFlush(token);
				
				return Optional.of(new TokenResponse(user.get().getId().longValue(), accessToken, newRefreshTokenString));
			}
		}

		return Optional.empty();
	}
	
}
