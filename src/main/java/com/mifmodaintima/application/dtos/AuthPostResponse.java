package com.mifmodaintima.application.dtos;

import com.mifmodaintima.domain.entity.Cliente;

import lombok.Data;

@Data
public class AuthPostResponse {

	private String mensagem;
	private String AcessToken;
	private Cliente Cliente;
	
	
}
