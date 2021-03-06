package pe.com.starcom.security;

import pe.com.starcom.dao.UsuarioDao;
import pe.com.starcom.model.Usuario;
import pe.com.starcom.util.Constantes;
import pe.com.starcom.util.Seguridad;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;

//import org.springframework.security.core.authority.SimpleGrantedAuthority;

/**
 * 
 * @author
 */
public class MyAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private UsuarioDao usuarioDao;

	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {

		Authentication auth = null;
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();

		// List<Usuario> listUsuario =
		// usuarioService.getListByUsername(username);
		List<Usuario> listUsuario = usuarioDao.getListByUsername(username);

		for (Usuario usr : listUsuario) {
			String plainText = "";
			try {
				plainText = Seguridad.decrypt(usr.getPassword());
			} catch (Exception e) {
			}

			if (plainText.equalsIgnoreCase(password)) {

				List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();

				if (usr.getRol().getIdRol() == Constantes.ROLE_ADMIN)
					grantedAuths.add(new GrantedAuthorityImpl("ROLE_ADMIN"));
				if (usr.getRol().getIdRol() == Constantes.ROLE_PE)
					grantedAuths.add(new GrantedAuthorityImpl("ROLE_PE"));
				if (usr.getRol().getIdRol() == Constantes.ROLE_GDGP)
					grantedAuths.add(new GrantedAuthorityImpl("ROLE_GDGP"));
				if (usr.getRol().getIdRol() == Constantes.ROLE_SELEC)
					grantedAuths.add(new GrantedAuthorityImpl("ROLE_SELEC"));
				if (usr.getRol().getIdRol() == Constantes.ROLE_ASIG)
					grantedAuths.add(new GrantedAuthorityImpl("ROLE_ASIG"));
				if (usr.getRol().getIdRol() == Constantes.ROLE_OAJ)
					grantedAuths.add(new GrantedAuthorityImpl("ROLE_OAJ"));
				if (usr.getRol().getIdRol() == Constantes.ROLE_MyE)
					grantedAuths.add(new GrantedAuthorityImpl("ROLE_MyE"));
				if (usr.getRol().getIdRol() == Constantes.ROLE_GG)
					grantedAuths.add(new GrantedAuthorityImpl("ROLE_GG"));
				if (usr.getRol().getIdRol() == Constantes.ROLE_TI)
					grantedAuths.add(new GrantedAuthorityImpl("ROLE_TI"));
				if (usr.getRol().getIdRol() == Constantes.ROLE_LEG)
					grantedAuths.add(new GrantedAuthorityImpl("ROLE_LEG"));
				if (usr.getRol().getIdRol() == Constantes.ROLE_CONV)
					grantedAuths.add(new GrantedAuthorityImpl("ROLE_CONV"));
				if (usr.getRol().getIdRol() == Constantes.ROLE_LOGI)
					grantedAuths.add(new GrantedAuthorityImpl("ROLE_LOGI"));

				FacesContext ctx = FacesContext.getCurrentInstance();
				ctx.getExternalContext().getSessionMap()
						.put(usr.getUsuario(), usr);

				auth = new UsernamePasswordAuthenticationToken(username,
						password, grantedAuths);
				break;
			} else
				auth = null;
		}

		return auth;

	}

	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
