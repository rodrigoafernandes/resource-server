package br.com.contmatic.api.resource.test.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;

import br.com.contmatic.integration.dto.EnderecoDto;
import br.com.contmatic.integration.dto.enumeration.EnumTipoEnderecoDto;

public class GivenEndercoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static List<EnderecoDto> getEnderecos() {
		List<EnderecoDto> enderecos = new ArrayList<EnderecoDto>();
		enderecos.add(getEndereco());
		return enderecos;
	}

	public static EnderecoDto getEndereco() {
		EnderecoDto endereco = new EnderecoDto();
		endereco.setBairro("São Mateus");
		endereco.setCep("03666-588");
		endereco.setComplemento("Casa 1");
		endereco.setId(NumberUtils.LONG_ONE);
		endereco.setLogradouro("Avenida Mateo Bei");
		endereco.setMunicipio(GivenMunicipioDto.getMunicipio());
		endereco.setNomeContato("Antonio Amaro Borges");
		endereco.setNumero("7500");
		endereco.setPais(GivenPaisDto.getPais());
		endereco.setTipoEndereco(EnumTipoEnderecoDto.PRINCIPAL);
		endereco.setTipoLogradouro(GivenTipoLogradouroDto.getTipoLogradouro());
		return endereco;
	}
	
}
