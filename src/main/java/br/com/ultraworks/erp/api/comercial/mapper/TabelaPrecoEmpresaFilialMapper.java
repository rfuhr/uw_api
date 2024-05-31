package br.com.ultraworks.erp.api.comercial.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.comercial.domain.tabelaprecoempresafilial.TabelaPrecoEmpresaFilial;
import br.com.ultraworks.erp.api.comercial.domain.tabelaprecoempresafilial.TabelaPrecoEmpresaFilialDTO;
import br.com.ultraworks.erp.api.comercial.repository.TabelaPrecoEmpresaFilialRepository;
import br.com.ultraworks.erp.api.comercial.repository.TabelaPrecoRepository;
import br.com.ultraworks.erp.api.organograma.repository.EmpresaFilialRepository;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class TabelaPrecoEmpresaFilialMapper extends GenericMapper<TabelaPrecoEmpresaFilial, TabelaPrecoEmpresaFilialDTO> {

	TabelaPrecoRepository tabelaPrecoRepository;
	EmpresaFilialRepository empresaFilialRepository;
	
	public TabelaPrecoEmpresaFilialMapper(TabelaPrecoEmpresaFilialRepository repository,
			TabelaPrecoRepository tabelaPrecoRepository,
			EmpresaFilialRepository empresaFilialRepository) {
		super(repository, TabelaPrecoEmpresaFilial::new, TabelaPrecoEmpresaFilialDTO::new);
		this.tabelaPrecoRepository = tabelaPrecoRepository;
		this.empresaFilialRepository = empresaFilialRepository;
	}

	@Override
	protected void setValuesToEntity(TabelaPrecoEmpresaFilialDTO dto, TabelaPrecoEmpresaFilial entity) {
		entity.setId(dto.getId());
		if (dto.getTabelaPrecoId() != null) {
			entity.setTabelaPreco(tabelaPrecoRepository.findById(dto.getTabelaPrecoId())
					.orElseThrow(() -> new RegisterNotFoundException("Não encontrada Tabela de Preço com id " + dto.getTabelaPrecoId())));			
		}
		entity.setEmpresaFilial(empresaFilialRepository.findById(dto.getEmpresaFilialId())
				.orElseThrow(() -> new RegisterNotFoundException("Não encontrada a Filial com id " + dto.getEmpresaFilialId())));	
	}

	@Override
	protected void setValuesToDto(TabelaPrecoEmpresaFilial entity, TabelaPrecoEmpresaFilialDTO dto) {
		dto.setId(entity.getId());
		dto.setTabelaPrecoId(entity.getTabelaPreco().getId());
		if (entity.getEmpresaFilial() != null) {
			dto.setEmpresaFilialSigla(entity.getEmpresaFilial().getSigla());
			dto.setEmpresaFilialNome(entity.getEmpresaFilial().getNome());
			dto.setEmpresaFilialId(entity.getEmpresaFilial().getId());
		}
	}
}
