package ec.com.pakay.repository;

import ec.com.pakay.domain.Solicitud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface ISolicitudDAO extends JpaRepository<Solicitud, Integer> {
	
	@Query(value = "CALL ConsultaPrestamo(:idSocio, :fechaDesde, :fechaHasta)", nativeQuery = true)
	List<Object[]> filtrar(@Param("idSocio") Integer idSocio,
			@Param("fechaDesde") Date fechaDesde, @Param("fechaHasta") Date fechaHasta);
	
}
