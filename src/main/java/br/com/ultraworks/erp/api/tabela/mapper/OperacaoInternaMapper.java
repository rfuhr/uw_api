package br.com.ultraworks.erp.api.tabela.mapper;

import java.util.ArrayList;

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
	private OperacaoInternaEstoqueMapper operacaoInternaEstoqueMapper;

	public OperacaoInternaMapper(OperacaoInternaRepository repository,
			NaturezaOperacaoMapper naturezaOperacaoInternaMapper, NaturezaOperacaoService naturezaOperacaoService,
			OperacaoInternaFiscalMapper operacaoInternaFiscalMapper,
			OperacaoInternaEstoqueMapper operacaoInternaEstoqueMapper) {
		super(repository, OperacaoInterna::new, OperacaoInternaDTO::new);
		this.naturezaOperacaoInternaMapper = naturezaOperacaoInternaMapper;
		this.naturezaOperacaoService = naturezaOperacaoService;
		this.operacaoInternaFiscalMapper = operacaoInternaFiscalMapper;
		this.operacaoInternaEstoqueMapper = operacaoInternaEstoqueMapper;
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
		entity.setCaracteristicaEstoque(dto.isCaracteristicaEstoque());
		if (dto.getOperacaoInternaFiscal() != null) {
			entity.setOperacoesInternasFiscal(new ArrayList<>());
			entity.getOperacoesInternasFiscal()
					.add(operacaoInternaFiscalMapper.toEntity(dto.getOperacaoInternaFiscal()));
			entity.getOperacoesInternasFiscal().forEach(dado -> {
				if (dado.getOperacaoInterna() == null)
					dado.setOperacaoInterna(entity);
			});
		}
		if (dto.getOperacaoInternaEstoque() != null) {
			entity.setOperacaoInternaEstoque(operacaoInternaEstoqueMapper.toEntity(dto.getOperacaoInternaEstoque()));
		}
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
		dto.setCaracteristicaEstoque(entity.isCaracteristicaEstoque());
		if (entity.getOperacoesInternasFiscal() != null && entity.getOperacoesInternasFiscal().size() > 0) {
			dto.setOperacaoInternaFiscal(operacaoInternaFiscalMapper.toDto(entity.getOperacoesInternasFiscal()).get(0));
		}
		if (entity.getOperacaoInternaEstoque() != null) {
			dto.setOperacaoInternaEstoque(operacaoInternaEstoqueMapper.toDto(entity.getOperacaoInternaEstoque()));			
		}
	}
}
