/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.starcom.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 * @author Hever
 */
@Entity
@Table
public class ItemMenu implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long idItemMenu;
	@Column
	private String nombre;
	@Column
	private String nombreXhtml;
	@Column
	private String estado;
	@JoinColumn(name = "idMenu")
	@ManyToOne
	private Menu menu;

	// @JoinColumn(name = "idRol")
	// @ManyToOne
	// private Rol rol;

	public ItemMenu() {
	}

	public Long getIdItemMenu() {
		return idItemMenu;
	}

	public void setIdItemMenu(Long idItemMenu) {
		this.idItemMenu = idItemMenu;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getNombreXhtml() {
		return nombreXhtml;
	}

	public void setNombreXhtml(String nombreXhtml) {
		this.nombreXhtml = nombreXhtml;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idItemMenu == null) ? 0 : idItemMenu.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemMenu other = (ItemMenu) obj;
		if (idItemMenu == null) {
			if (other.idItemMenu != null)
				return false;
		} else if (!idItemMenu.equals(other.idItemMenu))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ItemMenu [idItemMenu=");
		builder.append(idItemMenu);
		builder.append("]");
		return builder.toString();
	}

}
