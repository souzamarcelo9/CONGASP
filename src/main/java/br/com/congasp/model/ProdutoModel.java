package br.com.congasp.model;

public class ProdutoModel  {
	 
	private int idProduto;
	private String nomeProduto;
	private double valor;
	private String codClasseCusto;
	private ProdutoModel produtoModel;
 
	public int getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(int idProduto) {
		this.idProduto = idProduto;
	}
	public String getNomeProduto() {
		return nomeProduto;
	}
	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double Valor) {
		this.valor = Valor;
	}
	
	public String getCodClasseCusto() {
		return codClasseCusto;
	}
	public void setCodClasseCusto(String codClasseCusto) {
		this.codClasseCusto = codClasseCusto;
	}
    
	public ProdutoModel getProdutoModel() {
		return produtoModel;
	}
	public void setProdutoModel(ProdutoModel produtoModel) {
		this.produtoModel = produtoModel;
	}
	
}
