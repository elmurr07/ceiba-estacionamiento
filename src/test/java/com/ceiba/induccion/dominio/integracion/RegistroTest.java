package com.ceiba.induccion.dominio.integracion;

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
import com.ceiba.induccion.dominio.Registro;
import com.ceiba.induccion.dominio.ServiciosDelVigilante;
import com.ceiba.induccion.dominio.dto.RegistroDto;
import com.ceiba.induccion.dominio.dto.VehiculoDto;
import com.ceiba.induccion.utilidad.TipoVehiculoEnum;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class RegistroTest {

	private static final String PLACA_VEHICULO_SIN_RESTRICCION_1 = "FFH134";
	private static final String PLACA_VEHICULO_SIN_RESTRICCION_2 = "FFH146";
	private static final String PLACA_VEHICULO_SIN_RESTRICCION_3 = "ALB741";
	private static final Integer CILINDRAJE_MOTO = 550;
	private static final int NUMERO_VEHICULOS_ESTACIONADOS = 3;

	@Autowired
	private Registro registro;

	@Autowired
	private ServiciosDelVigilante serviciosDelVigilante;

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
		serviciosDelVigilante.registrarIngreso(vehiculoDto1);
		serviciosDelVigilante.registrarIngreso(vehiculoDto2);
		serviciosDelVigilante.registrarIngreso(vehiculoDto3);

		List<RegistroDto> listado = registro.listarEstacionados();

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
		serviciosDelVigilante.registrarIngreso(vehiculoDto1);
		serviciosDelVigilante.registrarIngreso(vehiculoDto2);
		serviciosDelVigilante.registrarIngreso(vehiculoDto3);

		List<RegistroDto> listado = registro.listarEstacionados();

		for (RegistroDto registroDto : listado) {
			if (registroDto.getFin() != null) {
				fail();
			}
		}

		// assert
		assert (true);
	}

}
