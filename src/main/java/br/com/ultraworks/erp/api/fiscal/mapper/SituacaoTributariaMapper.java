package br.com.ultraworks.erp.api.fiscal.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.fiscal.domain.situacaotributaria.SituacaoTributaria;
import br.com.ultraworks.erp.api.fiscal.domain.situacaotributaria.SituacaoTributariaDTO;
import br.com.ultraworks.erp.api.fiscal.domain.tipotributo.TipoTributo;
import br.com.ultraworks.erp.api.fiscal.repository.SituacaoTributariaRepository;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class SituacaoTributariaMapper extends GenericMapper<SituacaoTributaria, SituacaoTributariaDTO> {

	public SituacaoTributariaMapper(SituacaoTributariaRepository SituacaoTributariaRepository) {
		super(SituacaoTributariaRepository, SituacaoTributaria::new, SituacaoTributariaDTO::new);
    }

	@Override
	protected void setValuesToEntity(SituacaoTributariaDTO dto, SituacaoTributaria entity) {
		entity.setId(dto.getId());
		entity.setCodigo(dto.getCodigo());
		entity.setNome(dto.getNome());
		entity.setTipoTributo(TipoTributo.fromValue(dto.getTipoTributo()));
		entity.setAliquotaZero(dto.isAliquotaZero());
		entity.setControlaImpostoRetido(dto.isControlaImpostoRetido());
		entity.setDestacaStSaida(dto.isDestacaStSaida());
		entity.setExcluirIcmsBaseCalculo(dto.isExcluirIcmsBaseCalculo());
		entity.setExcluirIcmsBcPiscofins(dto.isExcluirIcmsBcPiscofins());
		entity.setExigeAliquotaDesonerada(dto.isExigeAliquotaDesonerada());
		entity.setNaoExcluirIcmsEntrada(dto.isNaoExcluirIcmsEntrada());
		entity.setRequerMensagemFiscal(dto.isRequerMensagemFiscal());
		entity.setSimplesNacional(dto.isSimplesNacional());
	}

	@Override
	protected void setValuesToDto(SituacaoTributaria entity, SituacaoTributariaDTO dto) {
		dto.setId(entity.getId());
		dto.setCodigo(entity.getCodigo());
		dto.setNome(entity.getNome());
		dto.setTipoTributo(entity.getTipoTributo().getValue());
		dto.setAliquotaZero(entity.isAliquotaZero());
		dto.setControlaImpostoRetido(entity.isControlaImpostoRetido());
		dto.setDestacaStSaida(entity.isDestacaStSaida());
		dto.setExcluirIcmsBaseCalculo(entity.isExcluirIcmsBaseCalculo());
		dto.setExcluirIcmsBcPiscofins(entity.isExcluirIcmsBcPiscofins());
		dto.setExigeAliquotaDesonerada(entity.isExigeAliquotaDesonerada());
		dto.setNaoExcluirIcmsEntrada(entity.isNaoExcluirIcmsEntrada());
		dto.setRequerMensagemFiscal(entity.isRequerMensagemFiscal());
		dto.setSimplesNacional(entity.isSimplesNacional());
	}	
}
