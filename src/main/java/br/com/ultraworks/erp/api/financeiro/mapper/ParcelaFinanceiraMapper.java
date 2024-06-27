package br.com.ultraworks.erp.api.financeiro.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.financeiro.domain.parcela.ParcelaFinanceiro;
import br.com.ultraworks.erp.api.financeiro.domain.parcela.ParcelaFinanceiroDTO;
import br.com.ultraworks.erp.api.financeiro.repository.ParcelaRepository;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class ParcelaFinanceiraMapper extends GenericMapper<ParcelaFinanceiro, ParcelaFinanceiroDTO> {

	private MovimentoFinanceiroMapper movimentoFinanceiroMapper;

	public ParcelaFinanceiraMapper(ParcelaRepository repository, MovimentoFinanceiroMapper movimentoFinanceiroMapper) {
		super(repository, ParcelaFinanceiro::new, ParcelaFinanceiroDTO::new);
		this.movimentoFinanceiroMapper = movimentoFinanceiroMapper;
	}

	@Override
	protected void setValuesToEntity(ParcelaFinanceiroDTO dto, ParcelaFinanceiro entity) {
		entity.setId(dto.getId());
	}

	@Override
	protected void setValuesToDto(ParcelaFinanceiro entity, ParcelaFinanceiroDTO dto) {
		dto.setId(entity.getId());
		dto.setTituloId(entity.getTitulo().getId());
		dto.setNumParcela(entity.getNumParcela());
		dto.setSeqParcela(entity.getSeqParcela());
		dto.setDataVencimento(entity.getDataVencimento());
		dto.setValorParcela(entity.getValorParcela());
		dto.setUltimaSeqMvto(entity.getUltimaSeqMvto());

		dto.setMovimentos(movimentoFinanceiroMapper.toDto(entity.getMovimentos()));
	}
}
