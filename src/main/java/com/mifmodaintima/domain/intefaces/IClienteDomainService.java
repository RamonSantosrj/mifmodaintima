package com.mifmodaintima.domain.intefaces;


import java.util.List;

import com.mifmodaintima.domain.entity.Cliente;



public interface IClienteDomainService  {

	Cliente save(Cliente cliente);
	
	Cliente delete (String id);
	
	Cliente update (Cliente cliente);
	
	Cliente Auth (String email,String senha);
	
	List<Cliente> findAll();
	
}
