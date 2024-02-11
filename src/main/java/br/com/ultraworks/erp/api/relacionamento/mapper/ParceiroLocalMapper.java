package br.com.ultraworks.erp.api.relacionamento.mapper;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocal.ParceiroLocal;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocal.ParceiroLocalDTO;
import br.com.ultraworks.erp.api.relacionamento.repository.ParceiroLocalRepository;
import br.com.ultraworks.erp.api.relacionamento.repository.ParceiroRepository;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class ParceiroLocalMapper extends GenericMapper<ParceiroLocal, ParceiroLocalDTO> {

	private ParceiroRepository parceiroRepository;
	private ParceiroLocalFisicaMapper parceiroFisicaMapper;
	private ParceiroLocalJuridicaMapper parceiroJuridicaMapper;
	private ParceiroLocalTipoParceiroMapper parceiroLocalTipoParceiroMapper;
	private ParceiroLocalEnderecoMapper parceiroLocalEnderecoMapper;
	private ParceiroLocalTelefoneMapper parceiroLocalTelefoneMapper;
	private ParceiroLocalEmailMapper parceiroLocalEmailMapper;

	public ParceiroLocalMapper(ParceiroLocalRepository parceiroLocalRepository, ParceiroRepository parceiroRepository,
			ParceiroLocalFisicaMapper parceiroFisicaMapper, ParceiroLocalJuridicaMapper parceiroJuridicaMapper,
			ParceiroLocalTipoParceiroMapper parceiroLocalTipoParceiroMapper,
			ParceiroLocalEnderecoMapper parceiroLocalEnderecoMapper,
			ParceiroLocalTelefoneMapper parceiroLocalTelefoneMapper,
			ParceiroLocalEmailMapper parceiroLocalEmailMapper) {
		super(parceiroLocalRepository, ParceiroLocal::new, ParceiroLocalDTO::new);
		this.parceiroRepository = parceiroRepository;
		this.parceiroFisicaMapper = parceiroFisicaMapper;
		this.parceiroJuridicaMapper = parceiroJuridicaMapper;
		this.parceiroLocalTipoParceiroMapper = parceiroLocalTipoParceiroMapper;
		this.parceiroLocalEnderecoMapper = parceiroLocalEnderecoMapper;
		this.parceiroLocalTelefoneMapper = parceiroLocalTelefoneMapper;
		this.parceiroLocalEmailMapper = parceiroLocalEmailMapper;
	}

	@Override
	protected void setValuesToEntity(ParceiroLocalDTO dto, ParceiroLocal entity) {
		entity.setId(dto.getId());
		if (dto.getParceiroId() != null) {
			entity.setParceiro(parceiroRepository.findById(dto.getParceiroId()).orElseThrow(
					() -> new RegisterNotFoundException("Não encontrado parceiro com id " + dto.getParceiroId())));
		} 
		entity.setCpfCnpj(dto.getCpfCnpj());
		entity.setNomeLocal(dto.getNomeLocal());
		if (dto.getDadosPessoaFisica() != null) {
			entity.getDadosPessoaFisica().clear();
			entity.getDadosPessoaFisica().add(parceiroFisicaMapper.toEntity(dto.getDadosPessoaFisica()));
			entity.getDadosPessoaFisica().forEach(dado -> {
				if (dado.getParceiroLocal() == null) dado.setParceiroLocal(entity);
			});
		}
		if (dto.getDadosPessoaJuridica() != null) {
			entity.getDadosPessoaJuridica().clear();
			entity.getDadosPessoaJuridica().add(parceiroJuridicaMapper.toEntity(dto.getDadosPessoaJuridica()));
			entity.getDadosPessoaJuridica().forEach(dado -> {
				if (dado.getParceiroLocal() == null) dado.setParceiroLocal(entity);
			});
		}
		if (dto.getTiposParceiro() != null) {
			entity.setTiposParceiro(new ArrayList<>());
			entity.getTiposParceiro().addAll(parceiroLocalTipoParceiroMapper.toEntity(dto.getTiposParceiro()));
		}
		if (dto.getEnderecos() != null && dto.getEnderecos().size() > 0) {
			entity.setEnderecos(new ArrayList<>());
			entity.getEnderecos().addAll(parceiroLocalEnderecoMapper.toEntity(dto.getEnderecos()));
		}
		if (dto.getTelefones() != null && dto.getTelefones().size() > 0) {
			entity.setTelefones(new ArrayList<>());
			entity.getTelefones().addAll(parceiroLocalTelefoneMapper.toEntity(dto.getTelefones()));
		}
		if (dto.getEmails() != null && dto.getEmails().size() > 0) {
			entity.setEmails(new ArrayList<>());
			entity.getEmails().addAll(parceiroLocalEmailMapper.toEntity(dto.getEmails()));
		}

	}

	@Override
	protected void setValuesToDto(ParceiroLocal entity, ParceiroLocalDTO dto) {
		dto.setId(entity.getId());
		dto.setParceiroId(entity.getParceiro().getId());
		dto.setCpfCnpj(entity.getCpfCnpj());
		dto.setNomeLocal(entity.getNomeLocal());
		if (entity.getDadosPessoaFisica() != null && entity.getDadosPessoaFisica().size() > 0) {
			dto.setDadosPessoaFisica(parceiroFisicaMapper.toDto(entity.getDadosPessoaFisica()).get(0));
		}
		if (entity.getDadosPessoaJuridica() != null && entity.getDadosPessoaJuridica().size() > 0) {
			dto.setDadosPessoaJuridica(parceiroJuridicaMapper.toDto(entity.getDadosPessoaJuridica()).get(0));
		}
		dto.setEnderecos(new ArrayList<>());
		if (entity.getEnderecos() != null) {
			dto.getEnderecos().addAll(parceiroLocalEnderecoMapper.toDto(entity.getEnderecos()));
		}
		dto.setTelefones(new ArrayList<>());
		if (entity.getTelefones() != null) {
			dto.getTelefones().addAll(parceiroLocalTelefoneMapper.toDto(entity.getTelefones()));
		}
		dto.setEmails(new ArrayList<>());
		if (entity.getEmails() != null) {
			dto.getEmails().addAll(parceiroLocalEmailMapper.toDto(entity.getEmails()));
		}
	}
}
