package br.com.congasp.repository;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import br.com.congasp.model.BancoModel;
import br.com.congasp.model.ContaModel;
import br.com.congasp.repository.entity.ContaEntity;
import br.com.congasp.uteis.Uteis;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ContaRepository {
    
	@Inject
	ContaEntity contaEntity;
 
	EntityManager entityManager;
	
	public List<ContaModel> GetContas(){
		 
		List<ContaModel> contasModel = new ArrayList<ContaModel>();
 
		entityManager =  Uteis.JpaEntityManager();
 
		Query query = entityManager.createNamedQuery("ContaEntity.findAll");
 
		@SuppressWarnings("unchecked")
		Collection<ContaEntity> contasEntity = (Collection<ContaEntity>)query.getResultList();
 
		ContaModel contaModel = null;
 
		for (ContaEntity contaEntity : contasEntity) {
 
			contaModel = new ContaModel();
			contaModel.setCodBanco(contaEntity.getBancoEntity().getCodBanco());
			contaModel.setCodConta(contaEntity.getCodConta());
			contaModel.setSaldo(contaEntity.getSaldo());
			
			
			contasModel.add(contaModel);
 
			
		}
 
		return contasModel;
 
	}
	

	public ContaEntity GetConta(String codConta){
		 
		entityManager =  Uteis.JpaEntityManager();
 
		return entityManager.find(ContaEntity.class, codConta);
	}
	
	public void AtualizaSaldo(String codConta,double valor) {
		
		    contaEntity = this.GetConta(codConta);
		    
		    contaEntity.setSaldo(contaEntity.getSaldo() - valor);
		    entityManager.merge(contaEntity);
	}
	
	
// 
}
