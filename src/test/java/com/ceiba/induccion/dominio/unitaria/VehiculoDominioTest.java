package com.ceiba.induccion.dominio.unitaria;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.ceiba.induccion.builder.VehiculoTestBuilder;
import com.ceiba.induccion.dominio.VehiculoConversor;
import com.ceiba.induccion.dominio.dto.VehiculoDto;
import com.ceiba.induccion.persistencia.entidad.VehiculoEntity;
import com.ceiba.induccion.utilidad.TipoVehiculoEnum;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class VehiculoDominioTest {

	private static final String PLACA_VEHICULO = "PLT652";
	private static final Integer CILINDRAJE_VEHICULO = 200;

	@Autowired
	private VehiculoConversor vehiculoConversor;

	@Test
	public void guardarVehiculo() {
		// arrange
		VehiculoDto vehiculoDto = VehiculoTestBuilder.defaultValues().conPlaca(PLACA_VEHICULO)
				.conTipo(TipoVehiculoEnum.CARRO).conCilindraje(CILINDRAJE_VEHICULO).buildDto();

		// act
		VehiculoEntity vehiculoAlmacenado = vehiculoConversor.crearVehiculo(vehiculoDto);

		// assert
		Assert.assertEquals(PLACA_VEHICULO, vehiculoAlmacenado.getPlaca());
		Assert.assertEquals(TipoVehiculoEnum.CARRO, vehiculoAlmacenado.getTipo());
		Assert.assertEquals(CILINDRAJE_VEHICULO, vehiculoAlmacenado.getCilindraje());
	}

}
