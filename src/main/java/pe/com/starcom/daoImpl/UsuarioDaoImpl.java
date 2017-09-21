package pe.com.starcom.daoImpl;

import pe.com.starcom.dao.UsuarioDao;
import pe.com.starcom.model.Usuario;
import pe.com.starcom.util.Constantes;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UsuarioDaoImpl implements UsuarioDao {
	@PersistenceContext
	private EntityManager em;

	public Usuario find(Long id) {
		try {
			return em.find(Usuario.class, id);
		} catch (Exception e) {
			return null;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	@Transactional
	public Usuario save(Usuario obj) {
		try {
			if (obj.getIdUsuario() == null) {
				em.persist(obj);
				return obj;
			} else {
				return em.merge(obj);
			}
		} catch (Exception e) {
			return null;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	@SuppressWarnings("unchecked")
	public List<Usuario> getListaUsuario() {
		Query q = null;
		q = (Query) em
				.createQuery("select u from Usuario u order by u.apPaterno");
		// q.setParameter("estado", "A");

		try {
			return q.getResultList();
		} catch (Exception as) {
			System.out.println("LISTA USUARIO VACIO");
			return null;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public Usuario findUsuarioByUsername(String username) {
		Query q = null;

		q = (Query) em
				.createQuery("select u from Usuario u where u.usuario =:username and u.estado =:estado");
		q.setParameter("username", username);
		q.setParameter("estado", "A");

		try {
			return (Usuario) q.getSingleResult();

		} catch (Exception as) {
			System.out.println("CUENTA DE USUARIO NO EXISTE");
			return null;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public Long getIdMax() {
		try {
			return (Long) em.createQuery("select max(r.idUsuario) from Usuario r")
					.getSingleResult() + 1L;
		} catch (Exception e) {

		}
		return 1L;
	}

	public List<Usuario> getListByUsername(String username) {

		TypedQuery<Usuario> tq = em.createNamedQuery("Usuario.findByUsername",
				Usuario.class);

		tq.setParameter("username", username);
		tq.setParameter("estado", Constantes.ACTIVO);

		try {
			return tq.getResultList();
		} catch (Exception as) {
			return null;
		}
	}

	
}
