package br.com.ultraworks.erp.api.seguranca.domain.usuario;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InfoUserViewDTO implements Serializable{

	private String nome;
	private String username;
	private String email;
}
