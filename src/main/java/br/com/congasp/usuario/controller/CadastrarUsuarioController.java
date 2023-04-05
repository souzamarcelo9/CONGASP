package br.com.congasp.usuario.controller;

import java.io.IOException;

import javax.activity.InvalidActivityException;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
 
import org.primefaces.model.UploadedFile;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
 
import br.com.congasp.model.PessoaModel;
import br.com.congasp.model.UsuarioModel;
import br.com.congasp.repository.PessoaRepository;
import br.com.congasp.repository.UsuarioRepository;
import br.com.congasp.usuario.controller.UsuarioController;
import br.com.congasp.uteis.Uteis;
 
@Named(value="cadastrarUsuarioController")
@RequestScoped
public class CadastrarUsuarioController {
 
	@Inject
	UsuarioModel usuarioModel;	
 
	@Inject
	UsuarioRepository usuarioRepository;
	
 
	public UsuarioModel getUsuarioModel() {
		return usuarioModel;
	}
 
	public void setUsuarioModel(UsuarioModel usuarioModel) {
		this.usuarioModel = usuarioModel;
	}
 
	/**
	 *SALVA UM NOVO REGISTRO VIA INPUT 
	 */
	public void Salvar() throws InvalidActivityException{
		
		try
		{
			usuarioRepository.SalvarNovoRegistro(this.usuarioModel);
	 
			this.usuarioModel = null;
	 
			Uteis.MensagemInfo("Registro cadastrado com sucesso");
		}
		catch(Exception ex)
		{
			throw new InvalidActivityException(ex.getMessage());
		}
 
	}
 
	/**
	 * REALIZANDO UPLOAD DE ARQUIVO
	 */
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