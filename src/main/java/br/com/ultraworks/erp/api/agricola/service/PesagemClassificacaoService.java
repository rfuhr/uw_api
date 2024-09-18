package br.com.ultraworks.erp.api.agricola.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.agricola.domain.pesagemclassificacao.PesagemClassificacao;
import br.com.ultraworks.erp.api.agricola.domain.pesagemclassificacao.PesagemClassificacaoDTO;
import br.com.ultraworks.erp.api.agricola.mapper.PesagemClassificacaoMapper;
import br.com.ultraworks.erp.api.agricola.repository.PesagemClassificacaoRepository;
import br.com.ultraworks.erp.api.configuracao.domain.configempresanfe.ConfigEmpresaNFe;
import br.com.ultraworks.erp.api.configuracao.repository.ConfigEmpresaNFeRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import br.com.ultraworks.erp.core.util.ListUtils;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class PesagemClassificacaoService extends GenericService<PesagemClassificacao, Long, PesagemClassificacaoDTO> {

	@Autowired
	public PesagemClassificacaoService(PesagemClassificacaoRepository repository, PesagemClassificacaoMapper mapper) {
		super(repository, mapper);
	}

	public List<PesagemClassificacao> findByPesagemId(Long id) {
		return ((PesagemClassificacaoRepository) repository).findByPesagemId(id);
	}
	
	public void persistList(Long pesagemId, List<PesagemClassificacao> classificacoes) {
		List<PesagemClassificacao> classificacoesSalvos = ((PesagemClassificacaoRepository) repository)
				.findByPesagemId(pesagemId);
		if (classificacoes != null)
			classificacoes.stream().forEach(classificacao -> {
				repository.save(classificacao);
			});
		List<PesagemClassificacao> classificacoesExcluir = (List<PesagemClassificacao>) ListUtils
				.compararListasERetornaDiferenca(classificacoesSalvos, classificacoes);
		classificacoesExcluir.stream().forEach(c -> repository.deleteById(c.getId()));		
	}
}