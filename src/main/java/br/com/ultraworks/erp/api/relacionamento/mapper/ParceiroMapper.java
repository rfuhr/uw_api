package br.com.ultraworks.erp.api.relacionamento.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.relacionamento.domain.parceiro.Parceiro;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiro.ParceiroDTO;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocal.ParceiroLocal;
import br.com.ultraworks.erp.api.relacionamento.repository.ParceiroRepository;
import br.com.ultraworks.erp.api.tabela.domain.tipopessoa.TipoPessoa;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class ParceiroMapper extends GenericMapper<Parceiro, ParceiroDTO> {

	private ParceiroLocalMapper parceiroLocalMapper;

	public ParceiroMapper(ParceiroRepository parceiroRepository, ParceiroLocalMapper parceiroLocalMapper) {
		super(parceiroRepository, Parceiro::new, ParceiroDTO::new);
		this.parceiroLocalMapper = parceiroLocalMapper;
    }

	@Override
	protected void setValuesToEntity(ParceiroDTO dto, Parceiro entity) {
		entity.setId(dto.getId());
		entity.setNomeRazaoSocial(dto.getNomeRazaoSocial());
		entity.setNomeFantasia(dto.getNomeFantasia());
		entity.setTipoPessoa(TipoPessoa.fromValue(dto.getTipoPessoa()));
		entity.setRaizCnpjCpf(dto.getRaizCnpjCpf());
		
		if (dto.getLocais() != null) {
			if (entity.getLocais() == null) entity.setLocais(new ArrayList<>()); else entity.getLocais().clear();
			List<ParceiroLocal> locais = parceiroLocalMapper.toEntity(dto.getLocais());
			locais.forEach(local -> {
				if (local.getParceiro() == null) local.setParceiro(entity);
			});
			entity.getLocais().addAll(locais);
		} else {
			entity.getLocais().clear();
		}
	}

	@Override
	protected void setValuesToDto(Parceiro entity, ParceiroDTO dto) {
		dto.setId(entity.getId());
		dto.setNomeRazaoSocial(entity.getNomeRazaoSocial());
		dto.setNomeFantasia(entity.getNomeFantasia());
		dto.setTipoPessoa(entity.getTipoPessoa().getValue());
		dto.setRaizCnpjCpf(entity.getRaizCnpjCpf());
		dto.setLocais(new ArrayList<>());
		
		if (entity.getLocais() != null && entity.getLocais().size() > 0) {
			entity.getLocais()
			.forEach(local -> dto.getLocais().add(parceiroLocalMapper.toDto(local)));
		}
	}	
}
