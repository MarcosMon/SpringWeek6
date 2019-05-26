package org.formacio.repositori;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.formacio.domain.Persona;
import org.springframework.stereotype.Repository;

@Repository
public class InformesCens {
	@PersistenceContext
	EntityManager em = null;
	/**
	 * Retorna la llista de persones que viuen al municipi indicat
	 */
	public List<Persona> habitantsMunicipi(String municipi) {
		

		TypedQuery<Persona> habitantes = em.createQuery("select persona from Persona persona where persona.municipi.nom = :municipi ", Persona.class);
		habitantes.setParameter("municipi", municipi);
		List<Persona> resultat = habitantes.getResultList();
		return  resultat;
	}

	/**
	 * Retorna el nombre d'habitants de la illa
	 */
	public int nombreHabitantsIlla(String illa) {
		TypedQuery<Persona> habitantes = em.createQuery("select persona from Persona persona where persona.municipi.illa.nom = :illa ", Persona.class);
		habitantes.setParameter("illa", illa);
		List<Persona> resultat = habitantes.getResultList();
		return resultat.size();
	}
	
	/**
	 * Retorna el nombre d'habitants del municipi que son menors d'edat
	 */
	public int nombreHabitantsMenorsEdat(String municipi) {
		TypedQuery<Persona> habitantes = em.createQuery("select persona from Persona persona where persona.municipi.nom = :municipi AND persona.edat < 18 ", Persona.class);
		habitantes.setParameter("municipi", municipi);
		List<Persona> resultat = habitantes.getResultList();
		return resultat.size();
	}

	/**
	 * Retorna la llista de persones que no tenen informat de quin municipi son
	 */
	public List<Persona> llistaPersonesSenseMunicipi() {
		return null;
    }

	/**
	 * Retorna la llista de noms de persones d'una illa ordenats per l'edat
	 * siii, ja ho se ..., no hem vist com ordenar, pero emprau order by i la vostra intuicio ;-)
	 */
	public List<String> llistaNomsPersonesOrdenatPerEdat(String illa) {
		return null;
    }
	
}
