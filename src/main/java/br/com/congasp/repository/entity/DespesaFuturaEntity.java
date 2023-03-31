package br.com.congasp.repository.entity;
 
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.type.DateType;
 
 
@Entity
@Table(name="tb_despesaFutura")
 
@NamedQueries({
 
	@NamedQuery(name = "DespesaFuturaEntity.findAll",query= "SELECT d FROM DespesaFuturaEntity d order by d.idDespesa"),
	@NamedQuery(name = "DespesaFuturaEntity.findByPeriod",query= "SELECT d FROM DespesaFuturaEntity d WHERE d.mes = :mes AND d.exercicio = :exercicio")
 
})
public class DespesaFuturaEntity {
 
	@Id
	@GeneratedValue
	@Column(name = "idDespesa")
	private Integer 		idDespesa;
    
	@OneToOne
	@JoinColumn(name="idProduto")
	private ProdutoEntity	produtoEntity;
	
	@Column(name = "valor")
	private double valor;
 
	@Column(name = "dataLimite")
	private Date  data_limite;
 
	@Column(name = "dataPagamento")
	private Date	data_pagamento;
 

	@Column(name = "mes")
	private int  		mes;
 
	
	@Column(name = "observacao")
	private String  observacao;
	
	@Column(name = "dataDespesa")
	private Date	data_despesa;
	
	@Column(name = "exercicio")
	private int  exercicio;
	
	
	

	public Integer getIdDespesa() {
		return idDespesa;
	}

	public void setIdDespesa(Integer idDespesa) {
		this.idDespesa = idDespesa;
	}

	public ProdutoEntity getProdutoEntity() {
		return produtoEntity;
	}

	public void setProdutoEntity(ProdutoEntity produtoEntity) {
		this.produtoEntity = produtoEntity;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Date getData_limite() {
		return data_limite;
	}

	public void setData_limite(Date data_limite) {
		this.data_limite = data_limite;
	}


	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}


	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Date getData_despesa() {
		return data_despesa;
	}

	public void setData_despesa(Date data_despesa) {
		this.data_despesa = data_despesa;
	}

	public int getExercicio() {
		return exercicio;
	}

	public void setExercicio(int exercicio) {
		this.exercicio = exercicio;
	}

	
	public Date getData_pagamento() {
		return data_pagamento;
	}

	public void setData_pagamento(Date data_pagamento) {
		this.data_pagamento = data_pagamento;
	}

}