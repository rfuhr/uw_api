package br.com.ultraworks.erp.api.estoque.mapper;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.estoque.domain.movimentoestoque.MovimentoEstoque;
import br.com.ultraworks.erp.api.estoque.domain.movimentoestoque.MovimentoEstoqueDTO;
import br.com.ultraworks.erp.api.estoque.domain.operacaoestoque.OperacaoEstoque;
import br.com.ultraworks.erp.api.estoque.domain.tipodocumentoestoque.TipoDocumentoEstoque;
import br.com.ultraworks.erp.api.estoque.domain.tipomovimentoestoque.TipoMovimentoEstoque;
import br.com.ultraworks.erp.api.estoque.repository.MovimentoEstoqueRepository;
import br.com.ultraworks.erp.api.estoque.service.GrupoContabilService;
import br.com.ultraworks.erp.api.estoque.service.ItemService;
import br.com.ultraworks.erp.api.estoque.service.LocalEstoqueService;
import br.com.ultraworks.erp.api.organograma.service.EmpresaFilialService;
import br.com.ultraworks.erp.api.tabela.service.OperacaoInternaService;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class MovimentoEstoqueMapper extends GenericMapper<MovimentoEstoque, MovimentoEstoqueDTO> {

	LocalEstoqueService localEstoqueService;
	EmpresaFilialService empresaFilialService;
	GrupoContabilService grupoContabilService;
	ItemService itemService;
	OperacaoInternaService operacaoInternaService;
	
	public MovimentoEstoqueMapper(MovimentoEstoqueRepository repository,
			LocalEstoqueService localEstoqueService,
			EmpresaFilialService empresaFilialService,
			GrupoContabilService grupoContabilService,
			ItemService itemService,
			OperacaoInternaService operacaoInternaService) {
		super(repository, MovimentoEstoque::new, MovimentoEstoqueDTO::new);
		this.localEstoqueService = localEstoqueService;
		this.empresaFilialService = empresaFilialService;
		this.grupoContabilService = grupoContabilService;
		this.itemService = itemService;
		this.operacaoInternaService = operacaoInternaService;
	}

	@Override
	protected void setValuesToEntity(MovimentoEstoqueDTO dto, MovimentoEstoque entity) {
		entity.setId(dto.getId());
		entity.setCalculaCustoMedioSaldo(dto.isCalculaCustoMedioSaldo());
		entity.setData(dto.getData());
		entity.setDocumento(dto.getDocumento());
		entity.setEntrada(dto.isEntrada());
		entity.setQuantidade(dto.getQuantidade());
		entity.setValor(dto.getValor());
		entity.setCustoMedio(dto.getCustoMedio());
		entity.setLocalEstoque(localEstoqueService.getById(dto.getLocalEstoqueId())
				.orElseThrow(() -> new RegisterNotFoundException(
						"Não encontrado Local de Estoque com id " + dto.getLocalEstoqueId())));
		entity.setEmpresaFilial(empresaFilialService.getById(dto.getEmpresaFilialId())
				.orElseThrow(() -> new RegisterNotFoundException(
						"Não encontrada a Filial com id " + dto.getEmpresaFilialId())));	
		entity.setGrupoContabil(grupoContabilService.getById(dto.getGrupoContabilId())
				.orElseThrow(() -> new RegisterNotFoundException(
						"Não encontrado o Grupo Contábil com id " + dto.getGrupoContabilId())));
		entity.setItem(itemService.getById(dto.getItemId())
				.orElseThrow(() -> new RegisterNotFoundException(
						"Não encontrado o Item com id " + dto.getItemId())));
		entity.setOperacaoInterna(operacaoInternaService.getById(dto.getOperacaoInternaId())
				.orElseThrow(() -> new RegisterNotFoundException(
						"Não encontrada a Operação Interna com id " + dto.getOperacaoInternaId())));
		if (StringUtils.isNotBlank(dto.getTipoMovimentoEstoque()))
			entity.setTipoMovimentoEstoque(TipoMovimentoEstoque.fromValue(dto.getTipoMovimentoEstoque()));
		if (StringUtils.isNotBlank(dto.getOperacaoEstoqueFisico()))
			entity.setOperacaoEstoqueFisico(OperacaoEstoque.fromValue(dto.getOperacaoEstoqueFisico()));
		if (StringUtils.isNotBlank(dto.getOperacaoEstoqueFinanceiro()))
			entity.setOperacaoEstoqueFinanceiro(OperacaoEstoque.fromValue(dto.getOperacaoEstoqueFinanceiro()));
		if (StringUtils.isNotBlank(dto.getTipoDocumentoEstoque()))
			entity.setTipoDocumentoEstoque(TipoDocumentoEstoque.fromValue(dto.getTipoDocumentoEstoque()));
		entity.setProtocoloDocumento(dto.getProtocoloDocumento());
	}

	@Override
	protected void setValuesToDto(MovimentoEstoque entity, MovimentoEstoqueDTO dto) {
		dto.setId(entity.getId());
		dto.setCalculaCustoMedioSaldo(entity.isCalculaCustoMedioSaldo());
		dto.setData(entity.getData());
		dto.setDocumento(entity.getDocumento());
		dto.setEntrada(entity.isEntrada());
		dto.setQuantidade(entity.getQuantidade());
		dto.setValor(entity.getValor());
		dto.setCustoMedio(entity.getCustoMedio());
		dto.setLocalEstoqueId(entity.getLocalEstoque().getId());
		dto.setLocalEstoqueCodigo(entity.getLocalEstoque().getCodigo());
		dto.setLocalEstoqueNome(entity.getLocalEstoque().getNome());
		dto.setEmpresaFilialId(entity.getEmpresaFilial().getId());
		dto.setEmpresaFilialSigla(entity.getEmpresaFilial().getSigla());
		dto.setEmpresaFilialNome(entity.getEmpresaFilial().getNome());
		dto.setGrupoContabilId(entity.getGrupoContabil().getId());
		dto.setGrupoContabilCodigo(entity.getGrupoContabil().getCodigo());
		dto.setGrupoContabilNome(entity.getGrupoContabil().getNome());
		dto.setItemId(entity.getItem().getId());
		dto.setItemCodigo(entity.getItem().getCodigo());
		dto.setItemNome(entity.getItem().getNome());
		dto.setOperacaoInternaId(entity.getOperacaoInterna().getId());
		dto.setOperacaoInternaSigla(entity.getOperacaoInterna().getSigla());
		dto.setOperacaoInternaNome(entity.getOperacaoInterna().getNome());
		if (entity.getTipoMovimentoEstoque() != null)
			dto.setTipoMovimentoEstoque(entity.getTipoMovimentoEstoque().getValue());
		if (entity.getOperacaoEstoqueFisico() != null)
			dto.setOperacaoEstoqueFisico(entity.getOperacaoEstoqueFisico().getValue());
		if (entity.getOperacaoEstoqueFinanceiro() != null)
			dto.setOperacaoEstoqueFinanceiro(entity.getOperacaoEstoqueFinanceiro().getValue());
		if (entity.getTipoDocumentoEstoque() != null)
			dto.setTipoDocumentoEstoque(entity.getTipoDocumentoEstoque().getValue());
		dto.setProtocoloDocumento(entity.getProtocoloDocumento());
	}

}
