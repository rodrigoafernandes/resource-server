package br.com.contmatic.api.resource.test.util;

import java.io.Serializable;
import java.util.Calendar;

import org.apache.commons.lang3.math.NumberUtils;

import br.com.contmatic.integration.dto.EmpresaDto;
import br.com.contmatic.integration.dto.enumeration.EnumAtualizacaoIbptDto;
import br.com.contmatic.integration.dto.enumeration.EnumEnquadradoDto;
import br.com.contmatic.integration.dto.enumeration.EnumEnquadramentoTributarioDto;
import br.com.contmatic.integration.dto.enumeration.EnumFechamentoBalancoDto;
import br.com.contmatic.integration.dto.enumeration.EnumProdutoLojaWindupDemonstracaoDto;
import br.com.contmatic.integration.dto.enumeration.EnumQuantidadeFuncionariosDto;
import br.com.contmatic.integration.dto.enumeration.EnumRamoAtividadeDto;
import br.com.contmatic.integration.dto.enumeration.EnumRegimeEspecialTributacaoDto;
import br.com.contmatic.integration.dto.enumeration.EnumRetencaoDto;
import br.com.contmatic.integration.dto.enumeration.EnumTipoAmbienteDto;
import br.com.contmatic.integration.dto.enumeration.EnumTipoCertificadoDto;
import br.com.contmatic.integration.dto.enumeration.EnumTipoEmpresaDto;
import br.com.contmatic.integration.dto.enumeration.EnumTipoPessoaDto;
import br.com.contmatic.integration.dto.enumeration.UFDto;

