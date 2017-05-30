package br.com.contmatic.api.resource.test.util;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;

import br.com.contmatic.integration.dto.ServicoDto;
import br.com.contmatic.integration.dto.enumeration.EnumLcServicosDto;
import br.com.contmatic.integration.dto.enumeration.EnumLeiNbsDto;
import br.com.contmatic.integration.dto.enumeration.EnumNaturezaOperacaoDto;
import br.com.contmatic.integration.dto.enumeration.EnumSituacaoDto;

public class GivenServicoDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8522458780645457806L;

	public static ServicoDto getServicoDto(long id) {
		ServicoDto dto = new ServicoDto();
		dto.setId(id);
		dto.setCodigo("1234....///....");
		dto.setSituacao(EnumSituacaoDto.ATIVO);
		dto.setPrestado(Boolean.TRUE);
		dto.setTomado(Boolean.TRUE);
		dto.setCnae(NumberUtils.INTEGER_ONE);
		dto.setDescricao("opa");
		dto.setNaturezaOperacao(EnumNaturezaOperacaoDto.TRIBUTADO);
		dto.setCodigoServicoMunicipio("1234556666445");
		dto.setDescricaoServicoMunicipio("descricaoServicoMunicipio");
		dto.setValorUnitario(BigDecimal.TEN);
		dto.setLcServico(EnumLcServicosDto.LC_SERVICO_1001);
		dto.setLeiNbs(EnumLeiNbsDto.NBS_101011000);
		dto.setAliquotaFederal(BigDecimal.TEN);
		dto.setAliquotaImportada(BigDecimal.TEN);
		dto.setAliquotaMunicipal(BigDecimal.TEN);
		dto.setTributacao(GivenServicoTributacaoDto.getTributação());
		dto.setRetencao(GivenServicoRetencaoDto.getRetencao());
		dto.setIntegracaoPis(GivenServicoIntegracaoPisDto.getIntegracaoPis());
		dto.setIntegracaoCofins(GivenServicoIntegracaoCofinsDto.getIntegracaoCofins());
		dto.setIntegracaoEscrituracao(GivenServicoIntegracaoEscrituracaoDto.getIntegracaoEscrituracao());
		dto.setRetencoesIss(GivenServicoRetencaoIssDto.getRetencoesIss());
		dto.setItensServico(GivenServicoItemDto.getServicoItens());
		dto.setEmpresa(GivenEmpresaDto.getEmpresa());
		dto.setRecibo(Boolean.FALSE);
		return dto;
	}

	public static ServicoDto getNewServicoDTO(){
		return new ServicoDto();
	}

	public static List<ServicoDto> getServicosDto() {
		List<ServicoDto> servicos = new ArrayList<ServicoDto>();
		servicos.add(getServicoDto(NumberUtils.LONG_ONE));
		servicos.add(getServicoDto(NumberUtils.createLong("2")));
		servicos.add(getServicoDto(NumberUtils.createLong("3")));
		return servicos;
	}
	
}