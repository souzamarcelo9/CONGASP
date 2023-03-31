package br.com.congasp.produto.controller;

import java.io.IOException;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jws.WebMethod;

import br.com.congasp.model.ProdutoModel;
import br.com.congasp.repository.ProdutoRepository;
import br.com.congasp.uteis.Uteis;
 
@Named(value="cadastrarProdutoController")
@RequestScoped
public class CadastrarProdutoController {
 
	@Inject
	ProdutoModel produtoModel;
 
	@Inject
	ProdutoRepository produtoRepository;
 
 
	public ProdutoModel getProdutoModel() {
		return produtoModel;
	}
 
	public void setProdutoModel(ProdutoModel produtoModel) {
		this.produtoModel = produtoModel;
	}
 
	/**
	 *SALVA UM NOVO REGISTRO VIA INPUT 
	 */
	
	public void SalvarProduto(){
 
		produtoRepository.SalvarProduto(this.produtoModel);
 
		this.produtoModel = null;
 
		Uteis.MensagemInfo("Registro cadastrado com sucesso");
 
	}
 
//	/**
//	 * REALIZANDO UPLOAD DE ARQUIVO
//	 */
//	 public void UploadRegistros() {
// 
//		 DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
// 
//		 try {
// 
// 
//			 if(this.file.getFileName().equals("")){
//				 Uteis.MensagemAtencao("Nenhum arquivo selecionado!");
//				 return;
//			 }
// 
//			 DocumentBuilder builder = factory.newDocumentBuilder();
// 
//	                 Document doc = builder.parse(this.file.getInputstream());
// 
//	                 Element element = doc.getDocumentElement();
// 
//	                 NodeList nodes = element.getChildNodes();
// 
//	                for (int i = 0; i < nodes.getLength(); i++) {
// 
//	        	     Node node  = nodes.item(i);
// 
//	        	    if(node.getNodeType() == Node.ELEMENT_NODE){
// 
//	        		 Element elementPessoa =(Element) node;
// 
//	        		 //PEGANDO OS VALORES DO ARQUIVO XML
//	        		 String nome     = elementPessoa.getElementsByTagName("nome").item(0).getChildNodes().item(0).getNodeValue();
//	        		 String sexo     = elementPessoa.getElementsByTagName("sexo").item(0).getChildNodes().item(0).getNodeValue();
//	        		 String email    = elementPessoa.getElementsByTagName("email").item(0).getChildNodes().item(0).getNodeValue();
//	        		 String endereco = elementPessoa.getElementsByTagName("endereco").item(0).getChildNodes().item(0).getNodeValue();
// 
//	        		 PessoaModel newPessoaModel = new PessoaModel();
// 
//	        		 newPessoaModel.setUsuarioModel(this.usuarioController.GetUsuarioSession());
//	        		 newPessoaModel.setEmail(email);
//	        		 newPessoaModel.setEndereco(endereco);
//	        		 newPessoaModel.setNome(nome);
//	        		 newPessoaModel.setOrigemCadastro("X");
//	        		 newPessoaModel.setSexo(sexo);
// 
//	        		 //SALVANDO UM REGISTRO QUE VEIO DO ARQUIVO XML
//	        		 pessoaRepository.SalvarNovoRegistro(newPessoaModel);
//	        	   }
//	              }
// 
// 
// 
//		     Uteis.MensagemInfo("Registros cadastrados com sucesso!");
// 
//		} catch (ParserConfigurationException e) {
// 
//			e.printStackTrace();
// 
//		} catch (SAXException e) {
// 
//			e.printStackTrace();
// 
//		} catch (IOException e) {
// 
//			e.printStackTrace();
//		}
// 
// 
//	 }
 
}