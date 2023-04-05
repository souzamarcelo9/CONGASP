package br.com.congasp.fechamento.controller;

import java.io.IOException;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.SelectEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import br.com.congasp.model.DespesaFuturaModel;
import br.com.congasp.model.DespesaModel;
import br.com.congasp.model.ExercicioModel;
import br.com.congasp.model.ProdutoModel;
import br.com.congasp.repository.DespesaFuturaRepository;
import br.com.congasp.repository.DespesaRepository;
import br.com.congasp.repository.ExercicioRepository;
import br.com.congasp.repository.ProdutoRepository;
import br.com.congasp.repository.entity.DespesaEntity;
import br.com.congasp.repository.entity.ProdutoEntity;
import br.com.congasp.uteis.Uteis;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@Named(value="fechamentoController")
@RequestScoped
public class FechamentoController implements Serializable {
	
	private static final long serialVersionUID = 1L;

	
	@Inject
	DespesaModel despesaModel;
	
	@Inject
	ExercicioModel exercicioModel;
 
	@Inject
	DespesaRepository despesaRepository;
	
	@Inject
	ProdutoRepository produtoRepository;
	
	@Inject
	DespesaFuturaRepository despesaFuturaRepository;
	
	@Inject 
	ExercicioRepository exercicioRepository;
 

	@Produces
	private List<ProdutoModel> listProdutos;
	
	private int Mes;
	
	private boolean custoFixo;
	
	//private boolean despesaFutura;
	
	private Date fechaInicioSeleccionada ;
	
	private Date novaCompetencia;
	
	//Get Setters
	public Date getnovaCompetencia() {
		return novaCompetencia;
	}
    
	public void setNovaCompetencia(Date novaCompetencia) {
		
		this.novaCompetencia = novaCompetencia;
	}
	
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
	
