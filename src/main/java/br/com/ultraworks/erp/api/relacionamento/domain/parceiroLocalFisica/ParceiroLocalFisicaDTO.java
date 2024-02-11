package br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocalFisica;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ParceiroLocalFisicaDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private Long parceiroLocalId;

	private String rg;

	private Long sexoId;
	private String sexoNome;

	private Long nacionalidadeId;
	private String nacionalidadeNome;

	private Long profissaoId;
	private String profissaoNome;

	private Long estadoCivilId;
	private String estadoCivilNome;

	private Long conjugeId;
	private String conjugeNome;

	private LocalDate dataNascimento;

}
