package br.com.contmatic.api.resource.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import br.com.contmatic.api.controller.ApiGuideController;
import br.com.contmatic.api.repository.ClienteRepository;
import br.com.contmatic.api.rest.ResourceRestController;
import br.com.contmatic.api.service.ClienteService;

@Configuration
@ComponentScan(basePackageClasses = {ApiGuideController.class, ResourceRestController.class, ClienteService.class, ClienteRepository.class})
public class MvcTestConfig extends WebMvcConfigurerAdapter {

}
