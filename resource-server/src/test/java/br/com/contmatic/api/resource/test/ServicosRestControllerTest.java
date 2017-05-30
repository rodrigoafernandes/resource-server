package br.com.contmatic.api.resource.test;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.subsectionWithPath;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.math.NumberUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.constraints.ConstraintDescriptions;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import br.com.contmatic.api.resource.config.DefaultTestConfiguration;
import br.com.contmatic.api.resource.test.util.GivenServicoDto;
import br.com.contmatic.integration.dto.ServicoDto;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest
@AutoConfigureJsonTesters
@RunWith(SpringJUnit4ClassRunner.class)
@Import(DefaultTestConfiguration.class)
@SuppressWarnings("deprecation")
public class ServicosRestControllerTest {

	@Autowired
	private WebApplicationContext wac;

	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	private ResourceServerTokenServices resourceServerTokenServices;

	@MockBean
	private RestTemplate restTemplate;
	
	@Rule
	public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets");

	private MockMvc mockMvc;

	private RestDocumentationResultHandler document;
	
	@Before
	public void setup() {
		document = document("{method-name}/", preprocessRequest(prettyPrint()),
				preprocessResponse(prettyPrint()));
		mockMvc = MockMvcBuilders.webAppContextSetup(wac)
					.apply(documentationConfiguration(this.restDocumentation)
							.uris().withScheme("http")
									.withHost("apicontmatic.com.br"))
				.alwaysDo(this.document)
				.build();
		when(this.resourceServerTokenServices.loadAuthentication(anyString()))
				.thenAnswer(invocation -> SecurityContextHolder.getContext().getAuthentication());
	}
	
	@Test
	public void findById() throws Exception{
		long id = 1;
		Map<String, Object> params = new HashMap<>();
		params.put("id", id);
		
		documentResponseServicoDto();
		documentIdPathParameters();
		
		when(this.restTemplate
				.getForObject("http://localhost:9191/faturamento/servico/" + id, ServicoDto.class, params))
		.thenReturn(GivenServicoDto.getServicoDto(id));
		
		this.mockMvc.perform(
				RestDocumentationRequestBuilders.get("/servico/{id}", id)
				.header("Authorization", "Bearer <YOUR-TOKEN-HERE>"))
		.andDo(print())
		.andExpect(status().isOk());
	}

	@Test
	public void findByCodigoAndEmpresa() throws Exception{
		Map<String, Object> params = new HashMap<>();
		
		params.put("codigoServico", "123");
		params.put("idEmpresa", NumberUtils.LONG_ONE);
		
		documentRequestParametersForFindByCodigoAndEmpresa();
		documentResponseServicoDto();
		
		when(this.restTemplate.postForObject(Mockito.anyString(), Mockito.any(), Mockito.anyObject()))
		.thenReturn(GivenServicoDto.getServicoDto(NumberUtils.LONG_ONE));
		
		this.mockMvc.perform(
				RestDocumentationRequestBuilders.post("/servico/findByCodigoAndEmpresa")
				.param("codigoServico", "123").param("idEmpresa", NumberUtils.LONG_ONE.toString())
				.header("Authorization", "Bearer <YOUR-TOKEN-HERE>"))
		.andDo(print())
		.andExpect(status().isOk());
	}

	@Test
	public void findGrid() throws Exception{
		ServicoDto request = GivenServicoDto.getServicoDto(NumberUtils.createLong("75"));
		
		documentRequestFields();
		documentListResponseServicoDto();
		
		when(this.restTemplate.postForObject(Mockito.anyString(), Mockito.any(), Mockito.anyObject()))
		.thenReturn(GivenServicoDto.getServicosDto());
		
		this.mockMvc.perform(
				RestDocumentationRequestBuilders.post("/servico/grid")
				.contentType(MediaType.APPLICATION_JSON)
				.content(this.objectMapper.writeValueAsString(request)))
		.andDo(print())
		.andExpect(status().isOk());
		
	}
	
	@Test
	public void save() throws Exception{
		ServicoDto request = GivenServicoDto.getServicoDto(NumberUtils.createLong("76"));
		
		documentRequestFields();
		documentResponseServicoDto();
		
		when(this.restTemplate.postForObject(Mockito.anyString(), Mockito.any(), Mockito.anyObject()))
		.thenReturn(GivenServicoDto.getServicoDto(NumberUtils.createLong("76")));
		
		this.mockMvc.perform(
				RestDocumentationRequestBuilders.post("/servico/save")
				.contentType(MediaType.APPLICATION_JSON)
				.content(this.objectMapper.writeValueAsString(request)))
		.andDo(print())
		.andExpect(status().isCreated());
		
	}
	
