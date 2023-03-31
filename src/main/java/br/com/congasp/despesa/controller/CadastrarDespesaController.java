package br.com.congasp.despesa.controller;
import java.io.IOException;
import java.io.Serializable;

import javax.activity.InvalidActivityException;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.congasp.fechamento.controller.FechamentoController;
import br.com.congasp.model.DespesaModel;
import br.com.congasp.model.ExercicioModel;
import br.com.congasp.model.ProdutoModel;
import br.com.congasp.repository.DespesaRepository;
import br.com.congasp.repository.ExercicioRepository;
import br.com.congasp.repository.ProdutoRepository;
import br.com.congasp.uteis.Uteis;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@Named(value="cadastrarDespesaController")
@RequestScoped
public class CadastrarDespesaController implements Serializable {
	
	private static final long serialVersionUID = 1L;

	
	@Inject
	DespesaModel despesaModel;
 
	@Inject
	DespesaRepository despesaRepository;
	
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
	
	public DespesaModel getDespesaModel() {
		return despesaModel;
	}
 
	public void setDespesaModel(DespesaModel despesaModel) {
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
	
//    private void CarregaComboAno()
//     {
//         Dictionary<int, int> test = new Dictionary<int, int>();
//         int ano = DateTime.Now.Year;
//         int indice = 0;
//         test.Add(ano, ano);
//         test.Add(ano - 1, ano - 1);
//         test.Add(ano - 2, ano - 2);
//         test.Add(ano + 1, ano + 1);
//         test.Add(ano + 2, ano + 2);
//
//         test.OrderBy(it => it.Value);
//         cmbAno.DataSource = new BindingSource(test, null);
//         cmbAno.DisplayMember = "Key";
//         cmbAno.ValueMember = "Value";
//
//         Configuracao confExercicio = new Configuracao();
//         Configuracao confBD = new Configuracao();
//         confExercicio = confBD.GetLastValidConfExercicio();
//
//         cmbAno.SelectedValue = int.Parse(confExercicio.Exercicio);
//     }
//  
 
	/**
	 *SALVA UM NOVO REGISTRO VIA INPUT 
	 */
	public void SalvarDespesa(){
       
     try 
     {
    	     
		this.Valida();
		despesaRepository.SalvarDespesa(this.despesaModel);
		Uteis.MensagemInfo("Registro cadastrado com sucesso");
		//this.despesaModel = null;
		
     }
     catch(Exception ex) {
    	 Uteis.MensagemInfo(ex.getMessage());
    	 
     }
    
	}
    
	 public void handleDateSelect(SelectEvent event) {

		 this.fechaInicioSeleccionada = (Date) event.getObject();


     }
	 
	 private void Valida() throws IOException
	 {
			
	        ExercicioModel conf = new ExercicioModel();
	        conf = exercicioRepository.GetLastValidConfExercicio();
           
	        
	        if (despesaModel.getMes() != conf.getCompetencia() && despesaModel.getExercicio() != conf.getExercicio())
	        {
	        	throw new InvalidActivityException("A competência selecionada está bloqueada no momento!");
	        	//Uteis.MensagemInfo("A competência selecionada está bloqueada no momento!");
	        	//return;
	        }
	        
	        if (despesaModel.getMes() != conf.getCompetencia() && despesaModel.getExercicio() == conf.getExercicio())
	        {
	        	throw new InvalidActivityException("A competência selecionada está bloqueada no momento!");
	        	//Uteis.MensagemInfo("A competência selecionada está bloqueada no momento!");
	        	//return;
	        }
            
	       
	  }
//	        
}
 
	

