package br.com.ultraworks.erp.core.security.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import br.com.ultraworks.erp.core.security.components.AccessTokenEntryPoint;
import br.com.ultraworks.erp.core.security.filters.SecurityFilter;

@Configuration
@EnableWebSecurity
public class WebSecurity {

	private final AuthenticationProvider authenticationProvider;
	private final AccessTokenEntryPoint accessTokenEntryPoint;
	private final SecurityFilter securityFilter;
	private CorsConfigurationSource corsConfigurationSource;
	
	
	public WebSecurity(AuthenticationProvider authenticationProvider, AccessTokenEntryPoint accessTokenEntryPoint, SecurityFilter securityFilter,  CorsConfigurationSource corsConfigurationSource) {
		super();
		this.authenticationProvider = authenticationProvider;
		this.accessTokenEntryPoint = accessTokenEntryPoint;
		this.securityFilter = securityFilter;
		this.corsConfigurationSource = corsConfigurationSource;
	}


	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity
				.cors(cors -> cors.configurationSource(corsConfigurationSource))
				.csrf(csrf -> csrf.disable())
				.exceptionHandling(exception -> exception.authenticationEntryPoint(accessTokenEntryPoint))
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(authorize -> authorize
						.requestMatchers(HttpMethod.POST, "tenant/new").permitAll()
						.requestMatchers(HttpMethod.POST, "tenant/checkdatabase").permitAll()
						.requestMatchers(HttpMethod.POST, "auth/login").permitAll()
						.requestMatchers(HttpMethod.GET, "auth/logout").permitAll()
						.anyRequest().authenticated())
				.authenticationProvider(authenticationProvider)
				.addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
				.build();
	}
	
	@Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOriginPattern("*");
        configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","PATCH","DELETE","OPTIONS"));
        configuration.setAllowCredentials(false);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
	
}