	public boolean  getCustoFixo() {
		return custoFixo;
	}
	public void setCustoFixo(boolean custoFixo) {
		this.custoFixo = custoFixo;
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
	
	public ExercicioModel getExercicioModel() {
		return exercicioModel;
	}
 
	public void setProdutoModel(ExercicioModel exercicioModel) {
		this.exercicioModel = exercicioModel;
	}
	
	@PostConstruct
	public void init(){
 
		
	}
	
    
	 public void handleDateSelect(SelectEvent event) {

		 this.fechaInicioSeleccionada = (Date) event.getObject();


     }

	public void CheckOpenItems()
	{
     List<DespesaModel> despesas = new ArrayList<DespesaModel>();
	 String ok = "";
     
     despesas = despesaRepository.GetDespesasFilter(despesaModel.getMes()
    		                                       ,despesaModel.getExercicio()
    		                                       ,despesaModel.getObservacao()
    		                                       ,despesaModel.getValor());

     try
     {
    	 //checa se as despesas foram pagas
    	 for( DespesaModel despesa : despesas) 
    	 {
    	 
	         if (despesa.getData_pagamento() == null)
	         {
	        	 Uteis.MensagemInfo("Você possui despesas em aberto.Favor chegar em Pagamentos-Despesas Mensais.");
	         }
	         else
	         {
	        	 ok = "X";
	        	 //Uteis.MensagemInfo("Todas as despesas do mês pagas, parabéns!.");
	         }
	         
	        
    	 }
    	 
    	 if (ok =="X") {
	        	Uteis.MensagemInfo("Todas as despesas do mês pagas, parabéns!.");
	        }
     }

     
     catch (Exception ex)
     {
    	 Uteis.MensagemInfo(ex.getMessage());
     }
 
	}
	
	public void CloseCopying() 
	{
	
	 String mes = "";
	 int mesInt = 0;
	 int mesDespesa = 0;
     DespesaModel despesaNova = null;
     DespesaEntity despesaBD = new DespesaEntity();
     ProdutoEntity produtoBD = new ProdutoEntity();
     List<DespesaModel> listaDespesasAtual;
     List<DespesaModel> listaDespesasNova = new ArrayList<DespesaModel>();
     List<ProdutoModel> listaProdutos = new ArrayList<ProdutoModel>();
     List<DespesaFuturaModel> listaDespesasFuturas = new ArrayList<DespesaFuturaModel>();



     try
     {
         listaDespesasAtual = despesaRepository.GetDespesasFilter(despesaModel.getMes()
        		 												,despesaModel.getExercicio()
        		 												,despesaModel.getObservacao()
        		 												,despesaModel.getValor());
         listaProdutos = produtoRepository.GetProdutos();
         this.ValidaFechamento();

         
         for( DespesaModel despesaCopia : listaDespesasAtual) 
         {
             produtoBD = produtoRepository.GetProduto(despesaCopia.getProdutoModel().getIdProduto());

             if (custoFixo == true)
             {
                 if (produtoBD.getCodClasseCusto() == "1")
                 {
                     despesaCopia.setExercicio(String.valueOf(FechamentoController.GetYear(novaCompetencia)));
                     despesaCopia.setMes(FechamentoController.GetMonth(novaCompetencia));
                     
                     if (despesaCopia.getMes() == 12) {
                    	 despesaCopia.setMes(1);
                    	 
                     }else {
                    		 despesaCopia.setMes(despesaCopia.getMes() + 1); 
                    }
                    	 
                     
                     despesaCopia.setEstorno("");
                     despesaCopia.setId_produto(despesaCopia.getProdutoModel().getIdProduto());
                     despesaCopia.setCodigo(null);
                     despesaCopia.setData_pagamento(null);
                     despesaCopia.setValor_pago(null);
                     despesaCopia.setData_limite( FechamentoController.addOneMonth(despesaCopia.getData_limite()));
                     despesaCopia.setData_despesa(FechamentoController.addOneMonth(despesaCopia.getData_despesa()));
                     despesaCopia.setDebito_automatico(despesaCopia.getDebito_automatico());
                     
                     despesaRepository.SalvarDespesa(despesaCopia);
                 }
             }
             else
             {
            	 despesaCopia.setExercicio(String.valueOf(FechamentoController.GetYear(novaCompetencia)));
            	 despesaCopia.setMes(FechamentoController.GetMonth(novaCompetencia));
            	 if (despesaCopia.getMes() == 12) {
                	 despesaCopia.setMes(1);
                	 
                 }
                 
                	 else {
                		 despesaCopia.setMes(despesaCopia.getMes() + 1);
                	 }
                	 
                 }
                 despesaCopia.setCodigo(null);
                 despesaCopia.setId_produto(despesaCopia.getProdutoModel().getIdProduto());
                 despesaCopia.setEstorno("");
                 despesaCopia.setData_pagamento(null);
                 despesaCopia.setValor_pago(null);
                 despesaCopia.setData_limite( FechamentoController.addOneMonth(despesaCopia.getData_limite()));
                 despesaCopia.setData_despesa(FechamentoController.addOneMonth(despesaCopia.getData_despesa()));
                 despesaCopia.setDebito_automatico(despesaCopia.getDebito_automatico());
                 
                 despesaRepository.SalvarDespesa(despesaCopia);
             
         }
         //COPIA AS DESPESAS FUTURAS PARA NO NOVO EXERCÍCIO
         mesInt = FechamentoController.GetMonth(novaCompetencia);
         if (mesInt == 12) {
        	 mesInt = 1;
        	 
         }
         
        	 else {
        		 mesInt++;
        	 }
        	 
         listaDespesasFuturas = despesaFuturaRepository.GetDespesas();      
//        		  despesaBD.ListarDespesasFuturas().Where(it=>it.DataLimite.Month == mesInt
//                  && it.DataLimite.Year.ToString() == dtpNovoExercicio.Value.Year.ToString()).ToList();
         
         for( DespesaFuturaModel despesaCopia : listaDespesasFuturas) 
         
         {       
        	 mesDespesa = FechamentoController.GetMonth(despesaCopia.getDataLimite());
        	   if (mesDespesa == 12 ) { mesDespesa = 1; } else {mesDespesa++;}
        	 
        	   if( mesDespesa == mesInt &&  FechamentoController.GetYear(novaCompetencia) == Integer.parseInt(despesaModel.getExercicio())) {
        		   
        	  
        	     despesaNova = new DespesaModel();
        	     despesaNova.setMes(mesInt);
        	     despesaNova.setId_produto(despesaCopia.getProdutoModel().getIdProduto());
        	     despesaNova.setExercicio(String.valueOf(FechamentoController.GetYear(novaCompetencia)));
        	     despesaNova.setDebito_automatico("");
        	     despesaNova.setObservacao(despesaCopia.getObservacao());
        	     despesaNova.setData_despesa(despesaCopia.getDataDespesa());
        	     despesaNova.setData_limite(despesaCopia.getDataLimite());
        	 	 despesaNova.setValor(despesaCopia.getValor());
        	 	
        	 		
                 despesaRepository.SalvarDespesa(despesaNova);
                 
                 despesaFuturaRepository.Excluir(despesaCopia.getIdDespesa());
        	   }
                 
           
         }
        // FIM DAS DESPESAS FUTURAS

  //     Acerta o Exercício
         ExercicioModel conf = new ExercicioModel();
         conf.setAtivo("X");
         conf.setDataAtivacao(despesaModel.getData_limite());
         conf.setExercicio(String.valueOf(FechamentoController.GetYear(novaCompetencia)));
         conf.setCompetencia(despesaNova.getMes());

         exercicioRepository.Salvar(conf);
         Uteis.MensagemInfo("Fechamento realizado com sucesso e o novo exercício foi aberto com as despesas anteriores.");

     }
     catch (Exception ex)
     {
    	 Uteis.MensagemInfo(ex.getMessage());
     }
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
	
	
	private void ValidaFechamento()
    {
		int mesNovo = 0;
		mesNovo = FechamentoController.GetMonth(novaCompetencia);
  	    if (mesNovo == 12 ) { mesNovo = 1; } else {mesNovo++;}
        ExercicioModel conf = new ExercicioModel();
        conf = exercicioRepository.GetLastValidConfExercicio();

        if (mesNovo < conf.getCompetencia() && (String.valueOf(FechamentoController.GetYear(novaCompetencia)) == conf.getExercicio()))
        {
        	Uteis.MensagemInfo("A competência selecionada já foi fechada!");
        	return;
        }

        if (mesNovo != conf.getCompetencia() && String.valueOf(FechamentoController.GetYear(novaCompetencia)) == conf.getExercicio())
        {
        	Uteis.MensagemInfo("A competência selecionada está bloqueada no momento!");
        	return;
        }

        if (despesaModel.getMes() == mesNovo && FechamentoController.GetYear(novaCompetencia) == despesaModel.getMes())
        {
        	Uteis.MensagemInfo("Selecione uma nova competência diferente da competência que estás a fechar.");
        	return;
        }

        if ((conf.getAtivo().isEmpty() || conf.getAtivo() == null )  
            && mesNovo < conf.getCompetencia()
            && despesaModel.getExercicio() == conf.getExercicio())
        {
        	Uteis.MensagemInfo("A competência selecionada para fechamento, no momento está bloqueada.");
        	return;
        }

        if (conf.getAtivo() == " ")
        {
            if( despesaModel.getMes() == conf.getCompetencia())
            {
                if(despesaModel.getExercicio() == conf.getExercicio())
                {
                	Uteis.MensagemInfo("A competência selecionada para fechamento, no momento está bloqueada.");
                	return;
                }
            }
        }
//        
    }
	 
//	private void CarregaComboExercicio()
//    {
//        Dictionary<int, int> test = new Dictionary<int, int>();
//        int ano = DateTime.Now.Year;
//        test.Add(ano, ano);
//        test.Add(ano - 1, ano - 1);
//        test.Add(ano - 2, ano - 2);
//       
//        cmbExercicio.DataSource = new BindingSource(test, null);
//        cmbExercicio.DisplayMember = "Key";
//        cmbExercicio.ValueMember = "Value";
//    }
//	
	 private void AbrirNovaCompetencia()
     {
        // Despesa despesa = new Despesa();
        // Despesa despesaBD = new Despesa();

         try
         {
             this.ValidaFechamento();

             //Acerta o Exercício
             ExercicioModel conf = new ExercicioModel();
             conf.setAtivo("X");
             conf.setDataAtivacao(despesaModel.getData_limite());
             conf.setExercicio(String.valueOf(FechamentoController.GetYear(novaCompetencia)));
             conf.setCompetencia(FechamentoController.GetYear(novaCompetencia));

             exercicioRepository.Salvar(conf);
             Uteis.MensagemInfo("Fechamento realizado com sucesso e o novo exercício foi aberto.");

         }
         catch (Exception ex)
         {
        	 Uteis.MensagemInfo(ex.getMessage());
         }
     }
		
		
}

