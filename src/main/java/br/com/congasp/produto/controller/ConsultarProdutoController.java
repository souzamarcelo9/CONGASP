package br.com.congasp.produto.controller;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.congasp.model.PessoaModel;
import br.com.congasp.model.ProdutoModel;
import br.com.congasp.repository.ProdutoRepository;

@Named(value="consultarProdutoController")
@ViewScoped

public class ConsultarProdutoController implements Serializable {

	
	private static final long serialVersionUID = 1L;
	 
	@Inject transient
	private ProdutoModel produtoModel;
 
	@Produces 
	private List<ProdutoModel> produtos;
 
	@Inject transient
	private ProdutoRepository produtoRepository;
 
	
	public List<ProdutoModel> getProdutos() {
		return produtos;
	}
	public void setProdutos(List<ProdutoModel> produtos) {
		this.produtos = produtos;
	}		
	public ProdutoModel getProdutoModel() {
		return produtoModel;
	}
	public void setProdutoModel(ProdutoModel produtoModel) {
		this.produtoModel = produtoModel;
	}
 
	/***
	 * CARREGA OS PRODUTOS
	 */
	@PostConstruct
	public void init(){
 
		this.produtos = produtoRepository.GetProdutos();
	}
	
	/***
	 * CARREGA INFORMAÇÕES DE UM PRODUTO PARA SER EDITADO
	 * @param pessoaModel
	 */
	public void Editar(ProdutoModel produtoModel){
 
		this.produtoModel = produtoModel;
 
	}
	
	/***
	 * ATUALIZA O REGISTRO QUE FOI ALTERADO
	 */
	public void AlterarRegistro(){
 
		this.produtoRepository.AlterarRegistro(this.produtoModel);	
 
 
		/*RECARREGA OS REGISTROS*/
		this.init();
	}
	
	public void ExcluirProduto(ProdutoModel produtoModel){
		 
		//EXCLUI O PRODUTO DO BANCO
		this.produtoRepository.ExcluirProduto(produtoModel.getIdProduto());
 
		
		//REMOVE PRODUTO
		this.produtos.remove(produtoModel);
 
	}
	
}

