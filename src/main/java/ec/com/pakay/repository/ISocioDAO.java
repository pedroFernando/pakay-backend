package ec.com.pakay.repository;

import ec.com.pakay.domain.Socio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISocioDAO extends JpaRepository<Socio, Integer> {
	
	List<Socio> findByTipo(String tipo);
	Boolean existsByCedula(String cedula);
	@Query("select count(1) from Socio s where s.cedula = :cedula and s.id != :id")
	int existsByCedulaAndNotId(@Param("cedula") String cedula, @Param("id") Integer id);
	
}
