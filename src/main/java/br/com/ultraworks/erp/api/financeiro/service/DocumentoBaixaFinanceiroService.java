package br.com.ultraworks.erp.api.financeiro.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ultraworks.erp.api.configuracao.service.ControleNumeracaoService;
import br.com.ultraworks.erp.api.financeiro.domain.conta.Conta;
import br.com.ultraworks.erp.api.financeiro.domain.documentobaixafinanceiro.DocumentoBaixaFinanceiro;
import br.com.ultraworks.erp.api.financeiro.domain.tipooperacaofinanceira.TipoOperacaoFinanceira;
import br.com.ultraworks.erp.api.financeiro.repository.DocumentoBaixaFinanceiroRepository;
import br.com.ultraworks.erp.api.organograma.domain.departamento.Departamento;
import br.com.ultraworks.erp.api.organograma.domain.empresa.Empresa;
import br.com.ultraworks.erp.api.organograma.domain.empresaFilial.EmpresaFilial;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class DocumentoBaixaFinanceiroService {

	private static final Long tipoDocumentoBaixa = 6L;
	private static final int serie = 1;

	DocumentoBaixaFinanceiroRepository documentoBaixaFinanceiroRepository;
	ControleNumeracaoService controleNumeracaoService;

	@Autowired
	public DocumentoBaixaFinanceiroService(DocumentoBaixaFinanceiroRepository documentoBaixaFinanceiroRepository,
			ControleNumeracaoService controleNumeracaoService) {
		this.documentoBaixaFinanceiroRepository = documentoBaixaFinanceiroRepository;
		this.controleNumeracaoService = controleNumeracaoService;
	}

	@Transactional
	public DocumentoBaixaFinanceiro create(Empresa empresa, EmpresaFilial empresaFilial, LocalDate dataBaixa,
			Departamento departamento, TipoOperacaoFinanceira tipoOperacaoFinanceira, Conta conta, String observacao) {
		int numeroRecibo = controleNumeracaoService.getProximoNumero(empresa.getId(), empresaFilial.getId(),
				tipoDocumentoBaixa, serie);
		return documentoBaixaFinanceiroRepository.saveAndFlush(
				DocumentoBaixaFinanceiro.builder().numero(numeroRecibo).dataBaixa(dataBaixa).departamento(departamento)
						.tipoOperacaoFinanceira(tipoOperacaoFinanceira).conta(conta).observacao(observacao).build());
	}
}