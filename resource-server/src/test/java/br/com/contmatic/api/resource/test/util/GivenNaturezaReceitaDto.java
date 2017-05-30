package br.com.contmatic.api.resource.test.util;

import java.io.Serializable;

import org.apache.commons.lang3.math.NumberUtils;

import br.com.contmatic.integration.dto.NaturezaReceitaDto;

public class GivenNaturezaReceitaDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static NaturezaReceitaDto getNaturezaReceita() {
		NaturezaReceitaDto naturezaReceita = new NaturezaReceitaDto();
		naturezaReceita.setCodigo("ED5487");
		naturezaReceita.setCodigoTabela("475825");
		naturezaReceita.setCst("CST");
		naturezaReceita.setDescricao("Descrição CST");
		naturezaReceita.setId(NumberUtils.LONG_ONE);
		naturezaReceita.setPrefixDialog("ABC");
		naturezaReceita.setPrefixSelect("FROM ABC");
		return naturezaReceita;
	}
	
}
