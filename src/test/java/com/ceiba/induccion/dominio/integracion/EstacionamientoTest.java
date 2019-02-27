package com.ceiba.induccion.dominio.integracion;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.ceiba.induccion.builder.VehiculoTestBuilder;
import com.ceiba.induccion.dominio.ServiciosDelVigilante;
import com.ceiba.induccion.dominio.VehiculoConversor;
import com.ceiba.induccion.dominio.dto.VehiculoDto;
import com.ceiba.induccion.persistencia.entidad.EstacionamientoEntity;
import com.ceiba.induccion.persistencia.entidad.PagoEntity;
import com.ceiba.induccion.persistencia.entidad.VehiculoEntity;
import com.ceiba.induccion.utilidad.TipoVehiculoEnum;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class EstacionamientoTest {

	@Autowired
	private ServiciosDelVigilante serviciosDelVigilante;

	@Autowired
	private VehiculoConversor vehiculoConversor;

	private static final String PLACA_VEHICULO_SIN_RESTRICCION_1 = "FFH134";
	private static final String PLACA_VEHICULO_SIN_RESTRICCION_2 = "FFH146";
	private static final Integer CILINDRAJE_MOTO = 550;
	private static final int TOTAL_CARROS_ESTACIONADOS = 2;
	private static final int TOTAL_MOTOS_ESTACIONADAS = 2;

	@Test
	public void crearEstacionamientoTest() {
		// arrange
		VehiculoDto vehiculoDto = VehiculoTestBuilder.defaultValues().conPlaca(PLACA_VEHICULO_SIN_RESTRICCION_1)
				.conTipo(TipoVehiculoEnum.CARRO).buildDto();
		// act
		VehiculoEntity vehiculoEntity = vehiculoConversor.crearVehiculo(vehiculoDto);
		EstacionamientoEntity estacionamientoEntity = serviciosDelVigilante.crearEstacionamiento(vehiculoEntity);

		// assert
		Assert.assertEquals(PLACA_VEHICULO_SIN_RESTRICCION_1, estacionamientoEntity.getVehiculo().getPlaca());
		Assert.assertEquals(TipoVehiculoEnum.CARRO, estacionamientoEntity.getVehiculo().getTipo());
	}

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

		int conteo = serviciosDelVigilante.contarVehiculos(TipoVehiculoEnum.CARRO);

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

		int conteo = serviciosDelVigilante.contarVehiculos(TipoVehiculoEnum.MOTO);

		// assert
		Assert.assertEquals(TOTAL_MOTOS_ESTACIONADAS, conteo);
	}

	@Test
	public void registrarVehiculoSinRestriccionTest() {
		// arrange
		VehiculoDto vehiculoDto = VehiculoTestBuilder.defaultValues().conPlaca(PLACA_VEHICULO_SIN_RESTRICCION_1)
				.conCilindraje(CILINDRAJE_MOTO).conTipo(TipoVehiculoEnum.MOTO).buildDto();

		// act
		EstacionamientoEntity estacionamientoEntity = serviciosDelVigilante.registrarIngreso(vehiculoDto);

		// assert
		Assert.assertEquals(PLACA_VEHICULO_SIN_RESTRICCION_1, estacionamientoEntity.getVehiculo().getPlaca());
		Assert.assertEquals(TipoVehiculoEnum.MOTO, estacionamientoEntity.getVehiculo().getTipo());
	}

//	@Test
//	public void registrarSalidaMotoTest() {
//		// arrage
//		VehiculoDto vehiculoDto = VehiculoTestBuilder.defaultValues().conPlaca(PLACA_VEHICULO_SIN_RESTRICCION_1)
//				.conCilindraje(CILINDRAJE_MOTO).conTipo(TipoVehiculoEnum.MOTO).buildDto();
//
//		// act
//		EstacionamientoEntity estacionamientoEntity = estacionamientoDominio.registrarIngreso(vehiculoDto);
//		PagoEntity pagoEntity = estacionamientoDominio.registrarSalida(estacionamientoEntity.getId());
//
//		// assert
//		Assert.assertNotNull(pagoEntity.getValor());
//	}
//
//	@Test
//	public void registrarSalidaCarroTest() {
//		// arrage
//		VehiculoDto vehiculoDto = VehiculoTestBuilder.defaultValues().conPlaca(PLACA_VEHICULO_SIN_RESTRICCION_2)
//				.conTipo(TipoVehiculoEnum.CARRO).buildDto();
//
//		// act
//		EstacionamientoEntity estacionamientoEntity = estacionamientoDominio.registrarIngreso(vehiculoDto);
//		PagoEntity pagoEntity = estacionamientoDominio.registrarSalida(estacionamientoEntity.getId());
//
//		// assert
//		Assert.assertNotNull(pagoEntity.getValor());
//	}

}
