package br.com.contmatic.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.contmatic.api.model.Cliente;
import br.com.contmatic.api.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	public List<Cliente> findAllClientes(){
		return clienteRepository.findAll();
	}

	public Cliente findClienteById(long id) {
		return clienteRepository.findById(id);
	}

	public void save(Cliente cliente) {
		clienteRepository.save(cliente);
	}
	
}
