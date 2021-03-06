package pe.com.starcom.dto;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import pe.com.starcom.model.ItemMenu;

public class DataModelPermiso extends ListDataModel<ItemMenu> implements
		SelectableDataModel<ItemMenu> {

	public DataModelPermiso() {

	}

	public DataModelPermiso(List<ItemMenu> data) {
		super(data);
	}

	public Object getRowKey(ItemMenu object) {
		return object.getNombre();
	}

	public ItemMenu getRowData(String rowKey) {
		List<ItemMenu> cars = (List<ItemMenu>) getWrappedData();

		for (ItemMenu car : cars) {
			if (car.getNombre().equals(rowKey))
				return car;
		}

		return null;
	}

}
