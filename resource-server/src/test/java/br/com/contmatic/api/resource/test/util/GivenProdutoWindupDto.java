package br.com.contmatic.api.resource.test.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;

import br.com.contmatic.integration.dto.ProdutoWindupDto;
import br.com.contmatic.integration.dto.enumeration.EnumProdutoLojaWindupDto;
import br.com.contmatic.integration.dto.enumeration.EnumSituacaoDto;
import br.com.contmatic.integration.dto.enumeration.EnumSituacaoProdutoWindupDto;

public class GivenProdutoWindupDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static List<ProdutoWindupDto> getProdutosWindup() {
		List<ProdutoWindupDto> produtosWindup = new ArrayList<ProdutoWindupDto>();
		produtosWindup.add(getProdutoWindup());
		return produtosWindup;
	}

	public static ProdutoWindupDto getProdutoWindup() {
		ProdutoWindupDto produtoWindup = new ProdutoWindupDto();
		Calendar calendar = Calendar.getInstance();
		produtoWindup.setConta(GivenContaDto.getNewConta());
		produtoWindup.setDataAlteracao(calendar.getTime());
		produtoWindup.setDataExpiracao(calendar.getTime());
		produtoWindup.setDataInclusao(calendar.getTime());
		produtoWindup.setDataInclusaoInicio(calendar.getTime());
		produtoWindup.setEnumProdutoLojaWindup(EnumProdutoLojaWindupDto.LOJA_PHOENIX);
		produtoWindup.setId(NumberUtils.LONG_ONE);
		produtoWindup.setSituacao(EnumSituacaoDto.ATIVO);
		produtoWindup.setSituacaoProduto(EnumSituacaoProdutoWindupDto.NORMAL);
		return produtoWindup;
	}

}
