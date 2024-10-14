package br.com.ultraworks.erp.api.agricola.integrator.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.ultraworks.erp.api.agricola.domain.contratoagricola.ContratoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.grupooperacaoagricola.GrupoOperacaoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.romaneioagricola.RomaneioAgricola;
import br.com.ultraworks.erp.api.agricola.domain.tipoprecoagricola.TipoPrecoAgricola;
import br.com.ultraworks.erp.api.estoque.domain.item.Item;
import br.com.ultraworks.erp.api.organograma.domain.departamento.Departamento;
import br.com.ultraworks.erp.api.organograma.domain.empresaFilial.EmpresaFilial;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocal.ParceiroLocal;
import br.com.ultraworks.erp.api.tabela.domain.operacaointerna.OperacaoInterna;
import lombok.Data;

@Data
public class ContainerConfiguracaoIntegracao {

	private ParceiroLocal parceiroLocal;
	private OperacaoInterna operacaoInternaFixacao;
	private Item item;
	private RomaneioAgricola romaneioAgricola;		
	private EmpresaFilial empresaFilial;
	private Departamento departamento;
	private TipoPrecoAgricola tipoPrecoAgricola;
	private GrupoOperacaoAgricola grupoOperacaoAgricola;
	private ContratoAgricola contratoAgricola;
	private BigDecimal quantidadeFixar;
	private boolean descontarLiquido;
	private boolean baixaAutomaticaContrato;
	private LocalDate dataBaixa;
	private LocalDate dataVencimento;
	private BigDecimal valorUnitario;
	private BigDecimal valorTotal;

	private List<ContainerConfiguracaoIntegracaoFixacaoParcelas> parcelasFixacao;

	public ContainerConfiguracaoIntegracao() {
		super();
		parcelasFixacao = new ArrayList<>();
	}

}
