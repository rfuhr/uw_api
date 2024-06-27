package br.com.ultraworks.erp.api.financeiro.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.financeiro.domain.negociacao.NegociacaoFinanceira;
import br.com.ultraworks.erp.api.financeiro.domain.negociacao.NegociacaoFinanceiraDTO;
import br.com.ultraworks.erp.api.financeiro.repository.NegociacaoFinanceiraRepository;
import br.com.ultraworks.erp.api.organograma.repository.DepartamentoRepository;
import br.com.ultraworks.erp.api.relacionamento.repository.ParceiroLocalRepository;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class NegociacaoFinanceiraMapper extends GenericMapper<NegociacaoFinanceira, NegociacaoFinanceiraDTO> {

	private DepartamentoRepository departamentoRepository;
	private ParceiroLocalRepository parceiroLocalRepository;

	public NegociacaoFinanceiraMapper(NegociacaoFinanceiraRepository repository,
			DepartamentoRepository departamentoRepository, ParceiroLocalRepository parceiroLocalRepository) {
		super(repository, NegociacaoFinanceira::new, NegociacaoFinanceiraDTO::new);
		this.departamentoRepository = departamentoRepository;
		this.parceiroLocalRepository = parceiroLocalRepository;
	}

	@Override
	protected void setValuesToEntity(NegociacaoFinanceiraDTO dto, NegociacaoFinanceira entity) {
		entity.setId(dto.getId());
		entity.setDepartamento(departamentoRepository.findById(dto.getDepartamentoId()).orElseThrow(
				() -> new RegisterNotFoundException("Não encontrado departamento com id " + dto.getDepartamentoId())));
		entity.setParceiroLocal(parceiroLocalRepository.findById(dto.getParceiroLocalId())
				.orElseThrow(() -> new RegisterNotFoundException(
						"Não encontrado local de parceiro com id " + dto.getParceiroLocalId())));

		entity.setValorTotalAtraso(dto.getValorTotalAtraso());
		entity.setValorJurosNegociado(dto.getValorJurosNegociado());
		entity.setValorDescontoNegociado(dto.getValorDescontoNegociado());
		entity.setValorNegociadoPagar(dto.getValorNegociadoPagar());
		entity.setDataNegociacao(dto.getDataNegociacao());
		entity.setNossoNumero(dto.getNossoNumero());
		entity.setObservacao(dto.getObservacao());
	}

	@Override
	protected void setValuesToDto(NegociacaoFinanceira entity, NegociacaoFinanceiraDTO dto) {
		dto.setId(entity.getId());
		dto.setDepartamentoId(entity.getDepartamento().getId());
		dto.setDepartamentoSigla(entity.getDepartamento().getSigla());
		dto.setDepartamentoNome(entity.getDepartamento().getNome());
		dto.setParceiroLocalId(entity.getParceiroLocal().getId());
		dto.setParceiroLocalNome(entity.getParceiroLocal().getNomeLocal());
		dto.setParceiroNomeRazaoSocial(entity.getParceiroLocal().getParceiro().getNomeRazaoSocial());
		dto.setValorTotalAtraso(entity.getValorTotalAtraso());
		dto.setValorJurosNegociado(entity.getValorJurosNegociado());
		dto.setValorDescontoNegociado(entity.getValorDescontoNegociado());
		dto.setValorNegociadoPagar(entity.getValorNegociadoPagar());
		dto.setDataNegociacao(entity.getDataNegociacao());
		dto.setNossoNumero(entity.getNossoNumero());
		dto.setObservacao(entity.getObservacao());

	}
}
