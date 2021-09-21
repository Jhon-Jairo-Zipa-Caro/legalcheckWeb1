package com.transunion.legalcheckweb.controller;



import java.util.HashMap;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.transunion.ldap.AutenticacionLDAP;
import com.transunion.legalcheckweb.dtos.UserTokenDTO;
import com.transunion.legalcheckweb.dtos.UserTokenResponseDTO;
import com.transunion.legalcheckweb.model.ValorParametro;
import com.transunion.legalcheckweb.repo.IValorParametroRepo;
import com.transunion.limitecredito.core.dto.Empleado;

import co.com.transunion.logger.UtilLogger;
import co.com.transunion.util.Constantes;
import com.transunion.legalcheckweb.util.ConstantesLCW;


@Controller
public class ValorController {
	
	private static UtilLogger utilLogger = UtilLogger.getLogger(ValorController.class);
	private static final String AUTH_HEADER = "Authentication-Token";
	private ConstantesLCW constantesLCW;
	
	@Autowired
	private IValorParametroRepo repo;
	
	private UserTokenDTO token = new UserTokenDTO();
	private Empleado dataEmpleado;
	private String claveEmpleado;
	private String codigoInformacion;     // codigo de producto laft, full ....
    private String numeroIdentificacion;  // identificacion de persona o entidad
    private String tipoIdentificacion;    // 1 = cc consulta en BD
    private String userId;              // id_cifin Tabla empleados 
    private String password;              // password tabla empleados
    private String correoUsuario;         //correoUsuario
	
	@GetMapping("")
	@CrossOrigin(origins = "*", methods = {RequestMethod.GET,RequestMethod.POST, RequestMethod.HEAD})
	public String lista(Model model) {
		
		Long horaInicio = System.currentTimeMillis();
		UtilLogger.generarTransactionId();
		
		HashMap<String, Object> parametrosMap = new HashMap<String, Object>();
		HashMap<String, Object> respuestaSalida = new HashMap<String, Object>();
		parametrosMap.put("LegalcheckWeb Controller", null);
		
		
		utilLogger.escribirLogger(
			    null,
                null,
                null,
                this.getClass().getName(),
                null,
                null,
                null,
                "legalCheckWeb",
                null,
                parametrosMap,
                null,
                null,
                null,
                null,
                Constantes.LOGGER_INFO,
                "INICIO PROCESO - LegalcheckWeb consulta URL",
                null,
                null
			 );
		
		try {
			ValorParametro valorUrl = repo.findByCodigoParametroAndCodigoValor(constantesLCW.CODIGO_PARAMETRO_TUS_DATOS, constantesLCW.CODIGO_VALOR_TUS_DATOS);
			System.out.println(valorUrl.getNombreValor());
			model.addAttribute("url", repo.findByCodigoParametroAndCodigoValor(constantesLCW.CODIGO_PARAMETRO_TUS_DATOS, constantesLCW.CODIGO_VALOR_TUS_DATOS));
		} catch (Exception e) {
			utilLogger.escribirLoggerEstandar(
					"Error: en conexión a base de datos " + e.getMessage() != null ? e.getMessage() : null,
					Constantes.LOGGER_ERROR,
					"consulta url Legalcheck",
					this.getClass()
				 );
		}
		
		
		Long horaFinal = System.currentTimeMillis();
		Long tiempoTranscurrido = horaFinal - horaInicio;
		utilLogger.escribirLogger(
								    null,
					                null,
					                null,
					                this.getClass().getName(),
					                null,
					                null,
					                null,
					                "consultarUrlLegalcheck",
					                null,
					                null,
					                null,
					                respuestaSalida,
					                tiempoTranscurrido.toString().concat(Constantes.MILISEGUNDOS),
					                null,
					                Constantes.LOGGER_INFO,
					                "FIN PROCESO - legalcheckWeb consulta URL",
					                null,
					                null
								 );
		return "index";
	}
	
	public void obtenerClaveUsuario() {
		System.out.println("Consultando usuario...");
		//HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
		//		.getRequest();
		//String claveEmpleado = (String) request.getSession(false)
		//		.getAttribute(CodigosLimiteCredito.CLAVE.getDescription());
		//String claveEmpleado = (String) FacesUtil.getSessionMapValue("clave");
		
		//System.out.println(claveEmpleado);
		//claveEmpleado = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("clave");
		System.out.println("claveEmpleado");
		System.out.println(claveEmpleado);
	}
	
	public void obtenerCorreoUsuario() {
		AutenticacionLDAP auth = new AutenticacionLDAP();
		correoUsuario = auth.obtenerCorreoUsuario("90681");
		//parametros.setEmail(correoUsuario);
		System.out.println("Correo usuario : " + correoUsuario);
	}
	
	public void setToken() {
		token.setEmail(this.correoUsuario);
		token.setCodigoProducto("5764"); //Por confirmar
		token.setCodigoCliente("111"); //Por Confirmar
		token.setCodigoUsuario("90681"); //Por Confirmar
	}
	
	private UserTokenResponseDTO getTokenUsuario(String url, String tokenTD, UserTokenDTO token) {

		Client client = ClientBuilder.newClient();
		WebTarget webtarget = client.target(url + "");
		Invocation.Builder invocationBuilder = webtarget.request(MediaType.APPLICATION_JSON);
		invocationBuilder.header(AUTH_HEADER, tokenTD);
		Response response = invocationBuilder.post(Entity.entity(token, MediaType.APPLICATION_JSON));
		return response.readEntity(UserTokenResponseDTO.class);
	}

}
