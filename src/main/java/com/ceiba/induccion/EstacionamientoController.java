package com.ceiba.induccion;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EstacionamientoController {

	@GetMapping("/hello")
	public String hello() {
		return "Hello World";
	}
	
}
