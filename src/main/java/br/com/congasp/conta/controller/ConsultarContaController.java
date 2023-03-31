package br.com.congasp.conta.controller;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import br.com.congasp.model.ContaModel;
import br.com.congasp.repository.ContaRepository;
import br.com.congasp.repository.entity.ContaEntity;


@Named(value="consultarContaController")
@ViewScoped

public class ConsultarContaController implements Serializable {

	
	private static final long serialVersionUID = 1L;
	 
	@Inject transient
	private ContaModel contaModel;
 
	@Produces 
	private List<ContaModel> contas;
 
	@Inject transient
	private ContaRepository contaRepository;
 
	
	public List<ContaModel> getContas() {
		return contas;
	}
	public void setContas(List<ContaModel> contas) {
		this.contas = contas;
	}		
	public ContaModel getContaModel() {
		return contaModel;
	}
	public void setContaModel(ContaModel contaModel) {
		this.contaModel = contaModel;
	}
 
	/***
	 * CARREGA AS CONTAS
	 */
	@PostConstruct
	public void init(){
 
		this.contas = contaRepository.GetContas();
	}
	

	
}

