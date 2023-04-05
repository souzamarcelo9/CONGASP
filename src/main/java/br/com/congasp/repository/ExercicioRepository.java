package br.com.congasp.repository;


import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.congasp.model.ExercicioModel;
import br.com.congasp.model.ProdutoModel;
import br.com.congasp.repository.entity.ExercicioEntity;
import br.com.congasp.repository.entity.ProdutoEntity;
import br.com.congasp.uteis.Uteis;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ExercicioRepository {
    
	@Inject
	ExercicioEntity exercicioEntity;
 
	EntityManager entityManager;
	
	
	public void Salvar(ExercicioModel exercicioModel){
		 
		entityManager =  Uteis.JpaEntityManager();
 
		exercicioEntity = new ExercicioEntity();
		exercicioEntity.setAtivo("X");
		exercicioEntity.setCompetencia(exercicioModel.getCompetencia());
		exercicioEntity.setExercicio(Integer.parseInt(exercicioModel.getExercicio()));
		exercicioEntity.setDataAtivacao(exercicioModel.getDataAtivacao());
		
 
		entityManager.persist(exercicioEntity);
 
	}
	
	public List<ExercicioModel> List(){
		 
		List<ExercicioModel> exerciciosModel = new ArrayList<ExercicioModel>();
 
		entityManager =  Uteis.JpaEntityManager();
 
		Query query = entityManager.createNamedQuery("ExercicioEntity.findAll");
 
		@SuppressWarnings("unchecked")
		Collection<ExercicioEntity> exerciciosEntity = (Collection<ExercicioEntity>)query.getResultList();
 
		ExercicioModel exercicioModel = null;
 
		for (ExercicioEntity exercicioEntity : exerciciosEntity) {
 
			exercicioModel = new ExercicioModel();
		    exercicioModel.setAtivo(exercicioEntity.getAtivo());
		    exercicioModel.setCod_exercicio(exercicioEntity.getCod_exercicio());
		    exercicioModel.setCompetencia(exercicioEntity.getCompetencia());
		    exercicioModel.setExercicio(String.valueOf(exercicioEntity.getExercicio()));
		    exercicioModel.setDataAtivacao(exercicioEntity.getDataAtivacao());
		    
			exerciciosModel.add(exercicioModel);
 
			
		}
	  return exerciciosModel;	
	}
	
	public ExercicioModel GetLastValidConfExercicio() {
	
		List<ExercicioModel> exerciciosModel = new ArrayList<ExercicioModel>();
		exerciciosModel = this.List();
		
		return exerciciosModel.get(exerciciosModel.size()-1);
	}
	
 
}
