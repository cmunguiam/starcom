package pe.com.starcom.daoImpl;

import pe.com.starcom.dao.RolDao;
import pe.com.starcom.model.Rol;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

//@Service
@Repository
public class RolDaoImpl implements RolDao {
	@PersistenceContext
	private EntityManager em;

	public Rol find(Long id) {
		return em.find(Rol.class, id);
	}

	@Transactional
	public Rol save(Rol obj) {
		if (obj.getIdRol() == null) {
			em.persist(obj);
			return obj;
		} else {
			return em.merge(obj);
		}
	}

	@SuppressWarnings("unchecked")
	public List<Rol> getListaRol() {
		Query q = (Query) em.createQuery("select r from Rol r order by r.nombre asc");
		// q.setParameter("estado", "A");
		try {
			return q.getResultList();
		} catch (Exception as) {
			as.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Rol> getListaRolByNombre(String nombre) {
		Query q = (Query) em
				.createQuery("select r from Rol r where r.nombre = :nombre");
		q.setParameter("nombre", nombre);
		try {
			return q.getResultList();
		} catch (Exception as) {
			as.printStackTrace();
			return null;
		}
	}

	public List<SelectItem> getComboRol() {

		List<SelectItem> listaCombo = new ArrayList<SelectItem>();
		List<Rol> lista = new ArrayList<Rol>();
		try {
			lista = getListaRol();
		} catch (Exception as) {
			as.printStackTrace();
			SelectItem fila = new SelectItem(0L, "Seleccione rol");
			listaCombo.add(fila);
			return listaCombo;
		}
		SelectItem fila = new SelectItem(0L, "Seleccione rol");
		listaCombo.add(fila);

		for (Rol tipo : lista) {
			fila = new SelectItem(tipo.getIdRol(), tipo.getNombre());
			listaCombo.add(fila);
		}
		return listaCombo;
	}

	public Long getIdMax() {
		try {
			return (Long) em.createQuery("select max(r.idRol) from Rol r")
					.getSingleResult() + 1L;
		} catch (Exception e) {

		}
		return 1L;
	}

}
