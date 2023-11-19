package org.ciberfarma.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="tb_productos")
public class Producto {

	@Id
	@Column(name="id_prod")
	private String codigo;
	
	@Column(name="des_prod")
	private String descripcion;
	
	@Column(name="stk_prod")
	private int stock;
	
	@Column(name="pre_prod")
	private double precio;
	
	//@Column(name="idcategoria")
	//private int idCategoria;
	
	@ManyToOne
	@JoinColumn(name="idcategoria")
	private Categoria categoria;
	
	@Column(name="est_prod")
	private int estado;
	
	@Column(name="idproveedor")
	private int idProveedor;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public int getIdProveedor() {
		return idProveedor;
	}

	public void setIdProveedor(int idProveedor) {
		this.idProveedor = idProveedor;
	}

	@Override
	public String toString() {
		return "Producto [codigo=" + codigo + ", descripcion=" + descripcion + ", stock=" + stock + ", precio=" + precio
				+ ", categoria=" + categoria + ", estado=" + estado + ", idProveedor=" + idProveedor + "]";
	}
	
	
}
