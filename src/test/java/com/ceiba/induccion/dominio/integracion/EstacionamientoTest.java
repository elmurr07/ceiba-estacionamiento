package com.ceiba.induccion.dominio.integracion;

import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.time.DayOfWeek;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.ceiba.induccion.builder.VehiculoTestBuilder;
import com.ceiba.induccion.dominio.CalendarioUtil;
import com.ceiba.induccion.dominio.EstacionamientoDominioImpl;
import com.ceiba.induccion.dominio.dto.VehiculoDto;
import com.ceiba.induccion.dominio.excepcion.EstacionamientoException;
import com.ceiba.induccion.utilidad.EstacionamientoConstants;
import com.ceiba.induccion.utilidad.TipoVehiculoEnum;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class EstacionamientoTest {

	@Mock
	private CalendarioUtil calendarioUtil;

	@InjectMocks
	private EstacionamientoDominioImpl estacionamientoDominio;

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
				.conTipo(TipoVehiculoEnum.CARRO).buildDto();
		VehiculoDto vehiculoDto2 = VehiculoTestBuilder.defaultValues().conPlaca(PLACA_VEHICULO_SIN_RESTRICCION_2)
				.conTipo(TipoVehiculoEnum.CARRO).buildDto();
		// act
		estacionamientoDominio.registrarIngreso(vehiculoDto1);
		estacionamientoDominio.registrarIngreso(vehiculoDto2);

		int conteo = estacionamientoDominio.contarVehiculos(TipoVehiculoEnum.CARRO);

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
		estacionamientoDominio.registrarIngreso(vehiculoDto1);
		estacionamientoDominio.registrarIngreso(vehiculoDto2);

		int conteo = estacionamientoDominio.contarVehiculos(TipoVehiculoEnum.MOTO);

		// assert
		Assert.assertEquals(TOTAL_MOTOS_ESTACIONADAS, conteo);
	}

	@Test
	public void registrarVehiculoConRestriccionTest() {
		// arrange
		VehiculoDto vehiculoDto = VehiculoTestBuilder.defaultValues().conPlaca(PLACA_VEHICULO_CON_RESTRICCION)
				.conCilindraje(CILINDRAJE_MOTO).conTipo(TipoVehiculoEnum.MOTO).buildDto();

		when(calendarioUtil.dayWeekFromDate(any())).thenReturn(DayOfWeek.SUNDAY);

		// act
		try {
			estacionamientoDominio.registrarIngreso(vehiculoDto);
		} catch (EstacionamientoException e) {
			Assert.assertEquals(EstacionamientoConstants.MENSAJE_ERROR_NO_INGRESO_FIN_SEMANA, e.getMessage());
			return;
		}

		// assert
		fail();
	}

	@Test
	public void registrarVehiculoSinRestriccionTest() {
		// arrange
		VehiculoDto vehiculoDto = VehiculoTestBuilder.defaultValues().conPlaca(PLACA_VEHICULO_SIN_RESTRICCION_1)
				.conCilindraje(CILINDRAJE_MOTO).conTipo(TipoVehiculoEnum.MOTO).buildDto();

		// act
		estacionamientoDominio.registrarIngreso(vehiculoDto);

		// assert
		Assert.assertTrue(Boolean.TRUE);
	}

}
