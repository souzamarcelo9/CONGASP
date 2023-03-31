package br.com.congasp.repository;


import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import br.com.congasp.model.ProdutoModel;
import br.com.congasp.repository.entity.ProdutoEntity;
import br.com.congasp.uteis.Uteis;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ProdutoRepository {
    
	@Inject
	ProdutoEntity produtoEntity;
 
	EntityManager entityManager;
	
	public List<ProdutoModel> GetProdutos(){
		 
		List<ProdutoModel> produtosModel = new ArrayList<ProdutoModel>();
 
		entityManager =  Uteis.JpaEntityManager();
 
		Query query = entityManager.createNamedQuery("ProdutoEntity.findAll");
 
		@SuppressWarnings("unchecked")
		Collection<ProdutoEntity> produtosEntity = (Collection<ProdutoEntity>)query.getResultList();
 
		ProdutoModel produtoModel = null;
 
		for (ProdutoEntity produtoEntity : produtosEntity) {
 
			produtoModel = new ProdutoModel();
			produtoModel.setIdProduto(produtoEntity.getIdProduto());
			produtoModel.setNomeProduto(produtoEntity.getNomeProduto());
			produtoModel.setCodClasseCusto(produtoEntity.getCodClasseCusto());
			produtoModel.setValor(produtoEntity.getValor());
			///produtoModel.setProdutoModel(produtoModel);
			
			produtosModel.add(produtoModel);
 
			
		}
 
		return produtosModel;
 
	}
	
	public void AlterarRegistro(ProdutoModel produtoModel){
		 
		entityManager =  Uteis.JpaEntityManager();
 
		ProdutoEntity produtoEntity = this.GetProduto(produtoModel.getIdProduto());
 
		produtoEntity.setCodClasseCusto(produtoModel.getCodClasseCusto());
		produtoEntity.setNomeProduto(produtoModel.getNomeProduto());
		produtoEntity.setValor(produtoModel.getValor());
		
 
		entityManager.merge(produtoEntity);
	}
	
	public ProdutoEntity GetProduto(int IdProduto){
		 
		entityManager =  Uteis.JpaEntityManager();
 
		return entityManager.find(ProdutoEntity.class, IdProduto);
	}
	
	public void SalvarProduto(ProdutoModel produtoModel){
		 
		entityManager =  Uteis.JpaEntityManager();
 
		produtoEntity = new ProdutoEntity();
		produtoEntity.setNomeProduto(produtoModel.getNomeProduto());
		produtoEntity.setValor(produtoModel.getValor());
		produtoEntity.setCodClasseCusto(produtoModel.getCodClasseCusto());
		
 
		entityManager.persist(produtoEntity);
 
	}
	
	public void ExcluirProduto(int codigo){
		 
		entityManager =  Uteis.JpaEntityManager();		
 
		ProdutoEntity produtoEntity = this.GetProduto(codigo);
 
		entityManager.remove(produtoEntity);
	}
 
}
