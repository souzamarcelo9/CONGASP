package br.com.congasp.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
 
@Entity
@Table(name="tb_conta")
 
@NamedQueries({
 
	@NamedQuery(name = "ContaEntity.findAll",query= "SELECT p FROM ContaEntity p")
 
})

public class ContaEntity {

	@Id
	@Column(name="codConta")
	private String codConta;
 
	@Column(name="saldo")
	private double saldo;
    
	@OneToOne
	@JoinColumn(name="codBanco")
	private BancoEntity	bancoEntity;

	public String getCodConta() {
		return codConta;
	}

	public void setCodConta(String codConta) {
		this.codConta = codConta;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public BancoEntity getBancoEntity() {
		return bancoEntity;
	}

	public void setBancoEntity(BancoEntity bancoEntity) {
		this.bancoEntity = bancoEntity;
	}
	
	

	
}
