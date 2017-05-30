package br.com.contmatic.api.resource.test.util;

import java.io.Serializable;

import org.apache.commons.lang3.math.NumberUtils;

import br.com.contmatic.integration.dto.MunicipioDto;
import br.com.contmatic.integration.dto.enumeration.UFDto;

public class GivenMunicipioDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static MunicipioDto getMunicipio() {
		MunicipioDto municipioDto = new MunicipioDto();
		municipioDto.setAmazoniaOcidental(Boolean.FALSE);
		municipioDto.setAreaLivreComercio(Boolean.TRUE);
		municipioDto.setCodigoFederal(NumberUtils.INTEGER_ONE);
		municipioDto.setCodigoIBGE(NumberUtils.INTEGER_ONE);
		municipioDto.setCodigoMunicipal(NumberUtils.INTEGER_ONE);
		municipioDto.setDescricao("Descrição");
		municipioDto.setId(NumberUtils.LONG_ONE);
		municipioDto.setUf(UFDto.SP);
		municipioDto.setZonaFrancaManaus(Boolean.FALSE);
		return municipioDto;
	}
	
}
