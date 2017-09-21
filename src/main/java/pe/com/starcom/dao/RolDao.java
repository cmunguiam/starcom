package pe.com.starcom.dao;

import java.util.List;

import javax.faces.model.SelectItem;

import pe.com.starcom.model.Rol;

public interface RolDao {
	
	public abstract Rol find(Long id);
	
	public abstract Rol save(Rol obj);
	
	public abstract List<Rol> getListaRol();
	
	public abstract List<Rol> getListaRolByNombre(String nombre);
	
	public abstract List<SelectItem> getComboRol();
	
	public abstract Long getIdMax();

}
