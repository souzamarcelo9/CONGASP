package br.com.congasp.model;

import java.time.LocalDateTime;
import java.util.Date;
import br.com.congasp.repository.entity.DespesaFuturaEntity;

public class DespesaFuturaModel  {
	 
    private Integer idDespesa;
    private int idProduto;
    private double valor;
    private Date  dataLimite;
    private Date  dataPagamento;
    private int  mes;
    private String  observacao;
    private Date	dataDespesa;
    private int  exercicio;
    private DespesaFuturaEntity despesaFuturaEntity;
	private ProdutoModel produtoModel;
	
	
	public Integer getIdDespesa() {
		return idDespesa;
	}
	public void setIdDespesa(Integer idDespesa) {
		this.idDespesa = idDespesa;
	}
	public int getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(int idProduto) {
		this.idProduto = idProduto;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public Date getDataLimite() {
		return dataLimite;
	}
	public void setDataLimite(Date dataLimite) {
		this.dataLimite = dataLimite;
	}
	public Date getDataPagamento() {
		return dataPagamento;
	}
	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
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
	public Date getDataDespesa() {
		return dataDespesa;
	}
	public void setDataDespesa(Date dataDespesa) {
		this.dataDespesa = dataDespesa;
	}
	public int getExercicio() {
		return exercicio;
	}
	public void setExercicio(int exercicio) {
		this.exercicio = exercicio;
	}
	public DespesaFuturaEntity getDespesaFuturaEntity() {
		return despesaFuturaEntity;
	}
	public void setDespesaFuturaEntity(DespesaFuturaEntity despesaFuturaEntity) {
		this.despesaFuturaEntity = despesaFuturaEntity;
	}
	public ProdutoModel getProdutoModel() {
		return produtoModel;
	}
	public void setProdutoModel(ProdutoModel produtoModel) {
		this.produtoModel = produtoModel;
	}
	
		

}
