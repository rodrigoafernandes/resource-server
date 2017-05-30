package br.com.contmatic.api.resource.test.util;

import java.io.Serializable;

import org.apache.commons.lang3.math.NumberUtils;

import br.com.contmatic.integration.dto.PaisDto;

public class GivenPaisDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static PaisDto getPais() {
		PaisDto pais = new PaisDto();
		pais.setCodigo(NumberUtils.LONG_ONE);
		pais.setDescricao("Brasil");
		pais.setId(NumberUtils.LONG_ONE);
		return pais;
	}
	
}
