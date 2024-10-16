package br.com.ultraworks.erp.api.fiscal.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ultraworks.erp.api.estoque.domain.item.Item;
import br.com.ultraworks.erp.api.estoque.repository.ItemRepository;
import br.com.ultraworks.erp.api.fiscal.domain.documento.Documento;
import br.com.ultraworks.erp.api.fiscal.domain.documento.DocumentoDTO;
import br.com.ultraworks.erp.api.fiscal.domain.documentoItem.DocumentoItem;
import br.com.ultraworks.erp.api.fiscal.domain.documentointegracao.DocumentoIntegracao;
import br.com.ultraworks.erp.api.fiscal.domain.documentoparcela.DocumentoParcela;
import br.com.ultraworks.erp.api.fiscal.domain.meiopagamento.MeioPagamento;
import br.com.ultraworks.erp.api.fiscal.domain.nfe.entity.NFe;
import br.com.ultraworks.erp.api.fiscal.domain.nfe.request.ItemNFeRequest;
import br.com.ultraworks.erp.api.fiscal.domain.nfe.request.NFeRequest;
import br.com.ultraworks.erp.api.fiscal.domain.nfe.request.PagamentoNFeRequest;
import br.com.ultraworks.erp.api.fiscal.domain.tipointegracao.TipoIntegracao;
import br.com.ultraworks.erp.api.fiscal.mapper.DocumentoMapper;
import br.com.ultraworks.erp.api.fiscal.repository.DocumentoRepository;
import br.com.ultraworks.erp.api.fiscal.repository.query.VerificaDuplicidadeDocumentoQuery;
import br.com.ultraworks.erp.api.organograma.domain.departamento.Departamento;
import br.com.ultraworks.erp.api.organograma.repository.DepartamentoRepository;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocal.ParceiroLocal;
import br.com.ultraworks.erp.api.relacionamento.repository.ParceiroLocalRepository;
import br.com.ultraworks.erp.api.tabela.domain.indicadorformapagamento.IndicadorFormaPagamento;
import br.com.ultraworks.erp.api.tabela.domain.operacaointerna.OperacaoInterna;
import br.com.ultraworks.erp.api.tabela.domain.origemdocumento.OrigemDocumento;
import br.com.ultraworks.erp.api.tabela.domain.situacaodocumento.SituacaoDocumento;
import br.com.ultraworks.erp.api.tabela.domain.tipodocumento.TipoDocumento;
import br.com.ultraworks.erp.api.tabela.repository.OperacaoInternaRepository;
import br.com.ultraworks.erp.api.tabela.repository.TipoDocumentoRepository;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class DocumentoService extends GenericService<Documento, Long, DocumentoDTO> {
	
	VerificaDuplicidadeDocumentoQuery verificaDuplicidadeDocumentoQuery;
	DepartamentoRepository departamentoRepository;
	OperacaoInternaRepository operacaoInternaRepository;
	ParceiroLocalRepository parceiroLocalRepository;
	ItemRepository itemRepository;
	TipoDocumentoRepository tipoDocumentoRepository;

	@Autowired
	public DocumentoService(DocumentoRepository repository, DocumentoMapper mapper,
			VerificaDuplicidadeDocumentoQuery verificaDuplicidadeDocumentoQuery,
			DepartamentoRepository departamentoRepository,
			OperacaoInternaRepository operacaoInternaRepository,
			ParceiroLocalRepository parceiroLocalRepository,
			ItemRepository itemRepository,
			TipoDocumentoRepository tipoDocumentoRepository) {
		super(repository, mapper);
		this.verificaDuplicidadeDocumentoQuery = verificaDuplicidadeDocumentoQuery;
		this.departamentoRepository = departamentoRepository;
		this.operacaoInternaRepository = operacaoInternaRepository;
		this.parceiroLocalRepository = parceiroLocalRepository;
		this.itemRepository = itemRepository;
		this.tipoDocumentoRepository = tipoDocumentoRepository;
	}
	
	@Override
	public Documento save(Documento entity) {
		
//		Long idDuplicidade = ((DocumentoRepository)repository).verificaDuplicidade(entity.getNumero(), entity.getDataDocumento(), entity.getParceiroLocal().getId(), 
//				entity.getOperacaoInterna().getId(), entity.getValor(), entity.getId() != null ? 0 : 1, entity.getId());
//		if (idDuplicidade != null) {
//			ValidationErrorResponse validationErrorResponse = new ValidationErrorResponse();
//			String label = "Validação de Duplicidade do Documento "; 
//			validationErrorResponse.getErrors().add(new ValidationError(label,
//					"Não é possível incluir o Documento " + entity.getNumero() + 
//					", pois existe um Documento cadastrado com o Identificador: " + idDuplicidade + "."));
//			throw new UnicidadeException(validationErrorResponse,
//					"Ocorreu um erro na validação de Duplicidade de Documento");
//		}

		return repository.save(entity);
	}
	
	@Transactional
	public void criarDocumentoByNFe(NFeRequest nFeRequest, NFe nfe) {
		Departamento departamento = departamentoRepository.findById(nFeRequest.getIdentificacaoNFeRequest().getEmitenteId())
				.orElseThrow(() -> new RegisterNotFoundException("Não foi encontrado o Departamento para o ID informado."));
		
		OperacaoInterna operacaoInterna = operacaoInternaRepository.findById(nFeRequest.getIdentificacaoNFeRequest().getOperacaoInternaId())
				.orElseThrow(() -> new RegisterNotFoundException("Não foi encontrada a Operação Interna para o ID informado."));
		
		ParceiroLocal parceiroLocal = parceiroLocalRepository.findById(nFeRequest.getIdentificacaoNFeRequest().getOperacaoInternaId())
				.orElseThrow(() -> new RegisterNotFoundException("Não foi encontrad o Local do Parceiro para o ID informado."));
		
		TipoDocumento tipoDocumento = tipoDocumentoRepository.findByCodigoReceita("55");
		
		Documento documento = new Documento();
		documento.setDataDocumento(nFeRequest.getIdentificacaoNFeRequest().getDataHoraEmissao().toLocalDate());
		documento.setDepartamento(departamento);
		documento.setNumero(Long.valueOf(nFeRequest.getIdentificacaoNFeRequest().getNumero()));
		documento.setNfe(nfe);
		documento.setOperacaoInterna(operacaoInterna);
		documento.setOrigemDocumento(OrigemDocumento.EMISSOR_NFE);
		documento.setParceiroLocal(parceiroLocal);
		documento.setSituacaoDocumento(SituacaoDocumento.PENDENTE);
		documento.setTipoDocumento(tipoDocumento); 
		documento.setValor(calculaValorTotalNota(nFeRequest.getItensNFeRequest().getItens()));
		documento.setIntegracoes(criarIntegracoesDocumento(nFeRequest, documento));
		documento.setItens(criarDocumentoItem(nFeRequest, documento));
		
		this.save(documento);
	}

	private BigDecimal calculaValorTotalNota(List<ItemNFeRequest> itens) {
		BigDecimal valorTotal = BigDecimal.ZERO;
		for (ItemNFeRequest item : itens) {
			valorTotal = valorTotal.add(item.getDetalhamentoItem().getValorTotal());
		}
		return valorTotal;
	}

	private List<DocumentoItem> criarDocumentoItem(NFeRequest nFeRequest, Documento documento) {
		List<DocumentoItem> listaItens = new ArrayList<>();
		int sequencia = 1;
		for (ItemNFeRequest item : nFeRequest.getItensNFeRequest().getItens()) {
			Item itemSalvo = itemRepository.findById(item.getDetalhamentoItem().getItemId())
					.orElseThrow(() -> new RegisterNotFoundException("Não foi encontrado o Item para o ID informado."));
			
			DocumentoItem documentoItem = new DocumentoItem();
			documentoItem.setDocumento(documento);
			documentoItem.setItem(itemSalvo);
			documentoItem.setPercentualDesconto(item.getDetalhamentoItem().getPercentualDesconto());
			documentoItem.setQuantidade(item.getDetalhamentoItem().getQuantidade());
			documentoItem.setSequencia(sequencia);
			documentoItem.setValorBruto(item.getDetalhamentoItem().getValorTotal());
			documentoItem.setValorDesconto(item.getDetalhamentoItem().getValorDesconto());
			documentoItem.setValorFrete(item.getDetalhamentoItem().getValorFrete());
			documentoItem.setValorLiquido(item.getDetalhamentoItem().getSubTotal());
			documentoItem.setValorOutrasDespesas(item.getDetalhamentoItem().getValorOutrasDespesas());
			documentoItem.setValorSeguro(item.getDetalhamentoItem().getValorSeguro());
			documentoItem.setValorUnitario(item.getDetalhamentoItem().getValorUnitario());
			listaItens.add(documentoItem);
			sequencia++;
		}
		return listaItens;
	}

	private List<DocumentoIntegracao> criarIntegracoesDocumento(NFeRequest nFeRequest,
			Documento documento) {
		List<DocumentoIntegracao> listaIntegracoes = new ArrayList<>();
		if (documento.getOperacaoInterna().isCaracteristicaEstoque()) {
			DocumentoIntegracao documentoIntegracao = new DocumentoIntegracao();
			documentoIntegracao.setDocumento(documento);
			documentoIntegracao.setTipoIntegracao(TipoIntegracao.ESTOQUE);
			documentoIntegracao.setIntegrado(false);
			listaIntegracoes.add(documentoIntegracao);
		}
		if (documento.getOperacaoInterna().isCaracteristicaFinanceira()) {
			DocumentoIntegracao documentoIntegracao = new DocumentoIntegracao();
			documentoIntegracao.setDocumento(documento);
			documentoIntegracao.setTipoIntegracao(TipoIntegracao.FINANCEIRO);
			documentoIntegracao.setIntegrado(false);
			listaIntegracoes.add(documentoIntegracao);
			documento.setParcelas(criarDocumentoParcela(nFeRequest, documento));
		}		
		return listaIntegracoes;
	}

	private List<DocumentoParcela> criarDocumentoParcela(NFeRequest nFeRequest, Documento documento) {
		List<DocumentoParcela> listaParcelas = new ArrayList<>();
		for (PagamentoNFeRequest pagamento : nFeRequest.getFinanceiroNFeRequest().getPagamentos()) {
			DocumentoParcela documentoParcela = new DocumentoParcela();
			documentoParcela.setDocumento(documento);
			documentoParcela.setDataVencimento(pagamento.getDataVencimento());
			documentoParcela.setNumero(pagamento.getNumero());
			documentoParcela.setIndicadorFormaPagamento(IndicadorFormaPagamento.fromValue(pagamento.getIndicadorFormaPagamento()));
			documentoParcela.setMeioPagamento(MeioPagamento.fromValue(pagamento.getMeioPagamento()));
			documentoParcela.setValor(pagamento.getValorPagamento());
			listaParcelas.add(documentoParcela);
		}
		return listaParcelas;
	}

	private void verificaDuplicidadeDocumento(Documento entity) {
		verificaDuplicidadeDocumentoQuery.executeSQL(entity);
	}
	
}