package com.ceiba.induccion.dominio.integracion;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ceiba.induccion.builder.VehiculoTestBuilder;
import com.ceiba.induccion.dominio.Registro;
import com.ceiba.induccion.dominio.ServiciosDelVigilante;
import com.ceiba.induccion.dominio.dto.VehiculoDto;
import com.ceiba.induccion.utilidad.TipoVehiculoEnum;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class RegistroTest {

	private static final String PLACA_VEHICULO_SIN_RESTRICCION_1 = "FFH134";
	private static final String PLACA_VEHICULO_SIN_RESTRICCION_2 = "FFH146";
	private static final Integer CILINDRAJE_MOTO = 550;
	private static final int TOTAL_CARROS_ESTACIONADOS = 2;
	private static final int TOTAL_MOTOS_ESTACIONADAS = 2;

	@Autowired
	private Registro registro;

	@Autowired
	private ServiciosDelVigilante serviciosDelVigilante;
	
	@Test
	public void contarCarrosEstacionadosTest() {
		// arrange
		VehiculoDto vehiculoDto1 = VehiculoTestBuilder.defaultValues().conPlaca(PLACA_VEHICULO_SIN_RESTRICCION_1)
				.conTipo(TipoVehiculoEnum.CARRO).buildDto();
		VehiculoDto vehiculoDto2 = VehiculoTestBuilder.defaultValues().conPlaca(PLACA_VEHICULO_SIN_RESTRICCION_2)
				.conTipo(TipoVehiculoEnum.CARRO).buildDto();
		// act
		serviciosDelVigilante.registrarIngreso(vehiculoDto1);
		serviciosDelVigilante.registrarIngreso(vehiculoDto2);

		int conteo = registro.contarVehiculosEstacionados(TipoVehiculoEnum.CARRO);

		// assert
		Assert.assertEquals(TOTAL_CARROS_ESTACIONADOS, conteo);
	}

	@Test
	public void contarMotosEstacionadasTest() {
		// arrange
		VehiculoDto vehiculoDto1 = VehiculoTestBuilder.defaultValues().conPlaca(PLACA_VEHICULO_SIN_RESTRICCION_1)
				.conCilindraje(CILINDRAJE_MOTO).conTipo(TipoVehiculoEnum.MOTO).buildDto();
		VehiculoDto vehiculoDto2 = VehiculoTestBuilder.defaultValues().conPlaca(PLACA_VEHICULO_SIN_RESTRICCION_2)
				.conCilindraje(CILINDRAJE_MOTO).conTipo(TipoVehiculoEnum.MOTO).buildDto();

		// act
		serviciosDelVigilante.registrarIngreso(vehiculoDto1);
		serviciosDelVigilante.registrarIngreso(vehiculoDto2);

		int conteo = registro.contarVehiculosEstacionados(TipoVehiculoEnum.MOTO);

		// assert
		Assert.assertEquals(TOTAL_MOTOS_ESTACIONADAS, conteo);
	}

}
