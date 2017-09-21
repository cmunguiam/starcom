package pe.com.starcom.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@NamedQueries({ @NamedQuery(name = "Usuario.findByUsername", query = "SELECT u " + "FROM Usuario u "
		+ "WHERE u.usuario =:username AND u.estado =:estado") })
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long idUsuario;
	@Column
	private String nombre;
	@Column
	private String apPaterno;
	@Column
	private String apMaterno;
	@Column
	private String usuario;
	@Column
	@Lob
	private byte[] password;
	@Transient
	private String clave;
	@Column
	private String estado;
	@JoinColumn(name = "idRol")
	@ManyToOne
	private Rol rol;

	public Usuario() {
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre.toUpperCase();
	}

	public String getApPaterno() {
		return apPaterno;
	}

	public void setApPaterno(String apPaterno) {
		this.apPaterno = apPaterno.toUpperCase();
	}

	public String getApMaterno() {
		return apMaterno;
	}

	public void setApMaterno(String apMaterno) {
		this.apMaterno = apMaterno.toUpperCase();
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public byte[] getPassword() {
		return password;
	}

	public void setPassword(byte[] password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idUsuario == null) ? 0 : idUsuario.hashCode());
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
		Usuario other = (Usuario) obj;
		if (idUsuario == null) {
			if (other.idUsuario != null)
				return false;
		} else if (!idUsuario.equals(other.idUsuario))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Usuario [nombre=");
		builder.append(nombre);
		builder.append(", apPaterno=");
		builder.append(apPaterno);
		builder.append(", apMaterno=");
		builder.append(apMaterno);
		builder.append("]");
		return builder.toString();
	}

}
