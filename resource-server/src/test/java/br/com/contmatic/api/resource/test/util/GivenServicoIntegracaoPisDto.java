package br.com.contmatic.api.resource.test.util;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang3.math.NumberUtils;

import br.com.contmatic.integration.dto.ServicoIntegracaoPisDto;
import br.com.contmatic.integration.dto.enumeration.EnumBaseCalculoCreditoPisCofinsDto;
import br.com.contmatic.integration.dto.enumeration.EnumContribuicaoSocialApuradaDto;
import br.com.contmatic.integration.dto.enumeration.EnumSituacaoTributariaPisEntradaDto;
import br.com.contmatic.integration.dto.enumeration.EnumSituacaoTributariaPisSaidaDto;
import br.com.contmatic.integration.dto.enumeration.EnumTipoDeCreditoDto;

public class GivenServicoIntegracaoPisDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static ServicoIntegracaoPisDto getIntegracaoPis() {
		ServicoIntegracaoPisDto integracaoPis = new ServicoIntegracaoPisDto();
		integracaoPis.setAliquotaPisPrestado(BigDecimal.TEN);
		integracaoPis.setAliquotaPisTomado(BigDecimal.TEN);
		integracaoPis.setBaseCalculoCredito(EnumBaseCalculoCreditoPisCofinsDto.BASE_CALCULO_CREDITO_PIS_COFINS_01);
		integracaoPis.setContribuicaoSocialApurada(EnumContribuicaoSocialApuradaDto.CONTRIBUICAO_SOCIAL_01);
		integracaoPis.setId(NumberUtils.LONG_ONE);
		integracaoPis.setNaturezaReceita(GivenNaturezaReceitaDto.getNaturezaReceita());
		integracaoPis.setServico(GivenServicoDto.getNewServicoDTO());
		integracaoPis.setSituacaoTributariaPisEntrada(EnumSituacaoTributariaPisEntradaDto.SITUACAO_PIS_50);
		integracaoPis.setSituacaoTributariaPisSaida(EnumSituacaoTributariaPisSaidaDto.SITUACAO_PIS_01);
		integracaoPis.setTipoDeCredito(EnumTipoDeCreditoDto.TIPO_CREDITO_101);
		return integracaoPis;
	}
	
}
