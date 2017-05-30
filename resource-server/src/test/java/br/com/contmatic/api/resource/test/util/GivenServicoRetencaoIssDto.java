package br.com.contmatic.api.resource.test.util;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.math.NumberUtils;

import br.com.contmatic.integration.dto.ServicoRetencaoIssDto;
import br.com.contmatic.integration.dto.enumeration.UFDto;

public class GivenServicoRetencaoIssDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static Set<ServicoRetencaoIssDto> getRetencoesIss() {
		Set<ServicoRetencaoIssDto> retencoesIss = new HashSet<ServicoRetencaoIssDto>();
		retencoesIss.add(getRetencaoIss());
		return retencoesIss;
	}

	public static ServicoRetencaoIssDto getRetencaoIss() {
		ServicoRetencaoIssDto retencaoIss = new ServicoRetencaoIssDto();
		retencaoIss.setAliquotaRetencao(BigDecimal.ONE);
		retencaoIss.setId(NumberUtils.LONG_ONE);
		retencaoIss.setMunicipio(GivenMunicipioDto.getMunicipio());
		retencaoIss.setReducao(BigDecimal.ONE);
		retencaoIss.setServico(GivenServicoDto.getNewServicoDTO());
		retencaoIss.setUf(UFDto.SP);
		return retencaoIss;
	}
	
}
