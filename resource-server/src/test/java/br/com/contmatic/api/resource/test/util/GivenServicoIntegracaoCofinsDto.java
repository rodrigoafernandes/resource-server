package br.com.contmatic.api.resource.test.util;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang3.math.NumberUtils;

import br.com.contmatic.integration.dto.ServicoIntegracaoCofinsDto;
import br.com.contmatic.integration.dto.enumeration.EnumBaseCalculoCreditoPisCofinsDto;
import br.com.contmatic.integration.dto.enumeration.EnumContribuicaoSocialApuradaDto;
import br.com.contmatic.integration.dto.enumeration.EnumSituacaoTributariaCofinsEntradaDto;
import br.com.contmatic.integration.dto.enumeration.EnumSituacaoTributariaCofinsSaidaDto;
import br.com.contmatic.integration.dto.enumeration.EnumTipoDeCreditoDto;

public class GivenServicoIntegracaoCofinsDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static ServicoIntegracaoCofinsDto getIntegracaoCofins() {
		ServicoIntegracaoCofinsDto integracaoCofins = new ServicoIntegracaoCofinsDto();
		integracaoCofins.setAliquotaCofinsPrestado(BigDecimal.TEN);
		integracaoCofins.setAliquotaCofinsTomado(BigDecimal.TEN);
		integracaoCofins.setBaseCalculoCredito(EnumBaseCalculoCreditoPisCofinsDto.BASE_CALCULO_CREDITO_PIS_COFINS_01);
		integracaoCofins.setContribuicaoSocialApurada(EnumContribuicaoSocialApuradaDto.CONTRIBUICAO_SOCIAL_01);
		integracaoCofins.setId(NumberUtils.LONG_ONE);
		integracaoCofins.setNaturezaReceita(GivenNaturezaReceitaDto.getNaturezaReceita());
		integracaoCofins.setServico(GivenServicoDto.getNewServicoDTO());
		integracaoCofins.setSituacaoTributariaCofinsEntrada(EnumSituacaoTributariaCofinsEntradaDto.SITUACAO_COFINS_50);
		integracaoCofins.setSituacaoTributariaCofinsSaida(EnumSituacaoTributariaCofinsSaidaDto.SITUACAO_COFINS_01);
		integracaoCofins.setTipoDeCredito(EnumTipoDeCreditoDto.TIPO_CREDITO_101);
		return integracaoCofins;
	}

}
