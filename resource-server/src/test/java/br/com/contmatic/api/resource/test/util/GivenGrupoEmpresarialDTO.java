package br.com.contmatic.api.resource.test.util;

import java.io.Serializable;

import org.apache.commons.lang3.math.NumberUtils;

import br.com.contmatic.integration.dto.GrupoEmpresarialDto;

public class GivenGrupoEmpresarialDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static GrupoEmpresarialDto getGrupoEmpresarial() {
		GrupoEmpresarialDto grupoEmpresarial = new GrupoEmpresarialDto();
		grupoEmpresarial.setCodigo(NumberUtils.INTEGER_ONE);
		grupoEmpresarial.setDescricao("Grupo Empresarial 1");
		grupoEmpresarial.setId(NumberUtils.LONG_ONE);
		grupoEmpresarial.setMascaraContaGerencial("##.######-#");
		return grupoEmpresarial;
	}
	
}
