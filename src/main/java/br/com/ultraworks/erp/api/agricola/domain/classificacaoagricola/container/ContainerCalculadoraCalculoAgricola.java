package br.com.ultraworks.erp.api.agricola.domain.classificacaoagricola.container;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import br.com.ultraworks.erp.api.agricola.domain.grupooperacaoagricola.GrupoOperacaoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.itemclassificacaoagricola.ItemClassificacaoAgricola;
import br.com.ultraworks.erp.api.estoque.domain.item.Item;
import br.com.ultraworks.erp.api.tabela.domain.operacaointerna.OperacaoInterna;
import br.com.ultraworks.erp.api.tabela.domain.regraatividade.RegraAtividade;
import lombok.Builder;
import lombok.Data;

@Data
public class ContainerCalculadoraCalculoAgricola {

	private Long itemId;
	private Long operacaoInternaId;
	private Long grupoOperacaoAgricolaId;
	private Long regraAtividadeId;
	private LocalDate dataBase;
	private boolean calcularGrupo;

	private BigDecimal pesoBruto;
	private BigDecimal pesoTara;
	private BigDecimal pesoLiquido;

	private Long itemClassificacaoAgricolaCalcularId;
	private List<ContainerCalculadoraItemCalculoAgricola> itensClassificacao;
}
