package pe.com.starcom.controller;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.ProviderNotFoundException;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Component;

import pe.com.starcom.dao.UsuarioDao;

@Component("loginController")
@Scope("session")
public class LoginController implements PhaseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory
			.getLogger(LoginController.class);

	@Autowired
	private UsuarioDao usuarioDao;

	private String username = "";
	private String clave = "";

	public LoginController() {
		username = "";
		clave = "";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	private String rpHash(String value) {
		int hash = 5381;
		value = value.toUpperCase();
		for (int i = 0; i < value.length(); i++) {
			hash = ((hash << 5) + hash) + value.charAt(i);
		}
		return String.valueOf(hash);
	}

	public String login() throws ServletException, IOException {
		logger.info("INICIAR SESION");

		if (!validarLogin())
			return "login.jsf";

		String defaultRealHash = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap()
				.get("defaultRealHash");
		String defaultReal = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap()
				.get("defaultReal");

		if (rpHash(defaultReal).equals(defaultRealHash)) {
			ExternalContext context = FacesContext.getCurrentInstance()
					.getExternalContext();

			RequestDispatcher dispatcher = ((ServletRequest) context
					.getRequest())
					.getRequestDispatcher("/j_spring_security_check");

			dispatcher.forward((ServletRequest) context.getRequest(),
					(ServletResponse) context.getResponse());

			FacesContext.getCurrentInstance().responseComplete();
			// It's OK to return null here because Faces is just going to exit.
			return null;
		} else {
			FacesContext
					.getCurrentInstance()
					.addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_WARN,
									"ADVERTENCIA",
									"El valor que ha ingresado para el Captcha es incorrecto"));
			return "login.jsf";
		}

	}

	public boolean validarLogin() {

		if (username.equalsIgnoreCase("") || username.length() == 0) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "ADVERTENCIA",
							"Ingrese datos de acceso"));
			return false;
		}

		if (clave.equalsIgnoreCase("") || username.length() == 0) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "ADVERTENCIA",
							"Ingrese datos de acceso"));
			return false;
		}

		return true;
	}

	public void afterPhase(PhaseEvent event) {
		// TODO Auto-generated method stub
	}

	public void beforePhase(PhaseEvent event) {

		Exception e = (Exception) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap()
				.get(WebAttributes.AUTHENTICATION_EXCEPTION);

		if (e instanceof BadCredentialsException) {

			FacesContext.getCurrentInstance().getExternalContext()
					.getSessionMap()
					.put(WebAttributes.AUTHENTICATION_EXCEPTION, null);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "ADVERTENCIA",
							"Datos de acceso incorretos"));
		}

		if (e instanceof ProviderNotFoundException) {

			FacesContext.getCurrentInstance().getExternalContext()
					.getSessionMap()
					.put(WebAttributes.AUTHENTICATION_EXCEPTION, null);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "ADVERTENCIA",
							"Datos de acceso incorretos"));
		}

	}

	public PhaseId getPhaseId() {
		// TODO Auto-generated method stub
		return PhaseId.RENDER_RESPONSE;
	}

}
