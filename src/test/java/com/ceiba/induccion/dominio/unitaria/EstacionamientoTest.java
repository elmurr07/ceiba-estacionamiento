package com.ceiba.induccion.dominio.unitaria;

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

import com.ceiba.induccion.dominio.EstacionamientoDominio;
import com.ceiba.induccion.dominio.VehiculoDominio;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class EstacionamientoTest {

	private static final int CARROS_EN_PARQUEADERO = 5;
	private static final int MOTOS_EN_PARQUEADERO = 3;

	@Autowired
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

}
