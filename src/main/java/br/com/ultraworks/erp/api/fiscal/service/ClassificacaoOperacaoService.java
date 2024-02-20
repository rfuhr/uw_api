package br.com.ultraworks.erp.api.fiscal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.fiscal.domain.classificacaooperacao.ClassificacaoOperacao;
import br.com.ultraworks.erp.api.fiscal.domain.classificacaooperacao.ClassificacaoOperacaoDTO;
import br.com.ultraworks.erp.api.fiscal.mapper.ClassificacaoOperacaoMapper;
import br.com.ultraworks.erp.api.fiscal.repository.ClassificacaoOperacaoRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class ClassificacaoOperacaoService extends GenericService<ClassificacaoOperacao, Long, ClassificacaoOperacaoDTO> {

	@Autowired
	public ClassificacaoOperacaoService(ClassificacaoOperacaoRepository repository, ClassificacaoOperacaoMapper mapper) {
		super(repository, mapper);
	}

}