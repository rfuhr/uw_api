package br.com.ultraworks.erp.api.seguranca.mapper;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.seguranca.domain.usuario.Usuario;
import br.com.ultraworks.erp.api.seguranca.domain.usuario.UsuarioDTO;
import br.com.ultraworks.erp.api.seguranca.repository.UsuarioRepository;
import br.com.ultraworks.erp.core.mapper.GenericMapper;
import br.com.ultraworks.erp.core.security.domain.user.User;
import br.com.ultraworks.erp.core.security.repository.UserRepository;

@Component
public class UsuarioMapper extends GenericMapper<Usuario, UsuarioDTO> {

	UserRepository userRepository;
	
	public UsuarioMapper(UsuarioRepository repository, UserRepository userRepository) {
		super(repository, Usuario::new, UsuarioDTO::new);
		this.userRepository = userRepository;
		
    }

	@Override
	protected void setValuesToEntity(UsuarioDTO dto, Usuario entity) {
		entity.setId(dto.getId());
		entity.setNome(dto.getNome());
		entity.setEmail(dto.getEmail());
		entity.setAdmin(dto.isAdmin());
		entity.setAtivo(dto.isAtivo());
	}

	@Override
	protected void setValuesToDto(Usuario entity, UsuarioDTO dto) {
		dto.setId(entity.getId());
		dto.setId(entity.getId());
		dto.setNome(entity.getNome());
		dto.setEmail(entity.getEmail());
		dto.setAdmin(entity.isAdmin());
		dto.setAtivo(entity.isAtivo());
		
		Optional<User> userOptional = userRepository.findById(BigDecimal.valueOf(entity.getUserId()));
		if (userOptional.isPresent())
			dto.setUsername(userOptional.get().getUsername());
		
	}
}
