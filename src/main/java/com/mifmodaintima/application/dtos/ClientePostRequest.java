package com.mifmodaintima.application.dtos;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Valid
public class ClientePostRequest {

	@NotBlank (message = "Nome do cliente obrigatorio")
	@Size ( min = 4, max = 25, message = "Nome do Cliente deve conter de 4 a 25 caracteres")
	private String nome;
	@Size(min = 8, max = 20, message = "senha deve conter no minimo 8 e no maximo 20 caracteres")
	@NotBlank(message = "senha obrigatoria")
	private String senha;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private String data;
	@Email(message = "Email do cliente invalido")
	@NotBlank(message = "email obrigatorio.")
	private String email;
	@Pattern(regexp = "(^$|[0-9]{11})", message = "Telefone deve ter 11 dígitos numéricos.")
	@NotBlank(message = "telefone Obrigatorio.")
	private String telefone;
	
	
	
}
