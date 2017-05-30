package br.com.contmatic.api.resource.test.util;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang3.math.NumberUtils;

import br.com.contmatic.integration.dto.ServicoRetencaoDto;

public class GivenServicoRetencaoDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static ServicoRetencaoDto getRetencao() {
		ServicoRetencaoDto retencao = new ServicoRetencaoDto();
		retencao.setId(NumberUtils.LONG_ONE);
		retencao.setCofins(BigDecimal.TEN);
		retencao.setCsll(BigDecimal.TEN);
		retencao.setInss(BigDecimal.TEN);
		retencao.setIrrf(BigDecimal.TEN);
		retencao.setPis(BigDecimal.TEN);
		retencao.setServico(GivenServicoDto.getNewServicoDTO());
		retencao.setValorMinimoRecolhimentoInss(BigDecimal.ONE);
		retencao.setValorMinimoRecolhimentoIrrf(BigDecimal.ONE);
		retencao.setValorMinimoRecolhimentoPcc(BigDecimal.ONE);
		return retencao;
	}
	
}
