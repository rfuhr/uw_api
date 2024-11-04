package br.com.ultraworks.erp.api.tabela.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.compras.service.solicitacaomercadoria.SolicitacaoMercadoriaService;
import br.com.ultraworks.erp.api.seguranca.domain.usuario.Usuario;
import br.com.ultraworks.erp.api.seguranca.service.UsuarioService;
import br.com.ultraworks.erp.api.tabela.domain.autorizacao.Autorizacao;
import br.com.ultraworks.erp.api.tabela.domain.autorizacao.AutorizacaoDTO;
import br.com.ultraworks.erp.api.tabela.domain.statusautorizacao.StatusAutorizacao;
import br.com.ultraworks.erp.api.tabela.domain.tipoautorizacao.TipoAutorizacao;
import br.com.ultraworks.erp.api.tabela.mapper.AutorizacaoMapper;
import br.com.ultraworks.erp.api.tabela.repository.AutorizacaoRepository;
import br.com.ultraworks.erp.core.exception.BusinessException;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.generics.GenericService;
import br.com.ultraworks.erp.core.security.domain.CustomUser;

@Service
public class AutorizacaoService extends GenericService<Autorizacao, Long, AutorizacaoDTO> {

	UsuarioService usuarioService;
	SolicitacaoMercadoriaService solicitacaoMercadoriaService;
	
	@Autowired
	public AutorizacaoService(AutorizacaoRepository repository, AutorizacaoMapper mapper, UsuarioService usuarioService, @Lazy SolicitacaoMercadoriaService solicitacaoMercadoriaService) {
		super(repository, mapper);
		this.usuarioService = usuarioService;
		this.solicitacaoMercadoriaService = solicitacaoMercadoriaService;
	}

	public List<Autorizacao> getAutorizacaoByOrigem(Long documentoOrigemId, String tipo) {
		TipoAutorizacao tipoAutorizacao = TipoAutorizacao.fromValue(tipo);
		return ((AutorizacaoRepository) repository).getAutorizacaoByDocumentoOrigemIdAndTipoAutorizacao(documentoOrigemId, tipoAutorizacao);
	}

	public void autorizar(Long id) {
		Autorizacao autorizacao = repository.findById(id).orElseThrow(RegisterNotFoundException::new);
		if (autorizacao.getStatusAutorizacao().equals(StatusAutorizacao.AUTORIZACAO_CANCELADA)) {
			throw new BusinessException("Autorização já foi cancelada, não é possível autorizar");
		}
		if (autorizacao.getStatusAutorizacao().equals(StatusAutorizacao.AUTORIZACAO_CONFIRMADA)) {
			throw new BusinessException("Autorização já foi confirmada, não é possível autorizar");
		}
		if (autorizacao.getStatusAutorizacao().equals(StatusAutorizacao.AUTORIZACAO_NEGADA)) {
			throw new BusinessException("Autorização já foi negada, não é possível autorizar");
		}
		if (autorizacao.getStatusAutorizacao().equals(StatusAutorizacao.AUTORIZACAO_TRANSFERIDA)) {
			throw new BusinessException("Autorização já foi transferida, não é possível autorizar");
		}
		autorizacao.setStatusAutorizacao(StatusAutorizacao.AUTORIZACAO_CONFIRMADA);
		autorizacao.setDataAutorizacao(LocalDate.now());
		CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Usuario usuarioAutorizador = usuarioService.findByUserId(user.getId()).get();		
		autorizacao.setUsuarioAutorizador(usuarioAutorizador);
		
		repository.save(autorizacao);
		if (autorizacao.getTipoAutorizacao().equals(TipoAutorizacao.SOLICITACAO_MERCADORIA)) {
			solicitacaoMercadoriaService.autorizar(autorizacao.getDocumentoOrigemId(), usuarioAutorizador);
		}
		
	}
	
	public void negar(Long id) {
		Autorizacao autorizacao = repository.findById(id).orElseThrow(RegisterNotFoundException::new);
		if (autorizacao.getStatusAutorizacao().equals(StatusAutorizacao.AUTORIZACAO_CANCELADA)) {
			throw new BusinessException("Autorização já foi cancelada, não é possível negar");
		}
		if (autorizacao.getStatusAutorizacao().equals(StatusAutorizacao.AUTORIZACAO_CONFIRMADA)) {
			throw new BusinessException("Autorização já foi autorizada, não é possível negar");
		}
		if (autorizacao.getStatusAutorizacao().equals(StatusAutorizacao.AUTORIZACAO_TRANSFERIDA)) {
			throw new BusinessException("Autorização já foi transferida, não é possível negar");
		}
		autorizacao.setStatusAutorizacao(StatusAutorizacao.AUTORIZACAO_NEGADA);
		autorizacao.setDataAutorizacao(LocalDate.now());
		CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Usuario usuarioAutorizador = usuarioService.findByUserId(user.getId()).get();		
		autorizacao.setUsuarioAutorizador(usuarioAutorizador);
		
		repository.save(autorizacao);
		if (autorizacao.getTipoAutorizacao().equals(TipoAutorizacao.SOLICITACAO_MERCADORIA)) {
			solicitacaoMercadoriaService.negar(autorizacao.getDocumentoOrigemId(), usuarioAutorizador);
		}
	}
}
