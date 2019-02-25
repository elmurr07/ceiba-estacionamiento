package com.ceiba.induccion.dominio.unitaria;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ceiba.induccion.builder.VehiculoTestBuilder;
import com.ceiba.induccion.dominio.VehiculoDominio;
import com.ceiba.induccion.persistencia.entidad.VehiculoEntity;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VehiculoTest {

	private static final String PLACA_VEHICULO = "PLT652";
	private static final Integer CILINDRAJE_VEHICULO = null;

	@Test
	public void guardarVehiculo() {
		// arrange
//		VehiculoEntity vehiculoEntity = VehiculoTestBuilder.defaultValues().conPlaca(PLACA_VEHICULO)
//				.conTipo(VehiculoDominio.TIPO_VEHICULO_CARRO).conCilindraje(cilindraje).build();

		// act
		

		// assert
	}

}
