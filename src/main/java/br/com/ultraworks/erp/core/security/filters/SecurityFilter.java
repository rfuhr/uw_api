package br.com.ultraworks.erp.core.security.filters;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import br.com.ultraworks.erp.core.multitenancy.util.TenantContext;
import br.com.ultraworks.erp.core.security.domain.CustomUser;
import br.com.ultraworks.erp.core.security.domain.token.Token;
import br.com.ultraworks.erp.core.security.domain.user.User;
import br.com.ultraworks.erp.core.security.helper.JwtHelper;
import br.com.ultraworks.erp.core.security.repository.TokenRepository;
import br.com.ultraworks.erp.core.security.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

	private JwtHelper jwtHelper;
	private UserRepository userRepository;
	private TokenRepository tokenRepository;
	private final RequestMappingHandlerMapping handlerMapping;
	private final HandlerExceptionResolver handlerExceptionResolver;

	@Autowired
	public SecurityFilter(UserRepository userRepository, JwtHelper jwtHelper, TokenRepository tokenRepository,
			@Qualifier("requestMappingHandlerMapping") RequestMappingHandlerMapping handlerMapping, HandlerExceptionResolver handlerExceptionResolver) {
		this.jwtHelper = jwtHelper;
		this.userRepository = userRepository;
		this.tokenRepository = tokenRepository;
		this.handlerMapping = handlerMapping;
		this.handlerExceptionResolver = handlerExceptionResolver;
	}

	@Override
	protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
			@NonNull FilterChain filterChain) throws ServletException, IOException {

		try {

			Optional<String> accessToken = parseAccessToken(request);
			if (accessToken.isPresent() && jwtHelper.validateAccessToken(accessToken.get())) {
				String userId = jwtHelper.getUserIdFromAccessToken(accessToken.get());
				BigDecimal tokenId = jwtHelper.getTokenIdFromAccessToken(accessToken.get());
				String tenant = request.getHeader("X-Tenant-Id");
				if (null == tenant) {
					throw new BadCredentialsException("Invalid tenant and user.");
				}
				TenantContext.setTenantId(tenant);
				Optional<User> user = userRepository.findById(new BigDecimal(userId));
				Optional<Token> token = tokenRepository.findById(tokenId);
				if (user.isPresent() && token.isPresent()) {
					Long empresaId = Long.parseLong(request.getHeader("X-Empresa-Id"));
					Long empresaFilialId = Long.parseLong(request.getHeader("X-Empresa-Filial-Id"));
					CustomUser customUser = new CustomUser(user.get().getUsername(), user.get().getPassword(), user.get().getAuthorities());
					customUser.setId(user.get().getId().longValue());
					customUser.setEmpresaId(empresaId);
					customUser.setEmpresaFilialId(empresaFilialId);
					
					UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(customUser, null,
							user.get().getAuthorities());
					upat.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(upat);
				}

			}

			filterChain.doFilter(request, response);
		} catch (final Exception exception) {

			handlerExceptionResolver.resolveException(request, response, null, exception);
		}

	}

	private Optional<String> parseAccessToken(HttpServletRequest request) {
		String authHeader = request.getHeader("Authorization");
		if (StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) {
			return Optional.of(authHeader.replace("Bearer ", ""));
		}
		return Optional.empty();
	}

}
