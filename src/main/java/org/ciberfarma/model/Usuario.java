package org.ciberfarma.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;

@Entity
@Table(name="tb_usuarios")
public class Usuario {
	
	@Id
	@Column(name="cod_usua")
	private int codUsuario;
	
	@Column(name="nom_usua")
	private String nombres;
	
	@Column(name="ape_usua")
	private String apellidos;
	
	@Column(name="usr_usua")
	private String correo;
	
	@Column(name="cla_usua")
	private String clave;

	
	/*Getter and Setter*/
	public int getCodUsuario() {
		return codUsuario;
	}

	public void setCodUsuario(int codUsuario) {
		this.codUsuario = codUsuario;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}
}
