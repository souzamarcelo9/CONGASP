package br.com.congasp.model;
import java.io.Serializable;
	
	 
	public class UsuarioModel implements Serializable {
	 
		private static final long serialVersionUID = 1L;
	 
		private String codigo;
		private String usuario;
		private String senha;
		private String status;
	 
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getCodigo() {
			return codigo;
		}
		public void setCodigo(String codigo) {
			this.codigo = codigo;
		}
		public String getUsuario() {
			return usuario;
		}
		public void setUsuario(String usuario) {
			this.usuario = usuario;
		}
		public String getSenha() {
			return senha;
		}
		public void setSenha(String senha) {
			this.senha = senha;
		}
	 
	}
