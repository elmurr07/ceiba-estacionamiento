package com.ceiba.induccion.dominio.unitaria;

import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.time.DayOfWeek;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.ceiba.induccion.builder.RegistroTestBuilder;
import com.ceiba.induccion.builder.VehiculoTestBuilder;
import com.ceiba.induccion.dominio.CalendarioVigilante;
import com.ceiba.induccion.dominio.LibretaVigilante;
import com.ceiba.induccion.dominio.ReglasEstacionamiento;
import com.ceiba.induccion.dominio.ReglasEstacionamientoCarro;
import com.ceiba.induccion.dominio.ReglasEstacionamientoMoto;
import com.ceiba.induccion.dominio.ServiciosDelVigilanteImpl;
import com.ceiba.induccion.dominio.dto.RegistroDto;
import com.ceiba.induccion.dominio.dto.VehiculoDto;
import com.ceiba.induccion.dominio.excepcion.RegistroException;
import com.ceiba.induccion.utilidad.TipoVehiculoEnum;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ServiciosDelVigilanteTest {

	private static final String PLACA_VEHICULO_CON_RESTRICCION = "ALG139";
	private static final String PLACA_VEHICULO_SIN_RESTRICCION = "NNL677";
	private static final Integer CILINDRAJE_MOTO = 550;
	private static final int CARROS_EN_PARQUEADERO = 5;
	private static final int MOTOS_EN_PARQUEADERO = 3;

	@Mock
	private CalendarioVigilante calendarioVigilante;

	@Mock
	private ReglasEstacionamiento reglasEstacionamiento;

	@Mock
	private LibretaVigilante libretaVigilante;

	@InjectMocks
	private ServiciosDelVigilanteImpl serviciosDelVigilante;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(ServiciosDelVigilanteTest.class);
	}

	@Test
	public void registrarMotoConCupoTest() {
		// arrange
		VehiculoDto vehiculoDto = VehiculoTestBuilder.defaultValues().conPlaca(PLACA_VEHICULO_SIN_RESTRICCION)
				.conCilindraje(CILINDRAJE_MOTO).conTipo(TipoVehiculoEnum.MOTO).buildDto();

		when(libretaVigilante.contarVehiculosEstacionados(TipoVehiculoEnum.MOTO)).thenReturn(MOTOS_EN_PARQUEADERO);

		when(reglasEstacionamiento.validarCupo(TipoVehiculoEnum.MOTO, MOTOS_EN_PARQUEADERO)).thenReturn(Boolean.TRUE);

		RegistroDto registroDto = RegistroTestBuilder.defaultValues().conVehiculoDto(vehiculoDto).buildDto();
		when(libretaVigilante.registrarIngresoVehiculo(any())).thenReturn(registroDto);

		// act
		RegistroDto registroAlmacenado = serviciosDelVigilante.registrarIngreso(vehiculoDto);

		// assert
		Assert.assertEquals(PLACA_VEHICULO_SIN_RESTRICCION, registroAlmacenado.getVehiculo().getPlaca());
		Assert.assertEquals(CILINDRAJE_MOTO, registroAlmacenado.getVehiculo().getCilindraje());
		Assert.assertEquals(TipoVehiculoEnum.MOTO, registroAlmacenado.getVehiculo().getTipo());
	}

	@Test
	public void registrarCarroConCupoTest() {
		// arrange
		VehiculoDto vehiculoDto = VehiculoTestBuilder.defaultValues().conPlaca(PLACA_VEHICULO_SIN_RESTRICCION)
				.conTipo(TipoVehiculoEnum.CARRO).buildDto();

		when(libretaVigilante.contarVehiculosEstacionados(TipoVehiculoEnum.CARRO)).thenReturn(CARROS_EN_PARQUEADERO);

		when(reglasEstacionamiento.validarCupo(TipoVehiculoEnum.CARRO, CARROS_EN_PARQUEADERO)).thenReturn(Boolean.TRUE);

		RegistroDto registroDto = RegistroTestBuilder.defaultValues().conVehiculoDto(vehiculoDto).buildDto();
		when(libretaVigilante.registrarIngresoVehiculo(any())).thenReturn(registroDto);

		// act
		RegistroDto registroAlmacenado = serviciosDelVigilante.registrarIngreso(vehiculoDto);

		// assert
		Assert.assertEquals(PLACA_VEHICULO_SIN_RESTRICCION, registroAlmacenado.getVehiculo().getPlaca());
		Assert.assertEquals(TipoVehiculoEnum.CARRO, registroAlmacenado.getVehiculo().getTipo());
	}

	@Test
	public void noPermitirRegistrarMotoSinCupoTest() {
		// arrange
		VehiculoDto vehiculoDto = VehiculoTestBuilder.defaultValues().conPlaca(PLACA_VEHICULO_SIN_RESTRICCION)
				.conCilindraje(CILINDRAJE_MOTO).conTipo(TipoVehiculoEnum.MOTO).buildDto();

		when(libretaVigilante.contarVehiculosEstacionados(TipoVehiculoEnum.CARRO))
				.thenReturn(ReglasEstacionamientoMoto.CUPO_MOTOS_PARQUEADERO);

		when(reglasEstacionamiento.validarCupo(TipoVehiculoEnum.MOTO, ReglasEstacionamientoMoto.CUPO_MOTOS_PARQUEADERO))
				.thenReturn(Boolean.FALSE);

		// act
		try {
			serviciosDelVigilante.registrarIngreso(vehiculoDto);
		} catch (RegistroException e) {
			Assert.assertEquals(ServiciosDelVigilanteImpl.MENSAJE_ERROR_NO_HAY_CUPO, e.getMessage());
			return;
		}

		// assert
		fail();
	}

	@Test
	public void noPermitirRegistrarCarroSinCupoTest() {
		// arrange
		VehiculoDto vehiculoDto = VehiculoTestBuilder.defaultValues().conPlaca(PLACA_VEHICULO_SIN_RESTRICCION)
				.conTipo(TipoVehiculoEnum.CARRO).buildDto();

		when(libretaVigilante.contarVehiculosEstacionados(TipoVehiculoEnum.CARRO))
				.thenReturn(ReglasEstacionamientoCarro.CUPO_CARROS_PARQUEADERO);

		when(reglasEstacionamiento.validarCupo(TipoVehiculoEnum.CARRO, ReglasEstacionamientoCarro.CUPO_CARROS_PARQUEADERO))
				.thenReturn(Boolean.FALSE);

		// act
		try {
			serviciosDelVigilante.registrarIngreso(vehiculoDto);
		} catch (RegistroException e) {
			Assert.assertEquals(ServiciosDelVigilanteImpl.MENSAJE_ERROR_NO_HAY_CUPO, e.getMessage());
			return;
		}

		// assert
		fail();
	}
	
	@Test
	public void noPermitirRegistrarVehiculoConRestriccionTest() {
		// arrange
		VehiculoDto vehiculoDto = VehiculoTestBuilder.defaultValues().conPlaca(PLACA_VEHICULO_CON_RESTRICCION)
				.conCilindraje(CILINDRAJE_MOTO).conTipo(TipoVehiculoEnum.MOTO).buildDto();
		
		when(calendarioVigilante.dayWeekFromDate(any())).thenReturn(DayOfWeek.THURSDAY);
		
		// act
		try {
			serviciosDelVigilante.registrarIngreso(vehiculoDto);
		} catch (RegistroException e) {
			Assert.assertEquals(ServiciosDelVigilanteImpl.MENSAJE_ERROR_NO_INGRESO_FIN_SEMANA, e.getMessage());
			return;
		}
		
		// assert
		fail();
	}

}
