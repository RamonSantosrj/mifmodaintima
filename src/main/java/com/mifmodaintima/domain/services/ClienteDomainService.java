package com.mifmodaintima.domain.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mifmodaintima.application.dtos.ClientePutRequest;
import com.mifmodaintima.application.dtos.EmailMessageDTO;
import com.mifmodaintima.domain.entity.Cliente;
import com.mifmodaintima.domain.intefaces.IClienteDomainService;
import com.mifmodaintima.helpers.MD5Helper;
import com.mifmodaintima.infrastructure.repositories.IClienteRepository;

@Service
public class ClienteDomainService implements IClienteDomainService {

	@Autowired
	IClienteRepository clienteRepository;

	@Override
	public Cliente save(Cliente cliente) {

		if (clienteRepository.findByEmail(cliente.getEmail()).isPresent())
			throw new IllegalArgumentException("Email ja cadastrado");

		if (clienteRepository.findByTelefone(cliente.getTelefone()).isPresent())
			throw new IllegalArgumentException("Telefone ja cadastrado");

		cliente.setSenha(MD5Helper.getHashMd5(cliente.getSenha()));
		
		
		cliente.setAtivo(true);
		cliente.setCadastradoEm(new Date());
		cliente.setAtualizadoEm(new Date());
		clienteRepository.save(cliente);

		return cliente;
	}

	@Override
	public Cliente delete(String id) {
		Optional<Cliente> optional = clienteRepository.findById(id);
		if (optional.isEmpty())
			throw new IllegalArgumentException("Cliente invalido");

		Cliente cliente = optional.get();
		clienteRepository.delete(cliente);
		return cliente;
	}

	@Override
	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}

	@Override
	public Cliente update(ClientePutRequest dto) {

		Optional<Cliente> optional = clienteRepository.findById(dto.get_id());

		if (optional.isEmpty())
			throw new IllegalArgumentException("Usuario nao encontrado");

		Cliente cliente = optional.get();

		cliente.setNome(dto.getNome());
		cliente.setTelefone(dto.getTelefone());
		cliente.setSenha(MD5Helper.getHashMd5(cliente.getSenha()));
		cliente.setAtualizadoEm(new Date());
		clienteRepository.save(cliente);

		return cliente;

	}

	@Override
	public Cliente Auth(String email, String senha) {

		Optional<Cliente> optional = clienteRepository.findByEmailAndSenha(email, MD5Helper.getHashMd5(senha));

		if (optional.isEmpty())
			throw new IllegalArgumentException("Email ou senha errado");

		Cliente cliente = optional.get();

		return cliente;
	}

	@Override
	public EmailMessageDTO emailMessage(Cliente cliente) {
		
		EmailMessageDTO emailMessageDTO = new EmailMessageDTO();
		emailMessageDTO.setTo(cliente.getEmail());
		emailMessageDTO.setSubject("Parabens cliente cadastrado com sucesso!");
		emailMessageDTO.setBody("Ola, " + cliente.getNome()
		+ "\n\nSua conta de cliuente foi cadastrada com sucesso no ecommerce Mifmodaintima."
		+ "\nSeus dados sao:"
		+ "\nNome: " + cliente.getNome() 
		+ "\nEmail: " + cliente.getEmail()
		+ "\nTelefone: " + cliente.getTelefone()
		+"\nData de nascimento: " + cliente.getDataDeNascimento()
		+ "\n\nAtt"
		
		
				
				
				);
		
		return emailMessageDTO;
	}

}
