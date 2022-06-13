package com.german.varas.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.german.varas.model.Tema;
import com.german.varas.util.HibernateUtil;

public class TemaDao {

	public TemaDao() {
		// TODO Auto-generated constructor stub
	}

	
	
	public void guardarTema(Tema tema) {
		Transaction transaction = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.save(tema);
			transaction.commit();
			
		} catch (Exception e) {
			// TODO: handle exception
			
			if(transaction!=null) {
				transaction.rollback();
			}
		}
	}
	
	
	public List<Tema> obtTodosTemas() {
		Transaction transaction = null;
		List<Tema> listaTemas = null;
		
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			
			transaction = session.beginTransaction();
			
			
			listaTemas = session.createQuery("from Tema").list();
			transaction.commit();
				
		} catch (Exception e) {
			// TODO: handle exception
				
			if(transaction!=null) {
				transaction.rollback();
			}
		}
		
		return listaTemas;
	}
}