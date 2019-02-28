package com.ceiba.induccion.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.induccion.dominio.Registro;
import com.ceiba.induccion.dominio.ServiciosDelVigilante;
import com.ceiba.induccion.dominio.dto.RegistroDto;
import com.ceiba.induccion.dominio.dto.VehiculoDto;

@RestController
@RequestMapping("registro")
public class RegistroController {

	@Autowired
	private ServiciosDelVigilante serviciosDelVigilante;

	@Autowired
	private Registro registro;

	@PostMapping("registrarIngreso")
	public void registrarIngreso(@RequestBody VehiculoDto vehiculoDto) {
		serviciosDelVigilante.registrarIngreso(vehiculoDto);
	}

	@PostMapping("registrarSalida")
	public void registrarSalida(long idRegistro) {
		serviciosDelVigilante.registrarSalida(idRegistro);
	}

	@GetMapping("listar")
	public List<RegistroDto> listar() {
		return registro.listarEstacionados();
	}

}
