package br.com.congasp.repository.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
 
@Entity
@Table(name="tb_produto")
 
@NamedQueries({
 
	@NamedQuery(name = "ProdutoEntity.findAll",query= "SELECT p FROM ProdutoEntity p order by p.nomeProduto")
 
})

public class ProdutoEntity {

	@Id
	@GeneratedValue
	@Column(name="idProduto")
	private int idProduto;
 
	@Column(name="nomeProduto")
	private String nomeProduto;
 
	@Column(name="valor")
	private double valor;
	
	@Column(name="codClasseCusto")
	private String codClasseCusto;
 
	public int getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(int IdProduto) {
		this.idProduto = IdProduto;
	}
	public String getNomeProduto() {
		return nomeProduto;
	}
	public void setNomeProduto(String NomeProduto) {
		this.nomeProduto = NomeProduto;
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
	public void setCodClasseCusto(String CodClasseCusto) {
		this.codClasseCusto = CodClasseCusto;
	}
	
	
	
 
}
