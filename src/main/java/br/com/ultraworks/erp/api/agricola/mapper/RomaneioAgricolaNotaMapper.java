package br.com.ultraworks.erp.api.agricola.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.agricola.domain.romaneioagricolanota.RomaneioAgricolaNota;
import br.com.ultraworks.erp.api.agricola.domain.romaneioagricolanota.RomaneioAgricolaNotaDTO;
import br.com.ultraworks.erp.api.agricola.repository.RomaneioAgricolaNotaRepository;
import br.com.ultraworks.erp.api.agricola.repository.RomaneioAgricolaRepository;
import br.com.ultraworks.erp.api.fiscal.repository.CfopRepository;
import br.com.ultraworks.erp.api.tabela.repository.OperacaoInternaRepository;
import br.com.ultraworks.erp.api.tabela.repository.TipoDocumentoRepository;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class RomaneioAgricolaNotaMapper extends GenericMapper<RomaneioAgricolaNota, RomaneioAgricolaNotaDTO> {

	private RomaneioAgricolaRepository romaneioAgricolaRepository;
	private OperacaoInternaRepository operacaoInternaRepository;
	private CfopRepository cfopRepository;
	private TipoDocumentoRepository tipoDocumentoRepository;

	public RomaneioAgricolaNotaMapper(RomaneioAgricolaNotaRepository romaneioAgricolaNotaRepository,
			RomaneioAgricolaRepository romaneioAgricolaRepository, OperacaoInternaRepository operacaoInternaRepository,
			CfopRepository cfopRepository, TipoDocumentoRepository tipoDocumentoRepository) {
		super(romaneioAgricolaNotaRepository, RomaneioAgricolaNota::new, RomaneioAgricolaNotaDTO::new);
		this.romaneioAgricolaRepository = romaneioAgricolaRepository;
		this.operacaoInternaRepository = operacaoInternaRepository;
		this.cfopRepository = cfopRepository;
		this.tipoDocumentoRepository = tipoDocumentoRepository;
	}

	@Override
	protected void setValuesToEntity(RomaneioAgricolaNotaDTO dto, RomaneioAgricolaNota entity) {
		entity.setId(dto.getId());
		if (dto.getRomaneioAgricolaId() != null) {
			entity.setRomaneioAgricola(romaneioAgricolaRepository.findById(dto.getRomaneioAgricolaId())
					.orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado romaneio agrícola com id " + dto.getRomaneioAgricolaId())));
		}
		if (dto.getOperacaoInternaId() != null) {
			entity.setOperacaoInterna(operacaoInternaRepository.findById(dto.getOperacaoInternaId())
					.orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado operação interna com id " + dto.getOperacaoInternaId())));
		}
		if (dto.getCfopId() != null) {
			entity.setCfop(cfopRepository.findById(dto.getCfopId())
					.orElseThrow(() -> new RegisterNotFoundException("Não encontrado cfop com id " + dto.getCfopId())));
		}
		if (dto.getTipoDocumentoId() != null) {
			entity.setTipoDocumento(tipoDocumentoRepository.findById(dto.getTipoDocumentoId())
					.orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado tipo de documento com id " + dto.getTipoDocumentoId())));
		}
		entity.setNumeroNota(dto.getNumeroNota());
		entity.setSerie(dto.getSerie());
		entity.setDataEmissao(dto.getDataEmissao());
		entity.setChaveNFe(dto.getChaveNFe());
		entity.setQuantidade(dto.getQuantidade());
		entity.setValorUnitario(dto.getValorUnitario());
		entity.setValorTotal(dto.getValorTotal());

	}

	@Override
	protected void setValuesToDto(RomaneioAgricolaNota entity, RomaneioAgricolaNotaDTO dto) {
		dto.setId(entity.getId());
		dto.setRomaneioAgricolaId(entity.getRomaneioAgricola().getId());
		dto.setOperacaoInternaId(entity.getOperacaoInterna().getId());
		dto.setOperacaoInternaNome(entity.getOperacaoInterna().getNome());
		dto.setOperacaoInternaSigla(entity.getOperacaoInterna().getSigla());
		dto.setCfopId(entity.getCfop().getId());
		dto.setCfopCodigo(entity.getCfop().getCodigo());
		dto.setCfopNome(entity.getCfop().getNome());
		dto.setTipoDocumentoId(entity.getTipoDocumento().getId());
		dto.setTipoDocumentoNome(entity.getTipoDocumento().getNome());
		dto.setTipoDocumentoSigla(entity.getTipoDocumento().getSigla());

		dto.setNumeroNota(entity.getNumeroNota());
		dto.setSerie(entity.getSerie());
		dto.setDataEmissao(entity.getDataEmissao());
		dto.setChaveNFe(entity.getChaveNFe());
		dto.setQuantidade(entity.getQuantidade());
		dto.setValorUnitario(entity.getValorUnitario());
		dto.setValorTotal(entity.getValorTotal());
	}
}
