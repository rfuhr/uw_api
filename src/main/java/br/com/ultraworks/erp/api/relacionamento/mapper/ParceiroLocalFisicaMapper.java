package br.com.ultraworks.erp.api.relacionamento.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.relacionamento.domain.parceiro.Parceiro;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiro.ParceiroDTO;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocal.ParceiroLocal;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocalFisica.ParceiroLocalFisica;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocalFisica.ParceiroLocalFisicaDTO;
import br.com.ultraworks.erp.api.relacionamento.repository.ParceiroLocalFisicaRepository;
import br.com.ultraworks.erp.api.relacionamento.repository.ParceiroLocalRepository;
import br.com.ultraworks.erp.api.relacionamento.repository.ParceiroRepository;
import br.com.ultraworks.erp.api.tabela.domain.tipopessoa.TipoPessoa;
import br.com.ultraworks.erp.api.tabela.repository.EstadoCivilRepository;
import br.com.ultraworks.erp.api.tabela.repository.NacionalidadeRepository;
import br.com.ultraworks.erp.api.tabela.repository.ProfissaoRepository;
import br.com.ultraworks.erp.api.tabela.repository.SexoRepository;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class ParceiroLocalFisicaMapper extends GenericMapper<ParceiroLocalFisica, ParceiroLocalFisicaDTO> {

	private ParceiroLocalRepository parceiroLocalRepository;
	private SexoRepository sexoRepository;
	private NacionalidadeRepository nacionalidadeRepository;
	private ProfissaoRepository profissaoRepository;
	private EstadoCivilRepository estadoCivilRepository;
	private ParceiroRepository parceiroRepository;

	public ParceiroLocalFisicaMapper(ParceiroLocalFisicaRepository parceiroFisicaRepository,
			ParceiroLocalRepository parceiroLocalRepository, SexoRepository sexoRepository,
			NacionalidadeRepository nacionalidadeRepository, ProfissaoRepository profissaoRepository,
			EstadoCivilRepository estadoCivilRepository, ParceiroRepository parceiroRepository) {
		super(parceiroFisicaRepository, ParceiroLocalFisica::new, ParceiroLocalFisicaDTO::new);
		this.parceiroLocalRepository = parceiroLocalRepository;
		this.sexoRepository = sexoRepository;
		this.nacionalidadeRepository = nacionalidadeRepository;
		this.profissaoRepository = profissaoRepository;
		this.estadoCivilRepository = estadoCivilRepository;
		this.parceiroRepository = parceiroRepository;
	}

	@Override
	protected void setValuesToEntity(ParceiroLocalFisicaDTO dto, ParceiroLocalFisica entity) {
		entity.setId(dto.getId());
		if (dto.getParceiroLocalId() != null) {
			entity.setParceiroLocal(parceiroLocalRepository.findById(dto.getParceiroLocalId())
					.orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado local de parceiro com id " + dto.getParceiroLocalId())));
		}
		entity.setSexo(sexoRepository.findById(dto.getSexoId())
				.orElseThrow(() -> new RegisterNotFoundException("Não encontrado sexo com id " + dto.getSexoId())));
		entity.setRg(dto.getRg());
		entity.setNacionalidade(nacionalidadeRepository.findById(dto.getNacionalidadeId())
				.orElseThrow(() -> new RegisterNotFoundException(
						"Não encontrado nacionalidade com id " + dto.getNacionalidadeId())));
		entity.setProfissao(profissaoRepository.findById(dto.getProfissaoId()).orElseThrow(
				() -> new RegisterNotFoundException("Não encontrado profissão com id " + dto.getProfissaoId())));
		entity.setEstadoCivil(estadoCivilRepository.findById(dto.getEstadoCivilId()).orElseThrow(
				() -> new RegisterNotFoundException("Não encontrado estado civil com id " + dto.getEstadoCivilId())));
		entity.setDataNascimento(dto.getDataNascimento());
	}

	@Override
	protected void setValuesToDto(ParceiroLocalFisica entity, ParceiroLocalFisicaDTO dto) {
		dto.setId(entity.getId());
		dto.setParceiroLocalId(entity.getParceiroLocal().getId());
		dto.setSexoId(entity.getSexo() != null ? entity.getSexo().getId() : null );
		dto.setSexoNome(entity.getSexo() != null ? entity.getSexo().getNome() : null );
		dto.setRg(entity.getRg());
		dto.setNacionalidadeId(entity.getNacionalidade() != null ? entity.getNacionalidade().getId() : null);
		dto.setNacionalidadeNome(entity.getNacionalidade() != null ? entity.getNacionalidade().getNome() : null);
		dto.setProfissaoId(entity.getProfissao() != null ? entity.getProfissao().getId() : null);
		dto.setProfissaoNome(entity.getProfissao() != null ? entity.getProfissao().getNome() : null);
		dto.setEstadoCivilId(entity.getEstadoCivil() != null ? entity.getEstadoCivil().getId() : null);
		dto.setEstadoCivilNome(entity.getEstadoCivil() != null ? entity.getEstadoCivil().getNome() : null);
		dto.setDataNascimento(entity.getDataNascimento());
	}
}
