package pe.com.starcom.dao;

import java.util.List;

import pe.com.starcom.model.Menu;

public interface MenuDao {
	
	public abstract Menu find(Long id);
	
	public abstract Menu save(Menu obj);
	
	public abstract List<Menu> getListaMenu();

}
