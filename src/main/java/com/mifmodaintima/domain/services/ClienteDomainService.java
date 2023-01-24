package com.mifmodaintima.domain.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public Cliente update(Cliente cliente) {

		Optional<Cliente> optional = clienteRepository.findById(cliente.get_id());
		
		if(optional.isEmpty())
			throw new IllegalArgumentException("Usuario nao encontrado");
		
		cliente.setSenha(MD5Helper.getHashMd5(cliente.getSenha()));
		cliente.setAtualizadoEm(new Date());
		clienteRepository.save(cliente);

		return cliente;

	}

	@Override
	public Cliente Auth(String email, String senha) {
		
		Optional<Cliente> optional = clienteRepository.findByEmailAndSenha(email, MD5Helper.getHashMd5(senha));
		
		if(optional.isEmpty())
			throw new IllegalArgumentException("Email ou senha errado");
		
		Cliente cliente = optional.get();
		
		
		return cliente;
	}
}
