package ec.com.pakay.repository;

import ec.com.pakay.domain.Caja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICajaDAO extends JpaRepository<Caja, Integer> {

	Boolean existsByNombre(String nombre);
	
	@Query("select count(1) from Caja c where c.nombre = :nombre and c.id != :id")
	int existsByNombreAndNotId(@Param("nombre") String nombre, @Param("id") Integer id);
	
}