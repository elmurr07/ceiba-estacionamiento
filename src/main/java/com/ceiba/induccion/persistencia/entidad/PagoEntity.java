package com.ceiba.induccion.persistencia.entidad;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity(name = "pago")
public class PagoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;

	@Column(nullable = false)
	private double valor;

	@OneToOne(optional = false)
	@JoinColumn(name = "registro")
	private RegistroEntity registroEntity;

	@Column(nullable = false)
	private String usuario;

	@Column(nullable = false)
	private Date fecha;

	public PagoEntity(double valor, RegistroEntity registroEntity, String usuario, Date fecha) {
		super();
		this.valor = valor;
		this.registroEntity = registroEntity;
		this.usuario = usuario;
		this.fecha = fecha;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public RegistroEntity getRegistroEntity() {
		return registroEntity;
	}

	public void setRegistroEntity(RegistroEntity registroEntity) {
		this.registroEntity = registroEntity;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

}