public class GivenEmpresaDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static EmpresaDto getEmpresa() {
		EmpresaDto empresa = new EmpresaDto();
		empresa.setAreaM2(NumberUtils.INTEGER_ONE);
		empresa.setAtualizacaoIbpt(EnumAtualizacaoIbptDto.CONCLUIDO);
		empresa.setAvaliacao(Boolean.TRUE);
		empresa.setCampoObrigatorioIntegracaoG5(Boolean.FALSE);
		empresa.setCcm("ccm");
		empresa.setCei(NumberUtils.LONG_ONE);
		empresa.setCertificado(GivenCertificadoDigitalDto.getCertificadoDigital());
		empresa.setChaveAutenticacao("Chave autenticação");
		empresa.setChaveTabelaIbpt("Chave tabela Ibpt");
		empresa.setClassificacao(NumberUtils.INTEGER_ONE);
		empresa.setCnae(NumberUtils.LONG_ONE);
		empresa.setCnaePreponderante("Cnae Preponderante");
		empresa.setCnpj("89.672.354/0001-96");
		empresa.setCodigo(NumberUtils.INTEGER_ONE);
		empresa.setConta(GivenContaDto.getConta());
		empresa.setContato("4578631");
		empresa.setCpf("138.433.140-98");
		Calendar calendar = Calendar.getInstance();
		empresa.setDataAlteracao(calendar);
		empresa.setDataCadastro(calendar.getTime());
		empresa.setDataEnquadramento(calendar.getTime());
		empresa.setDataInclusao(calendar.getTime());
		empresa.setDataUltimaSincronizacaoCteNav(calendar.getTime());
		empresa.setDataVigencia(calendar.getTime());
		empresa.setEmail("email@testmail.com.br");
		empresa.setEmailCliente("email.cliente@testmail.com.br");
		empresa.setEmpresaExibeTributosNf(Boolean.TRUE);
		empresa.setEndereco(GivenEndercoDTO.getEndereco());
		empresa.setEnderecoPrincipal(GivenEndercoDTO.getEndereco());
		empresa.setEnderecos(GivenEndercoDTO.getEnderecos());
		empresa.setEnquadramentoMP66(EnumEnquadradoDto.ENQUADRADO);
		empresa.setEnquadramentoTributario(EnumEnquadramentoTributarioDto.LUCRO_PRESUMIDO);
		empresa.setExportacaoDadosCompleta(Boolean.FALSE);
		empresa.setFax("0100-0006");
		empresa.setFechamentoBalanco(EnumFechamentoBalancoDto.MENSAL);
		empresa.setFlagApresentaModuloNaoContratado(Boolean.FALSE);
		empresa.setFlagAutoImplantacaoCompleta(Boolean.TRUE);
		empresa.setFlagCopiaNcm(Boolean.TRUE);
		empresa.setGrupoEmpresarial(GivenGrupoEmpresarialDTO.getGrupoEmpresarial());
		empresa.setId(NumberUtils.LONG_ONE);
		empresa.setIdToken("idToken");
		empresa.setIEstSubstitutoUF(UFDto.SP);
		empresa.setImagem(GivenImagemDTO.getImagem());
		empresa.setIncentivadorCultural(Boolean.TRUE);
		empresa.setInformacoesAdicionaisNfse("Informações adicionais NFSE");
		empresa.setInscricaoEstadual("755645564");
		empresa.setInscricaoEstadualST(GivenInscricaoEstadualStDTO.getInscricoesEstadualSt());
		empresa.setInscricaoMunicipal("47854");
		empresa.setIsento(Boolean.FALSE);
		empresa.setMunicipioMarketing(GivenMunicipioDto.getMunicipio());
		empresa.setNaturezaJuridica("Natureza Jurídica");
		empresa.setNomeDoRepresentante("Representante Teste");
		empresa.setNomeFantasia("Zuriq");
		empresa.setNumeroRegistro(NumberUtils.LONG_ONE);
		empresa.setPin("7554168");
		empresa.setPrincipal(Boolean.TRUE);
		empresa.setProduto(EnumProdutoLojaWindupDemonstracaoDto.LOJA_PHOENIX);
		empresa.setQuantidadeFuncionarios(EnumQuantidadeFuncionariosDto.DE_1_A_10);
		empresa.setRamoAtividade(EnumRamoAtividadeDto.ACADEMIA_ESPORTES);
		empresa.setRazaoSocial("Academia e Lutas Marciais ME");
		empresa.setRegimeEspecialTributacao(EnumRegimeEspecialTributacaoDto.MICROEMPRESA_MUNICIPAL);
		empresa.setRetencao(EnumRetencaoDto.EMISSAO);
		empresa.setSenha("password");
		empresa.setSenhaSigiss("password");
		empresa.setSimplesNacional(GivenSimplesNacional.getListSimplesNacional());
		empresa.setSincronizado(Boolean.TRUE);
		empresa.setSite("www.academia.com.br");
		empresa.setSolicitarSenha(Boolean.TRUE);
		empresa.setSolicitarSenhaBoleto(Boolean.FALSE);
		empresa.setSolicitarSenhaRemessa(Boolean.FALSE);
		empresa.setSped(EnumEnquadradoDto.ENQUADRADO);
		empresa.setSuframa("Suframa");
		empresa.setTelefone("0010-0005");
		empresa.setTemImagem(Boolean.TRUE);
		empresa.setTipoAmbienteCTe(EnumTipoAmbienteDto.PRODUCAO);
		empresa.setTipoAmbienteNFCe(EnumTipoAmbienteDto.PRODUCAO);
		empresa.setTipoAmbienteNFSe(EnumTipoAmbienteDto.PRODUCAO);
		empresa.setTipoCertificado(EnumTipoCertificadoDto.A3);
		empresa.setTipoEmpresa(EnumTipoEmpresaDto.SERVICO);
		empresa.setTipoPessoa(EnumTipoPessoaDto.JURIDICA);
		empresa.setToken("token");
		empresa.setTokenIbpt("token ibpt");
		empresa.setUtilizaCodigoNbs(Boolean.TRUE);
		return empresa;
	}
	
	public static EmpresaDto getNewEmpresa() {
		return new EmpresaDto();
	}
	
}
