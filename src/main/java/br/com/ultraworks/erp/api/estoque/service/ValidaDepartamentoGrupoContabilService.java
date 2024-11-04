package br.com.ultraworks.erp.api.estoque.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.estoque.domain.validadepartamentogrupocontabil.ValidaDepartamentoGrupoContabil;
import br.com.ultraworks.erp.api.estoque.domain.validadepartamentogrupocontabil.ValidaDepartamentoGrupoContabilDTO;
import br.com.ultraworks.erp.api.estoque.mapper.ValidaDepartamentoGrupoContabilMapper;
import br.com.ultraworks.erp.api.estoque.repository.ValidaDepartamentoGrupoContabilRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class ValidaDepartamentoGrupoContabilService
		extends GenericService<ValidaDepartamentoGrupoContabil, Long, ValidaDepartamentoGrupoContabilDTO> {

	@Autowired
	public ValidaDepartamentoGrupoContabilService(ValidaDepartamentoGrupoContabilRepository repository,
			ValidaDepartamentoGrupoContabilMapper mapper) {
		super(repository, mapper);
	}

	public Optional<ValidaDepartamentoGrupoContabil> getValidacao(Long departamentoId, Long grupoContabilId) {
		Optional<ValidaDepartamentoGrupoContabil> validacao = ((ValidaDepartamentoGrupoContabilRepository) repository).getValidacao(departamentoId, grupoContabilId);
		if (validacao.isEmpty()) {
			validacao = ((ValidaDepartamentoGrupoContabilRepository) repository).getValidacao(0L, grupoContabilId);
		} else if (validacao.isEmpty()) {
			validacao = ((ValidaDepartamentoGrupoContabilRepository) repository).getValidacao(departamentoId, 0L);
		} else if (validacao.isEmpty()) {
			validacao = ((ValidaDepartamentoGrupoContabilRepository) repository).getValidacao(0L, 0L);
		}
		return validacao;
	}

}