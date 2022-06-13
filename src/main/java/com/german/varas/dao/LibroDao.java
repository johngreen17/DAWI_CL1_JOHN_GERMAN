package com.german.varas.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.german.varas.model.Libro;
import com.german.varas.util.HibernateUtil;

//import cl1.hibernate.model.Libro;
//import cl1.hibernate.util.HibernateUtil;

public class LibroDao {

	public LibroDao() {
		// TODO Auto-generated constructor stub
		
		
	}
	
	
	public void guardarLibro(Libro libro) {
		Transaction transaction = null;
		
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			
			transaction = session.beginTransaction();
			session.save(libro);
			transaction.commit();
			
		} catch (Exception e) {
			// TODO: handle exception
			
			if(transaction!=null) {
				transaction.rollback();
			}
		}
	}
	
	
	
	public List<Libro> obtTodosLibros(){
		Transaction transaction = null;
		List<Libro> listaLibros = null;
		
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			
			transaction = session.beginTransaction();

			
			listaLibros = session.createQuery("from Libro").list();
			transaction.commit();
			
		} catch (Exception e) {
			// TODO: handle exception
			
			if(transaction!=null) {
				transaction.rollback();
			}
		}
		
		return listaLibros;
	}
	
	
	
	public Libro obtLibroPorId(int id) {
		Transaction transaction = null;
		Libro libro = null;
				
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			
			transaction = session.beginTransaction();
				
			
			libro = session.get(Libro.class, id);
			transaction.commit();
					
		} catch (Exception e) {
			// TODO: handle exception
					
			if(transaction!=null) {
				transaction.rollback();
			}
		}
		
		return libro;
	}
	
	
	
	public void actualizarLibro(Libro libro) {
		Transaction transaction = null;
			
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			
			transaction = session.beginTransaction();
			session.saveOrUpdate(libro);
			transaction.commit();
				
		} catch (Exception e) {
			// TODO: handle exception
				
			if(transaction!=null) {
				transaction.rollback();
			}
		}
	}
	
	
	
	public void eliminarLibro(int id) {
		Transaction transaction = null;
		Libro libro = null;
			
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			
			transaction = session.beginTransaction();
			
			
			libro = session.get(Libro.class, id);
			session.delete(libro);
			transaction.commit();
				
		} catch (Exception e) {
			// TODO: handle exception
				
			if(transaction!=null) {
				transaction.rollback();
			}
		}
	}

}