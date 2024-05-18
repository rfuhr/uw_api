package br.com.ultraworks.erp.api.estoque.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;
import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.estoque.domain.item.Item;
import br.com.ultraworks.erp.api.estoque.domain.movimentoestoque.AtualizaEstoqueItensRequest;
import br.com.ultraworks.erp.api.estoque.domain.movimentoestoque.AtualizaEstoqueRequest;
import br.com.ultraworks.erp.api.estoque.domain.movimentoestoque.MovimentoEstoque;
import br.com.ultraworks.erp.api.estoque.domain.tipodocumentoestoque.TipoDocumentoEstoque;
import br.com.ultraworks.erp.api.estoque.domain.tipomovimentoestoque.TipoMovimentoEstoque;
import br.com.ultraworks.erp.api.organograma.service.EmpresaFilialService;
import br.com.ultraworks.erp.api.tabela.domain.operacaointerna.OperacaoInterna;
import br.com.ultraworks.erp.api.tabela.domain.operacaointernaestoque.OperacaoInternaEstoque;
import br.com.ultraworks.erp.api.tabela.service.OperacaoInternaEstoqueService;
import br.com.ultraworks.erp.api.tabela.service.OperacaoInternaService;
import br.com.ultraworks.erp.core.exception.BusinessException;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class EstoqueService {
	
	OperacaoInternaEstoqueService operacaoInternaEstoqueService;
	OperacaoInternaService operacaoInternaService;
	GrupoContabilService grupoContabilService;
	EmpresaFilialService empresaFilialService;
	ItemService itemService;
	LocalEstoqueService localEstoqueService;
	MovimentoEstoqueService movimentoEstoqueService;
	SaldoEstoqueService saldoEstoqueService;
	
	@Autowired
	public EstoqueService (OperacaoInternaEstoqueService operacaoInternaEstoqueService,
			OperacaoInternaService operacaoInternaService,
			GrupoContabilService grupoContabilService,
			EmpresaFilialService empresaFilialService,
			ItemService itemService,
			LocalEstoqueService localEstoqueService,
			MovimentoEstoqueService movimentoEstoqueService,
			SaldoEstoqueService saldoEstoqueService) {
		this.operacaoInternaEstoqueService = operacaoInternaEstoqueService;
		this.operacaoInternaService = operacaoInternaService;
		this.grupoContabilService = grupoContabilService;
		this.empresaFilialService = empresaFilialService;
		this.itemService = itemService;
		this.localEstoqueService = localEstoqueService;
		this.movimentoEstoqueService = movimentoEstoqueService;
		this.saldoEstoqueService = saldoEstoqueService;
	}
	
	public void atualizar(AtualizaEstoqueRequest atualizaEstoqueRequest) {
		
		OperacaoInternaEstoque operacaoInternaEstoque = validarCamposObrigatorios(atualizaEstoqueRequest);
		
		for (AtualizaEstoqueItensRequest item : atualizaEstoqueRequest.getListaItens()) {
			Item itemEstoque = itemService.getById(item.getItemId())
					.orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado Item com id " + item.getItemId()));
			if (itemEstoque.isControlaEstoque()) {
				MovimentoEstoque movimentoEstoque = new MovimentoEstoque();
				movimentoEstoque.setItem(itemEstoque);
				movimentoEstoque.setEmpresaFilial(empresaFilialService.getById(atualizaEstoqueRequest.getEmpresaFilialId())
						.orElseThrow(() -> new RegisterNotFoundException(
								"Não encontrada a Filial da Empresa com id " + atualizaEstoqueRequest.getEmpresaFilialId())));
				movimentoEstoque.setOperacaoInterna(operacaoInternaService.getById(atualizaEstoqueRequest.getOperacaoInternaId())
						.orElseThrow(() -> new RegisterNotFoundException(
								"Não encontrada a Operação Interna com id " + atualizaEstoqueRequest.getOperacaoInternaId())));
				if (atualizaEstoqueRequest.getData() == null) {
					movimentoEstoque.setData(LocalDate.now());
				} else {
					movimentoEstoque.setData(atualizaEstoqueRequest.getData());
				}
				Long grupoContabilId = operacaoInternaEstoque.isInformaGrupoContabil() ? atualizaEstoqueRequest.getGrupoContabilId() : operacaoInternaEstoque.getGrupoContabil().getId();
				movimentoEstoque.setGrupoContabil(grupoContabilService.getById(grupoContabilId)
						.orElseThrow(() -> new RegisterNotFoundException(
								"Não encontrado o Grupo Contábil com id " + grupoContabilId)));
				Long localEstoqueId = operacaoInternaEstoque.isInformaLocalEstoque() ? atualizaEstoqueRequest.getLocalEstoqueId() : operacaoInternaEstoque.getLocalEstoque().getId();
				movimentoEstoque.setLocalEstoque(localEstoqueService.getById(localEstoqueId)
						.orElseThrow(() -> new RegisterNotFoundException(
								"Não encontrado o Local do Estoque com id " + localEstoqueId)));
				movimentoEstoque.setOperacaoEstoqueFinanceiro(operacaoInternaEstoque.getOperacaoEstoqueFinanceiro());
				movimentoEstoque.setOperacaoEstoqueFisico(operacaoInternaEstoque.getOperacaoEstoqueFisico());
				movimentoEstoque.setTipoMovimentoEstoque(TipoMovimentoEstoque.fromValue(atualizaEstoqueRequest.getTipoMovimentoEstoque()));
				movimentoEstoque.setTipoDocumentoEstoque(TipoDocumentoEstoque.fromValue(atualizaEstoqueRequest.getTipoDocumentoEstoque()));
				movimentoEstoque.setCalculaCustoMedioSaldo(operacaoInternaEstoque.isCalculaCustoMedio());
				movimentoEstoque.setDocumento(atualizaEstoqueRequest.getDocumento());
				movimentoEstoque.setEntrada(atualizaEstoqueRequest.isEntrada());
				movimentoEstoque.setProtocoloDocumento(atualizaEstoqueRequest.getProtocoloDocumento());
				movimentoEstoque.setQuantidade(item.getQuantidade());
				movimentoEstoque.setCustoMedio(item.getCustoMedio());
				movimentoEstoque.setValor(item.getValor());
				
				MovimentoEstoque movimento = movimentoEstoqueService.save(movimentoEstoque);
				
				saldoEstoqueService.atualizaSaldoEstoque(Date.valueOf(movimento.getData()), Date.valueOf(LocalDate.now()), 
						movimento.getItem().getId(), movimento.getEmpresaFilial().getId(), movimento.getLocalEstoque().getId(), 
						movimento.getGrupoContabil().getId(), movimento.getCriadoPor());
			}
		}
		
	}

	private OperacaoInternaEstoque validarCamposObrigatorios(AtualizaEstoqueRequest atualizaEstoqueRequest) {
		if (atualizaEstoqueRequest.getEmpresaFilialId() == null) {
			throw new BusinessException("Filial da Empresa não foi Informada para Atualização do Estoque!");
		}
		if (atualizaEstoqueRequest.getTipoMovimentoEstoque() == null) {
			throw new BusinessException("Tipo de Movimento do Estoque não foi Informado para Atualização do Estoque!");
		}
		if (atualizaEstoqueRequest.getTipoDocumentoEstoque() == null) {
			throw new BusinessException("Tipo de Documento do Estoque não foi Informado para Atualização do Estoque!");
		}
		if (atualizaEstoqueRequest.getProtocoloDocumento() == null) {
			throw new BusinessException("Protocolo do Documento não foi Informado para Atualização do Estoque!");
		}
		if (atualizaEstoqueRequest.getOperacaoInternaId() == null) {
			throw new BusinessException("Operação Interna não foi Informada para Atualização do Estoque!");
		}
		Optional<OperacaoInterna> operacaoInterna = operacaoInternaService.getById(atualizaEstoqueRequest.getOperacaoInternaId());
		if (operacaoInterna.isEmpty()) {
			throw new BusinessException("Operação Interna com ID " + atualizaEstoqueRequest.getOperacaoInternaId() + " não existe!");
		}
		OperacaoInternaEstoque operacaoInternaEstoque = operacaoInternaEstoqueService.getByOperacaoInterna(atualizaEstoqueRequest.getOperacaoInternaId());
		if (operacaoInternaEstoque == null) {
			throw new BusinessException("Operação Interna " + operacaoInterna.get().getNome() + " não possui Configuração de Operação Interna de Estoque!");
		}
		if (operacaoInternaEstoque.isInformaGrupoContabil() && atualizaEstoqueRequest.getGrupoContabilId() == null) {
			throw new BusinessException("Não é possível Atualizar o estoque pois o Grupo Contábil não foi Informado!");
		}
		if (operacaoInternaEstoque.isInformaLocalEstoque() && atualizaEstoqueRequest.getLocalEstoqueId() == null) {
			throw new BusinessException("Não é possível Atualizar o estoque pois o Local do Estoque não foi Informado!");
		}
		if (atualizaEstoqueRequest.getListaItens().isEmpty() || atualizaEstoqueRequest.getListaItens().size() == 0) {
			throw new BusinessException("Não é possível Atualizar o estoque pois não foi informado Itens!");
		}
		
		return operacaoInternaEstoque;
	}

}
