package com.ceiba.induccion.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.induccion.dominio.EstacionamientoDominio;
import com.ceiba.induccion.dominio.dto.VehiculoDto;

@RestController
@RequestMapping("estacionamiento")
public class EstacionamientoService {

	@Autowired
	private EstacionamientoDominio estacionamientoDominio;

	@PostMapping("/registrar")
	public void registrar(VehiculoDto vehiculoDto) {
		estacionamientoDominio.registrarIngreso(vehiculoDto);
	}

}
