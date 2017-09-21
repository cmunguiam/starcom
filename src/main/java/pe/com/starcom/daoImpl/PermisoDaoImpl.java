package pe.com.starcom.daoImpl;

import pe.com.starcom.dao.PermisoDao;
import pe.com.starcom.model.ItemMenu;
import pe.com.starcom.model.Permiso;
import pe.com.starcom.model.Rol;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class PermisoDaoImpl implements PermisoDao {
	@PersistenceContext
	private EntityManager em;

	public Permiso find(Long id) {
		return em.find(Permiso.class, id);
	}

	@Transactional
	public Permiso save(Permiso obj) {
		if (obj.getIdPermiso() == null) {
			em.persist(obj);
			return obj;
		} else {
			return em.merge(obj);
		}
	}

	public List<Permiso> getListaPermiso() {

		TypedQuery<Permiso> tq = em.createNamedQuery(
				"Permiso.getAllListaPermiso", Permiso.class);

		try {
			return tq.getResultList();
		} catch (Exception as) {
			as.printStackTrace();
			return null;
		}
	}

	public List<Permiso> getListaPermiso(Long idRol) {

		TypedQuery<Permiso> tq = em.createNamedQuery(
				"Permiso.getListaPermisoRol", Permiso.class);

		tq.setParameter("idRol", idRol);
		try {
			return tq.getResultList();
		} catch (Exception as) {
			as.printStackTrace();
			return null;
		}
	}

	public List<Permiso> getListaPermiso(Long idMenu, Long idRol) {

		TypedQuery<Permiso> tq = em.createNamedQuery(
				"Permiso.getListaPermisoMenuAndRol", Permiso.class);
		tq.setParameter("idMenu", idMenu);
		tq.setParameter("idRol", idRol);
		tq.setParameter("estado", true);
		try {
			return tq.getResultList();
		} catch (Exception as) {
			as.printStackTrace();
			return null;
		}
	}

	@Transactional
	public void save(Rol obj, List<ItemMenu> listItemSelec,
			List<ItemMenu> listItemMenu) {

		em.persist(obj);

		Permiso permiso = null;
		for (ItemMenu row : listItemMenu) {
			permiso = new Permiso();

			permiso.setIdPermiso(getIdMax());
			permiso.setRol(obj);
			permiso.setItemMenu(row);
			// permiso.setFechaCreacion(new Date());
			// permiso.setFechaModificacion(new Date());
			// permiso.setUsuarioRegistra(obj.getUsuarioRegistra());
			// permiso.setUsuarioModifica(obj.getUsuarioRegistra());

			for (ItemMenu itemSelec : listItemSelec) {
				if (row.getIdItemMenu().equals(itemSelec.getIdItemMenu())) {
					permiso.setEstado(true);
					break;
				} else
					permiso.setEstado(false);
			}

			em.persist(permiso);
		}
	}

	public Long getIdMax() {
		try {
			return (Long) em.createQuery(
					"select max(p.idPermiso) from Permiso p").getSingleResult() + 1L;
		} catch (Exception e) {

		}
		return 1L;
	}

}
