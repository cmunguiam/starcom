package pe.com.starcom.util;

import java.util.ResourceBundle;

import javax.faces.context.FacesContext;

/**
 * 
 * @author Hever Pumallihua
 */
public class Message {

	private FacesContext context = null;
	private ResourceBundle bundle = null;

	public Message() {
		super();
	}

	public String getMessage(String id) {
		String mensaje = "";
		context = FacesContext.getCurrentInstance();
		bundle = context.getApplication().getResourceBundle(context, "messages");
		try {
			mensaje = bundle.getString(id);
		} catch (Exception e) {
			e.printStackTrace();
			mensaje = "";
		}
		return mensaje;
	}

}
