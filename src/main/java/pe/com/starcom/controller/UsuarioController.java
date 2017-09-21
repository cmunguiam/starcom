package pe.com.starcom.controller;

import pe.com.starcom.dao.RolDao;
import pe.com.starcom.dao.UsuarioDao;
import pe.com.starcom.model.Usuario;
import pe.com.starcom.util.Seguridad;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("usuarioController")
@Scope("session")
public class UsuarioController {

	private static final Logger logger = LoggerFactory
			.getLogger(UsuarioController.class);

	@Autowired
	private LoginController loginController;
	@Autowired
	private UsuarioDao usuarioDao;
	@Autowired
	private RolDao rolDao;

	private Usuario nuevo = new Usuario();
	private Usuario modifica = new Usuario();

	private List<Usuario> list = null;
	private List<SelectItem> comboRol = new ArrayList<SelectItem>();
	private List<SelectItem> comboEstado = new ArrayList<SelectItem>();
	private Long rolSelec = 0L;
	private Long rolSelecM = 0L;
	private String confirmarClave = "";

	private Usuario userSesion = null;

	private List<Usuario> filteredUser;

	private ResourceBundle bundle = null;
	private FacesContext context = null;

	public UsuarioController() {

	}

	public Long getRolSelecM() {
		return rolSelecM;
	}

	public void setRolSelecM(Long rolSelecM) {
		this.rolSelecM = rolSelecM;
	}

	public String getConfirmarClave() {
		return confirmarClave;
	}

	public void setConfirmarClave(String confirmarClave) {
		this.confirmarClave = confirmarClave;
	}

	public List<Usuario> getFilteredUser() {
		return filteredUser;
	}

	public void setFilteredUser(List<Usuario> filteredUser) {
		this.filteredUser = filteredUser;
	}

	public Long getRolSelec() {
		return rolSelec;
	}

	public void setRolSelec(Long rolSelec) {
		this.rolSelec = rolSelec;
	}

	public Usuario getModifica() {
		return modifica;
	}

	public void setModifica(Usuario modifica) {
		this.modifica = modifica;
	}

	public Usuario getNuevo() {
		return nuevo;
	}

	public void setNuevo(Usuario nuevo) {
		this.nuevo = nuevo;
	}

	public List<Usuario> getList() {
		list = usuarioDao.getListaUsuario();
		return list;
	}

	public void setList(List<Usuario> list) {
		this.list = list;
	}

	public List<SelectItem> getComboRol() {
		comboRol = rolDao.getComboRol();
		return comboRol;
	}

	public void setComboRol(List<SelectItem> comboRol) {
		this.comboRol = comboRol;
	}

	public List<SelectItem> getComboEstado() {
		comboEstado = new ArrayList<SelectItem>();
		SelectItem item = null;
		item = new SelectItem("A", "Activo");
		comboEstado.add(item);
		item = new SelectItem("E", "Inactivo");
		comboEstado.add(item);

		return comboEstado;
	}

	public void setComboEstado(List<SelectItem> comboEstado) {
		this.comboEstado = comboEstado;
	}

