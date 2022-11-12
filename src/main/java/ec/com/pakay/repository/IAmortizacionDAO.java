package ec.com.pakay.repository;

import ec.com.pakay.domain.Amortizacion;
import ec.com.pakay.domain.Solicitud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAmortizacionDAO extends JpaRepository<Amortizacion, Integer> {
	
	List<Amortizacion> findBySolicitud(Solicitud solicitud);
	
}
