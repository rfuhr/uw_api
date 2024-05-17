package br.com.ultraworks.erp.core.security.domain;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Data;

@Data
public class CustomUser extends User {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Long empresaId;
	private Long empresaFilialId;
		
	public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}

}
