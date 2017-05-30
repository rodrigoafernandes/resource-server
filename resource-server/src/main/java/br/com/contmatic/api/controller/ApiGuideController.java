package br.com.contmatic.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/documentacao")
public class ApiGuideController {

	@RequestMapping(method = RequestMethod.GET)
	public String index(){
		return "docs/api-guide.html";
	}
	
}
