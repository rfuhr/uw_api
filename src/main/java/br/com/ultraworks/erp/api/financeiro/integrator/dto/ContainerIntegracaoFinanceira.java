package br.com.ultraworks.erp.api.financeiro.integrator.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ContainerIntegracaoFinanceira {

	private ContainerDefinicaoOperacao containerDefinicaoOperacao = null;
	private ContainerTitulo containerTitulo = null;
	private ContainerNegociacao containerNegociacao = null;
	private List<ContainerParcela> listContainerParcela = new ArrayList<>();
	private List<ContainerMovimento> listContainerMovimentos = new ArrayList<>();
	private List<ContainerParcelaBaixa> listContainerBaixaParcela = new ArrayList<>();
	private List<ContainerEstornoMovimento> listContainerEstornoMovimentos = new ArrayList<>();
	private List<ContainerParcelaSubstituicao> listContainerSubstituicaoParcela = new ArrayList<>();

}
