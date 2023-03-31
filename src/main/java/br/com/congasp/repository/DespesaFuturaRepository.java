package br.com.congasp.repository;


import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.congasp.model.ContaModel;
import br.com.congasp.model.DespesaFuturaModel;
import br.com.congasp.model.DespesaModel;
import br.com.congasp.model.ProdutoModel;
import br.com.congasp.model.UsuarioModel;
import br.com.congasp.repository.entity.ContaEntity;
import br.com.congasp.repository.entity.DespesaEntity;
import br.com.congasp.repository.entity.DespesaFuturaEntity;
import br.com.congasp.repository.entity.ProdutoEntity;
import br.com.congasp.repository.entity.UsuarioEntity;
import br.com.congasp.uteis.LocalDateTimeAttributeConverter;
import br.com.congasp.uteis.Uteis;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DespesaFuturaRepository {
    
	@Inject
	DespesaFuturaEntity despesaEntity;
	
	@Inject
	ContaEntity contaEntity;
    
	@Inject
	ContaRepository contaRepositorio;
	
	EntityManager entityManager;
	
	public LocalDateTime currentDate;
    public String format;  
	
	public List<DespesaFuturaModel> GetDespesas(){
		 
		List<DespesaFuturaModel> despesasModel = new ArrayList<DespesaFuturaModel>();
 
		entityManager =  Uteis.JpaEntityManager();
 
		Query query = entityManager.createNamedQuery("DespesaFuturaEntity.findAll");
 
		@SuppressWarnings("unchecked")
		Collection<DespesaFuturaEntity> despesasEntity = (Collection<DespesaFuturaEntity>)query.getResultList();
 
		DespesaFuturaModel despesaModel = null;
 
		for (DespesaFuturaEntity despesaEntity : despesasEntity) {
          
			despesaModel = new DespesaFuturaModel();
			despesaModel.setDataDespesa(despesaEntity.getData_despesa());
			despesaModel.setDataLimite(despesaEntity.getData_limite());
			despesaModel.setIdDespesa(despesaEntity.getIdDespesa());
			despesaModel.setValor(despesaEntity.getValor());
			despesaModel.setExercicio(despesaEntity.getExercicio());
			despesaModel.setMes(despesaEntity.getMes());
			despesaModel.setObservacao(despesaEntity.getObservacao());
			
			ProdutoEntity produtoEntity =  despesaEntity.getProdutoEntity();		
			
			 
			ProdutoModel produtoModel = new ProdutoModel();
			produtoModel.setIdProduto(produtoEntity.getIdProduto());
			produtoModel.setNomeProduto(produtoEntity.getNomeProduto());
			despesaModel.setProdutoModel(produtoModel);			
			
			despesasModel.add(despesaModel);
 
			
		}
 
		return despesasModel;
 
	}
	
	public List<DespesaFuturaModel> GetDespesasByPeriod(int mes,int exercicio){ 
		 
		List<DespesaFuturaModel> despesasModel = new ArrayList<DespesaFuturaModel>();
 
		entityManager =  Uteis.JpaEntityManager();
 
		Query query = entityManager.createNamedQuery("DespesaFuturaEntity.findByPeriod");
		 
		//PARÂMETROS DA QUERY
		query.setParameter("mes", mes);
		query.setParameter("exercicio", exercicio);

 
		@SuppressWarnings("unchecked")
		Collection<DespesaFuturaEntity> despesasEntity = (Collection<DespesaFuturaEntity>)query.getResultList();
 
		DespesaFuturaModel despesaModel = null;
 
		for (DespesaFuturaEntity despesaEntity : despesasEntity) {
          
			despesaModel = new DespesaFuturaModel();
			despesaModel.setDataDespesa(despesaEntity.getData_despesa());
			despesaModel.setDataLimite(despesaEntity.getData_limite());
			despesaModel.setIdDespesa(despesaEntity.getIdDespesa());
			despesaModel.setValor(despesaEntity.getValor());
			despesaModel.setExercicio(despesaEntity.getExercicio());
			despesaModel.setMes(despesaEntity.getMes());
			despesaModel.setObservacao(despesaEntity.getObservacao());
			
			ProdutoEntity produtoEntity =  despesaEntity.getProdutoEntity();		
			
			 
			ProdutoModel produtoModel = new ProdutoModel();
			produtoModel.setIdProduto(produtoEntity.getIdProduto());
			produtoModel.setNomeProduto(produtoEntity.getNomeProduto());
			despesaModel.setProdutoModel(produtoModel);			
			
			despesasModel.add(despesaModel);
 
			
		}
 
		return despesasModel;
 
	}
	
	public void AlterarRegistro(DespesaFuturaModel despesaModel){
		 
		entityManager =  Uteis.JpaEntityManager();
// 
		DespesaFuturaEntity despesaEntity = this.GetDespesa(despesaModel.getIdDespesa());
 
		despesaEntity.setData_despesa(despesaModel.getDataDespesa());
		despesaEntity.setData_limite(despesaModel.getDataLimite());	
		despesaEntity.setValor(despesaModel.getValor());
		despesaEntity.setExercicio(despesaModel.getExercicio());
		despesaEntity.setMes(despesaModel.getMes());
		despesaEntity.setObservacao(despesaModel.getObservacao());
				
// 
		entityManager.merge(despesaEntity);
	}
	
	private DespesaFuturaEntity GetDespesa(int IdDespesa){
		 
		entityManager =  Uteis.JpaEntityManager();
 
		return entityManager.find(DespesaFuturaEntity.class, IdDespesa);
	}
	
	public void SalvarDespesa(DespesaFuturaModel despesaModel){
		 
	    DespesaFuturaEntity despesaEntity = new DespesaFuturaEntity();
		entityManager =  Uteis.JpaEntityManager();
 		
 		despesaEntity.setData_despesa(despesaModel.getDataDespesa());
 		despesaEntity.setData_limite(despesaModel.getDataLimite());
 		despesaEntity.setValor(despesaModel.getValor());
 		despesaEntity.setObservacao(despesaModel.getObservacao());
 		despesaEntity.setExercicio(despesaModel.getExercicio());
 		despesaEntity.setMes(despesaModel.getMes());
 		
		
 		ProdutoEntity produtoEntity = entityManager.find(ProdutoEntity.class, despesaModel.getIdProduto()); 
 		 
		despesaEntity.setProdutoEntity(produtoEntity);
 		
		entityManager.persist(despesaEntity);
 
	}
	
	public void Excluir(int codigo){
		 
		entityManager =  Uteis.JpaEntityManager();		
 
		DespesaFuturaEntity despesaEntity = this.GetDespesa(codigo);
 
		entityManager.remove(despesaEntity);
	}
	
 
}
