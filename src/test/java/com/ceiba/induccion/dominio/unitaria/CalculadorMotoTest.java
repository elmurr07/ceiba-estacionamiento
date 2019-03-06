package com.ceiba.induccion.dominio.unitaria;

import static org.junit.Assert.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ceiba.induccion.builder.RegistroTestBuilder;
import com.ceiba.induccion.builder.VehiculoTestBuilder;
import com.ceiba.induccion.dominio.CalculadorMoto;
import com.ceiba.induccion.persistencia.entidad.RegistroEntity;
import com.ceiba.induccion.persistencia.entidad.VehiculoEntity;
import com.ceiba.induccion.utilidad.RegistroConstants;
import com.ceiba.induccion.utilidad.TipoVehiculoEnum;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CalculadorMotoTest {

	@InjectMocks
	private CalculadorMoto calculadorMoto;

	private static final String PLACA_MOTO = "LGH156";
	private static final int CILINDRAJE_MOTO_ALTO = 650;
	private static final int CILINDRAJE_MOTO_BAJO = 200;
	private static final String FECHA_INICIO_VEHICULO_1 = "20/02/2019 16:00";
	private static final String FECHA_FIN_VEHICULO_1 = "21/02/2019 02:00";
	private static final String FECHA_INICIO_VEHICULO_2 = "14/03/2019 07:00";
	private static final String FECHA_FIN_VEHICULO_2 = "14/03/2019 16:00";
	private static final String FECHA_INICIO_VEHICULO_3 = "15/01/2019 10:00";
	private static final String FECHA_FIN_VEHICULO_3 = "15/01/2019 10:30";
	private static final double COSTO_VEHICULO_1 = 6_000;
	private static final double COSTO_VEHICULO_2 = 4_000;
	private static final double COSTO_VEHICULO_3 = 500;
	private static final int MOTOS_EN_PARQUEADERO_PARCIAL = 7;

	private SimpleDateFormat formatoFechaHora = new SimpleDateFormat("dd/MM/yyyy HH:mm");

	@Test
	public void costoEstacionamiento10HorasCilindrajeAltoTest() {
		// arrange
		VehiculoEntity vehiculoEntity = VehiculoTestBuilder.defaultValues().conTipo(TipoVehiculoEnum.CARRO)
				.conPlaca(PLACA_MOTO).conCilindraje(CILINDRAJE_MOTO_ALTO).build();
		Date fechaInicio = null;
		Date fechaFin = null;
		try {
			fechaInicio = formatoFechaHora.parse(FECHA_INICIO_VEHICULO_1);
			fechaFin = formatoFechaHora.parse(FECHA_FIN_VEHICULO_1);
		} catch (ParseException e) {
			fail();
		}
		RegistroEntity registroEntity = RegistroTestBuilder.defaultValues().conVehiculoEntity(vehiculoEntity)
				.conInicio(fechaInicio).conFin(fechaFin).build();

		// act
		double costo = calculadorMoto.calcularCosto(registroEntity);

		// assert
		Assert.assertEquals(COSTO_VEHICULO_1, costo, 0);
	}

	@Test
	public void costoEstacionamiento9HorasCilindrajeBajoTest() {
		// arrange
		VehiculoEntity vehiculoEntity = VehiculoTestBuilder.defaultValues().conTipo(TipoVehiculoEnum.CARRO)
				.conPlaca(PLACA_MOTO).conCilindraje(CILINDRAJE_MOTO_BAJO).build();
		Date fechaInicio = null;
		Date fechaFin = null;
		try {
			fechaInicio = formatoFechaHora.parse(FECHA_INICIO_VEHICULO_2);
			fechaFin = formatoFechaHora.parse(FECHA_FIN_VEHICULO_2);
		} catch (ParseException e) {
			fail();
		}
		RegistroEntity registroEntity = RegistroTestBuilder.defaultValues().conVehiculoEntity(vehiculoEntity)
				.conInicio(fechaInicio).conFin(fechaFin).build();

		// act
		double costo = calculadorMoto.calcularCosto(registroEntity);

		// assert
		Assert.assertEquals(COSTO_VEHICULO_2, costo, 0);
	}

	@Test
	public void costoEstacionamiento30MinutosCilindrajeBajoTest() {
		// arrange
		VehiculoEntity vehiculoEntity = VehiculoTestBuilder.defaultValues().conTipo(TipoVehiculoEnum.CARRO)
				.conPlaca(PLACA_MOTO).conCilindraje(CILINDRAJE_MOTO_BAJO).build();
		Date fechaInicio = null;
		Date fechaFin = null;
		try {
			fechaInicio = formatoFechaHora.parse(FECHA_INICIO_VEHICULO_3);
			fechaFin = formatoFechaHora.parse(FECHA_FIN_VEHICULO_3);
		} catch (ParseException e) {
			fail();
		}
		RegistroEntity registroEntity = RegistroTestBuilder.defaultValues().conVehiculoEntity(vehiculoEntity)
				.conInicio(fechaInicio).conFin(fechaFin).build();

		// act
		double costo = calculadorMoto.calcularCosto(registroEntity);

		// assert
		Assert.assertEquals(COSTO_VEHICULO_3, costo, 0);
	}

	@Test
	public void siExisteCupoTest() {
		// arrange

		// act
		boolean resultado = calculadorMoto.existeCupo(MOTOS_EN_PARQUEADERO_PARCIAL);

		// assert
		Assert.assertTrue(resultado);
	}

	@Test
	public void noExisteCupoTest() {
		// arrange

		// act
		boolean resultado = calculadorMoto.existeCupo(RegistroConstants.CUPO_MOTOS_PARQUEADERO);

		// assert
		Assert.assertFalse(resultado);
	}

}