	@Test
	public void delete() throws Exception{
		long id = 1;
		
		documentIdPathParameters();
		
		doNothing().when(this.restTemplate).delete("http://localhost:9191/faturamento/" + id);
		
		this.mockMvc.perform(
				RestDocumentationRequestBuilders.delete("/servico/{id}", id))
		.andDo(print())
		.andExpect(status().isOk());
		
	}
	
	private static class ConstrainedFields {

        private final ConstraintDescriptions constraintDescriptions;

        ConstrainedFields(Class<?> input) {
            this.constraintDescriptions = new ConstraintDescriptions(input);
        }

        private FieldDescriptor withPath(String path) {
            return fieldWithPath(path).attributes(key("constraints").value(StringUtils
                    .collectionToDelimitedString(this.constraintDescriptions
                            .descriptionsForProperty(path), ". ")));
        }
    }
	
	private void documentRequestFields(){
		ConstrainedFields fields = new ConstrainedFields(ServicoDto.class);
		this.document.snippets(
				  requestFields(fields.withPath("id").description("Identificador do serviço"),
								fields.withPath("codigo").description("Código de controle"),
								fields.withPath("situacao").description("Situação do serviço"),
								fields.withPath("prestado").description("Prestado"),
								fields.withPath("tomado").description("Tomado"),
								fields.withPath("descricao").description("Descrição do serviço"),
								fields.withPath("cnae").description("CNAE do serviço"),
								fields.withPath("naturezaOperacao").description("Natureza Operação"),
								fields.withPath("codigoServicoMunicipio").description("Código do serviço no Município"),
								fields.withPath("descricaoServicoMunicipio").description("Descrição do serviço no Município"),
								fields.withPath("valorUnitario").description("Valor do serviço"),
								fields.withPath("lcServico").description("Lc Serviços"),
								fields.withPath("leiNbs").description("Lei NBS"),
								fields.withPath("aliquotaFederal").description("Valor da Aliquota Federal do serviço"),
								fields.withPath("aliquotaImportada").description("Valor da aliquota importada do serviço"),
								fields.withPath("aliquotaMunicipal").description("Valor da aliquota municipal do serviço"),
								fields.withPath("tributacao").description("Detalhes da tributação"),
								fields.withPath("tributacao.id").description("Id da tributação"),
								fields.withPath("tributacao.iss").description("Iss da tributação"),
								fields.withPath("tributacao.reducaoBaseCalculoIss").description("Iss da tributação"),
								subsectionWithPath("tributacao.servico").description("Referencia ao Parent"),
								fields.withPath("retencao").description("Detalhes da retenção"),
								fields.withPath("retencao.id").description("Id da retenção"),
								fields.withPath("retencao.cofins").description("Cofins da retenção"),
								fields.withPath("retencao.csll").description("Csll da retenção"),
								fields.withPath("retencao.inss").description("Inss da retenção"),
								fields.withPath("retencao.irrf").description("Irrf da retenção"),
								fields.withPath("retencao.pis").description("Pis da retenção"),
								fields.withPath("retencao.valorMinimoRecolhimentoInss").description("Valor Minimo Recolhimento Inss da retenção"),
								fields.withPath("retencao.valorMinimoRecolhimentoPcc").description("Valor Minimo Recolhimento Pcc da retenção"),
								fields.withPath("retencao.valorMinimoRecolhimentoIrrf").description("Valor Minimo Recolhimento Irrf da retenção"),
								subsectionWithPath("retencao.servico").description("Referencia ao Parent"),
								fields.withPath("integracaoPis").description("Detalhes da Integração PIS"),
								fields.withPath("integracaoPis.id").description("Id da Integração PIS"),
								fields.withPath("integracaoPis.situacaoTributariaPisEntrada").description("Situacão Tributaria Pis Entrada"),
								fields.withPath("integracaoPis.aliquotaPisTomado").description("Aliquota Pis Tomado da Integração PIS"),
								fields.withPath("integracaoPis.tipoDeCredito").description("Tipo de Crédito"),
								fields.withPath("integracaoPis.baseCalculoCredito").description("Base Calculo Credito da Integração PIS"),
								fields.withPath("integracaoPis.situacaoTributariaPisSaida").description("Situação tributária PIS saída"),
								fields.withPath("integracaoPis.aliquotaPisPrestado").description("Aliquota Pis Prestado da Integração PIS"),
								fields.withPath("integracaoPis.contribuicaoSocialApurada").description("Contribuição Social Apurada da Integração PIS"),
								fields.withPath("integracaoPis.naturezaReceita").description("Natureza Receita da Integração PIS"),
								fields.withPath("integracaoPis.naturezaReceita.id").description("Id da Natureza Receita"),
								fields.withPath("integracaoPis.naturezaReceita.codigo").description("Código da Natureza Receita"),
								fields.withPath("integracaoPis.naturezaReceita.cst").description("Cst da Natureza Receita"),
								fields.withPath("integracaoPis.naturezaReceita.descricao").description("Descrição da Natureza Receita"),
								fields.withPath("integracaoPis.naturezaReceita.codigoTabela").description("Código tabela da Natureza Receita"),
								fields.withPath("integracaoPis.naturezaReceita.prefixDialog").description("PrefixDialog da Natureza Receita"),
								fields.withPath("integracaoPis.naturezaReceita.prefixSelect").description("PrefixSelect da Natureza Receita"),
								subsectionWithPath("integracaoPis.servico").description("Referencia ao Parent"),
								fields.withPath("integracaoCofins").description("Detalhes da integração COFINS"),
								fields.withPath("integracaoCofins.id").description("Id da integração COFINS"),
								fields.withPath("integracaoCofins.situacaoTributariaCofinsEntrada").description("Situação Tributária Cofins"),
								fields.withPath("integracaoCofins.aliquotaCofinsTomado").description("Valor aliquota Cofins Tomado"),
								fields.withPath("integracaoCofins.tipoDeCredito").description("Tipo de crédito"),
								fields.withPath("integracaoCofins.baseCalculoCredito").description("Base Calculo Crédito"),
								fields.withPath("integracaoCofins.situacaoTributariaCofinsSaida").description("Situação Tributária Cofins Saída"),
								fields.withPath("integracaoCofins.aliquotaCofinsPrestado").description("Aliquota Cofins Prestado"),
								fields.withPath("integracaoCofins.aliquotaCofinsPrestado").description("Valor aliquota Cofins Prestado"),
								fields.withPath("integracaoCofins.contribuicaoSocialApurada").description("Contribuição Social Apurada"),
								fields.withPath("integracaoCofins.naturezaReceita").description("Natureza Receita"),
								fields.withPath("integracaoCofins.naturezaReceita.id").description("Id da Natureza Receita"),
								fields.withPath("integracaoCofins.naturezaReceita.cst").description("CST"),
								fields.withPath("integracaoCofins.naturezaReceita.codigoTabela").description("Código tabela"),
								fields.withPath("integracaoCofins.naturezaReceita.prefixDialog").description("Prefix Dialog"),
								fields.withPath("integracaoCofins.naturezaReceita.prefixSelect").description("Prefix Select"),
								fields.withPath("integracaoCofins.naturezaReceita.codigo").description("Código"),
								fields.withPath("integracaoCofins.naturezaReceita.descricao").description("Descrição"),
								subsectionWithPath("integracaoCofins.servico").description("Referencia ao Parent"),
								fields.withPath("integracaoEscrituracao").description("Detalhes da integração Escrituração"),
								fields.withPath("integracaoEscrituracao.id").description("ID da integração Escrituração"),
								fields.withPath("integracaoEscrituracao.id").description("ID da integração Escrituração"),
								fields.withPath("integracaoEscrituracao.lucroPresumido").description("Lucro Presumido"),
								fields.withPath("integracaoEscrituracao.darf").description("DARF"),
								fields.withPath("integracaoEscrituracao.anexo").description("Anexo"),
								subsectionWithPath("integracaoEscrituracao.servico").description("Referencia ao Parent"),
								fields.withPath("integracaoEscrituracao.locacao").description("Locação"),
								fields.withPath("integracaoEscrituracao.servicosContabeis").description("Serviços Contábeis"),
								fields.withPath("retencoesIss").description("Lista de Retenções ISS do serviço"),
								fields.withPath("retencoesIss[].id").description("Id da Retenções ISS"),
								fields.withPath("retencoesIss[].aliquotaRetencao").description("Aliquota da Retenções ISS"),
								fields.withPath("retencoesIss[].uf").description("UF da Retenções ISS"),
								fields.withPath("retencoesIss[].reducao").description("Valor redução"),
								fields.withPath("retencoesIss[].municipio").description("Municipio da Retenções ISS"),
								fields.withPath("retencoesIss[].municipio.id").description("Id do municipio"),
								fields.withPath("retencoesIss[].municipio.amazoniaOcidental").description("Indicador de Amazonia Ocidental"),
								fields.withPath("retencoesIss[].municipio.areaLivreComercio").description("Indicador de area Livre Comercio do municipio"),
								fields.withPath("retencoesIss[].municipio.codigoFederal").description("Código Federal"),
								fields.withPath("retencoesIss[].municipio.codigoMunicipal").description("Código Municipal"),
								fields.withPath("retencoesIss[].municipio.codigoIBGE").description("Código IGBE"),
								fields.withPath("retencoesIss[].municipio.descricao").description("Descrição do município"),
								fields.withPath("retencoesIss[].municipio.uf").description("UF do município"),
								fields.withPath("retencoesIss[].municipio.zonaFrancaManaus").description("Indicador de zona Franca Manaus"),
								subsectionWithPath("retencoesIss[].servico").description("Refrerencia ao Parent"),
								fields.withPath("itensServico").description("Lista de Itens do serviço"),
								fields.withPath("itensServico[].id").description("Id do Item do serviço"),
								fields.withPath("itensServico[].codigo").description("Código do Item do serviço"),
								fields.withPath("itensServico[].descricao").description("Descrição do Item do serviço"),
								fields.withPath("itensServico[].valorUnitario").description("Valor unitário do Item do serviço"),
								subsectionWithPath("itensServico[].servico").description("Referencia ao Parent"),
								subsectionWithPath("empresa").description("Detalhes da Empresa"),
								fields.withPath("recibo").description("Indicador do recibo do serviço")));
	}
	
