package br.com.congasp.repository.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
 
@Entity
@Table(name="tb_exercicio")
 
@NamedQueries({
 
	@NamedQuery(name = "ExercicioEntity.findAll",query= "SELECT e FROM ExercicioEntity e ")
 
})

public class ExercicioEntity {

	@Id
	@GeneratedValue
	@Column(name="cod_exercicio")
	private int cod_exercicio;
 
	@Column(name="exercicio")
	private int exercicio;
 
	@Column(name="ativo")
	private String ativo;
	
	@Column(name="dataAtivacao")
	private Date dataAtivacao;
	
	@Column(name="competencia")
	private int competencia;

	public int getCod_exercicio() {
		return cod_exercicio;
	}

	public void setCod_exercicio(int cod_exercicio) {
		this.cod_exercicio = cod_exercicio;
	}

	public int getExercicio() {
		return exercicio;
	}

	public void setExercicio(int exercicio) {
		this.exercicio = exercicio;
	}

	public String getAtivo() {
		return ativo;
	}

	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}

	public Date getDataAtivacao() {
		return dataAtivacao;
	}

	public void setDataAtivacao(Date dataAtivacao) {
		this.dataAtivacao = dataAtivacao;
	}

	public int getCompetencia() {
		return competencia;
	}

	public void setCompetencia(int competencia) {
		this.competencia = competencia;
	}
 
	
 
}
