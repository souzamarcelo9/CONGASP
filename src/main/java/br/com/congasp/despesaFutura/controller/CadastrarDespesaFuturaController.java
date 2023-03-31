package br.com.congasp.despesaFutura.controller;
import java.io.IOException;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.SelectEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.com.congasp.fechamento.controller.FechamentoController;
import br.com.congasp.model.DespesaFuturaModel;
import br.com.congasp.model.ExercicioModel;
import br.com.congasp.model.ProdutoModel;
import br.com.congasp.repository.DespesaFuturaRepository;
import br.com.congasp.repository.ExercicioRepository;
import br.com.congasp.repository.ProdutoRepository;
import br.com.congasp.uteis.Uteis;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@Named(value="cadastrarDespesaFuturaController")
@RequestScoped
public class CadastrarDespesaFuturaController implements Serializable {
	
	private static final long serialVersionUID = 1L;

	
	@Inject
	DespesaFuturaModel despesaModel;
 
	@Inject
	DespesaFuturaRepository despesaFuturaRepository;
	
	@Inject
	ExercicioRepository exercicioRepository;
	
	@Inject 
	ProdutoRepository produtoRepository;
 

	@Produces
	private List<ProdutoModel> listProdutos;
	
	private int Mes;
	
	private Date fechaInicioSeleccionada ;
	
	public Date getFechaInicioSeleccionada() {
		return fechaInicioSeleccionada;
	}
    
	public void setFechaInicioSeleccionada(Date datafim) {
		
		this.fechaInicioSeleccionada = datafim;
	}
	public int getMes() {
		return Mes;
	}
	public void setMes(int mes) {
		Mes = mes;
	}
	public List<ProdutoModel> getProdutos() {
		return listProdutos;
	}
	public void setProdutos(List<ProdutoModel> produtos) {
		this.listProdutos = produtos;
	}
	
	public DespesaFuturaModel getDespesaModel() {
		return despesaModel;
	}
 
	public void setDespesaModel(DespesaFuturaModel despesaModel) {
		this.despesaModel = despesaModel;
	}
	
	@PostConstruct
	public void init(){
 
		loadComboProdutos();
	}
	
	//Loading the items
    private void loadComboProdutos() {
    	
        this.listProdutos = produtoRepository.GetProdutos();

    }
    
    /***
	 * ATUALIZA O REGISTRO QUE FOI ALTERADO
	 */
	public void AlterarRegistro(){
 
		this.despesaFuturaRepository.AlterarRegistro(this.despesaModel);	
        
 
		/*RECARREGA OS REGISTROS*/
		this.init();
		Uteis.MensagemInfo("Atualizado com sucesso");
	}
 
	/**
	 *SALVA UM NOVO REGISTRO VIA INPUT 
	 */
	public void SalvarDespesa(){
     int mes;
     mes = CadastrarDespesaFuturaController.GetMonth(despesaModel.getDataLimite());
		this.Valida();
		
		if( mes == 12 )
		{
			this.despesaModel.setMes(1);
		
		}else {
			this.despesaModel.setMes((mes + 1));
		}
		despesaFuturaRepository.SalvarDespesa(this.despesaModel);
		//this.despesaModel = null;
		Uteis.MensagemInfo("Registro cadastrado com sucesso");
 
	}
    
	 public void handleDateSelect(SelectEvent event) {

		 this.fechaInicioSeleccionada = (Date) event.getObject();


     }
	 
	 private void Valida()
	 {
			int mesNovo = 0;
			mesNovo = CadastrarDespesaFuturaController.GetMonth(despesaModel.getDataLimite());
	  	    if (mesNovo == 12 ) { mesNovo = 1; } else {mesNovo++;}
	        ExercicioModel conf = new ExercicioModel();
	        conf = exercicioRepository.GetLastValidConfExercicio();

	        if (mesNovo <= conf.getCompetencia() && despesaModel.getExercicio() == conf.getExercicio())
	        {
	        	Uteis.MensagemInfo("A competência selecionada já foi fechada!");
	        	return;
	        }
	        
	        if (mesNovo <= conf.getCompetencia() && despesaModel.getExercicio() < conf.getExercicio())
	        {
	        	Uteis.MensagemInfo("O exercício está incorreto!");
	        	return;
	        }

	       
//	        
	  }

	 private static  Date addOneMonth(Date date)
		{
		    Calendar cal = Calendar.getInstance();
		    cal.setTime(date);
		    cal.add(Calendar.MONTH, 1);
		    return cal.getTime();
		}
		
		private static  int GetMonth(Date date)
		{
		    Calendar cal = Calendar.getInstance();
		    cal.setTime(date);
		    return cal.get(cal.MONTH);
		}
		
		private static  int GetYear(Date date)
		{
		    Calendar cal = Calendar.getInstance();
		    cal.setTime(date);
		    return cal.getWeekYear();
		}
}
