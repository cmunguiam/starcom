package pe.com.starcom.dao;

import java.util.List;

import pe.com.starcom.model.ItemMenu;
import pe.com.starcom.model.Permiso;
import pe.com.starcom.model.Rol;

public interface PermisoDao {
	
	public abstract Permiso find(Long id);
	
	public abstract Permiso save(Permiso obj);
	
	public abstract List<Permiso> getListaPermiso();
	
	public abstract List<Permiso> getListaPermiso(Long idRol);
	
	public abstract List<Permiso> getListaPermiso(Long idMenu, Long idRol);
	
	public abstract Long getIdMax();
	
	public abstract void save(Rol rol, List<ItemMenu> listItemSelec, List<ItemMenu> listItemMenu);

}
