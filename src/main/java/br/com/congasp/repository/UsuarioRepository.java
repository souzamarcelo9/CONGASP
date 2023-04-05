package br.com.congasp.repository;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.activity.InvalidActivityException;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.congasp.model.PessoaModel;
import br.com.congasp.model.UsuarioModel;
import br.com.congasp.repository.entity.PessoaEntity;
import br.com.congasp.repository.entity.UsuarioEntity;
import br.com.congasp.uteis.Uteis;
 
 
public class UsuarioRepository implements Serializable {
 
 
	private static final long serialVersionUID = 1L;
	
	@Inject
	UsuarioEntity usuarioEntity;
 
	EntityManager entityManager;
 
	public UsuarioEntity ValidaUsuario(UsuarioModel usuarioModel) throws InvalidActivityException{
 
		try {
 
			//QUERY QUE VAI SER EXECUTADA (UsuarioEntity.findUser) 	
			Query query = Uteis.JpaEntityManager().createNamedQuery("UsuarioEntity.findUser");
 
			//PARÂMETROS DA QUERY
			query.setParameter("usuario", usuarioModel.getUsuario());
			query.setParameter("senha", usuarioModel.getSenha());
 
			//RETORNA O USUÁRIO SE FOR LOCALIZADO
			return (UsuarioEntity)query.getSingleResult();
 
		} 
		catch (Exception e) 		
		{
 
			throw new InvalidActivityException(e.getMessage());
		}
 
 
	}
	
	public void SalvarNovoRegistro(UsuarioModel usuarioModel) throws InvalidActivityException{
		 
		entityManager =  Uteis.JpaEntityManager();
 
		usuarioEntity = new UsuarioEntity();
		usuarioEntity.setUsuario(usuarioModel.getUsuario());
		usuarioEntity.setSenha(usuarioModel.getSenha());
		usuarioEntity.setStatus(usuarioModel.getStatus());
 
		entityManager.persist(usuarioEntity);
 
	}
}