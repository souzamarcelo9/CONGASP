package br.com.congasp.despesaFutura.controller;

import java.io.Serializable;
import java.util.List;
 
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import br.com.congasp.model.DespesaFuturaModel;
import br.com.congasp.repository.DespesaFuturaRepository;

 
@Named(value="consultarDespesaFuturaController")
@ViewScoped
public class ConsultarDespesaFuturaController implements Serializable  {
 

	private static final long serialVersionUID = 1L;
	
	@Inject transient
	private DespesaFuturaModel despesaModel;
 
	@Produces 
	private List<DespesaFuturaModel> despesas;
 
	@Inject transient
	private DespesaFuturaRepository despesaRepository;
 
	public List<DespesaFuturaModel> getDespesas() {
		return despesas;
	}
	public void setDespesas(List<DespesaFuturaModel> despesas) {
		this.despesas = despesas;
	}		
	public DespesaFuturaModel getDespesaModel() {
		return despesaModel;
	}
	public void setDespesaModel(DespesaFuturaModel despesaModel) {
		this.despesaModel = despesaModel;
	}
 
	/***
	 * CARREGA AS DESPESAS NA INICIALIZAÇÃO 
	 */
	@PostConstruct
	public void init(){
 
		//RETORNAR AS PESSOAS CADASTRADAS
		this.despesas = despesaRepository.GetDespesas();
	}
	/***
	 * CARREGA INFORMAÇÕES DE UMA DESPESA PARA SER EDITADA
	 * @param pessoaModel
	 */
	public void Editar(DespesaFuturaModel despesaModel){
 
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
	
	
	public void Excluir(DespesaFuturaModel despesaModel){
 
		//EXCLUI A PESSOA DO BANCO DE DADOS
		this.despesaRepository.Excluir(despesaModel.getIdDespesa());
 
		//REMOVENDO A PESSOA DA LISTA
		//ASSIM QUE É A PESSOA É REMOVIDA DA LISTA O DATATABLE É ATUALIZADO
		this.despesas.remove(despesaModel);
 
	}
	
	public void List(DespesaFuturaModel despesaModel){
		 
		this.despesas = despesaRepository.GetDespesasByPeriod(despesaModel.getMes(),despesaModel.getExercicio());
	}
 
 
}
