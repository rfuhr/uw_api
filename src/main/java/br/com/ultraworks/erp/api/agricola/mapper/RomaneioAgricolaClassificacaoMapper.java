package br.com.ultraworks.erp.api.agricola.mapper;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.agricola.domain.basecalculoagricola.BaseCalculoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.romaneioagricolaclassificacao.RomaneioAgricolaClassificacao;
import br.com.ultraworks.erp.api.agricola.domain.romaneioagricolaclassificacao.RomaneioAgricolaClassificacaoDTO;
import br.com.ultraworks.erp.api.agricola.domain.tipotaxaagricola.TipoTaxaAgricola;
import br.com.ultraworks.erp.api.agricola.repository.ItemClassificacaoAgricolaRepository;
import br.com.ultraworks.erp.api.agricola.repository.RomaneioAgricolaClassificacaoRepository;
import br.com.ultraworks.erp.api.agricola.repository.RomaneioAgricolaRepository;
import br.com.ultraworks.erp.api.agricola.repository.SubItemClassificacaoAgricolaRepository;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class RomaneioAgricolaClassificacaoMapper
		extends GenericMapper<RomaneioAgricolaClassificacao, RomaneioAgricolaClassificacaoDTO> {

	private RomaneioAgricolaRepository romaneioAgricolaRepository;
	private ItemClassificacaoAgricolaRepository itemClassificacaoAgricolaRepository;
	private SubItemClassificacaoAgricolaRepository subItemClassificacaoAgricolaRepository;

	public RomaneioAgricolaClassificacaoMapper(
			RomaneioAgricolaClassificacaoRepository romaneioAgricolaClassificacaoRepository,
			RomaneioAgricolaRepository romaneioAgricolaRepository,
			ItemClassificacaoAgricolaRepository itemClassificacaoAgricolaRepository,
			SubItemClassificacaoAgricolaRepository subItemClassificacaoAgricolaRepository) {
		super(romaneioAgricolaClassificacaoRepository, RomaneioAgricolaClassificacao::new,
				RomaneioAgricolaClassificacaoDTO::new);
		this.romaneioAgricolaRepository = romaneioAgricolaRepository;
		this.itemClassificacaoAgricolaRepository = itemClassificacaoAgricolaRepository;
		this.subItemClassificacaoAgricolaRepository = subItemClassificacaoAgricolaRepository;
	}

	@Override
	protected void setValuesToEntity(RomaneioAgricolaClassificacaoDTO dto, RomaneioAgricolaClassificacao entity) {
		entity.setId(dto.getId());
		if (dto.getRomaneioAgricolaId() != null) {
			entity.setRomaneioAgricola(romaneioAgricolaRepository.findById(dto.getRomaneioAgricolaId())
					.orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado romaneio agrícola com id " + dto.getRomaneioAgricolaId())));
		}
		entity.setItemClassificacaoAgricola(itemClassificacaoAgricolaRepository
				.findById(dto.getItemClassificacaoAgricolaId()).orElseThrow(() -> new RegisterNotFoundException(
						"Não encontrado item de classificação com id " + dto.getItemClassificacaoAgricolaId())));
		if (dto.getSubItemClassificacaoAgricolaId() != null) {
			entity.setSubItemClassificacaoAgricola(subItemClassificacaoAgricolaRepository
					.findById(dto.getSubItemClassificacaoAgricolaId())
					.orElseThrow(() -> new RegisterNotFoundException("Não encontrado sub. item de classificação com id "
							+ dto.getSubItemClassificacaoAgricolaId())));
		} else {
			entity.setSubItemClassificacaoAgricola(null);
		}
		if (StringUtils.isNotBlank(dto.getBaseCalculoAgricola())) {
			entity.setBaseCalculoAgricola(BaseCalculoAgricola.fromValue(dto.getBaseCalculoAgricola()));
		} else {
			entity.setBaseCalculoAgricola(null);
		}
		entity.setValorBaseCalculo(dto.getValorBaseCalculo());
		entity.setValorBaseCalculoComplementar(dto.getValorBaseCalculoComplementar());
		entity.setValor(dto.getValor());
		entity.setIndicadorDc(dto.getIndicadorDC());
		entity.setOrdem(dto.getOrdem());
		entity.setFatorCalculo(dto.getFatorCalculo());
		if (StringUtils.isNotBlank(dto.getTipoTaxaAgricola())) {
			entity.setTipoTaxaAgricola(TipoTaxaAgricola.fromValue(dto.getTipoTaxaAgricola()));
		} else {
			entity.setTipoTaxaAgricola(null);
		}
		entity.setGerado(dto.isGerado());
		if (dto.getSubItemClassificacaoAgricolaOrigemId() != null) {
			entity.setSubItemClassificacaoAgricolaOrigem(subItemClassificacaoAgricolaRepository
					.findById(dto.getSubItemClassificacaoAgricolaOrigemId())
					.orElseThrow(() -> new RegisterNotFoundException("Não encontrado sub. item de classificação origem com id "
							+ dto.getSubItemClassificacaoAgricolaOrigemId())));
		} else {
			entity.setSubItemClassificacaoAgricolaOrigem(null);
		}

	}

	@Override
	protected void setValuesToDto(RomaneioAgricolaClassificacao entity, RomaneioAgricolaClassificacaoDTO dto) {
		dto.setId(entity.getId());
		dto.setRomaneioAgricolaId(entity.getRomaneioAgricola().getId());
		dto.setItemClassificacaoAgricolaId(entity.getItemClassificacaoAgricola().getId());
		dto.setItemClassificacaoAgricolaNome(entity.getItemClassificacaoAgricola().getNome());
		if (entity.getSubItemClassificacaoAgricola() != null) {
			dto.setSubItemClassificacaoAgricolaId(entity.getSubItemClassificacaoAgricola().getId());
			dto.setSubItemClassificacaoAgricolaNome(entity.getSubItemClassificacaoAgricola().getNome());
		}
		if (entity.getBaseCalculoAgricola() != null) {
			dto.setBaseCalculoAgricola(entity.getBaseCalculoAgricola().getValue());
			dto.setBaseCalculoAgricolaNome(entity.getBaseCalculoAgricola().getName());
		}
		dto.setValorBaseCalculo(entity.getValorBaseCalculo());
		dto.setValorBaseCalculoComplementar(entity.getValorBaseCalculoComplementar());
		dto.setValor(entity.getValor());
		dto.setIndicadorDC(entity.getIndicadorDc());
		dto.setOrdem(entity.getOrdem());
		dto.setFatorCalculo(entity.getFatorCalculo());
		dto.setGerado(entity.isGerado());
		if (entity.getTipoTaxaAgricola() != null) {
			dto.setTipoTaxaAgricola(entity.getTipoTaxaAgricola().getValue());
			dto.setTipoTaxaAgricolaNome(entity.getTipoTaxaAgricola().getName());
		}
		dto.setGrupoClassificacaoAgricolaId(
				entity.getItemClassificacaoAgricola().getGrupoClassificacaoAgricola().getId());
		dto.setGrupoClassificacaoAgricolaNome(
				entity.getItemClassificacaoAgricola().getGrupoClassificacaoAgricola().getNome());
		if (entity.getSubItemClassificacaoAgricolaOrigem() != null) {
			dto.setSubItemClassificacaoAgricolaOrigemId(entity.getSubItemClassificacaoAgricolaOrigem().getId());
			dto.setSubItemClassificacaoAgricolaOrigemNome(entity.getSubItemClassificacaoAgricolaOrigem().getNome());
		}
	}
}
