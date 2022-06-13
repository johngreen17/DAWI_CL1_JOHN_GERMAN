package com.german.hibernate;

import java.util.List;

import com.german.varas.dao.LibroDao;
import com.german.varas.dao.TemaDao;
import com.german.varas.model.Libro;
import com.german.varas.model.Tema;

public class TestCrud {

	public TestCrud() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		TemaDao daotem = new TemaDao();
		Tema tem1 = new Tema("Drama");
		Tema tem2 = new Tema("Comedia");
		Tema tem3 = new Tema("Suspenso");
		
		
		daotem.guardarTema(tem1);
		daotem.guardarTema(tem2);
		daotem.guardarTema(tem3);
		
		
		List<Tema> listarTemas = daotem.obtTodosTemas();
		listarTemas.forEach(tem->System.out.println(tem.getIdtema()+"-"+tem.getNombre()));
		
		
		LibroDao daolib = new LibroDao();
		Libro lib1 = new Libro("El Código da Vinci", 50.0, 60, "Dan Brow");
		Libro lib2 = new Libro("El Alquimista", 60.0, 50, "Paul Coelho");
		Libro lib3 = new Libro("Bajo la misma Estrella", 40.0, 50, "John Green");
		
		daolib.guardarLibro(lib1);
		daolib.guardarLibro(lib2);
		daolib.guardarLibro(lib3);
		
		
		List<Libro> listarLibros = daolib.obtTodosLibros();
		listarLibros.forEach(lib->System.out.println(lib.getIdlibro()+"-"+lib.getTitulo()));

		
		
		lib1.setTitulo("Buscando a Alaska");
		daolib.actualizarLibro(lib1);
		
		lib2.setTitulo("Ciudades de Papel");
		daolib.actualizarLibro(lib2);
		
		lib3.setTitulo("Pinocho");
		daolib.actualizarLibro(lib3);
		
		
		listarLibros = daolib.obtTodosLibros();
		listarLibros.forEach(lib->System.out.println(lib.getIdlibro()+"-"+lib.getTitulo()));
		
		
		daolib.eliminarLibro(lib2.getIdlibro());
		
		
		listarLibros = daolib.obtTodosLibros();
		listarLibros.forEach(lib->System.out.println(lib.getIdlibro()+"-"+lib.getTitulo()));
		
		

	}

}
