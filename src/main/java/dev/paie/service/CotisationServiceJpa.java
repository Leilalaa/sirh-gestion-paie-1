package dev.paie.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.paie.entite.Cotisation;

@Service
@Transactional
public class CotisationServiceJpa implements CotisationService {

	@PersistenceContext
	private EntityManager em;

	@Override
	public void sauvegarder(Cotisation nouvelleCotisation) {
		em.persist(nouvelleCotisation);
	}

	@Override
	public void mettreAJour(Cotisation cotisation) {
		Cotisation c = em.find(Cotisation.class, cotisation.getId());
		c.setCode(cotisation.getCode());
		c.setLibelle(cotisation.getLibelle());
		c.setTauxPatronal(cotisation.getTauxPatronal());
		c.setTauxSalarial(cotisation.getTauxSalarial());
	}

	@Override
	public List<Cotisation> lister() {
		List<Cotisation> cotisations = new ArrayList<Cotisation>();
		TypedQuery<Cotisation> query = em.createQuery("SELECT c FROM Cotisation c", Cotisation.class);
		for (Cotisation c : query.getResultList()) {
			cotisations.add(c);
		}
		return cotisations;
	}

}
