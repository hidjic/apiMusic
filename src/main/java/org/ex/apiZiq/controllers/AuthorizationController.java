package org.ex.apiZiq.controllers;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ex.apiZiq.AuthorizationCode;
import org.ex.apiZiq.AuthorizationCodeUri;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/authorization")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthorizationController {
	
	private static final Logger log = LogManager.getLogger(AuthorizationController.class);
	
	@GetMapping("/login")
	public String getLog() {
//		log.info("got to authorization code uri...");
		
		String uri = AuthorizationCodeUri.authorizationCodeUri();
		if(uri.indexOf(":443") != -1) {
			uri = uri.replace(":443", "");
		}
		return uri;
	}

	@GetMapping("/callback")
	public void getCallback(@RequestParam Map<String, String> resParams, HttpServletResponse response) {
		String code = resParams.get("code");
//		String state = resParams.get("state");
//		log.info("code => " + code);
//		log.info("state => " + state);
		AuthorizationCode.authorizationCode(code);
		if(!("").equals(code)) {
			try {
				response.sendRedirect("http://localhost:4200/bo");
			} catch (IOException e) {
				log.error(e.getMessage());
			}
		}
	}
	
}
