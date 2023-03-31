package br.com.congasp.banco.controller;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import br.com.congasp.model.BancoModel;
import br.com.congasp.repository.BancoRepository;


@Named(value="consultarBancoController")
@ViewScoped

public class ConsultarBancoController implements Serializable {

	
	private static final long serialVersionUID = 1L;
	 
	@Inject transient
	private BancoModel bancoModel;
 
	@Produces 
	private List<BancoModel> bancos;
 
	@Inject transient
	private BancoRepository bancoRepository;
 
	
	public List<BancoModel> getBancos() {
		return bancos;
	}
	public void setBancos(List<BancoModel> bancos) {
		this.bancos = bancos;
	}		
	public BancoModel getBancoModel() {
		return bancoModel;
	}
	public void setBancoModel(BancoModel bancoModel) {
		this.bancoModel = bancoModel;
	}
 
	/***
	 * CARREGA OS PRODUTOS
	 */
	@PostConstruct
	public void init(){
 
		this.bancos = bancoRepository.GetBancos();
	}
	
	/***
	 * CARREGA INFORMAÇÕES DE UM PRODUTO PARA SER EDITADO
	 * @param pessoaModel
	 */
	public void Editar(BancoModel bancoModel){
 
		this.bancoModel = bancoModel;
 
	}
	
	/***
	 * ATUALIZA O REGISTRO QUE FOI ALTERADO
	 */
//	public void AlterarRegistro(){
// 
//		this.bancoRepository.(this.bancoModel);	
// 
// 
//		/*RECARREGA OS REGISTROS*/
//		this.init();
//	}
	
	public void ExcluirBanco(BancoModel bancoModel){
		 
		//EXCLUI O PRODUTO DO BANCO
		this.bancoRepository.Excluir(bancoModel.getCodBanco());
 
		
		//REMOVE PRODUTO
		this.bancos.remove(bancoModel);
 
	}
	
}

