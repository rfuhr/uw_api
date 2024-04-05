package br.com.ultraworks.erp.api.fiscal.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.estoque.repository.ItemRepository;
import br.com.ultraworks.erp.api.fiscal.domain.configuracaofiscal.ConfiguracaoFiscal;
import br.com.ultraworks.erp.api.fiscal.domain.configuracaofiscal.ConfiguracaoFiscalDTO;
import br.com.ultraworks.erp.api.fiscal.repository.CfopRepository;
import br.com.ultraworks.erp.api.fiscal.repository.ClassificacaoOperacaoRepository;
import br.com.ultraworks.erp.api.fiscal.repository.ConfiguracaoFiscalRepository;
import br.com.ultraworks.erp.api.fiscal.repository.GrupoTributacaoRepository;
import br.com.ultraworks.erp.api.fiscal.repository.NcmRepository;
import br.com.ultraworks.erp.api.fiscal.repository.OrigemRepository;
import br.com.ultraworks.erp.api.fiscal.repository.RegimeTributarioRepository;
import br.com.ultraworks.erp.api.tabela.domain.indicadoroperacao.IndicadorOperacao;
import br.com.ultraworks.erp.api.tabela.repository.OperacaoInternaRepository;
import br.com.ultraworks.erp.api.tabela.repository.PaisRepository;
import br.com.ultraworks.erp.api.tabela.repository.UfRepository;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class ConfiguracaoFiscalMapper extends GenericMapper<ConfiguracaoFiscal, ConfiguracaoFiscalDTO> {

	PaisRepository paisRepository;
	UfRepository ufRepository;
	GrupoTributacaoRepository grupoTributacaoRepository;
	CfopRepository cfopRepository;
	NcmRepository ncmRepository;
	RegimeTributarioRepository regimeTributarioRepository;
	OrigemRepository origemRepository;
	OperacaoInternaRepository operacaoInternaRepository;
	ClassificacaoOperacaoRepository classificacaoOperacaoRepository;
	ItemRepository itemRepository;
	
	ConfiguracaoFiscalIcmsMapper configuracaoFiscalIcmsMapper;
	ConfiguracaoFiscalIpiMapper configuracaoFiscalIpiMapper;
	ConfiguracaoFiscalPisMapper configuracaoFiscalPisMapper;
	ConfiguracaoFiscalCofinsMapper configuracaoFiscalCofinsMapper;
	
	public ConfiguracaoFiscalMapper(ConfiguracaoFiscalRepository ConfiguracaoFiscalRepository, PaisRepository paisRepository, UfRepository ufRepository,
			GrupoTributacaoRepository grupoTributacaoRepository, CfopRepository cfopRepository, NcmRepository ncmRepository,
			RegimeTributarioRepository regimeTributarioRepository, OrigemRepository origemRepository, ItemRepository itemRepository,
			OperacaoInternaRepository operacaoInternaRepository, ClassificacaoOperacaoRepository classificacaoOperacaoRepository,
			ConfiguracaoFiscalIcmsMapper configuracaoFiscalIcmsMapper, ConfiguracaoFiscalIpiMapper configuracaoFiscalIpiMapper,
			ConfiguracaoFiscalPisMapper configuracaoFiscalPisMapper, ConfiguracaoFiscalCofinsMapper configuracaoFiscalCofinsMapper) {
		super(ConfiguracaoFiscalRepository, ConfiguracaoFiscal::new, ConfiguracaoFiscalDTO::new);
		this.paisRepository = paisRepository;
		this.ufRepository = ufRepository;
		this.grupoTributacaoRepository = grupoTributacaoRepository;
		this.cfopRepository = cfopRepository;
		this.ncmRepository = ncmRepository;
		this.regimeTributarioRepository = regimeTributarioRepository;
		this.origemRepository = origemRepository;
		this.itemRepository = itemRepository;
		this.operacaoInternaRepository = operacaoInternaRepository;
		this.classificacaoOperacaoRepository = classificacaoOperacaoRepository;
		this.configuracaoFiscalIcmsMapper = configuracaoFiscalIcmsMapper;
		this.configuracaoFiscalIpiMapper = configuracaoFiscalIpiMapper;
		this.configuracaoFiscalPisMapper = configuracaoFiscalPisMapper;
		this.configuracaoFiscalCofinsMapper = configuracaoFiscalCofinsMapper;
	}

	@Override
	protected void setValuesToEntity(ConfiguracaoFiscalDTO dto, ConfiguracaoFiscal entity) {
		entity.setId(dto.getId());
		entity.setDataInicioVigencia(dto.getDataInicioVigencia());
		entity.setDataFinalVigencia(dto.getDataFinalVigencia());
		entity.setEntradaSaida(IndicadorOperacao.fromValue(dto.getEntradaSaida()));
		entity.setUfOrigem(ufRepository.findById(dto.getUfOrigemId())
				.orElseThrow(() -> new RegisterNotFoundException("Não encontrado UF Origem com id " + dto.getUfOrigemId())));
		entity.setUfDestino(ufRepository.findById(dto.getUfDestinoId())
				.orElseThrow(() -> new RegisterNotFoundException("Não encontrado UF Destino com id " + dto.getUfDestinoId())));
		entity.setPaisDestino(paisRepository.findById(dto.getPaisDestinoId())
				.orElseThrow(() -> new RegisterNotFoundException("Não encontrado País Destino com id " + dto.getPaisDestinoId())));
		if (dto.getGrupoTributacaoId() != null) {
			entity.setGrupoTributacao(grupoTributacaoRepository.findById(dto.getGrupoTributacaoId())
					.orElseThrow(() -> new RegisterNotFoundException("Não encontrado Grupo de Tributação com id " + dto.getGrupoTributacaoId())));
		}
		if (dto.getCfopId() != null) {
			entity.setCfop(cfopRepository.findById(dto.getCfopId())
					.orElseThrow(() -> new RegisterNotFoundException("Não encontrado CFOP com id " + dto.getCfopId())));
		}
		if (dto.getNcmId() != null) {
			entity.setNcm(ncmRepository.findById(dto.getNcmId())
					.orElseThrow(() -> new RegisterNotFoundException("Não encontrado NCM com id " + dto.getNcmId())));
		}
		if (dto.getRegimeTributarioId() != null) {
			entity.setRegimeTributario(regimeTributarioRepository.findById(dto.getRegimeTributarioId())
					.orElseThrow(() -> new RegisterNotFoundException("Não encontrado Regime Tributário com id " + dto.getRegimeTributarioId())));
		}
		if (dto.getOrigemId() != null) {
			entity.setOrigem(origemRepository.findById(dto.getOrigemId())
					.orElseThrow(() -> new RegisterNotFoundException("Não encontrado NCM com id " + dto.getOrigemId())));
		}
		if (dto.getClassificacaoOperacaoId() != null) {
			entity.setClassificacaoOperacao(classificacaoOperacaoRepository.findById(dto.getClassificacaoOperacaoId())
					.orElseThrow(() -> new RegisterNotFoundException("Não encontrado Classificação Operação com id " + dto.getClassificacaoOperacaoId())));
		}
		if (dto.getOperacaoInternaId() != null) {
			entity.setOperacaoInterna(operacaoInternaRepository.findById(dto.getOperacaoInternaId())
					.orElseThrow(() -> new RegisterNotFoundException("Não encontrado Operação Interna com id " + dto.getOperacaoInternaId())));
		}
		if (dto.getItemId() != null) {
			entity.setItem(itemRepository.findById(dto.getItemId())
					.orElseThrow(() -> new RegisterNotFoundException("Não encontrado Item com id " + dto.getItemId())));
		}
		if (dto.getConfiguracaoFiscalIcms().getSituacaoTributariaId() != null) {
			entity.setConfiguracaoFiscalIcms(configuracaoFiscalIcmsMapper.toEntity(dto.getConfiguracaoFiscalIcms()));
		}
		if (dto.getConfiguracaoFiscalPis() != null) {
			entity.setConfiguracaoFiscalPis(configuracaoFiscalPisMapper.toEntity(dto.getConfiguracaoFiscalPis()));
		}
		if (dto.getConfiguracaoFiscalIpi().getSituacaoTributariaId() != null) {
			entity.setConfiguracaoFiscalIpi(configuracaoFiscalIpiMapper.toEntity(dto.getConfiguracaoFiscalIpi()));
		}
		if (dto.getConfiguracaoFiscalCofins() != null) {
			entity.setConfiguracaoFiscalCofins(configuracaoFiscalCofinsMapper.toEntity(dto.getConfiguracaoFiscalCofins()));
		}

	}

	@Override
	protected void setValuesToDto(ConfiguracaoFiscal entity, ConfiguracaoFiscalDTO dto) {
		dto.setId(entity.getId());
		dto.setDataInicioVigencia(entity.getDataInicioVigencia());
		dto.setDataFinalVigencia(entity.getDataFinalVigencia());
		dto.setEntradaSaida(entity.getEntradaSaida().getValue());
		dto.setUfOrigemId(entity.getUfOrigem().getId());
		dto.setUfOrigemNome(entity.getUfOrigem().getNome());
		dto.setUfOrigemSigla(entity.getUfOrigem().getSigla());
		dto.setUfDestinoId(entity.getUfDestino().getId());
		dto.setUfDestinoNome(entity.getUfDestino().getNome());
		dto.setUfDestinoSigla(entity.getUfDestino().getSigla());
		dto.setPaisDestinoId(entity.getPaisDestino().getId());
		dto.setPaisDestinoNome(entity.getPaisDestino().getNome());
		if (entity.getGrupoTributacao() != null) {
			dto.setGrupoTributacaoId(entity.getGrupoTributacao().getId());
			dto.setGrupoTributacaoNome(entity.getGrupoTributacao().getNome());
			dto.setGrupoTributacaoCodigo(new Long(entity.getGrupoTributacao().getCodigo()));
		}
		if (entity.getCfop() != null) {
			dto.setCfopId(entity.getCfop().getId());
			dto.setCfopNome(entity.getCfop().getNome());
			dto.setCfopCodigo(new Long(entity.getCfop().getCodigo()));
		}
		if (entity.getNcm() != null) {
			dto.setNcmId(entity.getNcm().getId());
			dto.setNcmNome(entity.getNcm().getNome());
			dto.setNcmCodigo(entity.getNcm().getCodigo());
		}
		if (entity.getRegimeTributario() != null) {
			dto.setRegimeTributarioId(entity.getRegimeTributario().getId());
			dto.setRegimeTributarioNome(entity.getRegimeTributario().getNome());
		}
		if (entity.getOrigem() != null) {
			dto.setOrigemId(entity.getOrigem().getId());
			dto.setOrigemNome(entity.getOrigem().getNome());
			dto.setOrigemCodigo(new Long(entity.getOrigem().getCodigo()));
		}
		if (entity.getClassificacaoOperacao() != null) {
			dto.setClassificacaoOperacaoId(entity.getClassificacaoOperacao().getId());
			dto.setClassificacaoOperacaoNome(entity.getClassificacaoOperacao().getNome());
			dto.setClassificacaoOperacaoCodigo(new Long(entity.getClassificacaoOperacao().getCodigo()));
		}
		if (entity.getOperacaoInterna() != null) {
			dto.setOperacaoInternaId(entity.getOperacaoInterna().getId());
			dto.setOperacaoInternaNome(entity.getOperacaoInterna().getNome());
			dto.setOperacaoInternaSigla(entity.getOperacaoInterna().getSigla());
		}
		if (entity.getItem() != null) {
			dto.setItemId(entity.getItem().getId());
			dto.setItemNome(entity.getItem().getNome());
			dto.setItemCodigo(new Long(entity.getItem().getCodigo()));
		}
		if (entity.getConfiguracaoFiscalIcms() != null) {
			dto.setConfiguracaoFiscalIcms(configuracaoFiscalIcmsMapper.toDto(entity.getConfiguracaoFiscalIcms()));
		}
		if (entity.getConfiguracaoFiscalIpi() != null) {
			dto.setConfiguracaoFiscalIpi(configuracaoFiscalIpiMapper.toDto(entity.getConfiguracaoFiscalIpi()));
		}
		if (entity.getConfiguracaoFiscalPis() != null) {
			dto.setConfiguracaoFiscalPis(configuracaoFiscalPisMapper.toDto(entity.getConfiguracaoFiscalPis()));
		}
		if (entity.getConfiguracaoFiscalCofins() != null) {
			dto.setConfiguracaoFiscalCofins(configuracaoFiscalCofinsMapper.toDto(entity.getConfiguracaoFiscalCofins()));
		}
	}	
}
