package pe.com.starcom.controller;

import pe.com.starcom.dao.PermisoDao;
import pe.com.starcom.model.Permiso;
import pe.com.starcom.model.Usuario;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 
 * @author Hever Pumallihua
 */

@Component("contenidoController")
@Scope("session")
public class ContenidoController implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory
			.getLogger(ContenidoController.class);

	@Autowired
	private LoginController loginController;
	@Autowired
	private PermisoDao permisoDao;

	private String includedPage = "";
	private Usuario userSesion = null;

	private List<Permiso> listaPermisoAdm = new ArrayList<Permiso>();
	private List<Permiso> listaPermisoOpe = new ArrayList<Permiso>();
	private List<Permiso> listaPermisoMon = new ArrayList<Permiso>();
	private List<Permiso> listaPermisoRep = new ArrayList<Permiso>();

	public ContenidoController() {
		includedPage = "contenido/bienvenido.xhtml";
	}

	@PostConstruct
	public void init() {
		validarSesion();
		getPermisoUsuario();
	}

	public void getPermisoUsuario() {
		listaPermisoAdm = permisoDao.getListaPermiso(1L, userSesion.getRol()
				.getIdRol());
		listaPermisoOpe = permisoDao.getListaPermiso(2L, userSesion.getRol()
				.getIdRol());
		listaPermisoMon = permisoDao.getListaPermiso(3L, userSesion.getRol()
				.getIdRol());
		listaPermisoRep = permisoDao.getListaPermiso(4L, userSesion.getRol()
				.getIdRol());
	}

	public String getIncludedPage() {
		validarSesion();
		return includedPage;
	}

	public void setIncludedPage(String includedPage) {
		eliminarSesion(includedPage);
		this.includedPage = includedPage;
	}

	public void validarSesion() {
		FacesContext context = FacesContext.getCurrentInstance();

		userSesion = (Usuario) context.getExternalContext().getSessionMap()
				.get(loginController.getUsername());

		if (userSesion == null) {
			String ctxPath = context.getExternalContext()
					.getRequestContextPath();
			try {
				context.getExternalContext().redirect(ctxPath + "/login.jsf");
			} catch (Exception e) {
				// e.printStackTrace();
			}
		}
	}

	public void eliminarSesion(String nombre) {
		try {
			String nombreControl = "";
			if (nombre.equalsIgnoreCase("contenido/atenderFlujo.xhtml")) {
				nombreControl = "atenderFlujoController";
			} else if (nombre.equalsIgnoreCase("contenido/bandeja.xhtml")) {
				nombreControl = "bandejaController";
			} else if (nombre
					.equalsIgnoreCase("contenido/bandejaDetalleFlujo.xhtml")) {
				nombreControl = "detalleBandejaController";
			} else if (nombre.equalsIgnoreCase("contenido/flujo.xhtml")) {
				nombreControl = "flujoController";
			} else if (nombre.equalsIgnoreCase("contenido/monitoreo.xhtml")) {
				nombreControl = "monitoreoFlujoController";
			} else if (nombre
					.equalsIgnoreCase("contenido/requerimientoActividad.xhtml")) {
				nombreControl = "reqActController";
			} else if (nombre.equalsIgnoreCase("contenido/rol.xhtml")) {
				nombreControl = "rolController";
			} else if (nombre.equalsIgnoreCase("contenido/terminarFlujo.xhtml")) {
				nombreControl = "terminarFlujoController";
			} else if (nombre.equalsIgnoreCase("contenido/tipoDocumento.xhtml")) {
				nombreControl = "tipoDocumentoController";
			} else if (nombre.equalsIgnoreCase("contenido/usuario.xhtml")) {
				nombreControl = "usuarioController";
			}

			if (nombreControl.length() > 0) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.getExternalContext().getSessionMap()
						.remove(nombreControl);
			}
		} catch (Exception e) {
			System.out.println("Error al eliminar sesión");
		}
	}

	public Usuario getUserSesion() {
		return userSesion;
	}

	public void setUserSesion(Usuario userSesion) {
		this.userSesion = userSesion;
	}

	public List<Permiso> getListaPermisoAdm() {
		return listaPermisoAdm;
	}

	public void setListaPermisoAdm(List<Permiso> listaPermisoAdm) {
		this.listaPermisoAdm = listaPermisoAdm;
	}

	public List<Permiso> getListaPermisoOpe() {
		return listaPermisoOpe;
	}

	public void setListaPermisoOpe(List<Permiso> listaPermisoOpe) {
		this.listaPermisoOpe = listaPermisoOpe;
	}

	public List<Permiso> getListaPermisoMon() {
		return listaPermisoMon;
	}

	public void setListaPermisoMon(List<Permiso> listaPermisoMon) {
		this.listaPermisoMon = listaPermisoMon;
	}

	public List<Permiso> getListaPermisoRep() {
		return listaPermisoRep;
	}

	public void setListaPermisoRep(List<Permiso> listaPermisoRep) {
		this.listaPermisoRep = listaPermisoRep;
	}

}
