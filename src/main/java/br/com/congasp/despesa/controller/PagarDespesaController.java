package br.com.congasp.despesa.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.SelectEvent;

import br.com.congasp.model.ContaModel;
import br.com.congasp.model.DespesaModel;
import br.com.congasp.model.ExercicioModel;
import br.com.congasp.repository.ContaRepository;
import br.com.congasp.repository.DespesaRepository;
import br.com.congasp.repository.ExercicioRepository;
import br.com.congasp.repository.entity.ContaEntity;
import br.com.congasp.uteis.Uteis;

 
@Named(value="pagarDespesaController")
@ViewScoped
public class PagarDespesaController implements Serializable  {
 

	private static final long serialVersionUID = 1L;
	
	@Inject transient
	private DespesaModel despesaModel;
	
	@Inject transient
	private ContaModel contaModel;
 
	@Produces 
	private List<DespesaModel> despesas;
 
	@Inject transient
	private DespesaRepository despesaRepository;
	
	@Inject transient
	private ExercicioRepository exercicioRepository;
	
	@Inject transient
	private ContaRepository contaRepository;
	
    
    private Date fechaInicioSeleccionada ;
	
	public Date getFechaInicioSeleccionada() {
		return fechaInicioSeleccionada;
	}
    
	public void setFechaInicioSeleccionada(Date datafim) {
		
		this.fechaInicioSeleccionada = datafim;
	}
	public List<DespesaModel> getDespesas() {
		return despesas;
	}
	public void setDespesas(List<DespesaModel> despesas) {
		this.despesas = despesas;
	}		
	public DespesaModel getDespesaModel() {
		return despesaModel;
	}
	public void setDespesaModel(DespesaModel despesaModel) {
		this.despesaModel = despesaModel;
	}
	
	public ContaModel getContaModel() {
		return contaModel;
	}
	public void setContaModel(ContaModel contaModel) {
		this.contaModel = contaModel;
	}
    
	@PostConstruct
	public void init(){
 
		//RETORNAR AS DESPESAS
		//this.despesas = despesaRepository.GetDespesas();
		
	}
	
	public void Pagar(){
        
		this.Valida();
		//this.despesaModel = despesaModel;
		this.despesaRepository.Pagar(this.despesaModel,this.contaModel);		 
		/*RECARREGA OS REGISTROS*/
		this.init(); 
		//this.despesaModel = null;
		Uteis.MensagemInfo("Pagamento realizado com sucesso");
 
	}
	
	public void handleChange(){  
	    //System.out.println("New value: " + employee);
		ContaEntity contaBD = contaRepository.GetConta(contaModel.getCodConta());
		this.contaModel.setSaldo(contaBD.getSaldo());
		
	}
	
	private void Valida()
	 {
			
	      ExercicioModel conf = new ExercicioModel();
	      conf = exercicioRepository.GetLastValidConfExercicio();
          DespesaModel despesaBd = despesaRepository.GetSingle(despesaModel.getCodigo());
	        
           
	        if (despesaBd.getData_pagamento() != null)
	        {
	        	Uteis.MensagemInfo("Pagamento já realizado.");
	        	return;
	        }
	        
	        if (despesaModel.getMes() != conf.getCompetencia() && despesaModel.getExercicio() != conf.getExercicio())
	        {
	        	Uteis.MensagemInfo("A competência selecionada está bloqueada no momento!");
	        	return;
	        }
	       
	 }
	
	 public void handleDateSelect(SelectEvent event) {

		 this.fechaInicioSeleccionada = (Date) event.getObject();


     }
     
		/***
		 * CARREGA INFORMAÇÕES DE UMA DESPESA PARA SER EDITADA
		 * @param despesaModel
		 */
		public void Editar(DespesaModel despesaModel){
	 
			this.despesaModel = despesaModel;
	 
		}
	
 
}
