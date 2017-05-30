package br.com.contmatic.api.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import br.com.contmatic.integration.dto.EmpresaDto;
import br.com.contmatic.integration.dto.ServicoDto;

@RestController
@RequestMapping(value = "/servico")
public class ServicosRestController {

	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ServicoDto findById(@PathVariable("id") long id){
		Map<String, Object> params = new HashMap<>();
		params.put("id", id);
		return restTemplate.getForObject("http://localhost:9191/faturamento/servico/" + id, ServicoDto.class, params);
	}
	
	@RequestMapping(value = "/findByCodigoAndEmpresa", method = RequestMethod.POST)
	public ServicoDto findByCodigoAndEmpresa(@RequestParam(value = "codigoServico", required = true) String codigoServico, 
			@RequestParam(required = true, value = "idEmpresa")Long idEmpresa){
		ServicoDto request = new ServicoDto();
		request.setCodigo(codigoServico);
		request.setEmpresa(new EmpresaDto(idEmpresa));
		return restTemplate.postForObject("http://localhost:9191/faturamento/servico/findByCodigoAndEmpresa", request, ServicoDto.class);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/grid", method = RequestMethod.POST)
	public List<ServicoDto> findGrid(@RequestBody ServicoDto request){
		List<ServicoDto> servicos = (List<ServicoDto>)restTemplate.postForObject("http://localhost:9191/faturamento/grid", request, List.class);
		return servicos;
	}
	
	@ResponseStatus(value = HttpStatus.CREATED)
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ServicoDto save(@RequestBody ServicoDto request){
		return restTemplate.postForObject("http://localhost:9191/faturamento/save", request, ServicoDto.class);
	}
	
	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> delete(@PathVariable("id") long id){
		restTemplate.delete("http://localhost:9191/faturamento/" + id);
		return ResponseEntity.ok(Boolean.TRUE);
	}
	
}
