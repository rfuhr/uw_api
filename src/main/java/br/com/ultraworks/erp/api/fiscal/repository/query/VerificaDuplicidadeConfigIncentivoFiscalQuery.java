package br.com.ultraworks.erp.api.fiscal.repository.query;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.fiscal.domain.configincentivofiscal.ConfigIncentivoFiscal;
import br.com.ultraworks.erp.core.exception.UnicidadeException;
import br.com.ultraworks.erp.core.exception.ValidationError;
import br.com.ultraworks.erp.core.exception.ValidationErrorResponse;
import br.com.ultraworks.erp.core.util.SQLUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Tuple;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class VerificaDuplicidadeConfigIncentivoFiscalQuery {

	EntityManager em;

	@SuppressWarnings("unchecked")
	public void executeSQL(ConfigIncentivoFiscal configIncentivoFiscal) {
		List<Tuple> resultTuples = em
				.createNativeQuery(SQLUtils.obterQuery("verificaDuplicidadeConfigIncentivoFiscal"), Tuple.class)
				.setParameter("tipoIncentivoFiscalId", configIncentivoFiscal.getTipoIncentivoFiscal().getId())
				.setParameter("dataInicio", configIncentivoFiscal.getDataInicioVigencia())
				.setParameter("dataFinal", configIncentivoFiscal.getDataFinalVigencia())
				.setParameter("validaId", configIncentivoFiscal.getId() != null ? 0 : 1)
				.setParameter("id", configIncentivoFiscal.getId())
				.getResultList();

		if (resultTuples != null && resultTuples.size() > 0) {
			ValidationErrorResponse validationErrorResponse = new ValidationErrorResponse();
			String label = "Validação de Duplicidade do Incentivo Fiscal "; 
			validationErrorResponse.getErrors().add(new ValidationError(label,
					"Não é possível incluir a Configuração de Incentivo Fiscal " + configIncentivoFiscal.getTipoIncentivoFiscal().getNome() + 
					", pois existe uma Configuração cadastrada com o Identificador: " + resultTuples.iterator().next().get("id") + "."));
			throw new UnicidadeException(validationErrorResponse,
					"Ocorreu um erro na validação de Duplicidade da Configuração de Incentivo Fiscal");
		}
	}
}
