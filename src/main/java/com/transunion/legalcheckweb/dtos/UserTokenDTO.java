package com.transunion.legalcheckweb.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserTokenDTO {

	@JsonProperty("email")
	private String email;
	@JsonProperty("codigo_producto")
	private String codigoProducto;
	@JsonProperty("codigo_cliente")
	private String codigoCliente;
	@JsonProperty("codigo_usuario")
	private String codigoUsuario;

	@JsonProperty("email")
	public String getEmail() {
		return email;
	}

	@JsonProperty("email")
	public void setEmail(String email) {
		this.email = email;
	}

	@JsonProperty("codigo_producto")
	public String getCodigoProducto() {
		return codigoProducto;
	}

	@JsonProperty("codigo_producto")
	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	@JsonProperty("codigo_cliente")
	public String getCodigoCliente() {
		return codigoCliente;
	}

	@JsonProperty("codigo_cliente")
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	@JsonProperty("codigo_usuario")
	public String getCodigoUsuario() {
		return codigoUsuario;
	}

	@JsonProperty("codigo_usuario")
	public void setCodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}


}