package br.com.contmatic.api.resource.config;

import org.springframework.boot.autoconfigure.security.oauth2.OAuth2AutoConfiguration;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.web.WebTestContextBootstrapper;

@TestConfiguration
@Import({WebTestContextBootstrapper.class, SecurityTestConfig.class, OAuth2AutoConfiguration.class})
public class DefaultTestConfiguration {

}
