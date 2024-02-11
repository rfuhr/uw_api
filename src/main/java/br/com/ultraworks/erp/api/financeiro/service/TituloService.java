package br.com.ultraworks.erp.api.financeiro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.financeiro.domain.titulo.Titulo;
import br.com.ultraworks.erp.api.financeiro.domain.titulo.TituloDTO;
import br.com.ultraworks.erp.api.financeiro.integrator.ServicoIntegracaoFinanceira;

@Service
public class TituloService {

	private final ApplicationContext context;
	
	@Autowired
    public TituloService(ApplicationContext context) {
        this.context = context;
    }
	
}
