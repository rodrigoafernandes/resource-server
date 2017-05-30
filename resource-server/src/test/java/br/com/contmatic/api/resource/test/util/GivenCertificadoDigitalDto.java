package br.com.contmatic.api.resource.test.util;

import java.io.Serializable;
import java.util.Calendar;

import org.apache.commons.lang3.math.NumberUtils;

import br.com.contmatic.integration.dto.CertificadoDigitalDto;

public class GivenCertificadoDigitalDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static CertificadoDigitalDto getCertificadoDigital() {
		CertificadoDigitalDto certificadoDigital = new CertificadoDigitalDto();
		certificadoDigital.setCertificado(NumberUtils.LONG_ONE.toString().getBytes());
		certificadoDigital.setCnpj("89.672.354/0001-96");
		certificadoDigital.setCpf("138.433.140-98");
		Calendar calendar = Calendar.getInstance();
		certificadoDigital.setDataNascimento(calendar.getTime());
		certificadoDigital.setEmpresaCertificadora("Certisign");
		certificadoDigital.setId(NumberUtils.LONG_ONE);
		certificadoDigital.setInfoExtra("Informações extra");
		certificadoDigital.setInss("654654.4546");
		certificadoDigital.setNis("564.5456.4");
		certificadoDigital.setNomeEmpresarial("Zuriq");
		certificadoDigital.setNomeResponsavel("Antonio Amaro Borges");
		certificadoDigital.setOrgaoExpedidor("SSP");
		certificadoDigital.setRg("45.687.098-7");
		certificadoDigital.setValidoAte(calendar.getTime());
		certificadoDigital.setValidoDe(calendar.getTime());
		return certificadoDigital;
	}
	
}
