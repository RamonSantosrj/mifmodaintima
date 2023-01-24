package com.mifmodaintima.application.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AuthPostRequest {

	@Email(message = "Email do cliente invalido")
	@NotBlank(message = "email obrigatorio.")
	private String email;
	@Size(min = 8, max = 20, message = "senha deve conter no minimo 8 e no maximo 20 caracteres")
	@NotBlank(message = "senha obrigatoria")
	private String senha;
}
