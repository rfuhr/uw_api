package br.com.ultraworks.erp.api.fiscal.domain.nfe.request;

import java.util.List;

public class TransporteNFeRequest {

	private String modalidadeFrete;
	private String tipoTransporte;
	private String placaVeiculo;
	private String siglaUf;
	private String rntc;
	private String vagao;
	private String balsa;
	private String tipoPessoa;
	private String cnpj;
	private String cpf;
	private String nomeRazaoSocial;
	private String inscricaoEstadual;
	private String cep;
	private String endereco;
	private String numero;
	private String complemento;
	private String bairro;
	private Long paisId;
	private Long ufId;
	private Long cidadeId;

	private boolean possuiReboque;
	private boolean possuiVolume;
	private boolean informarLacres;

	private List<ReboqueNFeRequest> reboques;
	private List<VolumeNFeRequest> volumes;
	private List<String> lacres;
}
