package com.transunion.legalcheckweb.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;




public class ValorParametroPK implements Serializable {
	
	private static final long serialVersionUID = 3L;

	@Basic(optional = true)
	@Column(name = "CODIGO_PARAMETRO")
	private String codigoParametro;
	
	@Basic(optional = true)
	@Column(name = "CODIGO_VALOR")
	private String codigoValor;


	public ValorParametroPK() {

	}

	public ValorParametroPK(String codigoValor, String codigoParametro) {
		this.codigoValor = codigoValor;
		this.codigoParametro = codigoParametro;
	}

	public String getCodigoValor() {
		return codigoValor;
	}

	public void setCodigoValor(String codigoValor) {
		this.codigoValor = codigoValor;
	}

	public String getCodigoParametro() {
		return codigoParametro;
	}

	public void setCodigoParametro(String codigoParametro) {
		this.codigoParametro = codigoParametro;
	}

}
