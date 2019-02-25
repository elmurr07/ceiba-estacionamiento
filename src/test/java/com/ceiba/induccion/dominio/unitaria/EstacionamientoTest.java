package com.ceiba.induccion.dominio.unitaria;

import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ceiba.induccion.builder.EstacionamientoTestBuilder;
import com.ceiba.induccion.dominio.EstacionamientoDominio;
import com.ceiba.induccion.dominio.VehiculoDominio;
import com.ceiba.induccion.persistencia.entidad.EstacionamientoEntity;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EstacionamientoTest {

	private static final int CARROS_EN_PARQUEADERO = 5;
	private static final int MOTOS_EN_PARQUEADERO = 3;
	private static final String PLACA_CARRO = "EFE318";

//	@Autowired
//	private Estacionamiento estacionamiento;

	@Mock
	private EstacionamientoDominio estacionamientoDominio;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(EstacionamientoTest.class);
	}

	@Test
	public void verificarSiEspacioMotoTest() {
		// arrange
		when(estacionamientoDominio.contarVehiculos(VehiculoDominio.TIPO_VEHICULO_MOTO))
				.thenReturn(MOTOS_EN_PARQUEADERO);

		// act
		boolean resultado = estacionamientoDominio.existeCupoMoto();

		// assert
		Assert.assertEquals(Boolean.TRUE, resultado);
	}

	@Test
	public void verificarNoEspacioMotoTest() {
		// arrange
		when(estacionamientoDominio.contarVehiculos(VehiculoDominio.TIPO_VEHICULO_MOTO))
				.thenReturn(EstacionamientoDominio.CUPO_MOTOS_PARQUEADERO);

		// act
		boolean resultado = estacionamientoDominio.existeCupoMoto();

		// assert
		Assert.assertEquals(Boolean.FALSE, resultado);
	}

	@Test
	public void verificarSiEspacioCarroTest() {
		// arrange
		when(estacionamientoDominio.contarVehiculos(VehiculoDominio.TIPO_VEHICULO_CARRO))
				.thenReturn(CARROS_EN_PARQUEADERO);

		// act
		boolean resultado = estacionamientoDominio.existeCupoCarro();

		// assert
		Assert.assertEquals(Boolean.TRUE, resultado);
	}

	@Test
	public void verificarNoEspacioCarroTest() {
		// arrange
		when(estacionamientoDominio.contarVehiculos(VehiculoDominio.TIPO_VEHICULO_CARRO))
				.thenReturn(EstacionamientoDominio.CUPO_CARROS_PARQUEADERO);

		// act
		boolean resultado = estacionamientoDominio.existeCupoMoto();

		// assert
		Assert.assertEquals(Boolean.FALSE, resultado);
	}

	@Test
	public void obtenerEstacionamientoByVehiculoTest() {
//		// arrage
//		EstacionamientoEntity estacionamientoEntity = EstacionamientoTestBuilder.defaultValues().
//
//		// act
//		EstacionamientoEntity estacionamientoEntity = estacionamientoDominio.obtenerEstacionamientoByPlaca(vehiculoEntity);

		// assert
	}

}
