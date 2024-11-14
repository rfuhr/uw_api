package br.com.ultraworks.erp.api.financeiro.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.financeiro.domain.condicaopagamento.CondicaoPagamento;
import br.com.ultraworks.erp.api.financeiro.domain.condicaopagamento.CondicaoPagamentoDTO;
import br.com.ultraworks.erp.api.financeiro.domain.tipocondicaopagamento.TipoCondicaoPagamento;
import br.com.ultraworks.erp.api.financeiro.repository.CondicaoPagamentoRepository;
import br.com.ultraworks.erp.api.tabela.domain.indicadorformapagamento.IndicadorFormaPagamento;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class CondicaoPagamentoMapper extends GenericMapper<CondicaoPagamento, CondicaoPagamentoDTO> {

	public CondicaoPagamentoMapper(CondicaoPagamentoRepository repository) {
		super(repository, CondicaoPagamento::new, CondicaoPagamentoDTO::new);
	}

	@Override
	protected void setValuesToEntity(CondicaoPagamentoDTO dto, CondicaoPagamento entity) {
		entity.setId(dto.getId());
		entity.setCodigo(dto.getCodigo());
		entity.setNome(dto.getNome());
		entity.setDescricao(dto.getDescricao());
		entity.setIndicadorFormaPagamento(IndicadorFormaPagamento.fromValue(dto.getIndicadorFormaPagamento()));
		entity.setPossuiEntrada(dto.isPossuiEntrada());
		entity.setQuantidadeParcelas(dto.getQuantidadeParcelas());
		entity.setTipoCondicaoPagamento(TipoCondicaoPagamento.fromValue(dto.getTipoCondicaoPagamento()));
		entity.setDiaVencimento(dto.getDiaVencimento());
		entity.setDiasIntervalo(dto.getDiasIntervalo());
		entity.setDiasDivisao(dto.getDiasDivisao());
		entity.setComposicao(dto.getComposicao());
	}

	@Override
	protected void setValuesToDto(CondicaoPagamento entity, CondicaoPagamentoDTO dto) {
		dto.setId(entity.getId());
		dto.setCodigo(entity.getCodigo());
		dto.setNome(entity.getNome());
		dto.setDescricao(entity.getDescricao());
		dto.setIndicadorFormaPagamento(entity.getIndicadorFormaPagamento().getValue());
		dto.setIndicadorFormaPagamentoNome(entity.getIndicadorFormaPagamento().getName());
		dto.setPossuiEntrada(entity.isPossuiEntrada());
		dto.setQuantidadeParcelas(entity.getQuantidadeParcelas());
		dto.setTipoCondicaoPagamento(entity.getTipoCondicaoPagamento().getValue());
		dto.setTipoCondicaoPagamentoNome(entity.getTipoCondicaoPagamento().getName());
		dto.setDiaVencimento(entity.getDiaVencimento());
		dto.setDiasIntervalo(entity.getDiasIntervalo());
		dto.setDiasDivisao(entity.getDiasDivisao());
		dto.setComposicao(entity.getComposicao());
	}
}
