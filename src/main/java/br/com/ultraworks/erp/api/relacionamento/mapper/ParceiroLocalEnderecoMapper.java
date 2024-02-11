package br.com.ultraworks.erp.api.relacionamento.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocalEndereco.ParceiroLocalEndereco;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocalEndereco.ParceiroLocalEnderecoDTO;
import br.com.ultraworks.erp.api.relacionamento.repository.ParceiroLocalEnderecoRepository;
import br.com.ultraworks.erp.api.relacionamento.repository.ParceiroLocalRepository;
import br.com.ultraworks.erp.api.tabela.domain.tipoendereco.TipoEndereco;
import br.com.ultraworks.erp.api.tabela.repository.CidadeRepository;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class ParceiroLocalEnderecoMapper extends GenericMapper<ParceiroLocalEndereco, ParceiroLocalEnderecoDTO> {

	private ParceiroLocalRepository parceiroLocalRepository;
	private CidadeRepository cidadeRepository;

	public ParceiroLocalEnderecoMapper(ParceiroLocalEnderecoRepository parceiroEnderecoRepository,
			ParceiroLocalRepository parceiroLocalRepository, CidadeRepository cidadeRepository) {
		super(parceiroEnderecoRepository, ParceiroLocalEndereco::new, ParceiroLocalEnderecoDTO::new);
		this.parceiroLocalRepository = parceiroLocalRepository;
		this.cidadeRepository = cidadeRepository;
	}

	@Override
	protected void setValuesToEntity(ParceiroLocalEnderecoDTO dto, ParceiroLocalEndereco entity) {
		entity.setId(dto.getId());
		if (dto.getParceiroLocalId() != null) {
			entity.setParceiroLocal(parceiroLocalRepository.findById(dto.getParceiroLocalId())
					.orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado local de parceiro com id " + dto.getParceiroLocalId())));
		}
		entity.setTipoEndereco(TipoEndereco.fromCodigo(dto.getTipoEndereco()));
		entity.setIdentificacao(dto.getIdentificacao());
		entity.setCep(dto.getCep());
		entity.setCidade(cidadeRepository.findById(dto.getCidadeId())
				.orElseThrow(() -> new RegisterNotFoundException("Não encontrado cidade com id " + dto.getCidadeId())));
		entity.setEndereco(dto.getEndereco());
		entity.setComplemento(dto.getComplemento());
		entity.setNumero(dto.getNumero());
		entity.setBairro(dto.getBairro());
		
	}

	@Override
	protected void setValuesToDto(ParceiroLocalEndereco entity, ParceiroLocalEnderecoDTO dto) {
		dto.setId(entity.getId());
		dto.setParceiroLocalId(entity.getParceiroLocal().getId());
		dto.setTipoEndereco(entity.getTipoEndereco().getCodigo());
		dto.setIdentificacao(entity.getIdentificacao());
		dto.setCep(entity.getCep());
		dto.setCidadeId(entity.getCidade().getId());
		dto.setCidadeNome(entity.getCidade().getNome());
		dto.setUfId(entity.getCidade().getUf().getId());
		dto.setUfSigla(entity.getCidade().getUf().getSigla());
		dto.setPaisId(entity.getCidade().getPais().getId());
		dto.setPaisNome(entity.getCidade().getPais().getNome());
		dto.setEndereco(entity.getEndereco());
		dto.setComplemento(entity.getComplemento());
		dto.setNumero(entity.getNumero());
		dto.setBairro(entity.getBairro());		
	}
}
