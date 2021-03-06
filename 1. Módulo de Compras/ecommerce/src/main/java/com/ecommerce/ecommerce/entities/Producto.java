package com.ecommerce.ecommerce.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "producto")
public class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idProducto;
	
	@Column(name = "nombre")
	private String nombre;

	@Column(name = "descripcion")
	private String descripcion;

	@Column(name = "precio")
	private double precio;

	@Column(name = "imagen")
	private String imagen;

	@Column(name = "stock")
	private int stock;

	@Column(name = "cantidadVendida")
	private int cantidadVendida;

	@Column(name = "formaDePago")
	private String formaDePago;
	
	@OneToOne
	private Categoria categoria;
	
	@OneToOne
	private User vendedor;

	public Producto() {}

	public Producto(long idProducto, double precio, String nombre, String descripcion, String imagen, int stock,
			Categoria categoria, int cantidadVendida, String formaDePago, User vendedor) {
		super();
		this.idProducto = idProducto;
		this.precio = precio;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.imagen = imagen;
		this.stock = stock;
		this.categoria = categoria;
		this.cantidadVendida = cantidadVendida;
		this.formaDePago = formaDePago;
		this.vendedor = vendedor;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public long getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(long idProducto) {
		this.idProducto = idProducto;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public int getCantidadVendida() {
		return cantidadVendida;
	}

	public void setCantidadVendida(int cantidadVendida) {
		this.cantidadVendida = cantidadVendida;
	}

	public String getFormaDePago() {
		return formaDePago;
	}

	public void setFormaDePago(String formaDePago) {
		this.formaDePago = formaDePago;
	}

	public User getVendedor() {
		return vendedor;
	}

	public void setVendedor(User vendedor) {
		this.vendedor = vendedor;
	}

	@Override
	public String toString() {
		return "Producto [idProducto=" + idProducto + ", nombre=" + nombre + ", descripcion=" + descripcion
				+ ", precio=" + precio + ", imagen=" + imagen + ", stock=" + stock + ", cantidadVendida="
				+ cantidadVendida + ", formaDePago=" + formaDePago + ", categoria=" + categoria + ", vendedor="
				+ vendedor + "]";
	}

}