package br.com.ultraworks.erp.api.fiscal.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.fiscal.domain.configuracaofiscal.ConfiguracaoFiscal;
import br.com.ultraworks.erp.api.fiscal.domain.configuracaofiscal.ConfiguracaoFiscalDTO;
import br.com.ultraworks.erp.api.fiscal.domain.configuracaofiscalcofins.ConfiguracaoFiscalCofins;
import br.com.ultraworks.erp.api.fiscal.domain.configuracaofiscalicms.ConfiguracaoFiscalIcms;
import br.com.ultraworks.erp.api.fiscal.domain.configuracaofiscalipi.ConfiguracaoFiscalIpi;
import br.com.ultraworks.erp.api.fiscal.domain.configuracaofiscalpis.ConfiguracaoFiscalPis;
import br.com.ultraworks.erp.api.fiscal.mapper.ConfiguracaoFiscalMapper;
import br.com.ultraworks.erp.api.fiscal.repository.ConfiguracaoFiscalRepository;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class ConfiguracaoFiscalService extends GenericService<ConfiguracaoFiscal, Long, ConfiguracaoFiscalDTO> {

	ConfiguracaoFiscalRepository repository;
	
	ConfiguracaoFiscalIcmsService configuracaoFiscalIcmsService;
	ConfiguracaoFiscalIpiService configuracaoFiscalIpiService;
	ConfiguracaoFiscalPisService configuracaoFiscalPisService;
	ConfiguracaoFiscalCofinsService configuracaoFiscalCofinsService;
	
	@Autowired
	public ConfiguracaoFiscalService(ConfiguracaoFiscalRepository repository, ConfiguracaoFiscalMapper mapper,
			ConfiguracaoFiscalIcmsService configuracaoFiscalIcmsService, ConfiguracaoFiscalIpiService configuracaoFiscalIpiService,
			ConfiguracaoFiscalPisService configuracaoFiscalPisService, ConfiguracaoFiscalCofinsService configuracaoFiscalCofinsService) {
		super(repository, mapper);
		this.repository = repository;
		this.configuracaoFiscalIcmsService = configuracaoFiscalIcmsService;
		this.configuracaoFiscalIpiService = configuracaoFiscalIpiService;
		this.configuracaoFiscalPisService = configuracaoFiscalPisService;
		this.configuracaoFiscalCofinsService = configuracaoFiscalCofinsService;
	}
	
	@Override
	public List<ConfiguracaoFiscal> getAll() {
		List<ConfiguracaoFiscal> listRegistros = new ArrayList<>();
		repository.findAll().forEach(configuracaoFiscal -> {
			configuracaoFiscal.setConfiguracaoFiscalIcms(configuracaoFiscalIcmsService.findByConfiguracaoFiscalId(configuracaoFiscal.getId()));
			configuracaoFiscal.setConfiguracaoFiscalIpi(configuracaoFiscalIpiService.findByConfiguracaoFiscalId(configuracaoFiscal.getId()));
			configuracaoFiscal.setConfiguracaoFiscalPis(configuracaoFiscalPisService.findByConfiguracaoFiscalId(configuracaoFiscal.getId()));
			configuracaoFiscal.setConfiguracaoFiscalCofins(configuracaoFiscalCofinsService.findByConfiguracaoFiscalId(configuracaoFiscal.getId()));
			listRegistros.add(configuracaoFiscal);
		});
		
		return listRegistros;
	}
	
	@Override
	public Optional<ConfiguracaoFiscal> getById(Long id) {
		Optional<ConfiguracaoFiscal> configuracaoFiscal = repository.findById(id);
		if (configuracaoFiscal.isPresent()) {
			configuracaoFiscal.get().setConfiguracaoFiscalIcms(configuracaoFiscalIcmsService.findByConfiguracaoFiscalId(configuracaoFiscal.get().getId()));
			configuracaoFiscal.get().setConfiguracaoFiscalIpi(configuracaoFiscalIpiService.findByConfiguracaoFiscalId(configuracaoFiscal.get().getId()));
			configuracaoFiscal.get().setConfiguracaoFiscalPis(configuracaoFiscalPisService.findByConfiguracaoFiscalId(configuracaoFiscal.get().getId()));
			configuracaoFiscal.get().setConfiguracaoFiscalCofins(configuracaoFiscalCofinsService.findByConfiguracaoFiscalId(configuracaoFiscal.get().getId()));
		}
		return configuracaoFiscal;
	}
	
	@Override
	public ConfiguracaoFiscal save(ConfiguracaoFiscal entity) {
		
		ConfiguracaoFiscalIcms icmsSalvo = null;
		ConfiguracaoFiscalIpi ipiSalvo = null;
		ConfiguracaoFiscalCofins cofinsSalvo = null;
		ConfiguracaoFiscalPis pisSalvo = null;
		
		if (entity.getId() != null) {
			icmsSalvo = configuracaoFiscalIcmsService.findByConfiguracaoFiscalId(entity.getId());
			ipiSalvo = configuracaoFiscalIpiService.findByConfiguracaoFiscalId(entity.getId());
			cofinsSalvo = configuracaoFiscalCofinsService.findByConfiguracaoFiscalId(entity.getId());
			pisSalvo = configuracaoFiscalPisService.findByConfiguracaoFiscalId(entity.getId());
		}

		repository.save(entity);
		
		if (entity.getConfiguracaoFiscalIcms() != null) {
			entity.getConfiguracaoFiscalIcms().setConfiguracaoFiscal(entity);
			configuracaoFiscalIcmsService.save(entity.getConfiguracaoFiscalIcms());
		}
		
		if (entity.getConfiguracaoFiscalIpi() != null) {
			entity.getConfiguracaoFiscalIpi().setConfiguracaoFiscal(entity);
			configuracaoFiscalIpiService.save(entity.getConfiguracaoFiscalIpi());
		}
		
		if (entity.getConfiguracaoFiscalPis() != null) {
			entity.getConfiguracaoFiscalPis().setConfiguracaoFiscal(entity);
			configuracaoFiscalPisService.save(entity.getConfiguracaoFiscalPis());
		}
		
		if (entity.getConfiguracaoFiscalCofins() != null) {
			entity.getConfiguracaoFiscalCofins().setConfiguracaoFiscal(entity);
			configuracaoFiscalCofinsService.save(entity.getConfiguracaoFiscalCofins());
		}
		
		if (icmsSalvo != null && entity.getConfiguracaoFiscalIcms() == null) {
			configuracaoFiscalIcmsService.delete(icmsSalvo.getId());
		}
		
		if (ipiSalvo != null && entity.getConfiguracaoFiscalIpi() == null) {
			configuracaoFiscalIpiService.delete(ipiSalvo.getId());
		}
		
		if (pisSalvo != null && entity.getConfiguracaoFiscalPis() == null) {
			configuracaoFiscalPisService.delete(pisSalvo.getId());
		}
		
		if (cofinsSalvo != null && entity.getConfiguracaoFiscalCofins() == null) {
			configuracaoFiscalCofinsService.delete(cofinsSalvo.getId());
		}
		
		return entity;
	}
	
	@Override
	public void delete(Long id) {
		
		ConfiguracaoFiscal configuracaoFiscal = this.getById(id).orElseThrow(RegisterNotFoundException::new);
		if (configuracaoFiscal.getConfiguracaoFiscalIcms() != null) {
			configuracaoFiscalIcmsService.delete(configuracaoFiscal.getConfiguracaoFiscalIcms().getId());
		}
		if (configuracaoFiscal.getConfiguracaoFiscalIpi() != null) {
			configuracaoFiscalIpiService.delete(configuracaoFiscal.getConfiguracaoFiscalIpi().getId());
		}
		if (configuracaoFiscal.getConfiguracaoFiscalPis() != null) {
			configuracaoFiscalPisService.delete(configuracaoFiscal.getConfiguracaoFiscalPis().getId());
		}
		if (configuracaoFiscal.getConfiguracaoFiscalCofins() != null) {
			configuracaoFiscalCofinsService.delete(configuracaoFiscal.getConfiguracaoFiscalCofins().getId());
		}
		repository.deleteById(id);
	}

}