package br.com.ultraworks.erp.api.compras.mapper;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.compras.domain.configautorizacaosolicitacaomercadoria.ConfigAutorizacaoSolicitacaoMercadoria;
import br.com.ultraworks.erp.api.compras.domain.configautorizacaosolicitacaomercadoria.ConfigAutorizacaoSolicitacaoMercadoriaDTO;
import br.com.ultraworks.erp.api.compras.repository.ConfigAutorizacaoSolicitacaoMercadoriaRepository;
import br.com.ultraworks.erp.api.estoque.repository.GrupoContabilRepository;
import br.com.ultraworks.erp.api.organograma.repository.DepartamentoRepository;
import br.com.ultraworks.erp.api.organograma.repository.EmpresaFilialRepository;
import br.com.ultraworks.erp.api.organograma.repository.EmpresaRepository;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class ConfigAutorizacaoSolicitacaoMercadoriaMapper
		extends GenericMapper<ConfigAutorizacaoSolicitacaoMercadoria, ConfigAutorizacaoSolicitacaoMercadoriaDTO> {

	private EmpresaRepository empresaRepository;
	private EmpresaFilialRepository empresaFilialRepository;
	private DepartamentoRepository departamentoRepository;
	private GrupoContabilRepository grupoContabilRepository;

	public ConfigAutorizacaoSolicitacaoMercadoriaMapper(
			ConfigAutorizacaoSolicitacaoMercadoriaRepository configAutorizacaoSolicitacaoMercadoriaRepository,
			DepartamentoRepository departamentoRepository, GrupoContabilRepository grupoContabilRepository,
			EmpresaRepository empresaRepository, EmpresaFilialRepository empresaFilialRepository) {
		super(configAutorizacaoSolicitacaoMercadoriaRepository, ConfigAutorizacaoSolicitacaoMercadoria::new,
				ConfigAutorizacaoSolicitacaoMercadoriaDTO::new);
		this.departamentoRepository = departamentoRepository;
		this.grupoContabilRepository = grupoContabilRepository;
		this.empresaRepository = empresaRepository;
		this.empresaFilialRepository = empresaFilialRepository;
	}

	@Override
	protected void setValuesToEntity(ConfigAutorizacaoSolicitacaoMercadoriaDTO dto,
			ConfigAutorizacaoSolicitacaoMercadoria entity) {
		entity.setId(dto.getId());
		entity.setEmpresa(empresaRepository.findById(dto.getEmpresaId()).orElseThrow(
				() -> new RegisterNotFoundException("Não encontrado empresa com id " + dto.getEmpresaId())));
		entity.setEmpresaFilial(empresaFilialRepository.findById(dto.getEmpresaFilialId())
				.orElseThrow(() -> new RegisterNotFoundException(
						"Não encontrado filial da empresa com id " + dto.getEmpresaFilialId())));

		entity.setDepartamento(departamentoRepository.findById(dto.getDepartamentoId()).orElseThrow(
				() -> new RegisterNotFoundException("Não encontrado departamento com id " + dto.getDepartamentoId())));
		entity.setGrupoContabil(grupoContabilRepository.findById(dto.getGrupoContabilId())
				.orElseThrow(() -> new RegisterNotFoundException(
						"Não encontrado grupo contábil com id " + dto.getGrupoContabilId())));
		entity.setAutorizadoresId(
				dto.getAutorizadoresId().stream().map(String::valueOf).collect(Collectors.joining(",")));
	}

	@Override
	protected void setValuesToDto(ConfigAutorizacaoSolicitacaoMercadoria entity,
			ConfigAutorizacaoSolicitacaoMercadoriaDTO dto) {
		dto.setId(entity.getId());
		dto.setEmpresaId(entity.getEmpresa().getId());
		dto.setEmpresaNome(entity.getEmpresa().getNome());
		dto.setEmpresaFilialId(entity.getEmpresaFilial().getId());
		dto.setEmpresaFilialNome(entity.getEmpresaFilial().getNome());
		dto.setDepartamentoId(entity.getDepartamento().getId());
		dto.setDepartamentoNome(entity.getDepartamento().getNome());
		dto.setGrupoContabilId(entity.getGrupoContabil().getId());
		dto.setGrupoContabilNome(entity.getGrupoContabil().getNome());
		dto.setAutorizadoresId(Arrays.stream(entity.getAutorizadoresId().split(",")).map(Long::parseLong)
				.collect(Collectors.toList()));
	}
}
