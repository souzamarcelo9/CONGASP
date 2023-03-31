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
@Table(name="tb_Despesa")
 
@NamedQueries({
 
	@NamedQuery(name = "DespesaEntity.findAll",query= "SELECT d FROM DespesaEntity d"),
	@NamedQuery(name = "DespesaEntity.findByPeriod",query= "SELECT d FROM DespesaEntity d WHERE d.mes = :mes AND d.exercicio = :exercicio"),
	@NamedQuery(name = "DespesaEntity.findObs",query = "SELECT d FROM DespesaEntity d WHERE d.mes = :mes AND d.exercicio = :exercicio AND d.observacao LIKE :observacao"),
	@NamedQuery(name = "DespesaEntity.findValor",query = "SELECT d FROM DespesaEntity d WHERE d.mes = :mes AND d.exercicio = :exercicio and d.valor = :valor"),
	@NamedQuery(name = "DespesaEntity.findOnProduct",query = "SELECT d FROM DespesaEntity d inner join d.produtoEntity  p WHERE  d.mes = :mes AND d.exercicio = :exercicio AND p.nomeProduto = :nomeProduto")
 
})
public class DespesaEntity {
 
	@Id
	@GeneratedValue
	@Column(name = "id_despesa")
	private Integer 		codigo;
    
	@OneToOne
	@JoinColumn(name="id_produto")
	private ProdutoEntity	produtoEntity;
	
	@Column(name = "valor")
	private double valor;
 
	@Column(name = "data_limite")
	private Date  data_limite;
 
	@Column(name = "data_pagamento")
	private Date	data_pagamento;
 
	@Column(name = "estorno")
	private String  		estorno;
	
	@Column(name = "valor_pago")
	private Double valor_pago;
 
	@Column(name = "mes")
	private int  		mes;
 
	@Column(name = "saldo_devedor")
	private double  saldo_devedor;
 
	@Column(name = "id_emprestimo")
	private int  id_emprestimo;
	
	@Column(name = "observacao")
	private String  observacao;
	
	@Column(name = "data_despesa")
	private Date	data_despesa;
	
	@Column(name = "exercicio")
	private int  exercicio;
	
	@Column(name = "debito_automatico")
	private String  debito_automatico;
	
	@Column(name = "data_estorno")
	private LocalDateTime	data_estorno;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
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


	public String getEstorno() {
		return estorno;
	}

	public void setEstorno(String estorno) {
		this.estorno = estorno;
	}

	public Double getValor_pago() {
		return valor_pago;
	}

	public void setValor_pago(Double valor_pago) {
		this.valor_pago = valor_pago;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public double getSaldo_devedor() {
		return saldo_devedor;
	}

	public void setSaldo_devedor(double saldo_devedor) {
		this.saldo_devedor = saldo_devedor;
	}

	public int getId_emprestimo() {
		return id_emprestimo;
	}

	public void setId_emprestimo(int id_emprestimo) {
		this.id_emprestimo = id_emprestimo;
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

	public String getDebito_automatico() {
		return debito_automatico;
	}

	public void setDebito_automatico(String debito_automatico) {
		this.debito_automatico = debito_automatico;
	}

	public LocalDateTime getData_estorno() {
		return data_estorno;
	}

	public void setData_estorno(LocalDateTime data_estorno) {
		this.data_estorno = data_estorno;
	}
	
	public Date getData_pagamento() {
		return data_pagamento;
	}

	public void setData_pagamento(Date data_pagamento) {
		this.data_pagamento = data_pagamento;
	}

}