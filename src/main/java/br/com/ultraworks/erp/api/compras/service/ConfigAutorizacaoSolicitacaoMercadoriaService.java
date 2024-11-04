package br.com.ultraworks.erp.api.compras.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.compras.domain.configautorizacaosolicitacaomercadoria.ConfigAutorizacaoSolicitacaoMercadoria;
import br.com.ultraworks.erp.api.compras.domain.configautorizacaosolicitacaomercadoria.ConfigAutorizacaoSolicitacaoMercadoriaDTO;
import br.com.ultraworks.erp.api.compras.mapper.ConfigAutorizacaoSolicitacaoMercadoriaMapper;
import br.com.ultraworks.erp.api.compras.repository.ConfigAutorizacaoSolicitacaoMercadoriaRepository;
import br.com.ultraworks.erp.api.compras.repository.query.BuscarConfiguracaoAutorizacaoSolicitacaoMercadoriaQuery;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class ConfigAutorizacaoSolicitacaoMercadoriaService extends
		GenericService<ConfigAutorizacaoSolicitacaoMercadoria, Long, ConfigAutorizacaoSolicitacaoMercadoriaDTO> {

	private BuscarConfiguracaoAutorizacaoSolicitacaoMercadoriaQuery buscarConfiguracaoAutorizacaoSolicitacaoMercadoriaQuery;

	@Autowired
	public ConfigAutorizacaoSolicitacaoMercadoriaService(ConfigAutorizacaoSolicitacaoMercadoriaRepository repository,
			ConfigAutorizacaoSolicitacaoMercadoriaMapper mapper,
			BuscarConfiguracaoAutorizacaoSolicitacaoMercadoriaQuery buscarConfiguracaoAutorizacaoSolicitacaoMercadoriaQuery) {
		super(repository, mapper);
		this.buscarConfiguracaoAutorizacaoSolicitacaoMercadoriaQuery = buscarConfiguracaoAutorizacaoSolicitacaoMercadoriaQuery;
	}

	public ConfigAutorizacaoSolicitacaoMercadoria buscaConfiguracao(Long empresaId, Long empresaFilialId,
			Long departamentoId, Long grupoContabilId) {
		return this.buscarConfiguracaoAutorizacaoSolicitacaoMercadoriaQuery.executeSQL(empresaId, empresaFilialId,
				departamentoId, grupoContabilId);
	}
}