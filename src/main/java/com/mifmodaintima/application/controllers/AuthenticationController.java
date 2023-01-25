package com.mifmodaintima.application.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mifmodaintima.application.dtos.AuthPostRequest;
import com.mifmodaintima.application.dtos.AuthPostResponse;
import com.mifmodaintima.domain.intefaces.IClienteDomainService;
import com.mifmodaintima.security.TokenAuthenticationService;

@RestController
public class AuthenticationController {
HttpStatus status;
	@Autowired
	IClienteDomainService clienteDomainService;
	@Autowired
	TokenAuthenticationService tokenAuthenticationService;

	@PostMapping ("/v1/auth")
	public ResponseEntity<AuthPostResponse> post(@RequestBody AuthPostRequest dto){
		AuthPostResponse authPostResponse = new AuthPostResponse();
	try {
		
		
		authPostResponse.setCliente(clienteDomainService.Auth(dto.getEmail(),dto.getSenha()));
		authPostResponse.setAcessToken(tokenAuthenticationService.generateToken(dto.getEmail()));
		authPostResponse.setMensagem("Cliente Authenticado com sucesso");
			status = HttpStatus.OK;
			
			
			
	} catch (IllegalArgumentException e) {
		status = HttpStatus.BAD_REQUEST;
		authPostResponse.setMensagem(e.getMessage());
	}catch (Exception e) {
		status = HttpStatus.INTERNAL_SERVER_ERROR;
		authPostResponse.setMensagem(e.getMessage());
		
	}
	return ResponseEntity.status(status).body(authPostResponse);
}
}
