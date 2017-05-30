package br.com.contmatic.api.resource.test.util;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang3.math.NumberUtils;

import br.com.contmatic.integration.dto.ServicoTributacaoDto;

public class GivenServicoTributacaoDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static ServicoTributacaoDto getTributação() {
		ServicoTributacaoDto tributacao = new ServicoTributacaoDto();
		tributacao.setId(NumberUtils.LONG_ONE);
		tributacao.setIss(BigDecimal.TEN);
		tributacao.setReducaoBaseCalculoIss(BigDecimal.TEN);
		tributacao.setServico(GivenServicoDto.getNewServicoDTO());
		return tributacao;
	}
	
}
