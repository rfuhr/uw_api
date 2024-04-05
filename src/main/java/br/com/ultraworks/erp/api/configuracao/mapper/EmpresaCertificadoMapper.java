package br.com.ultraworks.erp.api.configuracao.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.configuracao.domain.certificado.EmpresaCertificado;
import br.com.ultraworks.erp.api.configuracao.domain.certificado.EmpresaCertificadoDTO;
import br.com.ultraworks.erp.api.configuracao.domain.tipocertificado.TipoCertificado;
import br.com.ultraworks.erp.api.configuracao.repository.EmpresaCertificadoRepository;
import br.com.ultraworks.erp.api.organograma.service.EmpresaService;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class EmpresaCertificadoMapper extends GenericMapper<EmpresaCertificado, EmpresaCertificadoDTO> {

	private EmpresaService empresaService;

	public EmpresaCertificadoMapper(EmpresaCertificadoRepository repository, EmpresaService empresaService) {
		super(repository, EmpresaCertificado::new, EmpresaCertificadoDTO::new);
		this.empresaService = empresaService;
	}

	@Override
	protected void setValuesToEntity(EmpresaCertificadoDTO dto, EmpresaCertificado entity) {
		entity.setId(dto.getId());
		entity.setEmpresa(empresaService.getById(dto.getEmpresaId()).orElseThrow(
				() -> new RegisterNotFoundException("Não encontrado empresa com id " + dto.getEmpresaId())));
		entity.setDataValidade(dto.getDataValidade());
		entity.setSenha(dto.getSenha());
		entity.setTipoCertificado(TipoCertificado.fromValue(dto.getTipoCertificado()));
	}

	@Override
	protected void setValuesToDto(EmpresaCertificado entity, EmpresaCertificadoDTO dto) {
		dto.setId(entity.getId());
		dto.setEmpresaId(entity.getEmpresa().getId());
		dto.setDataValidade(entity.getDataValidade());
		dto.setSenha(entity.getSenha());
		dto.setTipoCertificado(entity.getTipoCertificado().getValue());
	}
}
