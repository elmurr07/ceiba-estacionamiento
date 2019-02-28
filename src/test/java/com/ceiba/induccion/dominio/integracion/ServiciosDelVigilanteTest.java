package com.ceiba.induccion.dominio.integracion;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.ceiba.induccion.builder.VehiculoTestBuilder;
import com.ceiba.induccion.dominio.Registro;
import com.ceiba.induccion.dominio.ServiciosDelVigilante;
import com.ceiba.induccion.dominio.dto.RegistroDto;
import com.ceiba.induccion.dominio.dto.VehiculoDto;
import com.ceiba.induccion.utilidad.TipoVehiculoEnum;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ServiciosDelVigilanteTest {

	@Autowired
	private ServiciosDelVigilante serviciosDelVigilante;

	@Autowired
	private Registro registro;

	private static final String PLACA_VEHICULO_SIN_RESTRICCION_1 = "FFH134";
	private static final String PLACA_VEHICULO_SIN_RESTRICCION_2 = "FFH146";
	private static final Integer CILINDRAJE_MOTO = 550;
	private static final int TOTAL_CARROS_ESTACIONADOS = 2;
	private static final int TOTAL_MOTOS_ESTACIONADAS = 2;

	@Test
	public void crearregistroTest() {
		// arrange
		VehiculoDto vehiculoDto = VehiculoTestBuilder.defaultValues().conPlaca(PLACA_VEHICULO_SIN_RESTRICCION_1)
				.conTipo(TipoVehiculoEnum.CARRO).buildDto();
		// act
		RegistroDto registroDto = registro.registrarVehiculo(vehiculoDto);

		// assert
		Assert.assertEquals(PLACA_VEHICULO_SIN_RESTRICCION_1, registroDto.getVehiculo().getPlaca());
		Assert.assertEquals(TipoVehiculoEnum.CARRO, registroDto.getVehiculo().getTipo());
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
		RegistroDto registroDto = serviciosDelVigilante.registrarIngreso(vehiculoDto);

		// assert
		Assert.assertEquals(PLACA_VEHICULO_SIN_RESTRICCION_1, registroDto.getVehiculo().getPlaca());
		Assert.assertEquals(TipoVehiculoEnum.MOTO, registroDto.getVehiculo().getTipo());
	}

//	@Test
//	public void registrarSalidaMotoTest() {
//		// arrage
//		VehiculoDto vehiculoDto = VehiculoTestBuilder.defaultValues().conPlaca(PLACA_VEHICULO_SIN_RESTRICCION_1)
//				.conCilindraje(CILINDRAJE_MOTO).conTipo(TipoVehiculoEnum.MOTO).buildDto();
//
//		// act
//		registroEntity registroEntity = registroDominio.registrarIngreso(vehiculoDto);
//		PagoEntity pagoEntity = registroDominio.registrarSalida(registroEntity.getId());
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
//		registroEntity registroEntity = registroDominio.registrarIngreso(vehiculoDto);
//		PagoEntity pagoEntity = registroDominio.registrarSalida(registroEntity.getId());
//
//		// assert
//		Assert.assertNotNull(pagoEntity.getValor());
//	}

}
