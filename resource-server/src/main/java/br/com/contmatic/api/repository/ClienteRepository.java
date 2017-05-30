package br.com.contmatic.api.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.contmatic.api.model.Cliente;

@Component
public class ClienteRepository {

	private List<Cliente> clientes = new ArrayList<>();
	
	public ClienteRepository() {
		clientes.add(new Cliente(1, "Cliente 1"));
		clientes.add(new Cliente(2, "Cliente 2"));
		clientes.add(new Cliente(3, "Cliente 3"));
	}

	public List<Cliente> findAll() {
		return clientes;
	}

	public Cliente findById(long id) {
		for (Cliente cliente : clientes) {
			if(cliente.getId() == id){
				return cliente;
			}
		}
		return null;
	}

	public void save(Cliente cliente) {
		cliente.setId(Integer.valueOf(clientes.size()) + 1);
		clientes.add(cliente);
	}
	
}
