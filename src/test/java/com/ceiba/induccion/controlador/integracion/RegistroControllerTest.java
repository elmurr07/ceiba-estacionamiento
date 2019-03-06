package com.ceiba.induccion.controlador.integracion;

import static org.junit.Assert.fail;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ceiba.induccion.builder.VehiculoTestBuilder;
import com.ceiba.induccion.controlador.RegistroController;
import com.ceiba.induccion.dominio.CalculadorCarro;
import com.ceiba.induccion.dominio.CalculadorMoto;
import com.ceiba.induccion.dominio.dto.PagoDto;
import com.ceiba.induccion.dominio.dto.RegistroDto;
import com.ceiba.induccion.dominio.dto.VehiculoDto;
import com.ceiba.induccion.utilidad.TipoVehiculoEnum;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class RegistroControllerTest {

	@Autowired
	private RegistroController registroController;

	private static final String PLACA_VEHICULO_SIN_RESTRICCION_1 = "FFH134";
	private static final String PLACA_VEHICULO_SIN_RESTRICCION_2 = "FFH146";
	private static final String PLACA_VEHICULO_SIN_RESTRICCION_3 = "RLB741";
	private static final Integer CILINDRAJE_MOTO = 300;
	private static final int NUMERO_VEHICULOS_ESTACIONADOS = 3;

	@Test
	public void crearRegistroTest() {
		// arrange
		VehiculoDto vehiculoDto = VehiculoTestBuilder.defaultValues().conPlaca(PLACA_VEHICULO_SIN_RESTRICCION_1)
				.conTipo(TipoVehiculoEnum.CARRO).buildDto();
		// act
		RegistroDto registroDto = registroController.registrarIngreso(vehiculoDto);

		// assert
		Assert.assertEquals(PLACA_VEHICULO_SIN_RESTRICCION_1, registroDto.getVehiculo().getPlaca());
		Assert.assertEquals(TipoVehiculoEnum.CARRO, registroDto.getVehiculo().getTipo());
	}

	@Test
	public void registrarVehiculoSinRestriccionTest() {
		// arrange
		VehiculoDto vehiculoDto = VehiculoTestBuilder.defaultValues().conPlaca(PLACA_VEHICULO_SIN_RESTRICCION_1)
				.conCilindraje(CILINDRAJE_MOTO).conTipo(TipoVehiculoEnum.MOTO).buildDto();

		// act
		RegistroDto registroDto = registroController.registrarIngreso(vehiculoDto);

		// assert
		Assert.assertEquals(PLACA_VEHICULO_SIN_RESTRICCION_1, registroDto.getVehiculo().getPlaca());
		Assert.assertEquals(TipoVehiculoEnum.MOTO, registroDto.getVehiculo().getTipo());
	}

	@Test
	public void registrarSalidaMotoTest() {
		// arrage
		VehiculoDto vehiculoDto = VehiculoTestBuilder.defaultValues().conPlaca(PLACA_VEHICULO_SIN_RESTRICCION_1)
				.conCilindraje(CILINDRAJE_MOTO).conTipo(TipoVehiculoEnum.MOTO).buildDto();

		// act
		RegistroDto registroDto = registroController.registrarIngreso(vehiculoDto);
		PagoDto pagoDto = registroController.registrarSalida(registroDto);

		// assert
		Assert.assertEquals(CalculadorMoto.VALOR_HORA, pagoDto.getValor(), 0);
	}

	@Test
	public void registrarSalidaCarroTest() {
		// arrage
		VehiculoDto vehiculoDto = VehiculoTestBuilder.defaultValues().conPlaca(PLACA_VEHICULO_SIN_RESTRICCION_2)
				.conTipo(TipoVehiculoEnum.CARRO).buildDto();

		// act
		RegistroDto registroDto = registroController.registrarIngreso(vehiculoDto);
		PagoDto pagoDto = registroController.registrarSalida(registroDto);

		// assert
		Assert.assertEquals(CalculadorCarro.VALOR_HORA, pagoDto.getValor(), 0);
	}

	@Test
	public void numeroCorrectolistarEstacionadosTest() {
		// arrange
		VehiculoDto vehiculoDto1 = VehiculoTestBuilder.defaultValues().conPlaca(PLACA_VEHICULO_SIN_RESTRICCION_1)
				.conTipo(TipoVehiculoEnum.CARRO).buildDto();
		VehiculoDto vehiculoDto2 = VehiculoTestBuilder.defaultValues().conPlaca(PLACA_VEHICULO_SIN_RESTRICCION_2)
				.conTipo(TipoVehiculoEnum.CARRO).buildDto();
		VehiculoDto vehiculoDto3 = VehiculoTestBuilder.defaultValues().conPlaca(PLACA_VEHICULO_SIN_RESTRICCION_3)
				.conCilindraje(CILINDRAJE_MOTO).conTipo(TipoVehiculoEnum.MOTO).buildDto();

		// act
		registroController.registrarIngreso(vehiculoDto1);
		registroController.registrarIngreso(vehiculoDto2);
		registroController.registrarIngreso(vehiculoDto3);

		List<RegistroDto> listado = registroController.listar();

		// assert
		Assert.assertEquals(NUMERO_VEHICULOS_ESTACIONADOS, listado.size());
	}

	@Test
	public void listarEstacionadosFechaFinNulaTest() {
		// arrange
		VehiculoDto vehiculoDto1 = VehiculoTestBuilder.defaultValues().conPlaca(PLACA_VEHICULO_SIN_RESTRICCION_1)
				.conTipo(TipoVehiculoEnum.CARRO).buildDto();
		VehiculoDto vehiculoDto2 = VehiculoTestBuilder.defaultValues().conPlaca(PLACA_VEHICULO_SIN_RESTRICCION_2)
				.conTipo(TipoVehiculoEnum.CARRO).buildDto();
		VehiculoDto vehiculoDto3 = VehiculoTestBuilder.defaultValues().conPlaca(PLACA_VEHICULO_SIN_RESTRICCION_3)
				.conCilindraje(CILINDRAJE_MOTO).conTipo(TipoVehiculoEnum.MOTO).buildDto();

		// act
		registroController.registrarIngreso(vehiculoDto1);
		registroController.registrarIngreso(vehiculoDto2);
		registroController.registrarIngreso(vehiculoDto3);

		List<RegistroDto> listado = registroController.listar();

		for (RegistroDto registroDto : listado) {
			if (registroDto.getFin() != null) {
				fail();
			}
		}

		// assert
		assert (true);
	}

}
