//package br.com.congasp.banco.controller;
//
//import java.io.IOException;
//import javax.enterprise.context.RequestScoped;
//import javax.inject.Inject;
//import javax.inject.Named;
//
//import br.com.congasp.model.BancoModel;
//import br.com.congasp.repository.BancoRepository;
//import br.com.congasp.uteis.Uteis;
// 
//@Named(value="cadastrarBancoController")
//@RequestScoped
//public class CadastrarBancoController {
// 
//	@Inject
//	BancoModel bancoModel;
// 
//	@Inject
//	BancoRepository bancoRepository;
// 
// 
//	public BancoModel getBancoModel() {
//		return bancoModel;
//	}
// 
//	public void setBancoModel(BancoModel bancoModel) {
//		this.bancoModel = bancoModel;
//	}
// 
//	/**
//	 *SALVA UM NOVO REGISTRO VIA INPUT 
//	 */
//	public void Salvar(){
// 
//		bancoRepository.SalvarBanco(this.bancoModel);
// 
//		this.bancoModel = null;
// 
//		Uteis.MensagemInfo("Banco cadastrado com sucesso");
// 
//	}
// 
//
// 
//}