package br.com.ultraworks.erp.api.financeiro.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.financeiro.domain.conta.Conta;
import br.com.ultraworks.erp.api.financeiro.domain.conta.ContaDTO;
import br.com.ultraworks.erp.api.financeiro.repository.AgenciaRepository;
import br.com.ultraworks.erp.api.financeiro.repository.ContaRepository;
import br.com.ultraworks.erp.api.financeiro.repository.TipoContaCxBcoRepository;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class ContaMapper extends GenericMapper<Conta, ContaDTO> {

	private AgenciaRepository agenciaRepository;
	private TipoContaCxBcoRepository tipoContaCxBcoRepository;

	public ContaMapper(ContaRepository repository, AgenciaRepository agenciaRepository,
			TipoContaCxBcoRepository tipoContaCxBcoRepository) {
		super(repository, Conta::new, ContaDTO::new);
		this.agenciaRepository = agenciaRepository;
		this.tipoContaCxBcoRepository = tipoContaCxBcoRepository;
	}

	@Override
	protected void setValuesToEntity(ContaDTO dto, Conta entity) {
		entity.setId(dto.getId());
		entity.setAgencia(agenciaRepository.findById(dto.getAgenciaId()).orElseThrow(
				() -> new RegisterNotFoundException("Não encontrado agência com id " + dto.getAgenciaId())));
		entity.setNumero(dto.getNumero());
		entity.setDv(dto.getDv());
		entity.setTipoContaCxBco(tipoContaCxBcoRepository.findById(dto.getTipoContaCxBcoId())
				.orElseThrow(() -> new RegisterNotFoundException(
						"Não encontrado tipo de conta com id " + dto.getTipoContaCxBcoId())));
		entity.setNome(dto.getNome());
	}

	@Override
	protected void setValuesToDto(Conta entity, ContaDTO dto) {
		dto.setId(entity.getId());
		dto.setAgenciaId(entity.getAgencia().getId());
		dto.setAgenciaNome(entity.getAgencia().getNome());
		dto.setBancoNome(entity.getAgencia().getBanco().getNome());
		dto.setNumero(entity.getNumero());
		dto.setDv(entity.getDv());
		dto.setTipoContaCxBcoId(entity.getTipoContaCxBco().getId());
		dto.setNome(entity.getNome());
	}
}
