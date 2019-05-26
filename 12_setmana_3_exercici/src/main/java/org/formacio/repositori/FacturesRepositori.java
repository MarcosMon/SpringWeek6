package org.formacio.repositori;


import java.util.List;

import org.formacio.domain.Factura;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;



public interface FacturesRepositori extends Repository<Factura, Long> {
	
	@Query("select count(*) from Client ")
	public Number totalClient(String client); 	
	
	public List<Factura> findByClientNom(String nom);
}