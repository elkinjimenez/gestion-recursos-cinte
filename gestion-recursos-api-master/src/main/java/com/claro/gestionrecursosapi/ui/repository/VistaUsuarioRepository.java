package com.claro.gestionrecursosapi.ui.repository;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;

import org.hibernate.annotations.QueryHints;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class VistaUsuarioRepository<T> {
	
	@Autowired
	EntityManager em;
	
	@Value("${spring.datasource.url}")
	private String BDUrl;
	@Value("${spring.datasource.username}")
	private String BDUsuario;
	@Value("${spring.datasource.password}")
	private String BDClave;
	
	/*public ResultSet findAll(String nombrevista) {
		
		
		/*Query q = em.createNativeQuery("SELECT * FROM " + nombrevista);
		List<Tuple> resultado = q.getResultList();
		return resultado;*/
		
		/*************************** Se debe validar que sólo se soliciten nombre de vistas de usuario permitidas ***************************/
		/*ResultSet rs = null;
		try {
			Connection cn = DriverManager.getConnection(BDUrl, BDUsuario, BDClave);
			PreparedStatement ps = cn.prepareStatement("SELECT * FROM " + nombrevista);		
	        rs = ps.executeQuery();
		} catch (Exception e) {
			System.out.println("************************************ ERROR: " + e.getMessage());
		}
		
		return rs;
	}*/
	
	 public List<Object[]> findAll(String nombreVista) {
	    /* CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
	     CriteriaQuery<T> criteriaQuery = cb.createQuery(type);
	     Root<T> root = criteriaQuery.from(type);
	     criteriaQuery.select(root);*/
	    /*List<PruebaDTO> list= em.createNativeQuery("select id as id from VIEW_TAREA_ESTADO")
	    		 .unwrap(org.hibernate.query.Query.class ).setResultTransformer(Transformers.aliasToBean( PruebaDTO.class ) )
	    		 .getResultList();*/
		 Query list= em.createNativeQuery("select * from "+ nombreVista);
	     return list.getResultList();
	  }

	
	
}
