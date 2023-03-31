package br.com.congasp.model;


import java.util.Date;

import javax.persistence.Column;


public class ExercicioModel  {
	 
	private int cod_exercicio;	
	private String exercicio;
	private String ativo;	
	private Date dataAtivacao;	
	private int competencia;

	public int getCod_exercicio() {
		return cod_exercicio;
	}

	public void setCod_exercicio(int cod_exercicio) {
		this.cod_exercicio = cod_exercicio;
	}

	public String getExercicio() {
		return exercicio;
	}

	public void setExercicio(String exercicio) {
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
