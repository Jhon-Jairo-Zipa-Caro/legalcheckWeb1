package com.transunion.legalcheckweb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Table(name = "VALORES_TABLA_PARAMETROS")
@Entity
@IdClass(ValorParametroPK.class)
public class ValorParametro {
	
	@Id
	@Column(name = "CODIGO_PARAMETRO")
	private String codigoParametro;
	
	@Id
	@Column(name = "CODIGO_VALOR")
	private String codigoValor;
	
	@Column(name = "NOMBRE_VALOR")
	private String nombreValor;
	
	

	public String getCodigoParametro() {
		return codigoParametro;
	}

	public void setCodigoParametro(String codigoParametro) {
		this.codigoParametro = codigoParametro;
	}

	public String getCodigoValor() {
		return codigoValor;
	}

	public void setCodigoValor(String codigoValor) {
		this.codigoValor = codigoValor;
	}

	public String getNombreValor() {
		return nombreValor;
	}

	public void setNombreValor(String nombreValor) {
		this.nombreValor = nombreValor;
	}

}
