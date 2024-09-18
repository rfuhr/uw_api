package br.com.ultraworks.erp.api.financeiro.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.financeiro.domain.tipotitulo.TipoTitulo;
import br.com.ultraworks.erp.api.financeiro.domain.tipotitulo.TipoTituloDTO;
import br.com.ultraworks.erp.api.financeiro.domain.titulo.Titulo;
import br.com.ultraworks.erp.api.financeiro.domain.titulo.TituloDTO;
import br.com.ultraworks.erp.api.financeiro.repository.TituloRepository;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class TituloMapper extends GenericMapper<Titulo, TituloDTO> {

	private ParcelaFinanceiraMapper parcelaFinanceiraMapper;

	public TituloMapper(TituloRepository repository, ParcelaFinanceiraMapper parcelaFinanceiraMapper) {
		super(repository, Titulo::new, TituloDTO::new);
		this.parcelaFinanceiraMapper = parcelaFinanceiraMapper;
	}

	@Override
	protected void setValuesToEntity(TituloDTO dto, Titulo entity) {
		entity.setId(dto.getId());
	}

	@Override
	protected void setValuesToDto(Titulo entity, TituloDTO dto) {
		dto.setId(entity.getId());
		dto.setTipoTituloId(entity.getTipoTitulo().getId());
		dto.setTipoTituloNome(entity.getTipoTitulo().getNome());
		dto.setNossoNumero(entity.getNossoNumero());
		dto.setEmpresaFilialId(entity.getDepartamento().getEmpresaFilial().getId());
		dto.setEmpresaFilialNome(entity.getDepartamento().getEmpresaFilial().getNome());
		dto.setDepartamentoId(entity.getDepartamento().getId());
		dto.setDepartamentoNome(entity.getDepartamento().getNome());
		dto.setParceiroId(entity.getParceiroLocal().getParceiro().getId());
		dto.setParceiroNome(entity.getParceiroLocal().getParceiro().getNomeRazaoSocial());
		dto.setParceiroLocalId(entity.getParceiroLocal().getId());
		dto.setParceiroLocalNome(entity.getParceiroLocal().getNomeLocal());
		dto.setCaracteristicaMovimentoFinanceiroId(entity.getCaracteristicaMovimentoFinanceiro().getId());
		dto.setCaracteristicaMovimentoFinanceiroNome(entity.getCaracteristicaMovimentoFinanceiro().getNome());
		dto.setGrupoFinanceiroId(entity.getGrupoFinanceiro().getId());
		dto.setGrupoFinanceiroNome(entity.getGrupoFinanceiro().getNome());
		dto.setFatoGeradorId(entity.getFatoGerador().getId());
		dto.setFatoGeradorNome(entity.getFatoGerador().getNome());
		dto.setHistoricoPadraoId(entity.getHistoricoPadrao().getId());
		dto.setHistoricoPadraoNome(entity.getHistoricoPadrao().getNome());
		dto.setDocumento(entity.getDocumento());
		dto.setComplemento(entity.getHistorico());
		dto.setValorTitulo(entity.getValorTotal());
		dto.setObservacao(entity.getObservacao());
		if (entity.getNegociacaoFinanceira() != null)
			dto.setNegociacaoFinanceiraId(entity.getNegociacaoFinanceira().getId());
		
		dto.setParcelas(parcelaFinanceiraMapper.toDto(entity.getParcelas()));

	}
}
