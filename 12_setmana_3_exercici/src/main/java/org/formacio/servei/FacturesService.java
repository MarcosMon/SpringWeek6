package org.formacio.servei;

import java.util.Optional;

import javax.transaction.Transactional;

import org.formacio.domain.Factura;
import org.formacio.domain.LiniaFactura;
import org.formacio.repositori.FacturesRepositori;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class FacturesService {

	@Autowired
	FacturesRepositori facturaRepo;
	
	@Autowired
	FidalitzacioService fidalitzacioServei;
	
	
	/*
	 * Aquest metode ha de carregar la factura amb id idFactura i afegir una nova linia amb les dades
	 * passades (producte i totalProducte)
	 * 
	 * S'ha de retornar la factura modificada
	 * 
	 * Per implementar aquest metode necessitareu una referencia (dependencia) a FacturesRepositori
	 */
	public Factura afegirProducte (long idFactura, String producte, int totalProducte) {
		
		Optional<Factura> factura = facturaRepo.findById(idFactura);
		
		if(factura.isPresent()) {
			
			LiniaFactura nuevalineafactura = new LiniaFactura();
			nuevalineafactura.setProducte(producte);
			nuevalineafactura.setTotal(totalProducte);
			factura.get().getLinies().add(nuevalineafactura);
			facturaRepo.save(factura.get());
			notificaPremi(factura.get());
			
		}
		return factura.get();
	
	}
	
	
	public void notificaPremi(Factura factura){
		final int NUM_LINIES_AMB_PREMI = 4;
		if(factura.getLinies().size() >= NUM_LINIES_AMB_PREMI){
			fidalitzacioServei.notificaRegal(factura.getClient().getEmail());
		}
	}
}
