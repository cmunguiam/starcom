package pe.com.starcom.dao;

import java.util.List;

import pe.com.starcom.model.Usuario;

public interface UsuarioDao {
	
	public abstract Usuario find(Long id);
	
	public abstract Usuario save(Usuario obj);
	
	public abstract List<Usuario> getListaUsuario();
	
	public abstract Usuario findUsuarioByUsername(String username); 
	
	public abstract Long getIdMax();

	public abstract List<Usuario> getListByUsername(String username);
	
}
