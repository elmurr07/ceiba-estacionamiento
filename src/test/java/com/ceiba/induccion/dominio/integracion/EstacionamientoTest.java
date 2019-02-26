package com.ceiba.induccion.dominio.integracion;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ceiba.induccion.builder.VehiculoTestBuilder;
import com.ceiba.induccion.dominio.EstacionamientoDominio;
import com.ceiba.induccion.dominio.VehiculoDominio;
import com.ceiba.induccion.dominio.dto.VehiculoDto;
import com.ceiba.induccion.dominio.excepcion.EstacionamientoException;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class EstacionamientoTest {

	@Autowired
	private EstacionamientoDominio estacionamientoDominio;

	private static final String PLACA_VEHICULO_CON_RESTRICCION = "ALG139";
	private static final String PLACA_VEHICULO_SIN_RESTRICCION_1 = "FFH134";
	private static final String PLACA_VEHICULO_SIN_RESTRICCION_2 = "FFH146";
	private static final Integer CILINDRAJE_MOTO = 550;
	private static final int TOTAL_CARROS_ESTACIONADOS = 2;
	private static final int TOTAL_MOTOS_ESTACIONADAS = 2;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(EstacionamientoTest.class);
	}

	@Test
	public void contarCarrosEstacionadosTest() {
		// arrange
		VehiculoDto vehiculoDto1 = VehiculoTestBuilder.defaultValues().conPlaca(PLACA_VEHICULO_SIN_RESTRICCION_1)
				.conTipo(VehiculoDominio.TIPO_VEHICULO_CARRO).buildDto();
		VehiculoDto vehiculoDto2 = VehiculoTestBuilder.defaultValues().conPlaca(PLACA_VEHICULO_SIN_RESTRICCION_2)
				.conTipo(VehiculoDominio.TIPO_VEHICULO_CARRO).buildDto();
		// act
		estacionamientoDominio.registrarIngreso(vehiculoDto1);
		estacionamientoDominio.registrarIngreso(vehiculoDto2);

		int conteo = estacionamientoDominio.contarVehiculos(VehiculoDominio.TIPO_VEHICULO_CARRO);

		// assert
		Assert.assertEquals(TOTAL_CARROS_ESTACIONADOS, conteo);
	}

	@Test
	public void contarMotosEstacionadasTest() {
		// arrange
		VehiculoDto vehiculoDto1 = VehiculoTestBuilder.defaultValues().conPlaca(PLACA_VEHICULO_SIN_RESTRICCION_1)
				.conCilindraje(CILINDRAJE_MOTO).conTipo(VehiculoDominio.TIPO_VEHICULO_MOTO).buildDto();
		VehiculoDto vehiculoDto2 = VehiculoTestBuilder.defaultValues().conPlaca(PLACA_VEHICULO_SIN_RESTRICCION_2)
				.conCilindraje(CILINDRAJE_MOTO).conTipo(VehiculoDominio.TIPO_VEHICULO_MOTO).buildDto();

		// act
		estacionamientoDominio.registrarIngreso(vehiculoDto1);
		estacionamientoDominio.registrarIngreso(vehiculoDto2);

		int conteo = estacionamientoDominio.contarVehiculos(VehiculoDominio.TIPO_VEHICULO_MOTO);

		// assert
		Assert.assertEquals(TOTAL_MOTOS_ESTACIONADAS, conteo);
	}

	@Test
	public void registrarVehiculoConRestriccionTest() {
		// arrange
		VehiculoDto vehiculoDto = VehiculoTestBuilder.defaultValues().conPlaca(PLACA_VEHICULO_CON_RESTRICCION)
				.conCilindraje(CILINDRAJE_MOTO).conTipo(VehiculoDominio.TIPO_VEHICULO_MOTO).buildDto();

		when(estacionamientoDominio.tieneRestriccion(PLACA_VEHICULO_CON_RESTRICCION)).thenReturn(Boolean.TRUE);

		// act
		try {
			estacionamientoDominio.registrarIngreso(vehiculoDto);
		} catch (EstacionamientoException e) {
			Assert.assertEquals(EstacionamientoDominio.MENSAJE_ERROR_NO_INGRESO_FIN_SEMANA, e.getMessage());
		}

		// assert
		fail();
	}

	@Test
	public void registrarVehiculoSinRestriccionTest() {
		// arrange
		VehiculoDto vehiculoDto = VehiculoTestBuilder.defaultValues().conPlaca(PLACA_VEHICULO_SIN_RESTRICCION_1)
				.conCilindraje(CILINDRAJE_MOTO).conTipo(VehiculoDominio.TIPO_VEHICULO_MOTO).buildDto();

		// act
		estacionamientoDominio.registrarIngreso(vehiculoDto);

		// assert
		Assert.assertTrue(Boolean.TRUE);
	}

}
