package br.com.ultraworks.erp.api.fiscal.repository.query;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.estoque.domain.item.Item;
import br.com.ultraworks.erp.api.estoque.service.ItemService;
import br.com.ultraworks.erp.api.fiscal.domain.configuracaofiscal.TributacaoRequest;
import br.com.ultraworks.erp.api.fiscal.domain.configuracaofiscal.TributacaoResponse;
import br.com.ultraworks.erp.api.fiscal.domain.configuracaofiscalcofins.ConfiguracaoFiscalCofins;
import br.com.ultraworks.erp.api.fiscal.domain.configuracaofiscalicms.ConfiguracaoFiscalIcms;
import br.com.ultraworks.erp.api.fiscal.domain.configuracaofiscalipi.ConfiguracaoFiscalIpi;
import br.com.ultraworks.erp.api.fiscal.domain.configuracaofiscalpis.ConfiguracaoFiscalPis;
import br.com.ultraworks.erp.api.fiscal.domain.tipotributo.TipoTributo;
import br.com.ultraworks.erp.api.fiscal.mapper.ConfiguracaoFiscalCofinsMapper;
import br.com.ultraworks.erp.api.fiscal.mapper.ConfiguracaoFiscalIcmsMapper;
import br.com.ultraworks.erp.api.fiscal.mapper.ConfiguracaoFiscalIpiMapper;
import br.com.ultraworks.erp.api.fiscal.mapper.ConfiguracaoFiscalPisMapper;
import br.com.ultraworks.erp.api.fiscal.service.ConfiguracaoFiscalCofinsService;
import br.com.ultraworks.erp.api.fiscal.service.ConfiguracaoFiscalIcmsService;
import br.com.ultraworks.erp.api.fiscal.service.ConfiguracaoFiscalIpiService;
import br.com.ultraworks.erp.api.fiscal.service.ConfiguracaoFiscalPisService;
import br.com.ultraworks.erp.core.exception.UnicidadeException;
import br.com.ultraworks.erp.core.exception.ValidationError;
import br.com.ultraworks.erp.core.exception.ValidationErrorResponse;
import br.com.ultraworks.erp.core.util.SQLUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Tuple;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BuscaConfiguracaoFiscalPorTributoQuery {

	EntityManager em;
	ItemService itemService;
	ConfiguracaoFiscalIcmsService configuracaoFiscalIcmsService;
	ConfiguracaoFiscalIcmsMapper configuracaoFiscalIcmsMapper;
	ConfiguracaoFiscalIpiService configuracaoFiscalIpiService;
	ConfiguracaoFiscalIpiMapper configuracaoFiscalIpiMapper;
	ConfiguracaoFiscalPisService configuracaoFiscalPisService;
	ConfiguracaoFiscalPisMapper configuracaoFiscalPisMapper;
	ConfiguracaoFiscalCofinsService configuracaoFiscalCofinsService;
	ConfiguracaoFiscalCofinsMapper configuracaoFiscalCofinsMapper;

	@SuppressWarnings({ "unchecked", "removal" })
	public TributacaoResponse executeSQL(TributacaoRequest tributacaoRequest) {
		TributacaoResponse response = new TributacaoResponse();
		Optional<Item> item = itemService.getById(tributacaoRequest.getItemId());
		
		for (int i = 0; i < TipoTributo.values().length; i++) {
			TipoTributo tipoTributo = TipoTributo.values()[i];

			List<Tuple> resultTuples = em
					.createNativeQuery(SQLUtils.obterQuery("buscaConfiguracaoFiscalPorTributo"), Tuple.class)
					.setParameter("ufOrigemId", tributacaoRequest.getUfOrigem())
					.setParameter("ufDestinoId", tributacaoRequest.getUfDestino())
					.setParameter("regimeTributarioId", tributacaoRequest.getRegimeTributarioId())
					.setParameter("indicadorOperacao", tributacaoRequest.getIndicadorOperacaoValue())
					.setParameter("dataEmissao", tributacaoRequest.getDataBase())
					.setParameter("cfopId", tributacaoRequest.getCfopId())
					.setParameter("ncmId", item.get().getNcm().getId())
					.setParameter("origemId", tributacaoRequest.getOrigemId())
					.setParameter("operacaoInternaId", tributacaoRequest.getOperacaoInternaId())
					.setParameter("classificacaoOperacaoId", tributacaoRequest.getClassificacaoOperacaoId())
					.setParameter("itemId", tributacaoRequest.getItemId())
					.setParameter("validaIcms", tipoTributo.getValue().equals(TipoTributo.ICMS.getValue()) ? 0 : 1)
					.setParameter("validaIpi", tipoTributo.getValue().equals(TipoTributo.IPI.getValue()) ? 0 : 1)
					.setParameter("validaPis", tipoTributo.getValue().equals(TipoTributo.PIS.getValue()) ? 0 : 1)
					.setParameter("validaCofins", tipoTributo.getValue().equals(TipoTributo.COFINS.getValue()) ? 0 : 1)
					.setParameter("icms", tipoTributo.getValue().equals(TipoTributo.ICMS.getValue()) ? true : false)
					.setParameter("ipi", tipoTributo.getValue().equals(TipoTributo.IPI.getValue()) ? true : false)
					.setParameter("pis", tipoTributo.getValue().equals(TipoTributo.PIS.getValue()) ? true : false)
					.setParameter("cofins", tipoTributo.getValue().equals(TipoTributo.COFINS.getValue()) ? true : false)					
					.getResultList();
			
			if (resultTuples == null || resultTuples.size() == 0) {
				ValidationErrorResponse validationErrorResponse = new ValidationErrorResponse();
				String label = "Configuração Fiscal do Tributo " + tipoTributo.getValue(); 
				validationErrorResponse.getErrors().add(new ValidationError(label,
						"Não é possível calcular a Tributação do Produto " + item.get().getNome() + 
						", pois não foi encontrada uma Configuração fiscal para o Tributo " + tipoTributo.getValue() + "."));
				throw new UnicidadeException(validationErrorResponse,
						"Não foi encontrada a Configuração Fiscal do "+ tipoTributo.getValue());
			}
			
			if (tipoTributo.getValue().equals(TipoTributo.ICMS.getValue())) {
				response.setTemIcms(true);
				response.setTemIcmsParaUfDestino(false);
				response.setTemRepasseIcms(false);
				response.setTemPartilhaIcms(false);
				Optional<ConfiguracaoFiscalIcms> configuracaoFiscalIcms = configuracaoFiscalIcmsService.getById(new Long(resultTuples.iterator().next().get("icmsId").toString()));
				 if (configuracaoFiscalIcms.isPresent()) {
					 response.setConfiguracaoFiscalIcms(configuracaoFiscalIcmsMapper.toDto(configuracaoFiscalIcms.get()));
				 }
			} else if (tipoTributo.getValue().equals(TipoTributo.IPI.getValue())) {
				response.setTemIpi(true);
				Optional<ConfiguracaoFiscalIpi> configuracaoFiscalIpi = configuracaoFiscalIpiService.getById(new Long(resultTuples.iterator().next().get("ipiId").toString()));
				 if (configuracaoFiscalIpi.isPresent()) {
					 response.setConfiguracaoFiscalIpi(configuracaoFiscalIpiMapper.toDto(configuracaoFiscalIpi.get()));
				 }
			} else if (tipoTributo.getValue().equals(TipoTributo.PIS.getValue())) {
				response.setTemPis(true);
				Optional<ConfiguracaoFiscalPis> configuracaoFiscalPis = configuracaoFiscalPisService.getById(new Long(resultTuples.iterator().next().get("pisId").toString()));
				 if (configuracaoFiscalPis.isPresent()) {
					 response.setConfiguracaoFiscalPis(configuracaoFiscalPisMapper.toDto(configuracaoFiscalPis.get()));
					 if (configuracaoFiscalPis.get().getAliquotaST() != null && configuracaoFiscalPis.get().getAliquotaST().doubleValue() > 0 ) {
						 response.setTemPisSt(true);
					 } else {
						 response.setTemPisSt(false);
					 }
				 }
			} else if (tipoTributo.getValue().equals(TipoTributo.COFINS.getValue())) {
				response.setTemCofins(true);
				Optional<ConfiguracaoFiscalCofins> configuracaoFiscalCofins = configuracaoFiscalCofinsService.getById(new Long(resultTuples.iterator().next().get("cofinsId").toString()));
				 if (configuracaoFiscalCofins.isPresent()) {
					 response.setConfiguracaoFiscalCofins(configuracaoFiscalCofinsMapper.toDto(configuracaoFiscalCofins.get()));
					 if (configuracaoFiscalCofins.get().getAliquotaST() != null && configuracaoFiscalCofins.get().getAliquotaST().doubleValue() > 0 ) {
						 response.setTemCofinsSt(true);
					 } else {
						 response.setTemCofinsSt(false);
					 }
				 }
			}
		}
		
		return response;
	}
}
