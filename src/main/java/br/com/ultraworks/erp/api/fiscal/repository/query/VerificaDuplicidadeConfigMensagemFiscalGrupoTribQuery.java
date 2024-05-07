package br.com.ultraworks.erp.api.fiscal.repository.query;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscalgrupotrib.ConfigMensagemFiscalGrupoTrib;
import br.com.ultraworks.erp.core.exception.UnicidadeException;
import br.com.ultraworks.erp.core.exception.ValidationError;
import br.com.ultraworks.erp.core.exception.ValidationErrorResponse;
import br.com.ultraworks.erp.core.util.SQLUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Tuple;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class VerificaDuplicidadeConfigMensagemFiscalGrupoTribQuery {

	EntityManager em;

	@SuppressWarnings("unchecked")
	public void executeSQL(ConfigMensagemFiscalGrupoTrib entity) {
		List<Tuple> resultTuples = em
				.createNativeQuery(SQLUtils.obterQuery("verificaDuplicidadeConfigMensagemFiscalGrupoTrib"), Tuple.class)
				.setParameter("mensagemFiscalId", entity.getConfigMensagemFiscal().getMensagemFiscal().getId())
				.setParameter("grupoTributacaoId", entity.getGrupoTributacao().getId())
				.setParameter("dataInicio", entity.getDataInicioVigencia())
				.setParameter("dataFinal", entity.getDataFinalVigencia())
				.setParameter("validaId", entity.getId() != null ? 0 : 1)
				.setParameter("id", entity.getId())
				.getResultList();

		if (resultTuples != null && resultTuples.size() > 0) {
			ValidationErrorResponse validationErrorResponse = new ValidationErrorResponse();
			String label = "Validação de Duplicidade da Configuração de Mensagem Fiscal - Grupo de Tributação "; 
			validationErrorResponse.getErrors().add(new ValidationError(label,
					"Não é possível incluir o Grupo de Tributação " + entity.getGrupoTributacao().getId() + 
					", pois existe uma Configuração cadastrada com o Identificador: " + resultTuples.iterator().next().get("id") + "."));
			throw new UnicidadeException(validationErrorResponse,
					"Ocorreu um erro na validação de Duplicidade da Configuração de Mensagem Fiscal - Grupo de Tributação");
		}
	}
}
