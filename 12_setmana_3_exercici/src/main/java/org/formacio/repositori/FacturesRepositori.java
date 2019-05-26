package org.formacio.repositori;


import java.util.List;

import org.formacio.domain.Factura;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;



public interface FacturesRepositori extends CrudRepository<Factura, Long> {
	
	@Query("select sum(linia.total) from Factura factura join factura.linies linia where factura.client.nom = :client")
	public Number totalClient(@Param("client")String client); 	
	
	public List<Factura> findByClientNom(String nom);
}