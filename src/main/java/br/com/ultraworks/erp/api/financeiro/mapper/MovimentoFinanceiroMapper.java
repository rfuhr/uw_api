package br.com.ultraworks.erp.api.financeiro.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.financeiro.domain.movimento.MovimentoFinanceiro;
import br.com.ultraworks.erp.api.financeiro.domain.movimento.MovimentoFinanceiroDTO;
import br.com.ultraworks.erp.api.financeiro.repository.MovimentoRepository;
import br.com.ultraworks.erp.api.seguranca.repository.UsuarioRepository;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class MovimentoFinanceiroMapper extends GenericMapper<MovimentoFinanceiro, MovimentoFinanceiroDTO> {

	private UsuarioRepository usuarioRepository;

	public MovimentoFinanceiroMapper(MovimentoRepository repository, UsuarioRepository usuarioRepository) {
		super(repository, MovimentoFinanceiro::new, MovimentoFinanceiroDTO::new);
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	protected void setValuesToEntity(MovimentoFinanceiroDTO dto, MovimentoFinanceiro entity) {
		entity.setId(dto.getId());
	}

	@Override
	protected void setValuesToDto(MovimentoFinanceiro entity, MovimentoFinanceiroDTO dto) {
		dto.setId(entity.getId());
		dto.setParcelaFinanceiroId(entity.getParcela().getId());
		dto.setDepartamentoId(entity.getDepartamento().getId());
		dto.setDepartamentoSigla(entity.getDepartamento().getSigla());
		dto.setDepartamentoNome(entity.getDepartamento().getNome());
		dto.setSeqMvto(entity.getSeqMvto());
		dto.setSubSeqMvto(entity.getSubSeqMvto());
		dto.setTipoOperacaoFinanceiraId(entity.getTipoOperacaoFinanceira().getId());
		dto.setTipoOperacaoFinanceiraNome(entity.getTipoOperacaoFinanceira().getNome());
		dto.setTipoOperacaoFinanceiraSigla(entity.getTipoOperacaoFinanceira().getSigla());
		dto.setOperacaoMovimentoFinanceiroId(entity.getOperacaoMovimentoFinanceiro().getId());
		dto.setOperacaoMovimentoFinanceiroNome(entity.getOperacaoMovimentoFinanceiro().getNome());
		dto.setOperacaoAcessoriaFinanceiraId(entity.getOperacaoAcessoriaFinanceira().getId());
		dto.setOperacaoAcessoriaFinanceiraNome(entity.getOperacaoAcessoriaFinanceira().getNome());
		dto.setGrupoFinanceiroId(entity.getGrupoFinanceiro().getId());
		dto.setGrupoFinanceiroSigla(entity.getGrupoFinanceiro().getSigla());
		dto.setGrupoFinanceiroNome(entity.getGrupoFinanceiro().getNome());
		dto.setCarteiraFinanceiraId(entity.getCarteiraFinanceira().getId());
		dto.setCarteiraFinanceiraSigla(entity.getCarteiraFinanceira().getSigla());
		dto.setCarteiraFinanceiraNome(entity.getCarteiraFinanceira().getNome());
		dto.setValorMovimento(entity.getValorMovimento());
		dto.setDataMovimento(entity.getDataMovimento());
		dto.setSaldoParcela(entity.getSaldoParcela());
		dto.setDataLancamento(entity.getDataLancamento());
		dto.setObservacao(entity.getObservacao());
		dto.setResponsavel(String.valueOf(entity.getCriadoPor()));
		if (entity.getConta() != null)
			dto.setContaId(entity.getConta().getId());
		if (entity.getNegociacaoFinanceira() != null) 
			dto.setNegociacaoFinanceiraId(entity.getNegociacaoFinanceira().getId());

	}
}
