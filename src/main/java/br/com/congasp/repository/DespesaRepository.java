package br.com.congasp.repository;


import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.congasp.model.ContaModel;
import br.com.congasp.model.DespesaModel;
import br.com.congasp.model.ProdutoModel;
import br.com.congasp.model.UsuarioModel;
import br.com.congasp.repository.entity.ContaEntity;
import br.com.congasp.repository.entity.DespesaEntity;
import br.com.congasp.repository.entity.ProdutoEntity;
import br.com.congasp.repository.entity.UsuarioEntity;
import br.com.congasp.uteis.LocalDateTimeAttributeConverter;
import br.com.congasp.uteis.Uteis;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DespesaRepository {
    
	@Inject
	DespesaEntity despesaEntity;
	
	@Inject
	ContaEntity contaEntity;
    
	@Inject
	ContaRepository contaRepositorio;
	
	EntityManager entityManager;
	
	public LocalDateTime currentDate;
    public String format;  
	
	public List<DespesaModel> GetDespesas(){
		 
		List<DespesaModel> despesasModel = new ArrayList<DespesaModel>();
 
		entityManager =  Uteis.JpaEntityManager();
 
		Query query = entityManager.createNamedQuery("DespesaEntity.findAll");
 
		@SuppressWarnings("unchecked")
		Collection<DespesaEntity> despesasEntity = (Collection<DespesaEntity>)query.getResultList();
 
		DespesaModel despesaModel = null;
 
		for (DespesaEntity despesaEntity : despesasEntity) {
          
			despesaModel = new DespesaModel();
			despesaModel.setData_despesa(despesaEntity.getData_despesa());
			despesaModel.setData_limite(despesaEntity.getData_limite());
			despesaModel.setData_estorno(despesaEntity.getData_estorno());
			despesaModel.setCodigo(despesaEntity.getCodigo());
			despesaModel.setValor(despesaEntity.getValor());
			despesaModel.setValor_pago(despesaEntity.getValor_pago());
			despesaModel.setEstorno(despesaEntity.getEstorno());
			despesaModel.setExercicio(String.valueOf(despesaEntity.getExercicio()));
			despesaModel.setMes(despesaEntity.getMes());
			despesaModel.setDebito_automatico(despesaEntity.getDebito_automatico());
			despesaModel.setObservacao(despesaEntity.getObservacao());
			despesaModel.setData_pagamento(despesaEntity.getData_pagamento());
			
			ProdutoEntity produtoEntity =  despesaEntity.getProdutoEntity();		
			
			 
			ProdutoModel produtoModel = new ProdutoModel();
			produtoModel.setIdProduto(produtoEntity.getIdProduto());
			produtoModel.setNomeProduto(produtoEntity.getNomeProduto());
			despesaModel.setProdutoModel(produtoModel);			
			
			despesasModel.add(despesaModel);
 
			
		}
 
		return despesasModel;
 
	}
	
	public List<DespesaModel> GetDespesasFilter(int mes,String exercicio,String observacao,double valor){
		String ehObs = "";
		List<DespesaModel> despesasModel = new ArrayList<DespesaModel>();
		//Collection<DespesaEntity> despesasEntity;
		entityManager =  Uteis.JpaEntityManager();
 
		Query query;
		query = entityManager.createNamedQuery("DespesaEntity.findByPeriod");
		query.setParameter("mes", mes);
		query.setParameter("exercicio", Integer.parseInt(exercicio)); 
		//PARÂMETROS DA QUERY
		
		
		if (observacao != null &&  observacao != "")
		{
			query = entityManager.createNamedQuery("DespesaEntity.findObs");
			query.setParameter("observacao", "%" + observacao + "%");
			query.setParameter("mes", mes);
			query.setParameter("exercicio", Integer.parseInt(exercicio));
			
			ehObs = "X";
			
		}
		
		if (valor > 0)
		{
			query = entityManager.createNamedQuery("DespesaEntity.findValor");
			query.setParameter("valor", valor);
			query.setParameter("mes", mes);
			query.setParameter("exercicio", Integer.parseInt(exercicio));
			
		}

 
		@SuppressWarnings("unchecked")
		Collection<DespesaEntity> despesasEntity = (Collection<DespesaEntity>)query.getResultList();
        
		if(ehObs == "X" && despesasEntity.size() == 0) {
			query = entityManager.createNamedQuery("DespesaEntity.findOnProduct");
			query.setParameter("nomeProduto",  observacao);
			query.setParameter("mes", mes);
			query.setParameter("exercicio", Integer.parseInt(exercicio));
			
			
		}
		
		if (despesasEntity.size() == 0) {
			Collection<DespesaEntity> despesasEntityObs = (Collection<DespesaEntity>)query.getResultList();
			despesasEntity = despesasEntityObs;
		}
		
		DespesaModel despesaModel = null;
 
		for (DespesaEntity despesaEntity : despesasEntity) {
          
			despesaModel = new DespesaModel();
			despesaModel.setData_despesa(despesaEntity.getData_despesa());
			despesaModel.setData_limite(despesaEntity.getData_limite());
			despesaModel.setData_estorno(despesaEntity.getData_estorno());
			despesaModel.setCodigo(despesaEntity.getCodigo());
			despesaModel.setValor(despesaEntity.getValor());
			despesaModel.setValor_pago(despesaEntity.getValor_pago());
			despesaModel.setEstorno(despesaEntity.getEstorno());
			despesaModel.setExercicio(String.valueOf(despesaEntity.getExercicio()));
			despesaModel.setMes(despesaEntity.getMes());
			despesaModel.setDebito_automatico(despesaEntity.getDebito_automatico());
			despesaModel.setObservacao(despesaEntity.getObservacao());
			despesaModel.setData_pagamento(despesaEntity.getData_pagamento());
			
			ProdutoEntity produtoEntity =  entityManager.find(ProdutoEntity.class, despesaEntity.getProdutoEntity().getIdProduto());		
			
			 
			ProdutoModel produtoModel = new ProdutoModel();
			produtoModel.setIdProduto(produtoEntity.getIdProduto());
			produtoModel.setNomeProduto(produtoEntity.getNomeProduto());
			despesaModel.setProdutoModel(produtoModel);			
			
			despesasModel.add(despesaModel);
 
			
		}
 
		return despesasModel;
 
	}
	
	public void AlterarRegistro(DespesaModel despesaModel){
		 
		entityManager =  Uteis.JpaEntityManager();
// 
		DespesaEntity despesaEntity = this.GetDespesa(despesaModel.getCodigo());
 
		despesaEntity.setData_despesa(despesaModel.getData_despesa());
		despesaEntity.setData_limite(despesaModel.getData_limite());
		despesaEntity.setData_estorno(despesaModel.getData_estorno());
		
		despesaEntity.setValor(despesaModel.getValor());
		despesaEntity.setValor_pago(despesaModel.getValor_pago());
		despesaEntity.setEstorno(despesaModel.getEstorno());
		despesaEntity.setExercicio(Integer.parseInt(despesaModel.getExercicio()));
		despesaEntity.setMes(despesaModel.getMes());
		despesaEntity.setDebito_automatico(despesaModel.getDebito_automatico());
		despesaEntity.setObservacao(despesaModel.getObservacao());
				
// 
		entityManager.merge(despesaEntity);
	}
	
	private DespesaEntity GetDespesa(int IdDespesa){
		 
		entityManager =  Uteis.JpaEntityManager();
 
		return entityManager.find(DespesaEntity.class, IdDespesa);
	}
	
	public DespesaModel GetSingle(int IdDespesa) {
		 
		entityManager =  Uteis.JpaEntityManager();
		DespesaEntity despesaEntity = this.GetDespesa(IdDespesa);
		
		DespesaModel despesaModel = null;
		
          
			despesaModel = new DespesaModel();
			despesaModel.setData_despesa(despesaEntity.getData_despesa());
			despesaModel.setData_limite(despesaEntity.getData_limite());
			despesaModel.setData_estorno(despesaEntity.getData_estorno());
			despesaModel.setCodigo(despesaEntity.getCodigo());
			despesaModel.setValor(despesaEntity.getValor());
			despesaModel.setValor_pago(despesaEntity.getValor_pago());
			despesaModel.setEstorno(despesaEntity.getEstorno());
			despesaModel.setExercicio(String.valueOf(despesaEntity.getExercicio()));
			despesaModel.setMes(despesaEntity.getMes());
			despesaModel.setDebito_automatico(despesaEntity.getDebito_automatico());
			despesaModel.setObservacao(despesaEntity.getObservacao());
			despesaModel.setData_pagamento(despesaEntity.getData_pagamento());
			
			ProdutoEntity produtoEntity =  entityManager.find(ProdutoEntity.class, despesaEntity.getProdutoEntity().getIdProduto());					 
			ProdutoModel produtoModel = new ProdutoModel();
			produtoModel.setIdProduto(produtoEntity.getIdProduto());
			produtoModel.setNomeProduto(produtoEntity.getNomeProduto());
			despesaModel.setProdutoModel(produtoModel);
			
			return despesaModel;
	
	}
	
	public void SalvarDespesa(DespesaModel despesaModel){
		 
		entityManager =  Uteis.JpaEntityManager();
 
 		despesaEntity = new DespesaEntity();
 		despesaEntity.setData_despesa(despesaModel.getData_despesa());
 		despesaEntity.setData_limite(despesaModel.getData_limite());
 		despesaEntity.setValor(despesaModel.getValor());
 		despesaEntity.setObservacao(despesaModel.getObservacao());
 		despesaEntity.setExercicio(Integer.parseInt(despesaModel.getExercicio()));
 		despesaEntity.setMes(despesaModel.getMes());
 		despesaEntity.setDebito_automatico(despesaModel.getDebito_automatico());
		
 		ProdutoEntity produtoEntity = entityManager.find(ProdutoEntity.class, despesaModel.getId_produto()); 
 		 
		despesaEntity.setProdutoEntity(produtoEntity);
 		
		entityManager.persist(despesaEntity);
 
	}
	
	public void Excluir(int codigo){
		 
		entityManager =  Uteis.JpaEntityManager();		
 
		DespesaEntity despesaEntity = this.GetDespesa(codigo);
 
		entityManager.remove(despesaEntity);
	}
	
	public void Pagar(DespesaModel despesaModel,ContaModel conta) {
		
		entityManager =  Uteis.JpaEntityManager();
		// 
		DespesaEntity despesaEntity = this.GetDespesa(despesaModel.getCodigo());
 
		despesaEntity.setData_pagamento(despesaModel.getData_pagamento());
		
		entityManager.merge(despesaEntity);
		
		//contaRepositorio = new ContaRepository();
	    contaRepositorio.AtualizaSaldo(conta.getCodConta(), despesaEntity.getValor());
	    
	}
 
}
