package br.com.congasp.repository;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import br.com.congasp.model.BancoModel;
import br.com.congasp.repository.entity.BancoEntity;
import br.com.congasp.uteis.Uteis;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BancoRepository {
    
	@Inject
	BancoEntity bancoEntity;
 
	EntityManager entityManager;
	
	public List<BancoModel> GetBancos(){
		 
		List<BancoModel> bancosModel = new ArrayList<BancoModel>();
 
		entityManager =  Uteis.JpaEntityManager();
 
		Query query = entityManager.createNamedQuery("BancoEntity.findAll");
 
		@SuppressWarnings("unchecked")
		Collection<BancoEntity> bancosEntity = (Collection<BancoEntity>)query.getResultList();
 
		BancoModel bancoModel = null;
 
		for (BancoEntity bancoEntity : bancosEntity) {
 
			bancoModel = new BancoModel();
			bancoModel.setCodAgencia(bancoEntity.getCodAgencia());
			bancoModel.setCodBanco(bancoEntity.getCodBanco());
			bancoModel.setNomeBanco(bancoEntity.getNomeBanco());
			
			bancosModel.add(bancoModel);
 
			
		}
 
		return bancosModel;
 
	}
	
//	public void AlterarRegistro(ProdutoModel produtoModel){
//		 
//		entityManager =  Uteis.JpaEntityManager();
// 
//		ProdutoEntity produtoEntity = this.GetProduto(produtoModel.getIdProduto());
// 
//		produtoEntity.setCodClasseCusto(produtoModel.getCodClasseCusto());
//		produtoEntity.setNomeProduto(produtoModel.getNomeProduto());
//		produtoEntity.setValor(produtoModel.getValor());
//		
// 
//		entityManager.merge(produtoEntity);
//	}
//	
	private BancoEntity GetBanco(String codBanco){
		 
		entityManager =  Uteis.JpaEntityManager();
 
		return entityManager.find(BancoEntity.class, codBanco);
	}
	
	public void SalvarBanco(BancoModel bancoModel){
		 
		entityManager =  Uteis.JpaEntityManager();
 
		bancoEntity = new BancoEntity();
		
		bancoEntity.setCodAgencia(bancoModel.getCodAgencia());
		bancoEntity.setCodBanco(bancoModel.getCodBanco());
		bancoEntity.setNomeBanco(bancoModel.getNomeBanco());
		
		entityManager.persist(bancoEntity);
 
	}
	
	public void Excluir(String codBanco){
		 
		entityManager =  Uteis.JpaEntityManager();		
 
		BancoEntity bancoEntity = this.GetBanco(codBanco);
 
		entityManager.remove(bancoEntity);
	}
 
}
