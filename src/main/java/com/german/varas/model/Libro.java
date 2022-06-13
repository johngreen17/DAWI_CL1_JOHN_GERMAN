package com.german.varas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="tbl_libros")
public class Libro {
	
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		@Column(name="ID")
		private int idlibro;
		
		@Column(name= "TITULO")
		private String titulo;
		
		public Tema getTema() {
			return tema;
		}

		public void setTema(Tema tema) {
			this.tema = tema;
		}

		@Column(name= "PRECIO")
		private Double precio;
		
		@Column(name= "CANTIDAD")
		private int cantEjemplares;
		
		@Column(name= "ORIGEN")
		private String origen;
		
		@JoinColumn(name="TEM_ID")
		@ManyToOne
		private Tema tema;

	public Libro() {
		// TODO Auto-generated constructor stub
	}

	public Libro(String titulo, Double precio, int cantEjemplares, String origen) {
		this.titulo = titulo;
		this.precio = precio;
		this.cantEjemplares = cantEjemplares;
		this.origen = origen;
	}
	
	public Libro(String titulo, Double precio, int cantEjemplares, String origen, String tema) {
		this.titulo = titulo;
		this.precio = precio;
		this.cantEjemplares = cantEjemplares;
		this.origen = origen;
		this.origen = origen;
	}

	public int getIdlibro() {
		return idlibro;
	}

	public void setIdlibro(int idlibro) {
		this.idlibro = idlibro;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public int getCantEjemplares() {
		return cantEjemplares;
	}

	public void setCantEjemplares(int cantEjemplares) {
		this.cantEjemplares = cantEjemplares;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	
}