package br.com.contmatic.api.resource.test.util;

import java.io.Serializable;

import org.apache.commons.lang3.math.NumberUtils;

import br.com.contmatic.integration.dto.ContaDto;
import br.com.contmatic.integration.dto.enumeration.EnumContaSituacaoDto;

public class GivenContaDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static ContaDto getConta() {
		ContaDto conta = new ContaDto();
		conta.setCnpjPrincipal("89.672.354/0001-96");
		conta.setCodigoWindup(NumberUtils.INTEGER_ONE);
		conta.setEmpresa(GivenEmpresaDto.getNewEmpresa());
		conta.setId(NumberUtils.LONG_ONE);
		conta.setProdutosWindup(GivenProdutoWindupDto.getProdutosWindup());
		conta.setQuantidadeSessoes(NumberUtils.INTEGER_ONE);
		conta.setQuantidadeTerminais(NumberUtils.INTEGER_ONE);
		conta.setQuantidadeTerminaisNfceSat(NumberUtils.INTEGER_ONE);
		conta.setSituacao(EnumContaSituacaoDto.ATIVA);
		return conta;
	}

	public static ContaDto getNewConta() {
		return new ContaDto();
	}
	
}
