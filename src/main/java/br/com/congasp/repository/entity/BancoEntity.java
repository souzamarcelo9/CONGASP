package br.com.congasp.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
 
@Entity
@Table(name="tb_banco")
 
@NamedQueries({
 
	@NamedQuery(name = "BancoEntity.findAll",query= "SELECT p FROM BancoEntity p")
 
})

public class BancoEntity {

	@Id
	@Column(name="codBanco")
	private String codBanco;
 
	@Column(name="nomeBanco")
	private String nomeBanco;
    
	@Column(name = "codAgencia")
	private String codAgencia;

	public String getCodBanco() {
		return codBanco;
	}

	public void setCodBanco(String codBanco) {
		this.codBanco = codBanco;
	}

	public String getNomeBanco() {
		return nomeBanco;
	}

	public void setNomeBanco(String nomeBanco) {
		this.nomeBanco = nomeBanco;
	}

	public String getCodAgencia() {
		return codAgencia;
	}

	public void setCodAgencia(String codAgencia) {
		this.codAgencia = codAgencia;
	}
		
	
}
