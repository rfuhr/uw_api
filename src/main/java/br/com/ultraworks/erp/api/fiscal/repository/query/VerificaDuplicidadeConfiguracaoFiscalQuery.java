package br.com.ultraworks.erp.api.fiscal.repository.query;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.fiscal.domain.configuracaofiscal.ConfiguracaoFiscal;
import br.com.ultraworks.erp.api.fiscal.domain.tipotributo.TipoTributo;
import br.com.ultraworks.erp.core.exception.UnicidadeException;
import br.com.ultraworks.erp.core.exception.ValidationError;
import br.com.ultraworks.erp.core.exception.ValidationErrorResponse;
import br.com.ultraworks.erp.core.util.SQLUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Tuple;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class VerificaDuplicidadeConfiguracaoFiscalQuery {

	EntityManager em;

	@SuppressWarnings("unchecked")
	public void executeSQL(ConfiguracaoFiscal configuracaoFiscal, TipoTributo tipoTributo) {
		List<Tuple> resultTuples = em
				.createNativeQuery(SQLUtils.obterQuery("verificaDuplicidadeConfiguracaoFiscal"), Tuple.class)
				.setParameter("ufOrigemId", configuracaoFiscal.getUfOrigem().getId())
				.setParameter("ufDestinoId", configuracaoFiscal.getUfDestino().getId())
				.setParameter("regimeTributarioId", configuracaoFiscal.getRegimeTributario().getId())
				.setParameter("indicadorOperacao", configuracaoFiscal.getIndicadorOperacao().getValue())
				.setParameter("dataInicio", configuracaoFiscal.getDataInicioVigencia())
				.setParameter("dataFinal", configuracaoFiscal.getDataFinalVigencia())
				.setParameter("validaId", configuracaoFiscal.getId() != null ? 0 : 1)
				.setParameter("id", configuracaoFiscal.getId())
				.setParameter("validaGrupoTributacao", configuracaoFiscal.getGrupoTributacao() != null ? 0 : 1)
				.setParameter("grupoTributacaoId", configuracaoFiscal.getGrupoTributacao() != null ? configuracaoFiscal.getGrupoTributacao().getId() : 0)
				.setParameter("validaCfop", configuracaoFiscal.getCfop() != null ? 0 : 1)
				.setParameter("cfopId", configuracaoFiscal.getCfop() != null ? configuracaoFiscal.getCfop().getId() : 0)
				.setParameter("validaNcm", configuracaoFiscal.getNcm() != null ? 0 : 1)
				.setParameter("ncmId", configuracaoFiscal.getNcm() != null ? configuracaoFiscal.getNcm().getId() : 0)
				.setParameter("validaOrigem", configuracaoFiscal.getOrigem() != null ? 0 : 1)
				.setParameter("origemId", configuracaoFiscal.getOrigem() != null ? configuracaoFiscal.getOrigem().getId() : 0)
				.setParameter("validaOperacaoInterna", configuracaoFiscal.getOperacaoInterna() != null ? 0 : 1)
				.setParameter("operacaoInternaId", configuracaoFiscal.getOperacaoInterna() != null ? configuracaoFiscal.getOperacaoInterna().getId() : 0)
				.setParameter("validaClassificacaoOperacao", configuracaoFiscal.getClassificacaoOperacao() != null ? 0 : 1)
				.setParameter("classificacaoOperacaoId", configuracaoFiscal.getClassificacaoOperacao() != null ? configuracaoFiscal.getClassificacaoOperacao().getId() : 0)
				.setParameter("validaItem", configuracaoFiscal.getItem() != null ? 0 : 1)
				.setParameter("itemId", configuracaoFiscal.getItem() != null ? configuracaoFiscal.getItem().getId() : 0)
				.setParameter("validaIcms", tipoTributo.getValue().equals(TipoTributo.ICMS.getValue()) ? 0 : 1)
				.setParameter("validaIpi", tipoTributo.getValue().equals(TipoTributo.IPI.getValue()) ? 0 : 1)
				.setParameter("validaPis", tipoTributo.getValue().equals(TipoTributo.PIS.getValue()) ? 0 : 1)
				.setParameter("validaCofins", tipoTributo.getValue().equals(TipoTributo.COFINS.getValue()) ? 0 : 1)
				.getResultList();

		if (resultTuples != null && resultTuples.size() > 0) {
			ValidationErrorResponse validationErrorResponse = new ValidationErrorResponse();
			String label = "Validação de Duplicidade do " + tipoTributo.getValue(); 
			validationErrorResponse.getErrors().add(new ValidationError(label,
					"Não é possível incluir a Configuração Fiscal do " + tipoTributo.getValue() + 
					", pois existe uma Configuração cadastrada de número: " + resultTuples.iterator().next().get("id") + "."));
			throw new UnicidadeException(validationErrorResponse,
					"Ocorreu um erro na validação de Duplicidade da Configuração Fiscal");
		}
	}
}
