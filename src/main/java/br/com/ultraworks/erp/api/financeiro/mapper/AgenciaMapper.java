package br.com.ultraworks.erp.api.financeiro.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.financeiro.domain.agencia.Agencia;
import br.com.ultraworks.erp.api.financeiro.domain.agencia.AgenciaDTO;
import br.com.ultraworks.erp.api.financeiro.repository.AgenciaRepository;
import br.com.ultraworks.erp.api.financeiro.repository.BancoRepository;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class AgenciaMapper extends GenericMapper<Agencia, AgenciaDTO> {

	private BancoRepository bancoRepository;

	public AgenciaMapper(AgenciaRepository repository, BancoRepository bancoRepository) {
		super(repository, Agencia::new, AgenciaDTO::new);
		this.bancoRepository = bancoRepository;
	}

	@Override
	protected void setValuesToEntity(AgenciaDTO dto, Agencia entity) {
		entity.setId(dto.getId());
		entity.setBanco(bancoRepository.findById(dto.getBancoId())
				.orElseThrow(() -> new RegisterNotFoundException("Não encontrado banco com id " + dto.getBancoId())));
		entity.setDv(dto.getDv());
		entity.setCodigo(dto.getCodigo());
		entity.setNome(dto.getNome());
	}

	@Override
	protected void setValuesToDto(Agencia entity, AgenciaDTO dto) {
		dto.setId(entity.getId());
		dto.setBancoId(entity.getBanco().getId());
		dto.setBancoNome(entity.getBanco().getNome());
		dto.setDv(entity.getDv());
		dto.setCodigo(entity.getCodigo());
		dto.setNome(entity.getNome());
	}
}
