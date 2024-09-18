package br.com.ultraworks.erp.api.financeiro.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.financeiro.domain.tipointegracaocaixabanco.TipoIntegracaoCaixaBanco;
import br.com.ultraworks.erp.api.financeiro.domain.tipooperacaofinanceira.TipoOperacaoFinanceira;
import br.com.ultraworks.erp.api.financeiro.domain.tipooperacaofinanceira.TipoOperacaoFinanceiraDTO;
import br.com.ultraworks.erp.api.financeiro.repository.OperacaoCaixaBancoRepository;
import br.com.ultraworks.erp.api.financeiro.repository.TipoOperacaoFinanceiraRepository;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class TipoOperacaoFinanceiraMapper extends GenericMapper<TipoOperacaoFinanceira, TipoOperacaoFinanceiraDTO> {

	private OperacaoCaixaBancoRepository operacaoCaixaBancoRepository;

	public TipoOperacaoFinanceiraMapper(TipoOperacaoFinanceiraRepository repository,
			OperacaoCaixaBancoRepository operacaoCaixaBancoRepository) {
		super(repository, TipoOperacaoFinanceira::new, TipoOperacaoFinanceiraDTO::new);
		this.operacaoCaixaBancoRepository = operacaoCaixaBancoRepository;
	}

	@Override
	protected void setValuesToEntity(TipoOperacaoFinanceiraDTO dto, TipoOperacaoFinanceira entity) {
		entity.setId(dto.getId());
		entity.setNome(dto.getNome());
		entity.setSigla(dto.getSigla());
		entity.setCriaTitulo(dto.isCriaTitulo());
		entity.setBaixaTitulo(dto.isBaixaTitulo());
		entity.setSelecionaBaixa(dto.isSelecionaBaixa());
		entity.setSelecionaSubstituicaoCarteira(dto.isSelecionaSubstituicaoCarteira());
		entity.setSelecionaNegociacao(dto.isSelecionaNegociacao());
		entity.setTipoIntegracaoCaixaBanco(TipoIntegracaoCaixaBanco.fromValue(dto.getIntegracaoCaixaBanco()));
		entity.setListaPosicaoTituloBaixados(dto.isListaPosicaoTituloBaixados());
		if (dto.getOperacaoCaixaBancoId() != null)
			entity.setOperacaoCaixaBanco(operacaoCaixaBancoRepository.findById(dto.getOperacaoCaixaBancoId())
					.orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado operação caixa banco com id " + dto.getOperacaoCaixaBancoId())));
	}

	@Override
	protected void setValuesToDto(TipoOperacaoFinanceira entity, TipoOperacaoFinanceiraDTO dto) {
		dto.setId(entity.getId());
		dto.setNome(entity.getNome());
		dto.setSigla(entity.getSigla());
		dto.setCriaTitulo(entity.isCriaTitulo());
		dto.setBaixaTitulo(entity.isBaixaTitulo());
		dto.setSelecionaBaixa(entity.isSelecionaBaixa());
		dto.setSelecionaSubstituicaoCarteira(entity.isSelecionaSubstituicaoCarteira());
		dto.setIntegracaoCaixaBanco(entity.getTipoIntegracaoCaixaBanco().getValue());
		if (entity.getOperacaoCaixaBanco() != null) {
			dto.setOperacaoCaixaBancoId(entity.getOperacaoCaixaBanco().getId());
		}
		dto.setSelecionaNegociacao(entity.isSelecionaNegociacao());
		dto.setListaPosicaoTituloBaixados(entity.isListaPosicaoTituloBaixados());
	}
}
