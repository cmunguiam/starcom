package pe.com.starcom.dao;

import java.util.List;

import pe.com.starcom.model.ItemMenu;

public interface ItemMenuDao {
	
	public abstract ItemMenu find(Long id);
	
	public abstract ItemMenu save(ItemMenu obj);
	
	public abstract List<ItemMenu> getListaItemMenu();
	
	public abstract List<ItemMenu> getListaMenuItem(Long idMenu, Long idRol);

}
