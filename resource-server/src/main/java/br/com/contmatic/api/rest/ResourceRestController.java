package br.com.contmatic.api.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.contmatic.api.model.Cliente;
import br.com.contmatic.api.service.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ResourceRestController {

	@Autowired
	private ClienteService clienteService;
	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Cliente>> findAllClientes(){ 
		return ResponseEntity.ok(this.clienteService.findAllClientes());
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Cliente> findClienteById(@PathVariable("id") long id){
		return ResponseEntity.ok(this.clienteService.findClienteById(id));
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<HttpStatus> saveCliente(Cliente cliente){
		this.clienteService.save(cliente);
		ResponseEntity<HttpStatus> response = new ResponseEntity<HttpStatus>(HttpStatus.CREATED);
		return response;
	}
	
}