package br.com.ultraworks.erp.api.tabela.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.tabela.domain.operacaointerna.OperacaoInterna;
import br.com.ultraworks.erp.api.tabela.domain.operacaointerna.OperacaoInternaDTO;
import br.com.ultraworks.erp.api.tabela.repository.OperacaoInternaRepository;
import br.com.ultraworks.erp.api.tabela.service.NaturezaOperacaoService;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class OperacaoInternaMapper extends GenericMapper<OperacaoInterna, OperacaoInternaDTO> {

	private NaturezaOperacaoMapper naturezaOperacaoInternaMapper;
	private NaturezaOperacaoService naturezaOperacaoService;
	private OperacaoInternaFiscalMapper operacaoInternaFiscalMapper;

	public OperacaoInternaMapper(OperacaoInternaRepository repository,
			NaturezaOperacaoMapper naturezaOperacaoInternaMapper, NaturezaOperacaoService naturezaOperacaoService,
			OperacaoInternaFiscalMapper operacaoInternaFiscalMapper) {
		super(repository, OperacaoInterna::new, OperacaoInternaDTO::new);
		this.naturezaOperacaoInternaMapper = naturezaOperacaoInternaMapper;
		this.naturezaOperacaoService = naturezaOperacaoService;
		this.operacaoInternaFiscalMapper = operacaoInternaFiscalMapper;
	}

	@Override
	protected void setValuesToEntity(OperacaoInternaDTO dto, OperacaoInterna entity) {
		entity.setId(dto.getId());
		entity.setNome(dto.getNome());
		entity.setSigla(dto.getSigla());
		entity.setNaturezaOperacao(naturezaOperacaoService.getById(dto.getNaturezaOperacaoId())
				.orElseThrow(() -> new RegisterNotFoundException(
						"Não encontrado empresa filial com id " + dto.getNaturezaOperacaoId())));
		entity.setCaracteristicaFiscal(dto.isCaracteristicaFiscal());
		if (dto.getOperacaoInternaFiscal() != null)
			entity.setOperacaoInternaFiscal(operacaoInternaFiscalMapper.toEntity(dto.getOperacaoInternaFiscal()));
	}

	@Override
	protected void setValuesToDto(OperacaoInterna entity, OperacaoInternaDTO dto) {
		dto.setId(entity.getId());
		dto.setId(entity.getId());
		dto.setNome(entity.getNome());
		dto.setSigla(entity.getSigla());
		dto.setNaturezaOperacao(naturezaOperacaoInternaMapper.toDto(entity.getNaturezaOperacao()));
		dto.setNaturezaOperacaoId(entity.getNaturezaOperacao().getId());
		dto.setCaracteristicaFiscal(entity.isCaracteristicaFiscal());
		if (entity.getOperacaoInternaFiscal() != null) {
			dto.setOperacaoInternaFiscal(operacaoInternaFiscalMapper.toDto(entity.getOperacaoInternaFiscal()));
		}

	}
}
