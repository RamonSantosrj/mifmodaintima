package com.mifmodaintima.application.controllers;

import java.text.ParseException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mifmodaintima.application.dtos.ClientePostRequest;
import com.mifmodaintima.application.dtos.ClientePutRequest;
import com.mifmodaintima.application.dtos.EmailMessageDTO;
import com.mifmodaintima.domain.entity.Cliente;
import com.mifmodaintima.domain.services.ClienteDomainService;
import com.mifmodaintima.helpers.FormatDate;
import com.mifmodaintima.producer.EmailMessageProducer;

import jakarta.validation.Valid;

@RestController
public class ClienteController {

	@Autowired
	private ClienteDomainService clienteDomainService;

	@Autowired
	private EmailMessageProducer emailMessageProducer;
	
	private HttpStatus status;
	
	@GetMapping("/v1/cliente")
	public String test() {
		return "Testando";
	}

	@PostMapping("/v1/cliente")
	public ResponseEntity<Cliente> post(@Valid @RequestBody ClientePostRequest dto) {

		ModelMapper modelMapper = new ModelMapper();
		ObjectMapper objectMapper = new ObjectMapper();
		Cliente cliente = modelMapper.map(dto, Cliente.class);
		try {
			cliente.setDataDeNascimento(FormatDate.formatDate(dto.getData()));
			clienteDomainService.save(cliente);
			status = HttpStatus.CREATED;
			
			EmailMessageDTO emailMessageDTO = clienteDomainService.emailMessage(cliente);
			String message = objectMapper.writeValueAsString(emailMessageDTO);
			
			emailMessageProducer.send(message);
			
			
		} catch (ParseException e) {
			status= HttpStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}catch(IllegalArgumentException e){
			status=HttpStatus.BAD_REQUEST;
			e.getMessage();
		}catch (Exception e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			e.getMessage();
		}
		
		return ResponseEntity.status(status).body(cliente);

	}
	
	@PutMapping ("/v1/cliente")
	public ResponseEntity<Cliente> put(@RequestBody ClientePutRequest dto ){

		ModelMapper modelMapper = new ModelMapper();
		String mensagem=null;
		Cliente cliente = null;
	try {
		
	cliente = clienteDomainService.update(dto);
	status = HttpStatus.OK;
	} catch (IllegalArgumentException e) {
		status = HttpStatus.BAD_REQUEST;
	 mensagem = e.getMessage();
	}catch (Exception e) {
		status = HttpStatus.INTERNAL_SERVER_ERROR;
		 mensagem = e.getMessage();
	}
	return ResponseEntity.status(status).body(cliente);
	}
	
}
