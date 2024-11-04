package br.com.ultraworks.erp.api.tabela.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.seguranca.service.UsuarioService;
import br.com.ultraworks.erp.api.tabela.domain.autorizacao.Autorizacao;
import br.com.ultraworks.erp.api.tabela.domain.autorizacao.AutorizacaoDTO;
import br.com.ultraworks.erp.api.tabela.domain.statusautorizacao.StatusAutorizacao;
import br.com.ultraworks.erp.api.tabela.domain.tipoautorizacao.TipoAutorizacao;
import br.com.ultraworks.erp.api.tabela.repository.AutorizacaoRepository;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class AutorizacaoMapper extends GenericMapper<Autorizacao, AutorizacaoDTO> {

	private UsuarioService usuarioService;

	public AutorizacaoMapper(AutorizacaoRepository repository, UsuarioService usuarioService) {
		super(repository, Autorizacao::new, AutorizacaoDTO::new);
		this.usuarioService = usuarioService;
	}

	@Override
	protected void setValuesToEntity(AutorizacaoDTO dto, Autorizacao entity) {
		entity.setId(dto.getId());
		entity.setDocumentoOrigemId(dto.getDocumentoOrigemId());
		entity.setDocumentoIdentificacao(dto.getDocumentoIdentificacao());
		entity.setTipoAutorizacao(TipoAutorizacao.fromValue(dto.getTipoAutorizacao()));
		entity.setStatusAutorizacao(StatusAutorizacao.fromValue(dto.getStatusAutorizacao()));
		entity.setUsuarioSolicitante(
				usuarioService.getById(dto.getUsuarioSolicitanteId()).orElseThrow(() -> new RegisterNotFoundException(
						"Não encontrado usuário solicitante com id " + dto.getUsuarioSolicitanteId())));
		entity.setUsuarioAutorizador(
				usuarioService.getById(dto.getUsuarioAutorizadorId()).orElseThrow(() -> new RegisterNotFoundException(
						"Não encontrado usuário autorizador com id " + dto.getUsuarioAutorizadorId())));
		entity.setDataSolicitacao(dto.getDataSolicitacao());
		entity.setDataAutorizacao(dto.getDataAutorizacao());
	}

	@Override
	protected void setValuesToDto(Autorizacao entity, AutorizacaoDTO dto) {
		dto.setId(entity.getId());
		dto.setDocumentoOrigemId(entity.getDocumentoOrigemId());
		dto.setDocumentoIdentificacao(entity.getDocumentoIdentificacao());
		dto.setTipoAutorizacao(entity.getTipoAutorizacao().getValue());
		dto.setTipoAutorizacaoNome(entity.getTipoAutorizacao().getName());
		dto.setStatusAutorizacao(entity.getStatusAutorizacao().getValue());
		dto.setStatusAutorizacaoNome(entity.getStatusAutorizacao().getName());
		dto.setUsuarioSolicitanteId(entity.getUsuarioSolicitante().getId());
		dto.setUsuarioSolicitanteNome(entity.getUsuarioSolicitante().getNome());
		dto.setUsuarioAutorizadorId(entity.getUsuarioAutorizador().getId());
		dto.setUsuarioAutorizadorNome(entity.getUsuarioAutorizador().getNome());
		dto.setDataAutorizacao(entity.getDataAutorizacao());
		dto.setDataSolicitacao(entity.getDataSolicitacao());
	}
}
