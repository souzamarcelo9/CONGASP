package br.com.congasp.despesa.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.activity.InvalidActivityException;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.congasp.model.DespesaModel;
import br.com.congasp.model.ExercicioModel;
import br.com.congasp.model.PessoaModel;
import br.com.congasp.repository.DespesaRepository;
import br.com.congasp.repository.ExercicioRepository;
import br.com.congasp.uteis.Uteis;

 
@Named(value="consultarDespesaController")
@ViewScoped
public class ConsultarDespesaController implements Serializable  {
 

	private static final long serialVersionUID = 1L;
	
	@Inject transient
	private DespesaModel despesaModel;
 
	@Produces 
	private List<DespesaModel> despesas;
 
	@Inject transient
	private DespesaRepository despesaRepository;
	
	@Inject transient
	private ExercicioRepository exercicioRepository;
	
	private double saldoTotal;
	
	private List<SelectItem> anos;
	
	private String selectedOption;
	
	
	
 
	public String getSelectedOption() {
		return selectedOption;
	}
	public void setSelectedOption(String selectedOption) {
		this.selectedOption = selectedOption;
	}
	
	public List<SelectItem> getAnos() {
		return anos;
	}
	public void setAnos(List<SelectItem> anos) {
		this.anos = anos;
	}

	public double getSaldoTotal() {
		return saldoTotal;
	}
	public void setSaldoTotal(double saldoTotal) {
		this.saldoTotal = saldoTotal;
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
 
	/***
	 * CARREGA AS DESPESAS NA INICIALIZAÇÃO 
	 */
	@PostConstruct
	public void init(){
       //this.AtualizaLabelSaldo();
       Date date = new Date();
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
		cal.setTime(date);
		Integer year = cal.get(Calendar.YEAR);
		Integer year1;
		Integer year2;
		Integer year3;
		int anoP = year;
		int anoP1 = anoP - 1;
		int anoP2 = anoP1 - 1;
		int anoP3 = anoP2 - 1;
		
		year1 = anoP1;
		year2 = anoP2;
		year3 = anoP3;
		
		
		this.selectedOption = year.toString();
		anos = new ArrayList<>();

       SelectItemGroup anosCalendario = new SelectItemGroup("Anos");
       anosCalendario.setSelectItems(new SelectItem[]{
           new SelectItem(anoP, year.toString()),           
           new SelectItem(anoP1, year1.toString()),
           new SelectItem(anoP2, year2.toString()),
           new SelectItem(anoP3, year3.toString()),           
       });
       
       anos.add(anosCalendario);
		
		//this.despesas = despesaRepository.GetDespesas();
       
	}
	
	public void onload(){
		Date date = new Date();
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
		cal.setTime(date);
		Integer year = cal.get(Calendar.YEAR);
		
		this.selectedOption = year.toString();
		anos = new ArrayList<>();

        SelectItemGroup anosCalendario = new SelectItemGroup("Anos");
        anosCalendario.setSelectItems(new SelectItem[]{
            new SelectItem(year, year.toString()),
            new SelectItem(year - 1, year.toString()),
            new SelectItem(year - 1, year.toString()),
            new SelectItem(year - 1, year.toString()),           
        });
        
        anos.add(anosCalendario);
		
	}
	/***
	 * CARREGA INFORMAÇÕES DE UMA DESPESA PARA SER EDITADA
	 * @param pessoaModel
	 */
	public void Editar(DespesaModel despesaModel){
 
		this.despesaModel = despesaModel;
 
	}
 
	/***
	 * ATUALIZA O REGISTRO QUE FOI ALTERADO
	 */
	public void AlterarRegistro(){
 
		this.despesaRepository.AlterarRegistro(this.despesaModel);	
 
 
		/*RECARREGA OS REGISTROS*/
		this.init();
	}
	
	
	public void Excluir(DespesaModel despesaModel){
       
	try
	{ 
		this.Valida(despesaModel.getCodigo());
		this.despesaRepository.Excluir(despesaModel.getCodigo());
		this.despesas.remove(despesaModel);
		
	}
	catch(Exception ex) {
       Uteis.Mensagem(ex.getMessage());
	}
	
	}
	
	public void List(DespesaModel despesaModel){
		
		this.despesas = despesaRepository.GetDespesasFilter(despesaModel.getMes()
															 ,despesaModel.getSelectedOption()
															 ,despesaModel.getObservacao()
															 ,despesaModel.getValor());
	}
	
	
	private void Valida(int codigoDespesa) throws IOException
	 {
			
	        ExercicioModel conf = new ExercicioModel();
	        conf = exercicioRepository.GetLastValidConfExercicio();
            DespesaModel despesaBd = this.despesaRepository.GetSingle(codigoDespesa);
	        	        
	        
	        if (despesaBd.getMes() != conf.getCompetencia() && despesaBd.getExercicio() != conf.getExercicio())
	        {   
	        	throw new InvalidActivityException("A competência selecionada está bloqueada no momento!");
	        	//Uteis.MensagemInfo("A competência selecionada está bloqueada no momento!");
	        	//return;
	        }
	        
	        if (despesaBd.getMes() != conf.getCompetencia() && despesaBd.getExercicio() == conf.getExercicio())
	        {
	        	throw new InvalidActivityException("A competência selecionada está bloqueada no momento!");
	        	//teis.MensagemInfo("A competência selecionada está bloqueada no momento!");
	        	//return;
	        }
	        
	        if(despesaBd.getData_pagamento() != null) {
	        	throw new InvalidActivityException("Não é possível excluir uma despesa já paga");
	        	//Uteis.MensagemInfo("Não é possível excluir uma despesa já paga");
	        	//return;
	        	
	        }
           
	       
	  }
	
	private void AtualizaLabelSaldo()
     {
         //DespesaModel despesa = new DespesaModel();
         List<DespesaModel> listaDespesas = new ArrayList<DespesaModel>();
         //int mes;
         //ExercicioModel confBD = new ExercicioModel();
         //confBD = exercicioRepository.GetLastValidConfExercicio();

//         if (cmbMes.SelectedIndex == 0)
//         {
//             mes = "1";
//         }
//         else if (cmbMes.SelectedIndex == 11)
//         {
//             mes = "12";
//         }
//         else
//         {
//             mes = (cmbMes.SelectedIndex + 1).ToString();
//         }

         listaDespesas = despesaRepository.GetDespesasFilter(despesaModel.getMes()
        		 										   , despesaModel.getExercicio()
        		 										   , despesaModel.getObservacao()
        		 										   ,despesaModel.getValor());
        
         for (DespesaModel fn : listaDespesas)
         {
             if (fn.getData_pagamento() == null)
             {
                 saldoTotal = saldoTotal + fn.getValor();
                 
             }
         }
     }
	
//	 private void CarregaLimiteDisponivel()
//     {
//         ContaCapital conta = new ContaCapital();
//         List<ContaCapital> listaContas = new List<ContaCapital>();
//         decimal saldo = 0;
//
//         listaContas = conta.Listar();
//
//         foreach (ContaCapital contaBd in listaContas)
//         {
//             saldo += contaBd.Saldo;
//         }
//
//         txtLimite.Text = saldo.ToString();
//     }
//
//     private void pctBox1_Click(object sender, EventArgs e)
//     {
//         this.CarregaLimiteDisponivel();
//     }
//
//     private void CarregaContaDefault()
//     {
//         Configuracao conta = new Configuracao();
//         lblContaDefault.Text = conta.GetContaDefault().ContaDefault;
//     }
 
 
}
