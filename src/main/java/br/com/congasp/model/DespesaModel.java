package br.com.congasp.model;

import java.time.LocalDateTime;
import java.util.Date;

import br.com.congasp.repository.entity.DespesaEntity;

public class DespesaModel  {
	 
    private Integer codigo;
    
    private int id_produto;
    
	private DespesaEntity despesaEntity;
	
	private double valor;
 
	private Date  data_limite;
 
	private Date	dataCadastro;
 
	private String  estorno;
	
	private Double valor_pago;
 
	private int  mes;
 
	private double  saldo_devedor;
 
	private int  id_emprestimo;
	
	private String  observacao;
	
	private Date	data_despesa;
	
	private String  exercicio;
	
	private String  debito_automatico;
	
	private LocalDateTime	data_estorno;
	
	private ProdutoModel produtoModel;
	
	private Date	data_pagamento;
	
	private String selectedOption;

	public String getSelectedOption() {
		return selectedOption;
	}

	public void setSelectedOption(String selectedOption) {
		this.selectedOption = selectedOption;
	}

	public Date getData_pagamento() {
		return data_pagamento;
	}

	public void setData_pagamento(Date data_pagamento) {
		this.data_pagamento = data_pagamento;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
    
	public int getId_produto() {
		return id_produto;
	}

	public void setId_produto(int id_produto) {
		this.id_produto = id_produto;
	}
	
	public DespesaEntity getDespesaEntity() {
		return despesaEntity;
	}

	public void setDespesaEntity(DespesaEntity despesaEntity) {
		this.despesaEntity = despesaEntity;
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

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
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

	public void setData_despesa(Date localDateTime) {
		this.data_despesa = localDateTime;
	}

	public String getExercicio() {
		return exercicio;
	}

	public void setExercicio(String exercicio) {
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

	public ProdutoModel getProdutoModel() {
		return produtoModel;
	}

	public void setProdutoModel(ProdutoModel produtoModel) {
		this.produtoModel = produtoModel;
	}


}
