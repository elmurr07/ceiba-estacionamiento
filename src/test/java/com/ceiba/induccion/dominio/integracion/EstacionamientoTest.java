package com.ceiba.induccion.dominio.integracion;

import java.util.Date;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ceiba.induccion.builder.EstacionamientoTestBuilder;
import com.ceiba.induccion.builder.VehiculoTestBuilder;
import com.ceiba.induccion.dominio.EstacionamientoDominio;
import com.ceiba.induccion.dominio.VehiculoDominio;
import com.ceiba.induccion.persistencia.entidad.EstacionamientoEntity;
import com.ceiba.induccion.persistencia.entidad.VehiculoEntity;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class EstacionamientoTest {

	@Autowired
	private VehiculoDominio vehiculoDominio;

	@Autowired
	private EstacionamientoDominio estacionamientoDominio;

	private static final String PLACA_VEHICULO_CON_RESTRICCION = "ALG139";
	private static final String PLACA_VEHICULO_SIN_RESTRICCION_1 = "FFH134";
	private static final String PLACA_VEHICULO_SIN_RESTRICCION_2 = "FFH146";
	private static final String USUARIO_SISTEMA = "user_test";
	private static final int TOTAL_CARROS_ESTACIONADOS = 2;
	private static final int TOTAL_MOTOS_ESTACIONADAS = 2;

	@Test
	public void contarCarrosEstacionadosTest() {
		// arrange
		VehiculoEntity vehiculo1 = VehiculoTestBuilder.defaultValues().conPlaca(PLACA_VEHICULO_SIN_RESTRICCION_1)
				.conTipo(VehiculoDominio.TIPO_VEHICULO_CARRO).build();
		VehiculoEntity vehiculo2 = VehiculoTestBuilder.defaultValues().conPlaca(PLACA_VEHICULO_SIN_RESTRICCION_2)
				.conTipo(VehiculoDominio.TIPO_VEHICULO_CARRO).build();
		EstacionamientoEntity estacionamiento1 = EstacionamientoTestBuilder.defaultValues().conInicio(new Date())
				.conFecha(new Date()).conUsuario(USUARIO_SISTEMA).conVehiculo(vehiculo1).build();
		EstacionamientoEntity estacionamiento2 = EstacionamientoTestBuilder.defaultValues().conInicio(new Date())
				.conFecha(new Date()).conUsuario(USUARIO_SISTEMA).conVehiculo(vehiculo2).build();

		// act
		vehiculoDominio.crearVehiculo(vehiculo1);
		vehiculoDominio.crearVehiculo(vehiculo2);
		estacionamientoDominio.crearEstacionamiento(estacionamiento1);
		estacionamientoDominio.crearEstacionamiento(estacionamiento2);

		int conteo = estacionamientoDominio.contarVehiculos(VehiculoDominio.TIPO_VEHICULO_CARRO);

		// assert
		Assert.assertEquals(TOTAL_CARROS_ESTACIONADOS, conteo);
	}

	@Test
	public void contarMotosEstacionadasTest() {
		// arrange
		VehiculoEntity vehiculo1 = VehiculoTestBuilder.defaultValues().conPlaca(PLACA_VEHICULO_SIN_RESTRICCION_1)
				.conTipo(VehiculoDominio.TIPO_VEHICULO_MOTO).build();
		VehiculoEntity vehiculo2 = VehiculoTestBuilder.defaultValues().conPlaca(PLACA_VEHICULO_SIN_RESTRICCION_2)
				.conTipo(VehiculoDominio.TIPO_VEHICULO_MOTO).build();
		EstacionamientoEntity estacionamiento1 = EstacionamientoTestBuilder.defaultValues().conInicio(new Date())
				.conFecha(new Date()).conUsuario(USUARIO_SISTEMA).conVehiculo(vehiculo1).build();
		EstacionamientoEntity estacionamiento2 = EstacionamientoTestBuilder.defaultValues().conInicio(new Date())
				.conFecha(new Date()).conUsuario(USUARIO_SISTEMA).conVehiculo(vehiculo2).build();

		// act
		vehiculoDominio.crearVehiculo(vehiculo1);
		vehiculoDominio.crearVehiculo(vehiculo2);
		estacionamientoDominio.crearEstacionamiento(estacionamiento1);
		estacionamientoDominio.crearEstacionamiento(estacionamiento2);

		int conteo = estacionamientoDominio.contarVehiculos(VehiculoDominio.TIPO_VEHICULO_MOTO);

		// assert
		Assert.assertEquals(TOTAL_MOTOS_ESTACIONADAS, conteo);
	}

	@Test
	public void vehiculoConRestriccionTest() {
		// arrange
		VehiculoEntity vehiculo = VehiculoTestBuilder.defaultValues().conPlaca(PLACA_VEHICULO_CON_RESTRICCION)
				.conTipo(VehiculoDominio.TIPO_VEHICULO_MOTO).build();
		EstacionamientoEntity estacionamiento1 = EstacionamientoTestBuilder.defaultValues().conInicio(new Date())
				.conFecha(new Date()).conUsuario(USUARIO_SISTEMA).conVehiculo(vehiculo).build();
		
		// act
		

		// assert
	}

}
