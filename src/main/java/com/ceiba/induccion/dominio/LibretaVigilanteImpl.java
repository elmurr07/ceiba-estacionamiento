package com.ceiba.induccion.dominio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceiba.induccion.dominio.builder.PagoBuilder;
import com.ceiba.induccion.dominio.builder.RegistroBuilder;
import com.ceiba.induccion.dominio.builder.VehiculoBuilder;
import com.ceiba.induccion.dominio.dto.PagoDto;
import com.ceiba.induccion.dominio.dto.RegistroDto;
import com.ceiba.induccion.dominio.dto.VehiculoDto;
import com.ceiba.induccion.persistencia.entidad.PagoEntity;
import com.ceiba.induccion.persistencia.entidad.RegistroEntity;
import com.ceiba.induccion.persistencia.entidad.VehiculoEntity;
import com.ceiba.induccion.persistencia.repositorio.PagoRepositorio;
import com.ceiba.induccion.persistencia.repositorio.RegistroRepositorio;
import com.ceiba.induccion.persistencia.repositorio.VehiculoRepositorio;
import com.ceiba.induccion.utilidad.TipoVehiculoEnum;

@Service
public class LibretaVigilanteImpl implements LibretaVigilante {

	public static final String USUARIO_SISTEMA = "usuario_prueba";
	private static final int CERO_VEHICULOS_ENCONTRADOS = 0;
	
	@Autowired
	private VehiculoRepositorio vehiculoRepositorio;

	@Autowired
	private RegistroRepositorio registroRepositorio;
	
	@Autowired
	private PagoRepositorio pagoRepositorio;

	@Override
	public RegistroDto registrarIngresoVehiculo(VehiculoDto vehiculoDto) {
		VehiculoEntity vehiculoEntity = VehiculoBuilder.toEntity(vehiculoDto);
		RegistroEntity registroEntity = RegistroBuilder.toEntity(vehiculoEntity);

		vehiculoRepositorio.save(vehiculoEntity);
		registroRepositorio.save(registroEntity);
		return RegistroBuilder.toDto(registroEntity, vehiculoDto);
	}

	@Override
	public RegistroEntity registrarSalidaVehiculo(long idRegistro) {
		RegistroEntity registroEntity = consultarRegistro(idRegistro);
		if (registroEntity != null) {
			registroEntity.setFin(new Date());
			registroEntity = registroRepositorio.save(registroEntity);
		}
		return registroEntity;
	}

	@Override
	public RegistroEntity consultarRegistro(long idRegistro) {
		Optional<RegistroEntity> opcional = registroRepositorio.findById(idRegistro);
		return opcional.isPresent() ? opcional.get() : null;
	}

	@Override
	public int contarVehiculosEstacionados(TipoVehiculoEnum tipo) {
		return registroRepositorio.contarVehiculosEstacionados(tipo);
	}

	@Override
	public List<RegistroDto> listarVehiculosEstacionados() {
		List<RegistroEntity> registrosEncontrados = registroRepositorio.findByFinIsNull();
		List<RegistroDto> registrosDto = new ArrayList<>();
		if (registrosEncontrados != null) {
			for (RegistroEntity registroEntity : registrosEncontrados) {
				VehiculoDto vehiculoDto = VehiculoBuilder.toDto(registroEntity.getVehiculo());
				registrosDto.add(RegistroBuilder.toDto(registroEntity, vehiculoDto));
			}
		}
		return registrosDto;
	}

	@Override
	public boolean existeVehiculoEnEstacionamiento(String placa) {
		return registroRepositorio.contarVehiculoEstacionadoPlaca(placa) > CERO_VEHICULOS_ENCONTRADOS;
	}
	
	@Override
	public PagoDto registrarPago(RegistroEntity registroEntity, Double valor) {
		PagoDto pagoRetorno = null;
		if (registroEntity != null) {
			PagoEntity pagoEntity = new PagoEntity(valor, registroEntity, USUARIO_SISTEMA, new Date());
			pagoEntity = pagoRepositorio.save(pagoEntity);
			pagoRetorno = PagoBuilder.toDto(pagoEntity);
		}
		return pagoRetorno;
	}

}
