package com.example.demo.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Politico {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column
	private String nombre;
	
	@Column
	private String apellido;
	
	@Column
	private String cargo;
	@Column 
	private boolean isCorrupto;
	@Column
	private String cualidad;
	
	public Politico() {}

	public Politico(long id, String nombre, String apellido, String cargo, boolean isCorrupto, String cualidad) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.cargo = cargo;
		this.isCorrupto = isCorrupto;
		this.cualidad = cualidad;
	}

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public boolean isCorrupto() {
		return isCorrupto;
	}

	public void setCorrupto(boolean isCorrupto) {
		this.isCorrupto = isCorrupto;
	}

	public String getCualidad() {
		return cualidad;
	}

	public void setCualidad(String cualidad) {
		this.cualidad = cualidad;
	}
	
	
	
	
	
}
