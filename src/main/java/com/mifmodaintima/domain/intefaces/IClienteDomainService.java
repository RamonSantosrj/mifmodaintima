package com.mifmodaintima.domain.intefaces;


import java.util.List;

import com.mifmodaintima.application.dtos.ClientePutRequest;
import com.mifmodaintima.application.dtos.EmailMessageDTO;
import com.mifmodaintima.domain.entity.Cliente;



public interface IClienteDomainService  {

	Cliente save(Cliente cliente);
	
	Cliente delete (String id);
	
	Cliente update (ClientePutRequest dto);
	
	Cliente Auth (String email,String senha);
	
	List<Cliente> findAll();
	
	EmailMessageDTO emailMessage(Cliente cliente);
	
}
