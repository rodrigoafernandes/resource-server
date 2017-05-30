package br.com.contmatic.api.resource.test.util;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;

import br.com.contmatic.integration.dto.SimplesNacionalDto;

public class GivenSimplesNacional implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static List<SimplesNacionalDto> getListSimplesNacional() {
		List<SimplesNacionalDto> listSimplesNacional = new ArrayList<SimplesNacionalDto>();
		listSimplesNacional.add(getSimplesNacional());
		return listSimplesNacional;
	}

	public static SimplesNacionalDto getSimplesNacional() {
		SimplesNacionalDto simplesNacional = new SimplesNacionalDto();
		simplesNacional.setAliquotaIcms(BigDecimal.TEN);
		simplesNacional.setAliquotaIss(BigDecimal.TEN);
		simplesNacional.setEmpresa(GivenEmpresaDto.getNewEmpresa());
		simplesNacional.setId(NumberUtils.LONG_ONE);
		simplesNacional.setKey(NumberUtils.INTEGER_ONE);
		simplesNacional.setPeriodo(Calendar.getInstance().getTime());
		return simplesNacional;
	}
	
}
