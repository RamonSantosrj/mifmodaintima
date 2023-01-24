package com.mifmodaintima.application.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mifmodaintima.application.dtos.ClientePostRequest;
import com.mifmodaintima.application.dtos.ClientePutRequest;
import com.mifmodaintima.domain.entity.Cliente;
import com.mifmodaintima.domain.services.ClienteDomainService;
import com.mifmodaintima.helpers.FormatDate;

@RestController
public class ClienteController {

	@Autowired
	private ClienteDomainService clienteDomainService;

	private HttpStatus status;
	
	@GetMapping("/v1/cliente")
	public String test() {
		return "Testando";
	}

	@PostMapping("/v1/cliente")
	public ResponseEntity<Cliente> post(@RequestBody ClientePostRequest dto) {

		ModelMapper modelMapper = new ModelMapper();
		Cliente cliente = modelMapper.map(dto, Cliente.class);
		try {
			cliente.setDataDeNascimento(FormatDate.formatDate(dto.getData()));
			clienteDomainService.save(cliente);
			status = HttpStatus.CREATED;
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
		
		Cliente cliente = modelMapper.map(dto, Cliente.class);
		
	cliente = clienteDomainService.update(cliente);
		
		return ResponseEntity.status(HttpStatus.OK).body(cliente);
	}
	
}
