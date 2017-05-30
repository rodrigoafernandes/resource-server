package br.com.contmatic.api.resource.test.util;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.math.NumberUtils;

import br.com.contmatic.integration.dto.ServicoItemDto;

public class GivenServicoItemDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static Set<ServicoItemDto> getServicoItens() {
		HashSet<ServicoItemDto> itens = new HashSet<ServicoItemDto>();
		itens.add(getServicoItem());
		return itens;
	}

	public static ServicoItemDto getServicoItem() {
		ServicoItemDto item = new ServicoItemDto();
		item.setCodigo(NumberUtils.LONG_ONE);
		item.setDescricao("Descrição item");
		item.setId(NumberUtils.LONG_ONE);
		item.setServico(GivenServicoDto.getNewServicoDTO());
		item.setValorUnitario(BigDecimal.TEN);
		return item;
	}
	
}