	private void documentListResponseServicoDto(){
		this.document.snippets(
				responseFields(fieldWithPath("[].id").description("Identificador do serviço"),
							   fieldWithPath("[].codigo").description("Código de controle"),
							   fieldWithPath("[].situacao").description("Situação do serviço"),
							   fieldWithPath("[].prestado").description("Prestado"),
							   fieldWithPath("[].tomado").description("Tomado"),
							   fieldWithPath("[].descricao").description("Descrição do serviço"),
							   fieldWithPath("[].cnae").description("CNAE do serviço"),
							   fieldWithPath("[].naturezaOperacao").description("Natureza Operação"),
							   fieldWithPath("[].codigoServicoMunicipio").description("Código do serviço no Município"),
							   fieldWithPath("[].descricaoServicoMunicipio").description("Descrição do serviço no Município"),
							   fieldWithPath("[].valorUnitario").description("Valor do serviço"),
							   fieldWithPath("[].lcServico").description("Lc Serviços"),
							   fieldWithPath("[].leiNbs").description("Lei NBS"),
							   fieldWithPath("[].aliquotaFederal").description("Valor da Aliquota Federal do serviço"),
							   fieldWithPath("[].aliquotaImportada").description("Valor da aliquota importada do serviço"),
							   fieldWithPath("[].aliquotaMunicipal").description("Valor da aliquota municipal do serviço"),
							   fieldWithPath("[].tributacao").description("Detalhes da tributação"),
							   fieldWithPath("[].tributacao.id").description("Id da tributação"),
							   fieldWithPath("[].tributacao.iss").description("Iss da tributação"),
							   fieldWithPath("[].tributacao.reducaoBaseCalculoIss").description("Iss da tributação"),
							   subsectionWithPath("[].tributacao.servico").description("Referencia ao Parent"),
							   fieldWithPath("[].retencao").description("Detalhes da retenção"),
							   fieldWithPath("[].retencao.id").description("Id da retenção"),
							   fieldWithPath("[].retencao.cofins").description("Cofins da retenção"),
							   fieldWithPath("[].retencao.csll").description("Csll da retenção"),
							   fieldWithPath("[].retencao.inss").description("Inss da retenção"),
							   fieldWithPath("[].retencao.irrf").description("Irrf da retenção"),
							   fieldWithPath("[].retencao.pis").description("Pis da retenção"),
							   fieldWithPath("[].retencao.valorMinimoRecolhimentoInss").description("Valor Minimo Recolhimento Inss da retenção"),
							   fieldWithPath("[].retencao.valorMinimoRecolhimentoPcc").description("Valor Minimo Recolhimento Pcc da retenção"),
							   fieldWithPath("[].retencao.valorMinimoRecolhimentoIrrf").description("Valor Minimo Recolhimento Irrf da retenção"),
							   subsectionWithPath("[].retencao.servico").description("Referencia ao Parent"),
							   fieldWithPath("[].integracaoPis").description("Detalhes da Integração PIS"),
							   fieldWithPath("[].integracaoPis.id").description("Id da Integração PIS"),
							   fieldWithPath("[].integracaoPis.situacaoTributariaPisEntrada").description("Situacão Tributaria Pis Entrada"),
							   fieldWithPath("[].integracaoPis.aliquotaPisTomado").description("Aliquota Pis Tomado da Integração PIS"),
							   fieldWithPath("[].integracaoPis.tipoDeCredito").description("Tipo de Crédito"),
							   fieldWithPath("[].integracaoPis.baseCalculoCredito").description("Base Calculo Credito da Integração PIS"),
							   fieldWithPath("[].integracaoPis.situacaoTributariaPisSaida").description("Situação tributária PIS saída"),
							   fieldWithPath("[].integracaoPis.aliquotaPisPrestado").description("Aliquota Pis Prestado da Integração PIS"),
							   fieldWithPath("[].integracaoPis.contribuicaoSocialApurada").description("Contribuição Social Apurada da Integração PIS"),
							   fieldWithPath("[].integracaoPis.naturezaReceita").description("Natureza Receita da Integração PIS"),
							   fieldWithPath("[].integracaoPis.naturezaReceita.id").description("Id da Natureza Receita"),
							   fieldWithPath("[].integracaoPis.naturezaReceita.codigo").description("Código da Natureza Receita"),
							   fieldWithPath("[].integracaoPis.naturezaReceita.cst").description("Cst da Natureza Receita"),
							   fieldWithPath("[].integracaoPis.naturezaReceita.descricao").description("Descrição da Natureza Receita"),
							   fieldWithPath("[].integracaoPis.naturezaReceita.codigoTabela").description("Código tabela da Natureza Receita"),
							   fieldWithPath("[].integracaoPis.naturezaReceita.prefixDialog").description("PrefixDialog da Natureza Receita"),
							   fieldWithPath("[].integracaoPis.naturezaReceita.prefixSelect").description("PrefixSelect da Natureza Receita"),
							   subsectionWithPath("[].integracaoPis.servico").description("Referencia ao Parent"),
							   fieldWithPath("[].integracaoCofins").description("Detalhes da integração COFINS"),
							   fieldWithPath("[].integracaoCofins.id").description("Id da integração COFINS"),
							   fieldWithPath("[].integracaoCofins.situacaoTributariaCofinsEntrada").description("Situação Tributária Cofins"),
							   fieldWithPath("[].integracaoCofins.aliquotaCofinsTomado").description("Valor aliquota Cofins Tomado"),
							   fieldWithPath("[].integracaoCofins.tipoDeCredito").description("Tipo de crédito"),
							   fieldWithPath("[].integracaoCofins.baseCalculoCredito").description("Base Calculo Crédito"),
							   fieldWithPath("[].integracaoCofins.situacaoTributariaCofinsSaida").description("Situação Tributária Cofins Saída"),
							   fieldWithPath("[].integracaoCofins.aliquotaCofinsPrestado").description("Aliquota Cofins Prestado"),
							   fieldWithPath("[].integracaoCofins.aliquotaCofinsPrestado").description("Valor aliquota Cofins Prestado"),
							   fieldWithPath("[].integracaoCofins.contribuicaoSocialApurada").description("Contribuição Social Apurada"),
							   fieldWithPath("[].integracaoCofins.naturezaReceita").description("Natureza Receita"),
							   fieldWithPath("[].integracaoCofins.naturezaReceita.id").description("Id da Natureza Receita"),
							   fieldWithPath("[].integracaoCofins.naturezaReceita.cst").description("CST"),
							   fieldWithPath("[].integracaoCofins.naturezaReceita.codigoTabela").description("Código tabela"),
							   fieldWithPath("[].integracaoCofins.naturezaReceita.prefixDialog").description("Prefix Dialog"),
							   fieldWithPath("[].integracaoCofins.naturezaReceita.prefixSelect").description("Prefix Select"),
							   fieldWithPath("[].integracaoCofins.naturezaReceita.codigo").description("Código"),
							   fieldWithPath("[].integracaoCofins.naturezaReceita.descricao").description("Descrição"),
							   subsectionWithPath("[].integracaoCofins.servico").description("Referencia ao Parent"),
							   fieldWithPath("[].integracaoEscrituracao").description("Detalhes da integração Escrituração"),
							   fieldWithPath("[].integracaoEscrituracao.id").description("ID da integração Escrituração"),
							   fieldWithPath("[].integracaoEscrituracao.id").description("ID da integração Escrituração"),
							   fieldWithPath("[].integracaoEscrituracao.lucroPresumido").description("Lucro Presumido"),
							   fieldWithPath("[].integracaoEscrituracao.darf").description("DARF"),
							   fieldWithPath("[].integracaoEscrituracao.anexo").description("Anexo"),
							   subsectionWithPath("[].integracaoEscrituracao.servico").description("Referencia ao Parent"),
							   fieldWithPath("[].integracaoEscrituracao.locacao").description("Locação"),
							   fieldWithPath("[].integracaoEscrituracao.servicosContabeis").description("Serviços Contábeis"),
							   fieldWithPath("[].retencoesIss").description("Lista de Retenções ISS do serviço"),
							   fieldWithPath("[].retencoesIss[].id").description("Id da Retenções ISS"),
							   fieldWithPath("[].retencoesIss[].aliquotaRetencao").description("Aliquota da Retenções ISS"),
							   fieldWithPath("[].retencoesIss[].uf").description("UF da Retenções ISS"),
							   fieldWithPath("[].retencoesIss[].reducao").description("Valor redução"),
							   fieldWithPath("[].retencoesIss[].municipio").description("Municipio da Retenções ISS"),
							   fieldWithPath("[].retencoesIss[].municipio.id").description("Id do municipio"),
							   fieldWithPath("[].retencoesIss[].municipio.amazoniaOcidental").description("Indicador de Amazonia Ocidental"),
							   fieldWithPath("[].retencoesIss[].municipio.areaLivreComercio").description("Indicador de area Livre Comercio do municipio"),
							   fieldWithPath("[].retencoesIss[].municipio.codigoFederal").description("Código Federal"),
							   fieldWithPath("[].retencoesIss[].municipio.codigoMunicipal").description("Código Municipal"),
							   fieldWithPath("[].retencoesIss[].municipio.codigoIBGE").description("Código IGBE"),
							   fieldWithPath("[].retencoesIss[].municipio.descricao").description("Descrição do município"),
							   fieldWithPath("[].retencoesIss[].municipio.uf").description("UF do município"),
							   fieldWithPath("[].retencoesIss[].municipio.zonaFrancaManaus").description("Indicador de zona Franca Manaus"),
							   subsectionWithPath("[].retencoesIss[].servico").description("Refrerencia ao Parent"),
							   fieldWithPath("[].itensServico").description("Lista de Itens do serviço"),
							   fieldWithPath("[].itensServico[].id").description("Id do Item do serviço"),
							   fieldWithPath("[].itensServico[].codigo").description("Código do Item do serviço"),
							   fieldWithPath("[].itensServico[].descricao").description("Descrição do Item do serviço"),
							   fieldWithPath("[].itensServico[].valorUnitario").description("Valor unitário do Item do serviço"),
							   subsectionWithPath("[].itensServico[].servico").description("Referencia ao Parent"),
							   subsectionWithPath("[].empresa").description("Detalhes da Empresa"),
							   fieldWithPath("[].recibo").description("Indicador do recibo do serviço")));
	}
	
