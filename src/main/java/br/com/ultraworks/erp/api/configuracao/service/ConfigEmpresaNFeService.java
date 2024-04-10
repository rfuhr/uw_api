package br.com.ultraworks.erp.api.configuracao.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.configuracao.domain.configempresanfe.ConfigEmpresaNFe;
import br.com.ultraworks.erp.api.configuracao.domain.configempresanfe.ConfigEmpresaNFeDTO;
import br.com.ultraworks.erp.api.configuracao.mapper.ConfigEmpresaNFeMapper;
import br.com.ultraworks.erp.api.configuracao.repository.ConfigEmpresaNFeRepository;
import br.com.ultraworks.erp.api.tabela.domain.operacaointernafiscal.OperacaoInternaFiscal;
import br.com.ultraworks.erp.api.tabela.repository.OperacaoInternaFiscalRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import br.com.ultraworks.erp.core.util.ListUtils;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class ConfigEmpresaNFeService extends GenericService<ConfigEmpresaNFe, Long, ConfigEmpresaNFeDTO> {

	@Autowired
	public ConfigEmpresaNFeService(ConfigEmpresaNFeRepository repository, ConfigEmpresaNFeMapper mapper) {
		super(repository, mapper);
	}

	public List<ConfigEmpresaNFe> getAllByConfigEmpresa(Long id) {
		List<ConfigEmpresaNFe> listRegistros = new ArrayList<>();

		listRegistros.addAll(((ConfigEmpresaNFeRepository) repository).findByConfigEmpresaId(id));
		return listRegistros;
	}

	public ConfigEmpresaNFe getByEmpresaFilialId(Long empresaFilialId) {
		return ((ConfigEmpresaNFeRepository) repository).findByEmpresaFilialId(empresaFilialId);
	}

	public void persistList(Long configEmpresaId, List<ConfigEmpresaNFe> configuracoesNFe) {
		List<ConfigEmpresaNFe> cnfeFiscalSalvos = ((ConfigEmpresaNFeRepository) repository)
				.findByConfigEmpresaId(configEmpresaId);
		if (configuracoesNFe != null)
			configuracoesNFe.stream().forEach(cnfe -> {
				repository.save(cnfe);
			});
		List<ConfigEmpresaNFe> cnfeFiscalExcluir = (List<ConfigEmpresaNFe>) ListUtils
				.compararListasERetornaDiferenca(cnfeFiscalSalvos, configuracoesNFe);
		cnfeFiscalExcluir.stream().forEach(oif -> repository.deleteById(oif.getId()));		
	}

	public void deleteList(List<ConfigEmpresaNFe> list) {
		if (list != null && !list.isEmpty() ) {
			list.stream().forEach(l -> {
				repository.deleteById(l.getId());
			});
		}		
	}

}