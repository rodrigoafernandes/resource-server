package br.com.contmatic.api.resource.test.util;

import java.io.Serializable;

import org.apache.commons.lang3.math.NumberUtils;

import br.com.contmatic.integration.dto.ImagemDto;
import br.com.contmatic.integration.dto.enumeration.EnumSituacaoDto;

public class GivenImagemDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static ImagemDto getImagem() {
		ImagemDto imagem = new ImagemDto();
		imagem.setCodigo(NumberUtils.INTEGER_ONE);
		imagem.setDescricao("Imagem empresa");
		imagem.setExtensaoArquivo(".jpg");
		imagem.setId(NumberUtils.LONG_ONE);
		imagem.setImagemData(NumberUtils.LONG_ZERO.toString().getBytes());
		imagem.setNome("Foto_Empresa");
		imagem.setSituacao(EnumSituacaoDto.ATIVO);
		return imagem;
	}
	
}