	public void guardar() {
		context = FacesContext.getCurrentInstance();
		bundle = context.getApplication()
				.getResourceBundle(context, "messages");

		validarSesion();

		if (!validarDatos())
			return;

		try {

			nuevo.setIdUsuario(usuarioDao.getIdMax());
			nuevo.setRol(rolDao.find(rolSelec));		
			nuevo.setPassword(Seguridad.encrypt(nuevo.getClave()));
			nuevo.setEstado("A");

			usuarioDao.save(nuevo);

			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "EXITO",
							bundle.getString("msgRegistrar")));
			limpiar();
		} catch (Exception e) {
			logger.info("ERRROR AL REGISTRAR USUARIO");
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR",
							bundle.getString("msgRegistrarError")));
		}
	}

	public void modificar() {

		context = FacesContext.getCurrentInstance();
		bundle = context.getApplication()
				.getResourceBundle(context, "messages");

		validarSesion();

		try {

			validarModificar();
			
			usuarioDao.save(modifica);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "EXITO",
							bundle.getString("msgModificar")));

			limpiar();
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR",
							bundle.getString("msgModificarError")));
		}
	}

	public void validarModificar() {

		Usuario temp = new Usuario();
		temp = usuarioDao.find(modifica.getIdUsuario());

		if (modifica.getNombre().trim().equalsIgnoreCase(""))
			modifica.setNombre(temp.getNombre());
		if (modifica.getApPaterno().trim().equalsIgnoreCase(""))
			modifica.setApPaterno(temp.getApPaterno());
		if (modifica.getApMaterno().trim().equalsIgnoreCase(""))
			modifica.setApMaterno(temp.getApMaterno().trim());
		if (validarUsuario(modifica.getUsuario().trim()))
			modifica.setUsuario(temp.getUsuario());
//		if (!(validarClave(modifica.getClave().trim())))
//			modifica.setClave(temp.getClave());
		if (rolSelecM == 0L)
			modifica.setRol(temp.getRol());
		else
			modifica.setRol(rolDao.find(rolSelecM));
	}

	public void limpiar() {
		nuevo = new Usuario();
		modifica = new Usuario();
		rolSelec = 0L;
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

	public boolean validarDatos() {
		context = FacesContext.getCurrentInstance();
		bundle = context.getApplication()
				.getResourceBundle(context, "messages");
		String txtObligatorio = bundle.getString("msgTxtObligatorio");

		if (nuevo.getNombre().trim().equalsIgnoreCase("")) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "ADVERTENCIA",
							txtObligatorio));
			return false;
		}
		if (nuevo.getApPaterno().trim().equalsIgnoreCase("")) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "ADVERTENCIA",
							txtObligatorio));
			return false;
		}

		if (nuevo.getUsuario().trim().equalsIgnoreCase("")) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "ADVERTENCIA",
							txtObligatorio));
			return false;
		}

		if (nuevo.getClave().trim().equalsIgnoreCase("")) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "ADVERTENCIA",
							txtObligatorio));
			return false;
		}

		if (rolSelec.equals(0L)) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "ADVERTENCIA",
							bundle.getString("msgSelecRol")));
			return false;
		}

		if (validarUsuario(nuevo.getUsuario())) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "ADVERTENCIA",
							bundle.getString("msgUsernameExiste")));
			return false;
		}

		if (!nuevo.getClave().trim().equalsIgnoreCase(confirmarClave)) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "ADVERTENCIA",
							bundle.getString("msgConfirmarClave")));
			return false;
		}

		if (validarClave()) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "ADVERTENCIA",
							bundle.getString("msgClaveError")));
			return false;
		}

		return true;
	}

	public boolean validarUsuario(String username) {
		Usuario user = null;
		user = usuarioDao.findUsuarioByUsername(username);

		if (user != null) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * public boolean validarUsuario(Usuario modificar) { Usuario user = null;
	 * user = usuarioDao.findUsuarioByUsername(modificar.getUsuario());
	 * 
	 * if (user != null) { return true; } else { return false; } }
	 */

	public boolean validarClave() {

		String numero[] = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
		String letrasMa[] = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
				"K", "L", "M", "N", "Ñ", "O", "P", "Q", "R", "S", "T", "U",
				"V", "W", "X", "Y", "Z" };
		String letrasMin[] = { "a", "b", "c", "d", "e", "f", "g", "h", "i",
				"j", "k", "l", "m", "n", "ñ", "o", "p", "q", "r", "s", "t",
				"u", "v", "w", "x", "y", "z" };
		String epeciales[] = { "☺", "☻", "♥", "♦", "♣", "♠", "•", "◘", "○",
				"◙", "♂", "♀", "♪", "♫", "☼", "►", "◄", "↕", "‼", "¶", "§",
				"▬", "↨", "↑", "↓", "→", "←", "∟", "↔", "▲", "▼", "!", "\"",
				"#", "$", "%", "'", "(", ")", "*", "+", ",", "-", ".", "/",
				":", ";", "<", "=", ">", "?", "@", "[", "\\", "]", "^", "_",
				"`", "{", "|", "}", "~", "⌂", "Ç", "ü", "é", "â", "ä", "à",
				"å", "ç", "ê", "ë", "è", "ï", "î", "ì", "Ä", "Å", "É", "æ",
				"Æ", "ô", "ö", "ò", "û", "û", "ù", "ÿ", "Ö", "Ü", "ø", "£",
				"Ø", "×", "ƒ", "á", "í", "ó", "ú", "ª", "º", "¿", "®", "¬",
				"½", "¼", "¡", "«", "»", "░", "▒", "▓", "│", "┤", "Á", "Â",
				"À", "©", "╣", "║", "╗", "╝", "¢", "¥", "┐", "└", "┴", "┬",
				"├", "─", "┼", "ã", "Ã", "╚", "╔", "╩", "╦", "╠", "═", "╬",
				"¤", "ð", "Ð", "Ê", "Ë", "È", "ı", "Í", "Î", "Ï", "┘", "┌",
				"█", "▄", "¦", "Ì", "▀", "Ó", "ß", "Ô", "Ò", "õ", "Õ", "µ",
				"þ", "Þ", "Ú", "Û", "Ù", "ý", "Ý", "¯", "´", "­", "±", "‗",
				"¾", "¶", "§", "¨", "·", "¹", "³", "²", "■", " " };

		boolean bnum = true;
		boolean blma = true;
		boolean blmi = true;
		boolean bles = true;
		boolean btam = false;

		if (nuevo.getClave().trim().length() == 8) {
			int k;
			for (k = 0; k < nuevo.getClave().length(); k++) {
				if (bnum)
					for (String num : numero) {

						if (("" + nuevo.getClave().charAt(k))
								.equalsIgnoreCase(num)) {
							bnum = false;
							break;
						}
					}
				if (blma)
					for (String lma : letrasMa) {

						if (("" + nuevo.getClave().charAt(k)).equals(lma)) {
							blma = false;
							break;
						}
					}

				if (blmi)
					for (String lmi : letrasMin) {

						if (("" + nuevo.getClave().charAt(k)).equals(lmi)) {
							blmi = false;
							break;
						}
					}
				if (bles)
					for (String le : epeciales) {

						if (("" + nuevo.getClave().charAt(k)).equals(le)) {
							bles = false;
							break;
						}
					}

			}
		} else {
			btam = true;

		}

		if (bnum == false && blma == false && blmi == false && bles == false
				&& btam == false)
			return false;

		return true;
	}

	public boolean validarClave(String Clave) {

		String numero[] = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
		String letrasMa[] = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
				"K", "L", "M", "N", "Ñ", "O", "P", "Q", "R", "S", "T", "U",
				"V", "W", "X", "Y", "Z" };
		String letrasMin[] = { "a", "b", "c", "d", "e", "f", "g", "h", "i",
				"j", "k", "l", "m", "n", "ñ", "o", "p", "q", "r", "s", "t",
				"u", "v", "w", "x", "y", "z" };
		String epeciales[] = { "☺", "☻", "♥", "♦", "♣", "♠", "•", "◘", "○",
				"◙", "♂", "♀", "♪", "♫", "☼", "►", "◄", "↕", "‼", "¶", "§",
				"▬", "↨", "↑", "↓", "→", "←", "∟", "↔", "▲", "▼", "!", "\"",
				"#", "$", "%", "'", "(", ")", "*", "+", ",", "-", ".", "/",
				":", ";", "<", "=", ">", "?", "@", "[", "\\", "]", "^", "_",
				"`", "{", "|", "}", "~", "⌂", "Ç", "ü", "é", "â", "ä", "à",
				"å", "ç", "ê", "ë", "è", "ï", "î", "ì", "Ä", "Å", "É", "æ",
				"Æ", "ô", "ö", "ò", "û", "û", "ù", "ÿ", "Ö", "Ü", "ø", "£",
				"Ø", "×", "ƒ", "á", "í", "ó", "ú", "ª", "º", "¿", "®", "¬",
				"½", "¼", "¡", "«", "»", "░", "▒", "▓", "│", "┤", "Á", "Â",
				"À", "©", "╣", "║", "╗", "╝", "¢", "¥", "┐", "└", "┴", "┬",
				"├", "─", "┼", "ã", "Ã", "╚", "╔", "╩", "╦", "╠", "═", "╬",
				"¤", "ð", "Ð", "Ê", "Ë", "È", "ı", "Í", "Î", "Ï", "┘", "┌",
				"█", "▄", "¦", "Ì", "▀", "Ó", "ß", "Ô", "Ò", "õ", "Õ", "µ",
				"þ", "Þ", "Ú", "Û", "Ù", "ý", "Ý", "¯", "´", "­", "±", "‗",
				"¾", "¶", "§", "¨", "·", "¹", "³", "²", "■", " " };

		boolean bnum = true;
		boolean blma = true;
		boolean blmi = true;
		boolean bles = true;
		boolean btam = false;

		if (Clave.length() == 8) {
			int k;
			for (k = 0; k < Clave.length(); k++) {

				if (bnum)
					for (String num : numero) {

						if (("" + Clave.charAt(k)).equalsIgnoreCase(num)) {
							bnum = false;
							break;
						}
					}
				if (blma)
					for (String lma : letrasMa) {

						if (("" + Clave.charAt(k)).equals(lma)) {
							blma = false;
							break;
						}
					}

				if (blmi)
					for (String lmi : letrasMin) {

						if (("" + Clave.charAt(k)).equals(lmi)) {
							blmi = false;
							break;
						}
					}
				if (bles)
					for (String le : epeciales) {

						if (("" + Clave.charAt(k)).equals(le)) {
							bles = false;
							break;
						}
					}

			}
		} else {
			btam = true;

		}

		if (bnum == false && blma == false && blmi == false && bles == false
				&& btam == false)
			return false;

		return true;
	}

}
