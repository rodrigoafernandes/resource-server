package br.com.contmatic.api.resource.test.util;

import java.io.Serializable;

import org.apache.commons.lang3.math.NumberUtils;

import br.com.contmatic.integration.dto.ServicoIntegracaoEscrituracaoDto;
import br.com.contmatic.integration.dto.enumeration.EnumAnexoDto;
import br.com.contmatic.integration.dto.enumeration.EnumDarfDto;
import br.com.contmatic.integration.dto.enumeration.EnumLucroPresumidoDto;

public class GivenServicoIntegracaoEscrituracaoDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static ServicoIntegracaoEscrituracaoDto getIntegracaoEscrituracao() {
		ServicoIntegracaoEscrituracaoDto integracaoEscrituracao = new ServicoIntegracaoEscrituracaoDto();
		integracaoEscrituracao.setAnexo(EnumAnexoDto.ANEXO_III);
		integracaoEscrituracao.setDarf(EnumDarfDto.DARF_0588);
		integracaoEscrituracao.setId(NumberUtils.LONG_ONE);
		integracaoEscrituracao.setLocacao(Boolean.TRUE);
		integracaoEscrituracao.setLucroPresumido(EnumLucroPresumidoDto.LUCRO_PRESUMIDO_8);
		integracaoEscrituracao.setServico(GivenServicoDto.getNewServicoDTO());
		integracaoEscrituracao.setServicosContabeis(Boolean.TRUE);
		return integracaoEscrituracao;
	}
	
}
