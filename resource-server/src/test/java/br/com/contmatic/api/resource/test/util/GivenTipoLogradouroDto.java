package br.com.contmatic.api.resource.test.util;

import java.io.Serializable;

import org.apache.commons.lang3.math.NumberUtils;

import br.com.contmatic.integration.dto.TipoLogradouroDto;

public class GivenTipoLogradouroDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static TipoLogradouroDto getTipoLogradouro() {
		TipoLogradouroDto tipoLogradouro = new TipoLogradouroDto();
		tipoLogradouro.setAbreviacao("AV");
		tipoLogradouro.setDescricao("Avenida");
		tipoLogradouro.setId(NumberUtils.LONG_ONE);
		return tipoLogradouro;
	}
	
}
