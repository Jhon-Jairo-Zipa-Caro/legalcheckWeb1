package com.transunion.legalcheckweb.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.transunion.legalcheckweb.model.ValorParametro;

public interface IValorParametroRepo extends JpaRepository<ValorParametro, String>{
	
	ValorParametro findByCodigoParametroAndCodigoValor(String codigoParametro, String codigoValor);

}
