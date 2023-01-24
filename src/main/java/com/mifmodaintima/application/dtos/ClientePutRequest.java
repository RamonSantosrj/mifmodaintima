package com.mifmodaintima.application.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ClientePutRequest {

	private String id;
	@NotBlank (message = "Nome do cliente obrigatorio")
	@Size ( min = 4, max = 25, message = "Nome do Cliente deve conter de 4 a 25 caracteres")
	private String nome;
	@Pattern(regexp = "(^$|[0-9]{11})", message = "Telefone deve ter 11 dígitos numéricos.")
	@NotBlank(message = "telefone Obrigatorio.")
	private String telefone;
	@Size(min = 8, max = 20, message = "senha deve conter no minimo 8 e no maximo 20 caracteres")
	@NotBlank(message = "senha obrigatoria")
	private String senha;
	
}