	private void documentResponseServicoDto(){
		this.document.snippets(
				responseFields(fieldWithPath("id").description("Identificador do serviço"),
							   fieldWithPath("codigo").description("Código de controle"),
							   fieldWithPath("situacao").description("Situação do serviço"),
							   fieldWithPath("prestado").description("Prestado"),
							   fieldWithPath("tomado").description("Tomado"),
							   fieldWithPath("descricao").description("Descrição do serviço"),
							   fieldWithPath("cnae").description("CNAE do serviço"),
							   fieldWithPath("naturezaOperacao").description("Natureza Operação"),
							   fieldWithPath("codigoServicoMunicipio").description("Código do serviço no Município"),
							   fieldWithPath("descricaoServicoMunicipio").description("Descrição do serviço no Município"),
							   fieldWithPath("valorUnitario").description("Valor do serviço"),
							   fieldWithPath("lcServico").description("Lc Serviços"),
							   fieldWithPath("leiNbs").description("Lei NBS"),
							   fieldWithPath("aliquotaFederal").description("Valor da Aliquota Federal do serviço"),
							   fieldWithPath("aliquotaImportada").description("Valor da aliquota importada do serviço"),
							   fieldWithPath("aliquotaMunicipal").description("Valor da aliquota municipal do serviço"),
							   fieldWithPath("tributacao").description("Detalhes da tributação"),
							   fieldWithPath("tributacao.id").description("Id da tributação"),
							   fieldWithPath("tributacao.iss").description("Iss da tributação"),
							   fieldWithPath("tributacao.reducaoBaseCalculoIss").description("Iss da tributação"),
							   subsectionWithPath("tributacao.servico").description("Referencia ao Parent"),
							   fieldWithPath("retencao").description("Detalhes da retenção"),
							   fieldWithPath("retencao.id").description("Id da retenção"),
							   fieldWithPath("retencao.cofins").description("Cofins da retenção"),
							   fieldWithPath("retencao.csll").description("Csll da retenção"),
							   fieldWithPath("retencao.inss").description("Inss da retenção"),
							   fieldWithPath("retencao.irrf").description("Irrf da retenção"),
							   fieldWithPath("retencao.pis").description("Pis da retenção"),
							   fieldWithPath("retencao.valorMinimoRecolhimentoInss").description("Valor Minimo Recolhimento Inss da retenção"),
							   fieldWithPath("retencao.valorMinimoRecolhimentoPcc").description("Valor Minimo Recolhimento Pcc da retenção"),
							   fieldWithPath("retencao.valorMinimoRecolhimentoIrrf").description("Valor Minimo Recolhimento Irrf da retenção"),
							   subsectionWithPath("retencao.servico").description("Referencia ao Parent"),
							   fieldWithPath("integracaoPis").description("Detalhes da Integração PIS"),
							   fieldWithPath("integracaoPis.id").description("Id da Integração PIS"),
							   fieldWithPath("integracaoPis.situacaoTributariaPisEntrada").description("Situacão Tributaria Pis Entrada"),
							   fieldWithPath("integracaoPis.aliquotaPisTomado").description("Aliquota Pis Tomado da Integração PIS"),
							   fieldWithPath("integracaoPis.tipoDeCredito").description("Tipo de Crédito"),
							   fieldWithPath("integracaoPis.baseCalculoCredito").description("Base Calculo Credito da Integração PIS"),
							   fieldWithPath("integracaoPis.situacaoTributariaPisSaida").description("Situação tributária PIS saída"),
							   fieldWithPath("integracaoPis.aliquotaPisPrestado").description("Aliquota Pis Prestado da Integração PIS"),
							   fieldWithPath("integracaoPis.contribuicaoSocialApurada").description("Contribuição Social Apurada da Integração PIS"),
							   fieldWithPath("integracaoPis.naturezaReceita").description("Natureza Receita da Integração PIS"),
							   fieldWithPath("integracaoPis.naturezaReceita.id").description("Id da Natureza Receita"),
							   fieldWithPath("integracaoPis.naturezaReceita.codigo").description("Código da Natureza Receita"),
							   fieldWithPath("integracaoPis.naturezaReceita.cst").description("Cst da Natureza Receita"),
							   fieldWithPath("integracaoPis.naturezaReceita.descricao").description("Descrição da Natureza Receita"),
							   fieldWithPath("integracaoPis.naturezaReceita.codigoTabela").description("Código tabela da Natureza Receita"),
							   fieldWithPath("integracaoPis.naturezaReceita.prefixDialog").description("PrefixDialog da Natureza Receita"),
							   fieldWithPath("integracaoPis.naturezaReceita.prefixSelect").description("PrefixSelect da Natureza Receita"),
							   subsectionWithPath("integracaoPis.servico").description("Referencia ao Parent"),
							   fieldWithPath("integracaoCofins").description("Detalhes da integração COFINS"),
							   fieldWithPath("integracaoCofins.id").description("Id da integração COFINS"),
							   fieldWithPath("integracaoCofins.situacaoTributariaCofinsEntrada").description("Situação Tributária Cofins"),
							   fieldWithPath("integracaoCofins.aliquotaCofinsTomado").description("Valor aliquota Cofins Tomado"),
							   fieldWithPath("integracaoCofins.tipoDeCredito").description("Tipo de crédito"),
							   fieldWithPath("integracaoCofins.baseCalculoCredito").description("Base Calculo Crédito"),
							   fieldWithPath("integracaoCofins.situacaoTributariaCofinsSaida").description("Situação Tributária Cofins Saída"),
							   fieldWithPath("integracaoCofins.aliquotaCofinsPrestado").description("Aliquota Cofins Prestado"),
							   fieldWithPath("integracaoCofins.aliquotaCofinsPrestado").description("Valor aliquota Cofins Prestado"),
							   fieldWithPath("integracaoCofins.contribuicaoSocialApurada").description("Contribuição Social Apurada"),
							   fieldWithPath("integracaoCofins.naturezaReceita").description("Natureza Receita"),
							   fieldWithPath("integracaoCofins.naturezaReceita.id").description("Id da Natureza Receita"),
							   fieldWithPath("integracaoCofins.naturezaReceita.cst").description("CST"),
							   fieldWithPath("integracaoCofins.naturezaReceita.codigoTabela").description("Código tabela"),
							   fieldWithPath("integracaoCofins.naturezaReceita.prefixDialog").description("Prefix Dialog"),
							   fieldWithPath("integracaoCofins.naturezaReceita.prefixSelect").description("Prefix Select"),
							   fieldWithPath("integracaoCofins.naturezaReceita.codigo").description("Código"),
							   fieldWithPath("integracaoCofins.naturezaReceita.descricao").description("Descrição"),
							   subsectionWithPath("integracaoCofins.servico").description("Referencia ao Parent"),
							   fieldWithPath("integracaoEscrituracao").description("Detalhes da integração Escrituração"),
							   fieldWithPath("integracaoEscrituracao.id").description("ID da integração Escrituração"),
							   fieldWithPath("integracaoEscrituracao.id").description("ID da integração Escrituração"),
							   fieldWithPath("integracaoEscrituracao.lucroPresumido").description("Lucro Presumido"),
							   fieldWithPath("integracaoEscrituracao.darf").description("DARF"),
							   fieldWithPath("integracaoEscrituracao.anexo").description("Anexo"),
							   subsectionWithPath("integracaoEscrituracao.servico").description("Referencia ao Parent"),
							   fieldWithPath("integracaoEscrituracao.locacao").description("Locação"),
							   fieldWithPath("integracaoEscrituracao.servicosContabeis").description("Serviços Contábeis"),
							   fieldWithPath("retencoesIss").description("Lista de Retenções ISS do serviço"),
							   fieldWithPath("retencoesIss[].id").description("Id da Retenções ISS"),
							   fieldWithPath("retencoesIss[].aliquotaRetencao").description("Aliquota da Retenções ISS"),
							   fieldWithPath("retencoesIss[].uf").description("UF da Retenções ISS"),
							   fieldWithPath("retencoesIss[].reducao").description("Valor redução"),
							   fieldWithPath("retencoesIss[].municipio").description("Municipio da Retenções ISS"),
							   fieldWithPath("retencoesIss[].municipio.id").description("Id do municipio"),
							   fieldWithPath("retencoesIss[].municipio.amazoniaOcidental").description("Indicador de Amazonia Ocidental"),
							   fieldWithPath("retencoesIss[].municipio.areaLivreComercio").description("Indicador de area Livre Comercio do municipio"),
							   fieldWithPath("retencoesIss[].municipio.codigoFederal").description("Código Federal"),
							   fieldWithPath("retencoesIss[].municipio.codigoMunicipal").description("Código Municipal"),
							   fieldWithPath("retencoesIss[].municipio.codigoIBGE").description("Código IGBE"),
							   fieldWithPath("retencoesIss[].municipio.descricao").description("Descrição do município"),
							   fieldWithPath("retencoesIss[].municipio.uf").description("UF do município"),
							   fieldWithPath("retencoesIss[].municipio.zonaFrancaManaus").description("Indicador de zona Franca Manaus"),
							   subsectionWithPath("retencoesIss[].servico").description("Refrerencia ao Parent"),
							   fieldWithPath("itensServico").description("Lista de Itens do serviço"),
							   fieldWithPath("itensServico[].id").description("Id do Item do serviço"),
							   fieldWithPath("itensServico[].codigo").description("Código do Item do serviço"),
							   fieldWithPath("itensServico[].descricao").description("Descrição do Item do serviço"),
							   fieldWithPath("itensServico[].valorUnitario").description("Valor unitário do Item do serviço"),
							   subsectionWithPath("itensServico[].servico").description("Referencia ao Parent"),
							   subsectionWithPath("empresa").description("Detalhes da Empresa"),
							   fieldWithPath("recibo").description("Indicador do recibo do serviço")));
	}
	
	private void documentRequestParametersForFindByCodigoAndEmpresa() {
		this.document.snippets(
				requestParameters(parameterWithName("codigoServico").description("Código do Serviço"),
				parameterWithName("idEmpresa").description("Id da empresa")));
	}
	
	private void documentIdPathParameters() {
		this.document.snippets(pathParameters(parameterWithName("id").description("Id do serviço a ser pesquisado")));
	}
}
