package br.com.ultraworks.erp.core.multitenancy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.core.multitenancy.config.TenantConfigs;
import br.com.ultraworks.erp.core.multitenancy.repository.TenantRepository;
import br.com.ultraworks.erp.core.multitenancy.service.FlywayService;

@Component
public class AppLifecycle {

	@Autowired
	private TenantRepository tenantRepository;
	
	@Autowired
	private FlywayService flywayService;
	
	@EventListener(ApplicationReadyEvent.class)
    public void onStart() {
        System.out.println("Iniciando aplicação");
        TenantConfigs.setConfigs(tenantRepository.findAll());
        flywayService.checkMigration();
        
    }
	
	@EventListener(ContextClosedEvent.class)
    public void onStop() {
		System.out.println("Encerrando aplicação");
    }
}
