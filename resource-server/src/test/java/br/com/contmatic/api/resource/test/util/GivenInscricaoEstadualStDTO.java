package br.com.contmatic.api.resource.test.util;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.math.NumberUtils;

import br.com.contmatic.integration.dto.InscricaoEstadualStDto;
import br.com.contmatic.integration.dto.enumeration.UFDto;

public class GivenInscricaoEstadualStDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static Set<InscricaoEstadualStDto> getInscricoesEstadualSt() {
		Set<InscricaoEstadualStDto> inscricoesEstadualSt = new HashSet<InscricaoEstadualStDto>();
		inscricoesEstadualSt.add(getInscricaoEstadualSt());
		return inscricoesEstadualSt;
	}

	public static InscricaoEstadualStDto getInscricaoEstadualSt() {
		InscricaoEstadualStDto inscricaoEstadualSt = new InscricaoEstadualStDto();
		inscricaoEstadualSt.setEmpresa(GivenEmpresaDto.getNewEmpresa());
		inscricaoEstadualSt.setId(NumberUtils.LONG_ONE);
		inscricaoEstadualSt.setInscricaoEstadualST("487456.456");
		inscricaoEstadualSt.setInscricaoEstadualSubsUf(UFDto.SP);
		return inscricaoEstadualSt;
	}
	
}
