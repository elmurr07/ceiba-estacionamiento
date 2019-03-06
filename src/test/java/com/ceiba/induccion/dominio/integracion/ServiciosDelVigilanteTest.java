package com.ceiba.induccion.dominio.integracion;

import static org.junit.Assert.fail;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ceiba.induccion.builder.VehiculoTestBuilder;
import com.ceiba.induccion.dominio.ServiciosDelVigilante;
import com.ceiba.induccion.dominio.dto.VehiculoDto;
import com.ceiba.induccion.dominio.excepcion.RegistroException;
import com.ceiba.induccion.utilidad.RegistroConstants;
import com.ceiba.induccion.utilidad.TipoVehiculoEnum;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ServiciosDelVigilanteTest {

	private static final String PLACA_VEHICULO_SIN_RESTRICCION = "EFQ779";
	private static final int CILINDRAJE_MOTO = 720;

	@Autowired
	private ServiciosDelVigilante serviciosDelVigilante;

	@Test
	public void noPermitirRegistrarVehiculoConPlacaYaEstacionadoTest() {
		// arrange
		VehiculoDto vehiculoDto = VehiculoTestBuilder.defaultValues().conPlaca(PLACA_VEHICULO_SIN_RESTRICCION)
				.conCilindraje(CILINDRAJE_MOTO).conTipo(TipoVehiculoEnum.MOTO).buildDto();

		// act
		try {
			serviciosDelVigilante.registrarIngreso(vehiculoDto);
			serviciosDelVigilante.registrarIngreso(vehiculoDto);
		} catch (RegistroException e) {
			Assert.assertEquals(RegistroConstants.MENSAJE_ERROR_VEHICULO_ESTACIONADO, e.getMessage());
			return;
		}

		// assert
		fail();
	}

}
